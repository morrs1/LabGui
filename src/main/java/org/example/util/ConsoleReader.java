package org.example.util;

import com.ibm.icu.text.RuleBasedNumberFormat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class ConsoleReader {

  private static final RuleBasedNumberFormat numberFormat = new RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.SPELLOUT);

  public static Object executeTask(Class<?> solutionClass, String numberOfQuestion, Object... args) {
    try {
      var methodName = numberFormat.format(Integer.parseInt(numberOfQuestion), "%spellout-ordinal") + "Task";

      Class<?>[] paramTypes = new Class[args.length];
      for (int i = 0; i < args.length; i++) {
        paramTypes[i] = args[i].getClass();
      }

      Method method = solutionClass.getMethod(methodName, paramTypes);
      return method.invoke(solutionClass.getDeclaredConstructor().newInstance(), args);

    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      return e.toString();
    }
  }
}
