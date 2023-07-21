package com.hexa.entrenamiento.dominio.hexa.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Controlador para resolver los ejercicios propuestos.
 */
public class ProblemasController {

    /**
     * Método que recibe dos palabras y determina si son o no anagramas.
     * @param palabra1 Palabra 1
     * @param palabra2 Palabra 2
     * @return Mensaje indicando si son o no anagramas.
     */
    public static String palabrasAnagramas(String palabra1, String palabra2){
        //TODO Convierto las palabras en arreglos
        String[] palabra1Partes = palabra1.split("");
        String[] palabra2Partes = palabra2.split("");
        //TODO se busca coincidencia de cada letra y cuando si la encuentra reeemplazo con ""
        //TODO 1. si el tamaño difiere en las palabras no serían anagramas
        if(palabra1Partes.length != palabra2Partes.length){
            return "Las palabras: " + palabra1 + " y " + palabra2 + " NO son anagramas";
        }else{
            for(int i = 0 ; i < palabra1Partes.length; i++){
                for(int j = 0 ; j < palabra2Partes.length; j++){
                    if(palabra1Partes[i].equals(palabra2Partes[j])){
                        palabra1Partes[i] = "";
                        palabra2Partes[j] = "";
                        break;
                    }
                }
            }
            //Convierto de array a String para saber si quedó vacía "".
            String palabraReformada = String.join("", palabra1Partes);
            if(palabraReformada.equals("")){
                return "Las palabras: " + palabra1 + " y " + palabra2 + " son anagramas";
            }
        }
        return "Las palabras: " + palabra1 + " y " + palabra2 + " NO son anagramas";
    }

    /**
     * Método que recibe un número y determina si es par o impar.
     * @param numero Número a evaluar
     * @return Mensaje indicando si es par o impar.
     */
    public static String numeroParImpar(Integer numero){
        //TODO verifico que le número sea de 4 digitos o menos
        if(numero > 9999 || numero < 0) return "El número: " + numero + " es mayor a 4 digitos o es negativo";
        //TODO verifico si es par
        if(numero % 2 == 0) return "Par";
        return "Impar";
    }

    /**
     * Método para determinar cual es la moneda más pesada de 9 monedas
     * Se hace un algoritmo que simula el proceso que se haría en la vida real.
     * @return Mensaje indicando cual es la moneda más pesada.
     */
    public static String monedaMasPesada(){
        List<String> listMonedas = Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9");
        //TODO definimos moneda más pesada aleatoriamente
        String monedaMasPesada = listMonedas.get((int) (Math.random() * listMonedas.size()));
        //TODO se divide en 3 grupos de 3 monedas
        List<String> grupo1 = listMonedas.subList(0, 3);
        List<String> grupo2 = listMonedas.subList(3, 6);
        List<String> grupo3 = listMonedas.subList(6, 9);
        //TODO Se pesan los grupos en balanza de platos
        //TODO 1. Peso grupo 1 vs grupo 2, en caso de quedar balanceados, la moneda más pesada está en
        //TODO el grupo 3, en caso contrario, la moneda más pesada está en el grupo en la cual se
        //TODO inclina la balanza.
        //TODO 2. Por último se pesan las monedas del grupo en el cual se encuentra la moneda más pesada
        if(grupo1.stream().findFirst().equals(monedaMasPesada)) {
            return "La moneda más pesada es: " + monedaMasPesada + " grupo 1";
        }else if (grupo2.stream().findFirst().equals(monedaMasPesada)){
            return "La moneda más pesada es: " + monedaMasPesada + "grupo 2";
        }else {
            return "La moneda más pesada es: " + monedaMasPesada + "grupo 3";
        }
    }

    /**
     * Método para determinar si una cadena de texto es palíndroma.
     * @param palabra Palabra a evaluar
     * @return true si es palíndroma, false si no lo es.
     */
    public static Boolean cadenaPalindroma(String palabra){
        //TODO se verifica que sea mayor a 2 caracteres
        if (palabra.length() < 3) return false;
        //parabra en reversa
        String palabraReversa = new StringBuilder(palabra).reverse().toString();
        //TODO se verifica si es palindroma
        if(palabra.equals(palabraReversa)) return true;
        return false;
    }

    /**
     * Método que recibe una fecha y le suma 2 años y 3 meses y luego calcula la diferencia de años
     * entre la fecha actual y la fecha resultante.
     * @param fecha Fecha a evaluar
     * @return Mensaje indicando la diferencia de años entre la fecha actual y la fecha resultante.
     * @throws ParseException Excepción en caso de error en el formato de la fecha.
     */
    public static String manejoFechas(String fecha) throws ParseException {
        //TODO convertir fecha a formato dd/mm/yyyy
        String[] fechaPartes = fecha.split("/");
        String dia = fechaPartes[0];
        String mes = fechaPartes[1];
        String anio = fechaPartes[2];
        //TODO se verifica que el día sea menor a 31
        if(Integer.parseInt(dia) > 31) return "El día no puede ser mayor a 31";
        //TODO se verifica que el mes sea menor a 12
        if(Integer.parseInt(mes) > 12) return "El mes no puede ser mayor a 12";
        //TODO Convierto a formato LocalDate
        LocalDate fechaLocal = LocalDate.parse(anio + "-" + mes + "-" + dia);
        //TODO Sumo 2 años y 3 meses
        fechaLocal = fechaLocal.plusYears(2);
        fechaLocal = fechaLocal.plusMonths(3);
        //TODO verifico que no sea mayor a la fecha actual
        if(fechaLocal.isAfter(LocalDate.now())) return "La fecha no puede ser mayor a la fecha actual";
        //TODO calculo la diferencia de años con respecto a la fecha actual
        Period periodo = Period.between(fechaLocal, LocalDate.now());
        return "La diferencia de años es: " + periodo.getYears();
    }

}