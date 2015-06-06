package br.com.caelum.cadastro.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5044 on 06/06/15.
 */
public class Localizador {

    private Geocoder geocoder;

    public Localizador(Context context) {
        geocoder = new Geocoder(context);
    }

    public LatLng getCoordenada(String endereco) {
        try {
            List<Address> resultados;
            resultados = geocoder.getFromLocationName(endereco, 1);
            if(!resultados.isEmpty()) {
                Address resultado = resultados.get(0);
                return new LatLng(resultado.getLatitude(), resultado.getLongitude());
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
