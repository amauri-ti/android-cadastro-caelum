package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by android5044 on 06/06/15.
 */
public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(isTablet()) {
            transaction.replace(R.id.provas_lista, new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }

        transaction.commit();
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionarProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();

        if(isTablet()) {
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment)
                    manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaCamposComDados(prova);
        } else {
            Bundle arguments = new Bundle();
            arguments.putSerializable("prova", prova);
            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(arguments);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhesProva);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
