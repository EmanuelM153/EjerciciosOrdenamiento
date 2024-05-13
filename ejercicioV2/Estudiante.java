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

  public float getNotaFinal() {
    return notaFinal;
  }

  public void setNotaFinal(float notaFinal) {
    this.notaFinal = notaFinal;
  }
}
