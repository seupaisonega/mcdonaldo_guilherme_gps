package com.example.mcdonaldo_guilherme;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.File;

public class gps extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().setUserAgentValue("McDonaldoGuilhermeApp/1.0 (Android)");

        File basePath = new File(getCacheDir(), "osmdroid_tiles");
        File tileCache = new File(basePath, "tiles");
        Configuration.getInstance().setOsmdroidBasePath(basePath);
        Configuration.getInstance().setOsmdroidTileCache(tileCache);

        setContentView(R.layout.activity_gps);

        map = findViewById(R.id.map);
        if (map != null) {
            map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
            map.setMultiTouchControls(true);

            // Coordenada ajustada para o desenho de quadra do OpenStreetMap na Rua Chico Pontes
            double latitude = -23.504450;
            double longitude = -46.608900;

            GeoPoint pontoExatoMc = new GeoPoint(latitude, longitude);

            Marker startMarker = new Marker(map);
            startMarker.setPosition(pontoExatoMc);

            // Define o ponto de ancoragem exato da ponta inferior do pino
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle("McDonald's - Vila Guilherme");
            startMarker.setSnippet("Rua Chico Pontes, 1565");
            map.getOverlays().add(startMarker);

            map.post(new Runnable() {
                @Override
                public void run() {
                    IMapController mapController = map.getController();
                    mapController.setZoom(17.0);
                    mapController.setCenter(pontoExatoMc);
                }
            });

            map.invalidate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (map != null) {
            map.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (map != null) {
            map.onPause();
        }
    }
}