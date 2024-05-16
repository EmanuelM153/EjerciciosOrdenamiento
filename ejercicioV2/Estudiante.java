package ejercicioV2;

import java.util.ArrayList;

public class Estudiante {
  private final String nombre;
  private final int codigo;
  private final ArrayList<Float> notas;
  private float notaFinal;

  public Estudiante(String nombre, int codigo) {
    notas = new ArrayList<>();
    this.nombre = nombre;
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public int getCodigo() {
    return codigo;
  }

  public ArrayList<Float> getNotas() {
    return notas;
  }

  public Float getNotaFinal() {
    return notaFinal;
  }

  public void calcularNotaFinal() {
    this.notaFinal = notas.get(0) * 0.3f + notas.get(1) * 0.3f + notas.get(2) * 0.4f;
  }

  @Override
  public String toString() {
    return "Estudiante [nombre="
        + nombre
        + ", codigo="
        + codigo
        + ", notas="
        + notas.toString()
        + ", notaFinal="
        + notaFinal
        + "]";
  }
}
