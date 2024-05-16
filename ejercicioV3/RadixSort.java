package ejercicioV3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class RadixSort {
  public static void ordenarBinarios(ArrayList<String> datos, int maxDigitos) {
    Function<Integer, Function<String, Integer>> obtenerDigitoBinario =
        (numDigito) ->
            (str) -> {
              int digito = 0;
              if (str.length() > numDigito)
                digito = Character.getNumericValue(str.charAt(str.length() - numDigito - 1));

              return digito;
            };

    RadixSort.<String>radixSort(maxDigitos, 2).accept(datos, obtenerDigitoBinario);
  }

  public static void ordenarEnteros(ArrayList<Integer> datos, int maxDigitos) {
    RadixSort.<Integer>radixSort(maxDigitos, 10).accept(datos, OrdenamientoUtils.obtenerDigito);
  }

  public static void ordenarOctal(ArrayList<Integer> datos, int maxDigitos) {
    RadixSort.<Integer>radixSort(maxDigitos, 8).accept(datos, OrdenamientoUtils.obtenerDigito);
  }

  public static void ordenarCadenas(ArrayList<String> datos, int maxDigitos) {
    Function<Integer, Function<String, Integer>> obtenerDigitoASCII =
        (numDigito) ->
            (str) -> {
              int digito = 0;
              int diferencia = maxDigitos - str.length();
              if (numDigito >= diferencia) {
                digito = (int) str.charAt(str.length() - (numDigito - diferencia) - 1);
              }

              return digito;
            };

    RadixSort.<String>radixSort(maxDigitos, 255).accept(datos, obtenerDigitoASCII);
  }

  public static void ordenarHexadecimal(ArrayList<String> datos, int maxDigitos) {
    Function<Integer, Function<String, Integer>> obtenerDigitoHexadecimal =
        (numDigito) ->
            (num) -> {
              int digito = 0;

              if (num.length() > numDigito) {
                int digitoAscii = (int) num.charAt(num.length() - numDigito - 1);

                if (digitoAscii >= 48 && digitoAscii <= 57) digito = digitoAscii - 48;
                else digito = digitoAscii - 55;
              }

              return digito;
            };

    RadixSort.<String>radixSort(maxDigitos, 16).accept(datos, obtenerDigitoHexadecimal);
  }

  public static final <T>
      BiConsumer<ArrayList<T>, Function<Integer, Function<T, Integer>>> radixSort(
          int maxDigitos, int maxKey) {

    return (datos, obtenerDigito) -> {
      Function<T, Integer> digitos;
      for (int i = 0; i < maxDigitos; i++) {
        digitos = obtenerDigito.apply(i);
        RadixSort.<T>bucketSort(maxKey).accept(datos, digitos);
      }
    };
  }

  private static final <T> BiConsumer<ArrayList<T>, Function<T, Integer>> bucketSort(int maxKey) {
    return (datos, obtenerKey) -> {
      ArrayList<Queue<T>> colas = new ArrayList<>(maxKey);

      for (int i = 0; i < maxKey; i++) colas.add(new ArrayDeque<T>());

      // Hacer las cuentas
      for (T dato : datos) {
        int key = obtenerKey.apply(dato);
        colas.get(key).add(dato);
      }

      // Poner los datos
      int indice = 0;
      for (Queue<T> cola : colas) {
        for (T dato : cola) {
          datos.set(indice, dato);
          indice++;
        }
      }
    };
  }
}
