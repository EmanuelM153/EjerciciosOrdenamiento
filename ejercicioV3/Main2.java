package ejercicioV3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {
  public static void main(String[] args) {
    int maxDigitos;

    String[] numerosBase16 = {"1C8", "A89", "401", "16C", "1E2", "173"};
    ArrayList<String> numerosBase16Arr = new ArrayList<>(Arrays.asList(numerosBase16));

    maxDigitos = OrdenamientoUtils.encontrarMayorNumDigitoStr(numerosBase16Arr);
    RadixSort.ordenarHexadecimal(numerosBase16Arr, maxDigitos);

    System.out.println("Hexadecimal ordenado: " + numerosBase16Arr.toString());

    //
    //
    //
    Integer[] numerosBase8 = {62, 12, 24, 50};
    ArrayList<Integer> numerosBase8Arr = new ArrayList<>(Arrays.asList(numerosBase8));

    maxDigitos = OrdenamientoUtils.encontrarMayorNumDigito(numerosBase8Arr);
    RadixSort.ordenarOctal(numerosBase8Arr, maxDigitos);

    System.out.println("Octal ordenado: " + numerosBase8Arr.toString());

    //
    //
    //
    String[] cadenas = {"radix", "prueba", "la", "sort"};
    ArrayList<String> cadenasArr = new ArrayList<>(Arrays.asList(cadenas));

    maxDigitos = OrdenamientoUtils.encontrarMayorNumDigitoStr(cadenasArr);
    RadixSort.ordenarCadenas(cadenasArr, maxDigitos);

    System.out.println("Cadenas ordenadas: " + cadenasArr.toString());

    //
    //
    //
    String[] binarios = {"110010", "10100", "1010", "101000"};
    ArrayList<String> binariosArr = new ArrayList<>(Arrays.asList(binarios));

    maxDigitos = OrdenamientoUtils.encontrarMayorNumDigitoStr(binariosArr);
    RadixSort.ordenarBinarios(binariosArr, maxDigitos);

    System.out.println("Binarios ordenados: " + binariosArr.toString());

    //
    //
    //
    Integer[] numeros = {15, 2, 4, 123, 511, 123, 512};
    ArrayList<Integer> numerosArr = new ArrayList<>(Arrays.asList(numeros));

    maxDigitos = OrdenamientoUtils.encontrarMayorNumDigito(numerosArr);
    RadixSort.ordenarEnteros(numerosArr, maxDigitos);

    System.out.println("Números ordenados: " + numerosArr.toString());
  }
}
