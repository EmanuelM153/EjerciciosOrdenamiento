package ejercicioV1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Main {
  private static final int numEstudiantes = 5;
  private static final int numNotas = 4;
  private static final Random random = new Random();
  private static float promedio;

  public Main() {
    random.setSeed(System.currentTimeMillis());
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Integer codigos[] = new Integer[numEstudiantes];
    String nombres[] = new String[numEstudiantes];
    Float notas[][] = new Float[numNotas][numEstudiantes];

    guardarDatos(codigos, nombres, notas);
    mostrarDatos(codigos, nombres, notas);

    int opcion = -1;

    while (opcion != 0) {
      System.out.println("0. Salir");
      System.out.println("1. Buscar por código");
      System.out.println("2. Imprimir datos");
      System.out.print("> ");

      opcion = scanner.nextInt();

      switch (opcion) {
        case 1:
          System.out.print(">> ");
          boolean encontrado = buscarEstudiante(scanner.nextInt(), codigos, nombres, notas);
          if (!encontrado) System.out.println("ESTUDIANTE NO REGISTRADO");
          break;
        case 2:
          mostrarDatos(codigos, nombres, notas);
        default:
          break;
      }
      System.out.println();
    }

    scanner.close();
  }

  public static boolean buscarEstudiante(
      int codigoBusqueda, Integer codigos[], String nombres[], Float notas[][]) {
    ordenarPorCodigo(codigos, nombres, notas);
    return buscarEstudianteRecursivo(
        codigoBusqueda, codigos, nombres, notas, 0, codigos.length - 1);
  }

  public static boolean buscarEstudianteRecursivo(
      int codigoBusqueda,
      Integer codigos[],
      String nombres[],
      Float notas[][],
      int inicio,
      int fin) {
    if (inicio > fin) {
      return false;
    }

    int medio = (inicio + fin) / 2;
    if (codigos[medio] == codigoBusqueda) {
      System.out.println("Estudiante encontrado:");
      System.out.println("Código: " + codigos[medio]);
      System.out.println("Nombre: " + nombres[medio]);
      System.out.println("Notas:");
      for (int i = 0; i < numNotas - 1; i++) {
        System.out.printf("Corte %d: %.2f\n", i + 1, notas[i][medio]);
      }
      System.out.printf("Final: %.2f\n", notas[3][medio]);
      return true;
    } else if (codigos[medio] < codigoBusqueda) {
      return buscarEstudianteRecursivo(codigoBusqueda, codigos, nombres, notas, medio + 1, fin);
    } else {
      return buscarEstudianteRecursivo(codigoBusqueda, codigos, nombres, notas, inicio, medio - 1);
    }
  }

  public static void ordenarPorCodigo(Integer codigos[], String nombre[], Float notas[][]) {
    for (int i = 0; i < numEstudiantes - 1; i++) {
      for (int j = 0; j < numEstudiantes - i - 1; j++) {
        if (codigos[j] > codigos[j + 1]) {

          int temp = codigos[j];
          codigos[j] = codigos[j + 1];
          codigos[j + 1] = temp;

          String tempNombre = nombre[j];
          nombre[j] = nombre[j + 1];
          nombre[j + 1] = tempNombre;

          for (int k = 0; k < numNotas; k++) {
            float aux = notas[k][j];
            notas[k][j] = notas[k][j + 1];
            notas[k][j + 1] = aux;
          }
        }
      }
    }
  }

  public static void ordenarPorNotas(Integer codigos[], String nombres[], Float notas[][]) {
    Float notasFinal[] = notas[3];

    Function<Object, Integer> mapa =
        (num) -> {
          Float flotante = (Float) num;

          return (int) (flotante * 100);
        };

    Function<Integer, Float> mapaInverso =
        (num) -> {
          return num / (float) 100;
        };

    Integer[] keysOrdenados =
        Arrays.copyOf(radixSort(notasFinal, mapa), notasFinal.length, Integer[].class);

    Object[] notasOrdenadasObjects =
        Arrays.stream(keysOrdenados).map((key) -> mapaInverso.apply(key)).toArray();
    Float[] notasOrdenadas = Arrays.copyOf(notasOrdenadasObjects, numEstudiantes, Float[].class);

    reversarArreglo(notasOrdenadas);

    Function<Float, Integer> indiceDeNotas = indiceDe.apply(notasFinal);

    String nombresCopia[] = Arrays.copyOf(nombres, numEstudiantes, String[].class);
    Integer[] codigoCopia = Arrays.copyOf(codigos, numEstudiantes, Integer[].class);

    Float[] corte1 = notas[0];
    Float[] corte2 = notas[1];
    Float[] corte3 = notas[2];
    Float[] corte1Copia = Arrays.copyOf(corte1, numEstudiantes, Float[].class);
    Float[] corte2Copia = Arrays.copyOf(corte2, numEstudiantes, Float[].class);
    Float[] corte3Copia = Arrays.copyOf(corte3, numEstudiantes, Float[].class);

    for (int i = 0; i < numEstudiantes; i++) {
      int indiceNuevo = indiceDeNotas.apply(notasOrdenadas[i]);

      nombres[i] = nombresCopia[indiceNuevo];
      codigos[i] = codigoCopia[indiceNuevo];
      corte1[i] = corte1Copia[indiceNuevo];
      corte2[i] = corte2Copia[indiceNuevo];
      corte3[i] = corte3Copia[indiceNuevo];
    }

    for (int i = 0; i < numEstudiantes; i++) notasFinal[i] = notasOrdenadas[i];
  }

  public static void reversarArreglo(Object[] arr) {
    for (int i = 0; i < arr.length / 2; i++) {
      Object aux = arr[i];
      arr[i] = arr[arr.length - i - 1];
      arr[arr.length - i - 1] = aux;
    }
  }

  public static final Function<Float[], Function<Float, Integer>> indiceDe =
      (arr) ->
          (e) -> {
            return IntStream.rangeClosed(0, arr.length - 1)
                .filter(i -> e == truncar(arr[i], 2))
                .findFirst()
                .orElse(-1);
          };

  public static float truncar(Float value, int precision) {
    float resultado = value * 100;
    resultado = (float) Math.floor(resultado);
    resultado = resultado / 100;
    return resultado;
  }

  public static void mostrarDatos(Integer codigos[], String nombres[], Float notas[][]) {
    System.out.println("Informe Final del Grupo");
    System.out.println("Codigo \t Nombre       Corte-1  Corte-2 Corte-3  Final");

    promedio = 0;

    ordenarPorNotas(codigos, nombres, notas);

    recorrerDatos(codigos, nombres, notas, 0);

    promedio /= numEstudiantes;

    System.out.printf("\t\t\t\tPromedio curso: %.2f\n", promedio);
  }

  public static void recorrerDatos(
      Integer codigos[], String nombres[], Float notas[][], int indice) {
    if (indice == numEstudiantes) {
      return;
    } else {
      System.out.printf("%d\t", codigos[indice]);
      System.out.printf("%s\t", nombres[indice]);
      recorrerNotas(0, notas, indice);
      System.out.println();
      recorrerDatos(codigos, nombres, notas, ++indice);
    }
  }

  public static void recorrerNotas(int indice, Float notas[][], int estudiante) {
    if (indice == numNotas) {
      promedio += notas[3][estudiante];
      return;
    } else {
      System.out.printf("%.2f\t", notas[indice][estudiante]);
      recorrerNotas(++indice, notas, estudiante);
    }
  }

  public static void guardarDatos(Integer codigos[], String nombres[], Float notas[][]) {
    for (int i = 0; i < numEstudiantes; i++) {
      nombres[i] = "John Doe - " + (i + 1);
      codigos[i] = random.nextInt(1, 100);

      for (int j = 0; j < numNotas - 1; j++) notas[j][i] = random.nextFloat(0, 5);

      notas[3][i] = (float) (notas[0][i] * 0.3 + notas[1][i] * 0.3 + notas[2][i] * 0.4);
    }
  }

  public static int obtenerMaxDigitos(Integer[] arr) {
    int maxNum = Collections.max(Arrays.asList(arr));
    int maxDigitos;

    if (maxNum == 0) maxDigitos = 1;
    else maxDigitos = (int) (Math.floor(Math.log10(maxNum)) + 1);

    return maxDigitos;
  }

  public static final Function<Integer, Function<Object, Integer>> hallarDigitos =
      (lugarDigito) ->
          (num) -> {
            Integer entero = (Integer) num;
            int digito;

            if (lugarDigito == 0) digito = entero % 10;
            else digito = (int) ((entero / (Math.pow(10, lugarDigito))) % 10);

            return digito;
          };

  public static Object[] radixSort(Object[] arr, Function<Object, Integer> obtenerKey) {
    Object[] arrConKeysObjects = Arrays.stream(arr).map((o) -> obtenerKey.apply(o)).toArray();
    Integer[] arrConKeys =
        Arrays.copyOf(arrConKeysObjects, arrConKeysObjects.length, Integer[].class);

    Object[] salida = arrConKeys;

    int max = obtenerMaxDigitos(arrConKeys);

    Function<Object, Integer> digito;
    for (int i = 0; i < max; i++) {
      digito = hallarDigitos.apply(i);
      salida = countingSort(salida, 10, digito);
    }

    return salida;
  }

  public static Object[] countingSort(
      Object[] arr, Integer maxKey, Function<Object, Integer> mapear) {
    int[] cuenta = new int[maxKey];

    Object[] salida = new Object[arr.length];

    // Hacer las cuentas
    for (int i = 0; i < arr.length; i++) {
      int key = mapear.apply(arr[i]);
      cuenta[key]++;
    }

    // Suma prefija
    for (int i = 1; i < maxKey; i++) {
      cuenta[i] += cuenta[i - 1];
    }

    // Ordenar
    for (int i = arr.length - 1; i >= 0; i--) {
      int key = mapear.apply(arr[i]);
      cuenta[key]--;
      salida[cuenta[key]] = arr[i];
    }

    return salida;
  }
}
