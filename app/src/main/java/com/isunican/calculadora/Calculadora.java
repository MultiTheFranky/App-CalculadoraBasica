package com.isunican.calculadora;

public class Calculadora {

    double operador1, operador2;
    double resultado;

    public Calculadora(){
        this.operador1 = 0.0;
        this.operador2 = 0.0;
        this.resultado = 0.0;
    }

    public void setOperador1 (double op1){
        this.operador1=op1;
    }

    public void setOperador2 (double op2){
        this.operador2=op2;
    }

    public double getOperador1() {
        return operador1;
    }

    public double getOperador2() {
        return operador2;
    }

    public double suma(){
        resultado = operador1+operador2;
        return resultado;
    }

    public double resta(){
        resultado = operador1-operador2;
        return resultado;
    }

    public double divide(){
        resultado = operador1/operador2;
        return resultado;
    }

    public double multiplica(){
        resultado = operador1*operador2;
        return resultado;
    }

}
