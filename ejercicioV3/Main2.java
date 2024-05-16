package ejercicioV3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {
  public static void main(String[] args) {
    String[] palabras = {"hola", "hielo", "a", "tronco", "murcielago", "dinosaurio", "mamut"};

    ArrayList<String> palabrasArr = new ArrayList<String>(Arrays.asList(palabras));

    RadixSort.ordenarTexto(palabrasArr, 10);

    System.out.println(palabrasArr.toString());
  }
}
