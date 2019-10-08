package com.isunican.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraReal extends AppCompatActivity implements View.OnClickListener {

    String text1 = "";
    String text2 = "";
    String operation = "";
    String result = "";
    boolean escribiendoEnMain = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_real);
    }

    public void writeResult(){
        TextView resultTxt = findViewById(R.id.uiResult);
        resultTxt.setText(text1+"\n"+operation+"\n"+text2+"\n"+result);
    }

    public void calculateResult(){
        double res1 = Double.parseDouble(text1);
        double res2 = Double.parseDouble(text2);
        switch (operation){
            case "+":
                result = String.valueOf(res1 + res2);
                break;
            case "-":
                result = String.valueOf(res1 - res2);
                break;
            case "*":
                result = String.valueOf(res1 * res2);
                break;
            case "/":
                result = String.valueOf(res1 / res2);
                break;
        }
        writeResult();
    }

    public void writeNumber(String text){
        if (escribiendoEnMain){
            if(!(text1.equals("0")) || !text.equals("0") || (!text1.contains(".") && text.equals("."))){
                text1+=text;
            }
        }else{
            if(!text2.equals("0") || !text.equals("0")){
                text2+=text;
            }
        }
        if (text1.startsWith("0")){
            text1 = text1.substring(1);
        }
        if (text2.startsWith("0")){
            text2 = text2.substring(1);
        }
        if(!escribiendoEnMain){
            calculateResult();
        }
        writeResult();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Maneja los clicks a los items del action bar
        // Los clicks a los botones inicio y atras se manejan automaticamente
        // segun la actividad que hayamos definido como padre en el AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = null;

        switch(id){
            case R.id.navigation_simple:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_complex:
                intent = new Intent(this,CalculadoraReal.class);
                startActivity(intent);
                break;
            case R.id.navigation_acercade:
                intent = new Intent(this, AcercaDe.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //===================================
    // Menu de la aplicacion (action bar)
    // ===================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Carga los items al action bar
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    public void calculateResultDirect(){
        if(!escribiendoEnMain) {
            text1 = result;
            text2 = "";
            result = "";
            operation = "";
            escribiendoEnMain = true;
        }
        writeResult();
    }

    public void writeOperation(String operator){
        if(!escribiendoEnMain){
            text1 = result;
            text2 = "";
            result = "";
        }
        operation = operator;
        escribiendoEnMain = false;
        writeResult();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zeroBtn:
                writeNumber("0");
                break;
            case R.id.oneBtn:
                Log.d("Test","TEST");
                writeNumber("1");
                break;
            case R.id.twoBtn:
                writeNumber("2");
                break;
            case R.id.threeBtn:
                writeNumber("3");
                break;
            case R.id.fourBtn:
                writeNumber("4");
                break;
            case R.id.fiveBtn:
                writeNumber("5");
                break;
            case R.id.sixBtn:
                writeNumber("6");
                break;
            case R.id.sevenBtn:
                writeNumber("7");
                break;
            case R.id.eightBtn:
                writeNumber("8");
                break;
            case R.id.nineBtn:
                writeNumber("9");
                break;
            case R.id.divBtn:
                writeOperation("/");
                break;
            case R.id.mulBtn:
                writeOperation("*");
                break;
            case R.id.resBtn:
                writeOperation("-");
                break;
            case R.id.sumBtn:
                writeOperation("+");
                break;
            case R.id.equalBtn:
                calculateResultDirect();
                break;
            case R.id.clrBtn:
                text1 = "0";
                operation = "";
                text2 = "";
                escribiendoEnMain = true;
                writeResult();
                break;
            case R.id.pointBtn:
                writeNumber(".");
                break;
        }
    }
}
