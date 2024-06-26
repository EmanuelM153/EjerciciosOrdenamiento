package ejercicioV2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  private static final Random random = new Random();
  private static final int numEstudiantes = 5;
  private static final int numNotas = 3;
  private static ArrayList<Estudiante> estudiantes = new ArrayList<>();

  public Main() {
    random.setSeed(System.currentTimeMillis());
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    guardarDatos();

    float promedio = calcularPromedioGeneral();

    mostrarDatos(promedio);

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

          int codigo = scanner.nextInt();

          Optional<Estudiante> resultado = buscarEstudiante(codigo);

          if (resultado.isEmpty()) {
            System.out.println("No encontrado");
          } else {
            Estudiante e = resultado.get();
            System.out.println(e.toString());
          }

          break;
        case 2:
          mostrarDatos(promedio);
        default:
          break;
      }
      System.out.println();
    }

    scanner.close();
  }

  public static float calcularPromedioGeneral() {
    float promedio = 0;

    for (Estudiante e : estudiantes) promedio += e.getNotaFinal();

    return promedio / numEstudiantes;
  }

  public static void ordenarPorCodigo() {
    estudiantes =
        new ArrayList<Estudiante>(
            estudiantes.stream()
                .sorted(
                    (e1, e2) -> {
                      return Integer.compare(e1.getCodigo(), e2.getCodigo());
                    })
                .collect(Collectors.toList()));
  }

  public static void ordenarPorNotaFinal() {
    estudiantes =
        new ArrayList<Estudiante>(
            estudiantes.stream()
                .sorted(
                    (e1, e2) -> {
                      return Float.compare(e2.getNotaFinal(), e1.getNotaFinal());
                    })
                .collect(Collectors.toList()));
  }

  public static Optional<Estudiante> buscarEstudiante(int codigoBusqueda) {
    ordenarPorCodigo();

    BusquedaBinaria<Estudiante, Integer> busquedaBinaria =
        new BusquedaBinaria<Estudiante, Integer>(
            Estudiante::getCodigo, estudiantes, codigoBusqueda);

    return busquedaBinaria.buscar();
  }

  public static void mostrarDatos(float promedio) {
    System.out.println("Informe Final del Grupo");
    System.out.println("Codigo \t Nombre       Corte-1  Corte-2 Corte-3  Final");

    ordenarPorNotaFinal();

    recorrerDatos(0);

    System.out.printf("\t\t\t\tPromedio curso: %.2f\n", promedio);
  }

  public static void recorrerDatos(int indice) {
    if (indice == numEstudiantes) {
      return;
    } else {
      System.out.printf("%d\t", estudiantes.get(indice).getCodigo());
      System.out.printf("%s\t", estudiantes.get(indice).getNombre());

      recorrerNotas(indice, 0);

      System.out.println();
      recorrerDatos(++indice);
    }
  }

  public static void recorrerNotas(int estudianteIndice, int indice) {
    if (indice == numNotas) {
      System.out.printf("%.2f", estudiantes.get(estudianteIndice).getNotaFinal());
      return;
    } else {
      System.out.printf("%.2f\t", estudiantes.get(estudianteIndice).getNotas().get(indice));
      recorrerNotas(estudianteIndice, ++indice);
    }
  }

  public static void guardarDatos() {
    for (int i = 0; i < numEstudiantes; i++) {
      Estudiante e = new Estudiante("John Doe - " + (i + 1), random.nextInt(1, 1000));

      for (int j = 0; j < numNotas; j++) e.getNotas().add(random.nextFloat(0, 5));
      e.calcularNotaFinal();

      estudiantes.add(e);
    }
  }
}
