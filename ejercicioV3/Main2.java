package ejercicioV3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {
  public static void main(String[] args) {
    String[] numerosBase16 = {"C", "6", "7B", "2D", "FF", "337", "3E8"};

    ArrayList<String> numerosBase10Arr = new ArrayList<String>(Arrays.asList(numerosBase16));

    RadixSort.ordenarHexadecimal(numerosBase10Arr, 3);

    System.out.println(numerosBase10Arr.toString());
  }
}
