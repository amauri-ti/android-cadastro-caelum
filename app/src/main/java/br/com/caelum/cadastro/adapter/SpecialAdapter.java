package br.com.caelum.cadastro.adapter;

/**
 * Created by android5044 on 23/05/15.
 */
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import br.com.caelum.cadastro.modelo.Aluno;

public class SpecialAdapter<T> extends ArrayAdapter<Aluno> {

    private int[] colors = new int[] {Color.rgb(255, 207, 60), Color.rgb(255, 168, 20) };

    public SpecialAdapter(Context context, int resource, List<Aluno> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }
}