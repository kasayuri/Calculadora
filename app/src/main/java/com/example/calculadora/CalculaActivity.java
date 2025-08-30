package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculaActivity extends AppCompatActivity implements View.OnClickListener {

    private String operacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcula);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btVoltar = findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(this);

        Button btCalcular = findViewById(R.id.btCalcular);
        btCalcular.setOnClickListener(this);

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        String titulo = getIntent().getStringExtra("operacao");
        operacao = titulo;
        tvTitulo.setText(titulo.concat(" Números"));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btVoltar) {
            Intent telaHome = new Intent(CalculaActivity.this, MainActivity.class);
            startActivity(telaHome);
            finish();
        } else if (view.getId() == R.id.btCalcular) {
            calcular();
        }
    }

    private void calcular() {
        EditText etNumero1 = findViewById(R.id.etNumero1);
        EditText etNumero2 = findViewById(R.id.etNumero2);

        int numero1 = Integer.parseInt(etNumero1.getText().toString());
        int numero2 = Integer.parseInt(etNumero2.getText().toString());

        int resultado = 0;

        switch (operacao) {
            case "Somar":
                resultado = numero1 + numero2;
                break;
            case "Subtrair":
                resultado = numero1 - numero2;
                break;
            case "Multiplicar":
                resultado = numero1 * numero2;
                break;
            case "Dividir":
                resultado = numero1 / numero2;
                break;
        }

        Toast.makeText(CalculaActivity.this,
                        "Resultado: ".concat(String.valueOf(resultado)),
                        Toast.LENGTH_LONG)
                .show();
    }
}