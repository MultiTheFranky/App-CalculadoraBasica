package com.isunican.calculadora;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Calculadora calculadora;

    Button btnCalcula;
    Spinner spnOperacion;
    TextView txtOperador1;
    TextView txtOperador2;
    TextView txtResultado;


    //===================================
    // Método principal de inicialización
    //===================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculadora_simple);

        // Inicializamos el objeto calculadora
        calculadora = new Calculadora();

        // Vistas
        spnOperacion = findViewById(R.id.spnOperacion);
        txtOperador1 = findViewById(R.id.txtOperador1);
        txtOperador2 = findViewById(R.id.txtOperador2);
        txtResultado = findViewById(R.id.txtResultado);

        // Cargar spinner
        cargarSpineer();

        // Listener del boton
        btnCalcula = findViewById(R.id.btnCalcula);
        btnCalcula.setOnClickListener(this);
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
    // Carga las operaciones en el spiner
    //===================================
    private void cargarSpineer(){
        // Crea un ArrayAdapter usando un array de strings con las operaciones (definido en string.xml)
        // y un recurso spinner layout generico de android
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operacionesArray, android.R.layout.simple_spinner_item);
        // Indica el layout a usar para las opciones, uno generico de android
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplica el adaptador creado a nuestro spinner
        spnOperacion.setAdapter(adapter);
    }


    //===================================
    // Listener del boton
    //===================================
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btnCalcula){
            Log.v("Test", "Boton pulsado");
            Toast.makeText(this, android.R.string.ok, Toast.LENGTH_SHORT).show();
            txtResultado.setText(calcula());

        }
    }


    //===================================
    // Metodo que toma los valores de los operadores y realiza el calculo
    //===================================
    private String calcula(){

        double op1, op2;
        String value1, value2, res;

        // Obtenemos el valor de los operadores
        value1 = txtOperador1.getText().toString();
        value2 = txtOperador2.getText().toString();

        // Comprobamos que se ha introducido algun valor numerico
        if(value1.matches("") || value2.matches("")){

            // Mostramos un mensaje
            Log.d("Prueba",spnOperacion.getSelectedItem().toString());
            Toast.makeText(this, R.string.error_sinvalores, Toast.LENGTH_SHORT).show();
            res = getResources().getString(R.string.error_sinvalores);
            return res;
        }

        else{

            //Obtenemos los valores numericos de los operaderos
            calculadora.setOperador1(Double.parseDouble(txtOperador1.getText().toString()));
            calculadora.setOperador2(Double.parseDouble(txtOperador2.getText().toString()));

            //Sacamos el tipo de operacion seleccionada por el usuario
            String op = spnOperacion.getSelectedItem().toString();

            switch(op){
                case "+":
                    res= ""+calculadora.suma();
                    break;
                case "-":
                    res= ""+calculadora.resta();
                    break;
                case "/":
                    res = ""+calculadora.divide();
                    break;
                case "*":
                    res = ""+calculadora.multiplica();
                    break;
                default:
                    res= "";
                    break;
            }
            return res;
        }

    }


}
