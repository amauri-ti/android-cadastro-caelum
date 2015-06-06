package br.com.caelum.cadastro.location;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by android5044 on 06/06/15.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks {

    private AtualizadorDeLocalizacao atualizador;

    public Configurador(AtualizadorDeLocalizacao atualizador) {
        this.atualizador = atualizador;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50);

        atualizador.inicia(request);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
