package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.adapter.SpecialAdapter;
import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.fragment.MapaFragment;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;
import br.com.caelum.cadastro.task.EnviaContatosTask;


public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDao dao=new AlunoDao(this);

        this.alunos=dao.getLista();

        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setBackgroundColor(Color.RED);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao=new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                Aluno aluno= (Aluno) listaAlunos.getItemAtPosition(position);
                edicao.putExtra("aluno", aluno);
                startActivity(edicao);
            }
        });

        /*
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "A posicao e " + position, Toast.LENGTH_LONG).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno)parent.getItemAtPosition(position);
                Toast.makeText(ListaAlunosActivity.this, "Clique longo: " + aluno.getId(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        */

        Button botaoAdiciona = (Button) findViewById(R.id.lista_alunos_floating_button);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        AlunoDao dao=new AlunoDao(this);
        List<Aluno> alunos=dao.getLista();
        dao.close();
        final ListaAlunosAdapter adapter=new ListaAlunosAdapter(this, alunos);
        this.listaAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
                new EnviaContatosTask(this).execute();
                return true;
            case R.id.menu_receber_provas:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);
                return true;
            case R.id.menu_mapa:
                Intent mapa = new Intent(this, MostraAlunosActivity.class);
                startActivity(mapa);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno alunoSelecionado = (Aluno) listaAlunos.getAdapter().getItem(info.position);
        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar=new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:"+alunoSelecionado.getTelefone()));
        ligar.setIntent(intentLigar);
        MenuItem sms = menu.add("Enviar SMS");
        Intent intentSms=new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
        intentSms.putExtra("sms_body", "Mensagem");
        sms.setIntent(intentSms);
        MenuItem mapa = menu.add("Achar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q="+Uri.encode(alunoSelecionado.getEndereco())));
        mapa.setIntent(intentMapa);
        MenuItem site = menu.add("Navegar no Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String siteAluno = alunoSelecionado.getSite();
        if(!siteAluno.startsWith("http://")) {
            siteAluno = "http://"+siteAluno;
        }
        intentSite.setData(Uri.parse(siteAluno));
        site.setIntent(intentSite);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlunoDao dao=new AlunoDao(ListaAlunosActivity.this);
                                        dao.apagar(alunoSelecionado);
                                        dao.close();
                                        carregaLista();
                                    }
                                }).setNegativeButton("Nao", null).show();
                return false;
            }
        });

    }
}
