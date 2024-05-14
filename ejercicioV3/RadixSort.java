package ejercicioV3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class RadixSort {
  public static final <T>
      BiConsumer<ArrayList<T>, Function<Integer, Function<T, Integer>>> radixSort(
          int maxDigitos, int maxKey) {

    return (datos, obtenerDigito) -> {
      Function<T, Integer> digitos;
      for (int i = 0; i < maxDigitos; i++) {
        digitos = obtenerDigito.apply(i);
        RadixSort.<T>bucketSort(maxKey + 1).accept(datos, digitos);
      }
    };
  }

  private static final Function<Integer, Function<Integer, Integer>> obtenerDigito =
      (numDigito) ->
          (num) -> {
            int digito;

            if (numDigito == 0) digito = num % 10;
            else digito = ((int) (num / Math.pow(10, numDigito)) % 10);

            return digito;
          };

  public static void ordenarEnteros(ArrayList<Integer> datos, int maxDigitos) {
    RadixSort.<Integer>radixSort(maxDigitos, 9).accept(datos, obtenerDigito);
  }

  public static void ordenarOctal(ArrayList<Integer> datos, int maxDigitos) {
    RadixSort.<Integer>radixSort(maxDigitos, 7).accept(datos, obtenerDigito);
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

    RadixSort.<String>radixSort(maxDigitos, 15).accept(datos, obtenerDigitoHexadecimal);
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
