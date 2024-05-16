package ejercicioV3;

import java.util.ArrayList;
import java.util.function.Function;

public class OrdenamientoUtils {
  public static int encontrarMayorNumDigito(ArrayList<Integer> datos) {
    int mayorNumDigito = 1;

    for (Integer dato : datos) {
      int numDigitos = (int) Math.log10(dato) + 1;
      if (numDigitos > mayorNumDigito) mayorNumDigito = numDigitos;
    }

    return mayorNumDigito;
  }

  public static int encontrarMayorNumDigitoStr(ArrayList<String> strings) {
    int mayorNumDigito = 1;

    for (String str : strings) {
      int numDigitos = str.length();
      if (numDigitos > mayorNumDigito) mayorNumDigito = numDigitos;
    }

    return mayorNumDigito;
  }

  public static final Function<Integer, Function<Integer, Integer>> obtenerDigito =
      (numDigito) ->
          (num) -> {
            int digito;

            if (numDigito == 0) digito = num % 10;
            else digito = ((int) (num / Math.pow(10, numDigito)) % 10);

            return digito;
          };
}
