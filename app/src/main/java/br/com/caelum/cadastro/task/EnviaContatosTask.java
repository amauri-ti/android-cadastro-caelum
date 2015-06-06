package br.com.caelum.cadastro.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;

/**
 * Created by android5044 on 30/05/15.
 */
public class EnviaContatosTask extends AsyncTask<Object, Object, String> {

    private final Context context;
    private ProgressDialog progress;

    public EnviaContatosTask(Context context) {
        this.context = context;
    }

    //MyThread
    @Override
    protected String doInBackground(Object... params) {
        AlunoDao alunoDao=new AlunoDao(context);
        List<Aluno> alunos= alunoDao.getLista();
        alunoDao.close();

        String json = new AlunoConverter().toJson(alunos);
        WebClient client=new WebClient();
        String resposta = client.post(json);
        return resposta;
    }

    //UIThread
    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    //UIThread
    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);
        super.onPreExecute();
    }
}
