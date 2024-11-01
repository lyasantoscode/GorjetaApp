package com.lya.gorjeta;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.lya.gorjeta.databinding.ActivityMainBinding;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura o evento de clique para o botão Calcular
        binding.btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contaText = binding.editconta1.getText().toString();
                String porcentagemText = binding.editconta2.getText().toString();

                if (contaText.isEmpty()) {
                    binding.editconta1.setError("Informe o valor");
                } else if (porcentagemText.isEmpty()) {
                    binding.editconta2.setError("Informe a gorjeta");
                } else {
                    calcularGorjetaETotal();
                }
            }
        });
    }

    // Método para calcular a gorjeta e o total
    private void calcularGorjetaETotal() {
        // Converter os valores para float
        float valorConta = Float.parseFloat(binding.editconta1.getText().toString());
        float porcentagemGorjeta = Float.parseFloat(binding.editconta2.getText().toString());

        // Calcular a gorjeta e o total
        float valorGorjeta = valorConta * (porcentagemGorjeta / 100);
        float valorTotal = valorConta + valorGorjeta;

        // Formatar os valores para duas casas decimais e exibir com vírgula
        NumberFormat formatter = DecimalFormat.getInstance(new Locale("pt", "BR"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        binding.txtResultado.setText(
                "Gorjeta: R$ " + formatter.format(valorGorjeta) +
                        "\nTotal a Pagar: R$ " + formatter.format(valorTotal)
        );
    }
}
