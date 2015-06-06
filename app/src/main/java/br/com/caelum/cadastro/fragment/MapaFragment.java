package br.com.caelum.cadastro.fragment;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.Localizador;

/**
 * Created by android5044 on 06/06/15.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());

        //LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");

        //this.centralizaNo(local);

        AlunoDao dao = new AlunoDao(getActivity());
        List<Aluno> alunos = dao.getLista();
        dao.close();

        for(Aluno aluno : alunos) {
            LatLng coordenada = localizador.getCoordenada(aluno.getEndereco());
            this.centralizaNo(coordenada);
            if(coordenada != null) {
                MarkerOptions marcador = new MarkerOptions()
                        .position(coordenada)
                        .title(aluno.getNome())
                        .snippet(aluno.getEndereco());
                getMap().addMarker(marcador);
            }
        }

        //Log.i("MAPA", "Coordenadas da Caelum: " + local);
    }

    public void centralizaNo(LatLng local) {
        GoogleMap mapa=this.getMap();
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 14));
    }
}
