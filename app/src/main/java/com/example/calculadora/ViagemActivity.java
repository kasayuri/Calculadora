package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViagemActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viagem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btVoltarViagem = findViewById(R.id.btVoltarViagem);
        btVoltarViagem.setOnClickListener(this);

        Button btCalcularViagem = findViewById(R.id.btCalcularViagem);
        btCalcularViagem.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btVoltarViagem) {
            Intent telaHome = new Intent(ViagemActivity.this, MainActivity.class);
            startActivity(telaHome);
            finish();
        } else if (view.getId() == R.id.btCalcularViagem) {
            calcularViagem();
        }
    }

    private void calcularViagem(){
        EditText etDistanciaViagem = findViewById(R.id.etDistanciaViagem);
        EditText etValorCombustivelViagem = findViewById(R.id.etValorCombustivelViagem);
        EditText etMediaKmPorLViagem = findViewById(R.id.etMediaKmPorLViagem);

        double distancia = Double.parseDouble(etDistanciaViagem.getText().toString());
        double valorCombustivel = Double.parseDouble(etValorCombustivelViagem.getText().toString());
        double mediaKmPorL = Double.parseDouble(etMediaKmPorLViagem.getText().toString());

        double custoViagem = (distancia / mediaKmPorL) * valorCombustivel;
        String resultado = String.format("%.2f", custoViagem);

        Toast.makeText(ViagemActivity.this,
                        "Resultado: R$ ".concat(String.valueOf(resultado)),
                        Toast.LENGTH_LONG)
                .show();
    }
}