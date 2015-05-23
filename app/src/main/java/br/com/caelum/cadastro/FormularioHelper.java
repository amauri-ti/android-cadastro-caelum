package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;
    private Button fotoButton;

    public FormularioHelper(FormularioActivity activity) {
        this.aluno = new Aluno();
        this.nome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.site = (EditText) activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.formulario_foto_button);
    }

    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String)foto.getTag());
        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        telefone.setText(aluno.getTelefone());
        nota.setProgress(aluno.getNota().intValue());
        foto.setImageBitmap(BitmapFactory.decodeFile(aluno.getCaminhoFoto()));
        this.aluno = aluno;
    }

    public void mostraErro() {
        nome.setError("Campo nome nao pode ser vazio");
    }

    public boolean temNome() {
        return !nome.getText().toString().isEmpty();
    }

    public Button getFotoButton() {
        return fotoButton;
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida =  Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
