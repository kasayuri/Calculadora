package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btSomar = findViewById(R.id.btSomar);
        btSomar.setOnClickListener(this);

        Button btSubtrair = findViewById(R.id.btSubtrair);
        btSubtrair.setOnClickListener(this);

        Button btMultiplicar = findViewById(R.id.btMultiplicar);
        btMultiplicar.setOnClickListener(this);

        Button btDividir = findViewById(R.id.btDividir);
        btDividir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent telaCalcula = new Intent(HomeActivity.this, CalculaActivity.class);

        if (view.getId() == R.id.btSomar) {
            telaCalcula.putExtra("operacao","Somar");
        } else if (view.getId() == R.id.btSubtrair) {
            telaCalcula.putExtra("operacao","Subtrair");
        } else if (view.getId() == R.id.btMultiplicar) {
            telaCalcula.putExtra("operacao","Multiplicar");
        } else if (view.getId() == R.id.btDividir) {
            telaCalcula.putExtra("operacao","Dividir");
        }

        startActivity(telaCalcula);
    }
}