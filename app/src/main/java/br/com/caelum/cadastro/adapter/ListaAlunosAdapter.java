package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5044 on 23/05/15.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        Aluno aluno = alunos.get(position);

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        nome.setText(Html.fromHtml("<b>" + aluno.getNome() + "</b>"));

        Bitmap bm;

        if(aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }

        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);

        ImageView foto = (ImageView) view.findViewById(R.id.item_foto);

        if(aluno.getCaminhoFoto() != null) {
            foto.setImageBitmap(bm);
        }

        view.setBackgroundColor(position % 2 ==0 ?
                activity.getResources().getColor(R.color.linha_par) :
                activity.getResources().getColor(R.color.linha_impar));

        return view;
    }
}
