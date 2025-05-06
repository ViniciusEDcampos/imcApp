package com.example.imcapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText peso,altura;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //recuperação dos id's dos componentes do formulario

        peso = findViewById(R.id.pesoImc);
        altura = findViewById(R.id.alturaImc);
        resultado = findViewById(R.id.resultadoImc);

        //usando um metodo para 'escutar'  eventos de click dos botao
        // recuperando o 'id' do botão usando o objeto tipo BUTTON

        Button calcularImc = findViewById(R.id.buttonImc);

        // inclusão do o evento click no botão, com o metodo de Soma
        calcularImc.setOnClickListener(new View.OnClickListener() {
            @Override

            //este metodo permite 'escutar' (receber) o evento acionado pelo botão
            public void onClick(View SomaImc) {

                String valorPeso = peso.getText().toString();
                String valorAltura = altura.getText().toString();

                //validacao do preenchimento dos dados
                //validacao do preenchimento dos dados
                if(valorPeso.isEmpty() || valorAltura.isEmpty()){
                    // SNACKBAR - mensagem na parte inferior do APP
                    Snackbar.make(SomaImc, "Preencha os Numeros JUMENTO : ", Snackbar.LENGTH_SHORT).show();
                    return; // Interrompe a execução se algum campo estiver vazio
                }

                // Converter os valores para números (double para permitir casas decimais)
                double numeroPeso = Double.parseDouble(valorPeso);
                double numeroAltura = Double.parseDouble(valorAltura) ; // converter altura de cm para metros


                double IMC = numeroPeso / (numeroAltura * numeroAltura);

                String result;

                if(IMC == 18.5){
                     result = "Magreza";
                }else if(IMC > 18.5 && IMC <24.9){
                     result = "Normal";
                }else if(IMC > 25 && IMC <29.9){
                     result = "Sobrepeso";
                }else if(IMC > 30 && IMC <34.9){
                     result = "Obesidade grau I";
                }else if(IMC > 35 && IMC <39.9){
                     result = "Obesidade grau II";
                }else {
                     result = "Obesidade grau III";
                }
               resultado.setText(String.format("O resultado é: %s\nIMC: %.2f", result, IMC));
            }
        });
    }
}