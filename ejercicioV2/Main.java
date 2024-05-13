package ejercicioV2;

import java.util.ArrayList;
import java.util.Random;

public class Main {
  private static final Random random = new Random();
  private static final int numEstudiantes = 5;
  private static final int numNotas = 3;

  public Main() {
    random.setSeed(System.currentTimeMillis());
  }

  public static void main(String[] args) {
    ArrayList<Estudiante> estudiantes = new ArrayList<>();

    // Scanner scanner = new Scanner(System.in);

    guardarDatos(estudiantes);
    // mostrarDatos(codigos, nombres, notas);

    // int opcion = -1;
    //
    // while (opcion != 0) {
    //   System.out.println("0. Salir");
    //   System.out.println("1. Buscar por código");
    //   System.out.println("2. Imprimir datos");
    //   System.out.print("> ");
    //
    //   opcion = scanner.nextInt();
    //
    //   switch (opcion) {
    //     case 1:
    //       System.out.print(">> ");
    //       boolean encontrado = buscarEstudiante(scanner.nextInt(), codigos, nombres, notas);
    //       if (!encontrado) System.out.println("ESTUDIANTE NO REGISTRADO");
    //       break;
    //     case 2:
    //       mostrarDatos(codigos, nombres, notas);
    //     default:
    //       break;
    //   }
    //   System.out.println();
    // }
    //
    // scanner.close();

    // Function<Estudiante, Integer> obtenerCodigo = e -> e.getCodigo();
    //
    // BusquedaBinaria<Estudiante, Integer> busquedaBinaria =
    //     new BusquedaBinaria<Estudiante, Integer>(obtenerCodigo, estudiantes, 4);
    // Optional<Estudiante> resultado = busquedaBinaria.buscar();
    //
    // if (resultado.isEmpty()) {
    //   System.out.println("No encontrado");
    // } else {
    //   Estudiante e = resultado.get();
    //
    //   System.out.println("Estudiante hallado:");
    //   System.out.println(e.getNombre());
    //   System.out.println(e.getCodigo());
    // }
  }

  // public static boolean buscarEstudiante(
  //     int codigoBusqueda, Integer codigos[], String nombres[], Float notas[][]) {
  //   ordenarPorCodigo(codigos, nombres, notas);
  //   return buscarEstudianteRecursivo(
  //       codigoBusqueda, codigos, nombres, notas, 0, codigos.length - 1);
  // }

  // public static void mostrarDatos(ArrayList<Estudiante> estudiantes) {
  //   System.out.println("Informe Final del Grupo");
  //   System.out.println("Codigo \t Nombre       Corte-1  Corte-2 Corte-3  Final");
  //
  //   ordenarPorNotas(codigos, nombres, notas);
  //
  //   recorrerDatos(codigos, nombres, notas, 0);
  //
  //   promedio /= numEstudiantes;
  //
  //   System.out.printf("\t\t\t\tPromedio curso: %.2f\n", promedio);
  // }

  // public static void recorrerDatos(
  //     Integer codigos[], String nombres[], Float notas[][], int indice) {
  //   if (indice == numEstudiantes) {
  //     return;
  //   } else {
  //     System.out.printf("%d\t", codigos[indice]);
  //     System.out.printf("%s\t", nombres[indice]);
  //     recorrerNotas(0, notas, indice);
  //     System.out.println();
  //     recorrerDatos(codigos, nombres, notas, ++indice);
  //   }
  // }

  // public static void recorrerNotas(int indice, Float notas[][], int estudiante) {
  //   if (indice == numNotas) {
  //     promedio += notas[3][estudiante];
  //     return;
  //   } else {
  //     System.out.printf("%.2f\t", notas[indice][estudiante]);
  //     recorrerNotas(++indice, notas, estudiante);
  //   }
  // }

  public static void guardarDatos(ArrayList<Estudiante> estudiantes) {
    for (int i = 0; i < numEstudiantes; i++) {
      // Estudiante e = new Estudiante("John Doe - " + (i + 1), random.nextInt(1, 100));
      Estudiante e = new Estudiante("John Doe - " + (i + 1), i);

      for (int j = 0; j < numNotas; j++) e.getNotas().add(random.nextFloat(0, 5));

      estudiantes.add(e);
    }
  }
}
