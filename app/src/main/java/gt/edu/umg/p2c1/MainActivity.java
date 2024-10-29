package gt.edu.umg.p2c1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import gt.edu.umg.p2c1.BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button bntSaludo, btnCrearDb, btnGps, btnOpenCamera;
    TextView tvSaludo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //codigo nuevo
        bntSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDb = findViewById(R.id.btncrearDb);
        btnGps = findViewById(R.id.btnGps);
        btnOpenCamera = findViewById(R.id.btnOpenCamera); // Movido fuera del listener

        bntSaludo.setOnClickListener(v -> {
            Toast.makeText(this, "Aviso melkito", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola tulito");
        });

        btnOpenCamera.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });

        btnCrearDb.setOnClickListener(view -> {
            // Mostrar mensaje de éxito
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Base de datos creada");

            // Crear base de datos
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.getWritableDatabase();

            // Iniciar la nueva activity
            Intent intent = new Intent(this, NuevoActivity.class);
            startActivity(intent);
        });

        btnGps.setOnClickListener(view -> {
            Intent intent = new Intent(this, GpsActivity.class);
            startActivity(intent);
            Toast.makeText(this, "OBTENIENDO UBICACION...", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}