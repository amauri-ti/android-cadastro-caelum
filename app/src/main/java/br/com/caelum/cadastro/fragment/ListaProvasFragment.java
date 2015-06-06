package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by android5044 on 06/06/15.
 */
public class ListaProvasFragment extends android.support.v4.app.Fragment {

    private ListView listViewProvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);
        this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova prova1=new Prova("20/06/3025", "Matematica");
        prova1.setTopicos(Arrays.asList("Algebra linear", "Calculo", "Estatistica"));
        Prova prova2=new Prova("20/07/3025", "Portugues");
        prova2.setTopicos(Arrays.asList("Complemento nominal", "Oracao subordinadas", "Analise sintatica"));

        List<Prova> provas= Arrays.asList(prova1, prova2);

        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada = (Prova)  parent.getItemAtPosition(position);
                ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
                calendarioProvas.selecionarProva(selecionada);
                //Toast.makeText(getActivity(), "Prova selecionada: " + selecionada, Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;
    }
}
