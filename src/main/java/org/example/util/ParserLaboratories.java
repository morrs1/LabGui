package org.example.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import org.antlr.runtime.tree.Tree;

public class ParserLaboratories {

  public static HashMap<Class<?>, Integer> parserLaboratories() throws ClassNotFoundException {
    String path = System.getProperty("user.dir") + "/src/main/java/Programming/FourthSemLab";
    var classes = new ArrayList<Class<?>>();
    var dirWhereLabs = new File(path);

    return findClasses(dirWhereLabs, classes);
  }


  private static HashMap<Class<?>, Integer> findClasses(File dir, ArrayList<Class<?>> classes) {
    var dictLab = new HashMap<Class<?>, Integer>();
    var files = dir.listFiles();
    assert files != null;
    for (var file : files) {
      if (file.isDirectory()) {
        findClasses(file, classes);
      } else {
        if (file.getName().startsWith("Main")) {
          classes.add(ClassFinder.getaClass(file));
        }
        classes.forEach(clazz -> {
          var value = Arrays.stream(clazz.getMethods()).filter(x -> x.getName().endsWith("Task"))
              .toList()
              .size();

          dictLab.put(clazz,value);
        });
//
//        Method t1 = a.getMethods()[1];
//        try {
//          System.out.println(t1.invoke(a.getDeclaredConstructor().newInstance(), ""));
//        } catch (InstantiationException e) {
//          throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//          throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//          throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//          throw new RuntimeException(e);
//        }
      }
    }

    return dictLab;

  }
}
