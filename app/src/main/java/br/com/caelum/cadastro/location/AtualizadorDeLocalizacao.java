package br.com.caelum.cadastro.location;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.cadastro.fragment.MapaFragment;

/**
 * Created by android5044 on 06/06/15.
 */
public class AtualizadorDeLocalizacao implements LocationListener {

    private MapaFragment mapa;
    private GoogleApiClient client;

    public AtualizadorDeLocalizacao(Context context) {
        this.mapa = mapa;
        Configurador config = new Configurador(this);
        this.client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(config)
                .build();
        this.client.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng local = new LatLng(latitude, longitude);
        this.mapa.centralizaNo(local);
    }

    public void inicia(LocationRequest request) {
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    public void cancelar() {
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        this.client.disconnect();
    }
}
