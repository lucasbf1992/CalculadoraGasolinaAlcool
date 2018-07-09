package com.example.lucas.alcoolougasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.alcoolougasolina.model.Posto;

public class MainActivity extends AppCompatActivity {

    private EditText gasolina, alcool;
    private Posto posto = new Posto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasolina = findViewById(R.id.editTextValorGasolina);
        alcool = findViewById(R.id.editTextValorAlcool);

        gasolina.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                   gasolina.setText("");
                }

                return false;
            }
        });

        alcool.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    alcool.setText("");
                }

                return false;
            }
        });
    }

    public void botaoCalcular (View v) {


        if ((gasolina.getText().toString().equals("") | (alcool.getText().toString().equals(""))) ){
            Toast.makeText(this,"Valor gasolina ou álcool não pode ser vazio! :(",Toast.LENGTH_SHORT).show();
        }else{

            posto.setValorAlcool(Double.parseDouble(alcool.getText().toString()));
            posto.setValorGasolina(Double.parseDouble(gasolina.getText().toString()));

            if(posto.calcularCombustivel()){
                Toast.makeText(this,"Abasteça com ETANOL! :)",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Abasteça com GASOLINA! :)",Toast.LENGTH_LONG).show();
            }
        }



    }

}
