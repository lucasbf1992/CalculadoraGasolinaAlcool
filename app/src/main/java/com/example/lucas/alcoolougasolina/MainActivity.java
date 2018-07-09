package com.example.lucas.alcoolougasolina;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.alcoolougasolina.model.Posto;

public class MainActivity extends AppCompatActivity {

    private EditText gasolina, etanol;
    private TextView titulo, resultado;
    private FloatingActionButton btnCalcular;

    private Posto posto = new Posto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        gasolina = findViewById(R.id.editTextGasolina);
        etanol = findViewById(R.id.editTextEtanol);
        titulo = findViewById(R.id.textViewTitulo);
        resultado = findViewById(R.id.textViewResultado);
        btnCalcular = findViewById(R.id.btnCalcular);

        gasolina.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    gasolina.setText("");
                }

                return false;
            }
        });

        etanol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    etanol.setText("");
                }

                return false;
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((gasolina.getText().toString().equals("") | (etanol.getText().toString().equals("")))) {

                    titulo.setText("OPS... Algo errado!");
                    resultado.setText("Valor gasolina ou álcool não pode ser vazio! :(");
                    hideSoftKeyboard();

                } else {

                    posto.setValorAlcool(Double.parseDouble(etanol.getText().toString()));
                    posto.setValorGasolina(Double.parseDouble(gasolina.getText().toString()));
                    hideSoftKeyboard();



                    if (posto.calcularCombustivel()) {
                        resultadoAlcool();


                    } else {
                        resultadoGasolina();

                    }
                }


            }
        });


    }


    private void resultadoGasolina() {

        double valorMaximoEtanol = posto.getValorGasolina() * 0.7;
        String valor = String.valueOf(valorMaximoEtanol);

        titulo.setBackgroundResource(R.color.tituloResultadoGasolina);
        resultado.setBackgroundResource(R.color.resultadoGasolina);
        titulo.setText("GASOLINA");
        resultado.setText("Abasteça com gasolina!\n " +
                "Etanol somente será viável abaixo de R$" + valor);
    }

    private void resultadoAlcool() {

        double valorMaximoEtanol = posto.getValorGasolina() * 0.7;
        String valor = String.valueOf(valorMaximoEtanol);

        titulo.setBackgroundResource(R.color.tituloResultadoEtanol);
        resultado.setBackgroundResource(R.color.resultadoEtanol);
        titulo.setText("ETANOL");
        resultado.setText("Abasteça com etanol!\n" +
                "Etanol será viável enquanto estiver abaixo de R$ " + valor);
    }

    private void hideSoftKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(btnCalcular.getWindowToken(), 0);

    }

}
