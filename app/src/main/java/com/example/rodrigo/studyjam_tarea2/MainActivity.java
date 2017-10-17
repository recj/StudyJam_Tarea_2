package com.example.rodrigo.studyjam_tarea2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView vista;
    private String contenido="";
    private String operator= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    vista = (TextView)findViewById(R.id.tv1);
    vista.setText(contenido);

    }
    public void disableAll(){
        Button b03,b13,b23,b33;
        b03=(Button)findViewById(R.id.button03);
        b13=(Button)findViewById(R.id.button13);
        b23=(Button)findViewById(R.id.button23);
        b33=(Button)findViewById(R.id.button33);

        b03.setEnabled(false);
        b13.setEnabled(false);
        b23.setEnabled(false);
        b33.setEnabled(false);
    }
    public void enableAll(){
        Button b03,b13,b23,b33;
        b03=(Button)findViewById(R.id.button03);
        b13=(Button)findViewById(R.id.button13);
        b23=(Button)findViewById(R.id.button23);
        b33=(Button)findViewById(R.id.button33);

        b03.setEnabled(true);
        b13.setEnabled(true);
        b23.setEnabled(true);
        b33.setEnabled(true);
    }
    public void modificarTextView(){
        vista.setText(contenido);
    }
    public void onClickNumber(View v){
        Button b= (Button) v;
        contenido += b.getText();
        modificarTextView();
    }
    public void onClickOperator(View v){
        Button b=(Button) v;
        contenido+= b.getText();
        operator = b.getText().toString();
        modificarTextView();
        disableAll();

    }
    public void ac(){
        contenido="";
        operator="";


    }
    public double operacion(String a,String b,String s){
        switch (s){
            case "+":   return Double.valueOf(a)+ Double.valueOf(b);
            case "-":   if (Double.valueOf(a)> Double.valueOf(b)){
                            return Double.valueOf(a)- Double.valueOf(b);
                        }else {
                            return Double.valueOf(b)- Double.valueOf(a);
                        }
            case "×":   return Double.valueOf(a)* Double.valueOf(b);
            case "÷":  try{ return Double.valueOf(a)/ Double.valueOf(b);}
            catch (Exception e ){
                Log.d("Calc",e.getMessage() );

            }
                default: return -1;

        }
    }
    public void onClickAc(View v){
        ac();
        modificarTextView();
        enableAll();
    }
    public void onClickEqual(View v){
        try{String [] operaciones = contenido.split(Pattern.quote(operator));
        if (operaciones.length< 2) return ;
        Double resultado = operacion(operaciones[0],operaciones[1],operator);
        vista.setText( String.valueOf(resultado));
        contenido = "";
        }catch (Exception e){
            Toast.makeText(this, "Operación invalida", Toast.LENGTH_SHORT).show();

        }
        enableAll();
    }
}
