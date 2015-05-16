package br.com.caelum.cadastro;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5044 on 16/05/15.
 */
public class FormularioHelper {

    private Aluno aluno;
    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;

    public FormularioHelper(FormularioActivity activity) {
        this.aluno = new Aluno();
        this.nome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.site = (EditText) activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
    }

    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;
    }

    public void mostraErro() {
        nome.setError("Campo nome nao pode ser vazio");
    }

    public boolean temNome() {
        return !nome.getText().toString().isEmpty();
    }
}
