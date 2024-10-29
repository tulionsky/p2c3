package gt.edu.umg.p2c1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

    public class GpsActivity extends AppCompatActivity { // Cambiado a GpsActivity

        private FusedLocationProviderClient fusedLocationProviderClient;
        private TextView locationTv;
        private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.gps_activity);// Asegúrate de que el nombre del archivo sea correcto

            Button btnRegresar2 = findViewById(R.id.btnRegresar2);

            btnRegresar2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent = new Intent(GpsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            locationTv = findViewById(R.id.locationTv);
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            getCurrentLocation();
        }

        private void getCurrentLocation() {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_LOCATION_PERMISSION
                );
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    locationTv.setText(
                            "Tu Latitud es de: " + location.getLatitude() + "\n" +
                                    "Tu Longitud es de: " + location.getLongitude()
                    );
                } else {
                    locationTv.setText("Ubicación no disponible");
                }
            });
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    locationTv.setText("Permiso de ubicación denegado");
                }
            }
        }
    }
