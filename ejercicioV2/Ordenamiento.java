package ejercicioV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Ordenamiento {
  // public static void main(String[] args) {
  //   String[] datos = {"AA", "AC", "AB", "BZ", "CN"};
  //   ArrayList<String> datosArrList = new ArrayList<String>(Arrays.asList(datos));
  //
  //   Integer[] datosNumericos = {1, 2, 5, 2, 62, 4};
  //   ArrayList<Integer> datosNumericosArr = new ArrayList<Integer>(Arrays.asList(datosNumericos));
  //
  //   Ordenamiento.<Integer>bubbleSort().accept(datosNumericosArr, Integer::compareTo);
  //
  //   Function<String, Integer> obtenerKey =
  //       (str) -> {
  //         int c = (int) str.charAt(0);
  //         return c - 64;
  //       };
  //
  //   Function<Integer, Function<String, Integer>> obtenerCaracter =
  //       (digito) ->
  //           (str) -> {
  //             return obtenerKey.apply(String.valueOf(str.charAt(str.length() - digito - 1)));
  //           };
  //
  //   Ordenamiento.<String>radixSort(2, 27).accept(datosArrList, obtenerCaracter);
  //
  //   System.out.println(datosNumericosArr.toString());
  //   System.out.println(datosArrList.toString());
  // }

  public static final <T>
      BiConsumer<ArrayList<T>, Function<Integer, Function<T, Integer>>> radixSort(
          int maxDigitos, int maxKey) {

    return (datos, obtenerDigito) -> {
      Function<T, Integer> digitos;
      for (int i = 0; i < maxDigitos; i++) {
        digitos = obtenerDigito.apply(i);
        Ordenamiento.<T>countingSort(maxKey).accept(datos, digitos);
      }
    };
  }

  public static final <T> BiConsumer<ArrayList<T>, Function<T, Integer>> countingSort(int maxKey) {
    return (datos, obtenerKey) -> {
      ArrayList<Queue<T>> colas = new ArrayList<>(maxKey);

      for (int i = 0; i < maxKey; i++) colas.add(new LinkedList<T>());

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

  public static final <T> BiConsumer<ArrayList<T>, BiFunction<T, T, Integer>> bubbleSort() {
    return (datos, comparador) -> {
      boolean ordenados = false;

      while (!ordenados) {
        ordenados = true;

        for (int i = 0; i < datos.size() - 1; i++) {
          boolean masGrande = comparador.apply(datos.get(i), datos.get(i + 1)) > 0;
          if (masGrande) Collections.swap(datos, i, i + 1);
        }
      }
    };
  }
}
