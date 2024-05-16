package ejercicioV2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BusquedaBinaria<T, U extends Comparable<U>> {
  private final Function<T, U> obtenerKey;
  private final ArrayList<T> datos;
  private final U key;

  public BusquedaBinaria(Function<T, U> obtenerKey, ArrayList<T> datos, U key) {
    this.obtenerKey = obtenerKey;
    this.datos = datos;
    this.key = key;
  }

  public Optional<T> buscar() {
    return buscarRecursivamente().apply(0, datos.size() - 1);
  }

  private final BiFunction<Integer, Integer, Optional<T>> buscarRecursivamente() {
    return (inicio, fin) -> {
      if (inicio > fin) return Optional.empty();

      int medio = (inicio + fin) / 2;

      U keyMedio = obtenerKey.apply(datos.get(medio));

      if (keyMedio.equals(key)) return Optional.of(datos.get(medio));
      else if (key.compareTo(keyMedio) < 0)
        return this.buscarRecursivamente().apply(inicio, medio - 1);
      else return this.buscarRecursivamente().apply(medio + 1, fin);
    };
  }
}
