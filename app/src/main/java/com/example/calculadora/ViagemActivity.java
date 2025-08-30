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

import java.util.Locale;

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

        String distanciaStr = etDistanciaViagem.getText().toString();
        String valorCombustivelStr = etValorCombustivelViagem.getText().toString();
        String mediaKmPorLStr = etMediaKmPorLViagem.getText().toString();

        // Validar campos
        if (distanciaStr.isEmpty() || valorCombustivelStr.isEmpty() || mediaKmPorLStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double distancia = Double.parseDouble(distanciaStr);
            double valorCombustivel = Double.parseDouble(valorCombustivelStr);
            double mediaKmPorL = Double.parseDouble(mediaKmPorLStr);

            if (mediaKmPorL == 0) {
                Toast.makeText(this, "A média de Km/L não pode ser zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            double custoViagem = (distancia / mediaKmPorL) * valorCombustivel;

            Locale brasilLocale = new Locale("pt", "BR");
            String resultado = String.format(brasilLocale, "%.2f", custoViagem);

            Toast.makeText(ViagemActivity.this,
                            "Custo da Viagem: R$ " + resultado,
                            Toast.LENGTH_LONG)
                    .show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, insira valores numéricos válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}
