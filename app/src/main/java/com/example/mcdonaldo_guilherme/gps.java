package com.example.mcdonaldo_guilherme;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Locale;

public class gps extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Força a leitura correta de pontos decimais independentemente do idioma do sistema
        Locale.setDefault(Locale.US);

        // Evita o erro "Access Blocked" do servidor do OpenStreetMap
        Configuration.getInstance().setUserAgentValue("McDonal_VilaGuilherme_App_v3");

        setContentView(R.layout.activity_gps);

        map = findViewById(R.id.map);
        if (map != null) {
            map.setTileSource(TileSourceFactory.MAPNIK);
            map.setMultiTouchControls(true);

            // Desativa a persistência de estado antigo do mapa na memória
            map.setDestroyMode(false);
            if (map.getTileProvider() != null) {
                map.getTileProvider().clearTileCache();
            }

            // Coordenada do ponto selecionado no círculo (R. Maria Cândida x Av. Guilherme Cotching)
            double latitude = -23.506820;
            double longitude = -46.598280;
            final GeoPoint pontoExatoMc = new GeoPoint(latitude, longitude);

            // Limpa todos os marcadores antigos
            map.getOverlays().clear();

            // Configuração do pino
            Marker startMarker = new Marker(map);
            startMarker.setPosition(pontoExatoMc);

            // Garante que a PONTA INFERIOR do ícone é o ponto exato no mapa
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle("McDonald's - Vila Guilherme");
            startMarker.setSnippet("Av. Guilherme Cotching x R. Maria Cândida");

            map.getOverlays().add(startMarker);

            // Centraliza o mapa diretamente no ponto assim que a visualização for desenhada
            map.post(new Runnable() {
                @Override
                public void run() {
                    IMapController mapController = map.getController();
                    mapController.setZoom(18.5);
                    mapController.setCenter(pontoExatoMc);
                    map.invalidate();
                }
            });
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