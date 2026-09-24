package com.example.mcdonaldo_guilherme;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import android.content.Intent;
import android.widget.Button;

import java.util.Locale;

public class gps extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Define o User-Agent antes de inflar o layout
        Locale.setDefault(Locale.US);
        Configuration.getInstance().setUserAgentValue("McDonal_VilaGuilherme_GPS_v6");

        setContentView(R.layout.activity_gps);

        map = findViewById(R.id.map);
        if (map != null) {
            map.setTileSource(TileSourceFactory.MAPNIK);
            map.setMultiTouchControls(true);

            // Coordenada exata do círculo (Rua Maria Cândida x Av. Guilherme Cotching)
            final GeoPoint pontoExatoMc = new GeoPoint(-23.50769579832505, -46.59918590299468);

            map.getOverlays().clear();

            Marker startMarker = new Marker(map);
            startMarker.setPosition(pontoExatoMc);
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle("McDonald's Vila Guilherme");
            map.getOverlays().add(startMarker);

            // O map.post garante que a largura e altura do mapa já foram calculadas antes de posicionar
            map.post(new Runnable() {
                @Override
                public void run() {
                    IMapController mapController = map.getController();
                    mapController.setZoom(18.5);
                    mapController.setCenter(pontoExatoMc);
                    map.invalidate();
                }
            });
            Button btnInicio = findViewById(R.id.button6);
            btnInicio.setOnClickListener(view -> {
                Intent intent = new Intent(gps.this,
                        MainActivity.class);

                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (map != null) map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (map != null) map.onPause();
    }
}