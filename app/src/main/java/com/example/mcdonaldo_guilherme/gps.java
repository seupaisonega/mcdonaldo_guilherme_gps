package com.exemple.mcdonaldo_guilherme; // Altere para o seu pacote correto

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.views.MapView;
import com.example.mcdonaldo_guilherme.R;

public class gps extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuração obrigatória do User-Agent para o OpenStreetMap
        Configuration.getInstance().setUserAgentValue(getPackageName());

        setContentView(R.layout.activity_gps);

        map = findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        // Define a posição inicial e o nível de zoom
        IMapController mapController = map.getController();
        mapController.setZoom(15.0);

        // Coordenadas de exemplo (São Paulo)
        GeoPoint startPoint = new GeoPoint(-23.550520, -46.633308);
        mapController.setCenter(startPoint);
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