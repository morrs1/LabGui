package org.example.util;

import java.io.File;

public class ClassFinder {

  static Class<?> getaClass(File file)  {
    Class<?> result = null;
    var path = file.getAbsolutePath();
    var pathForClass = path.replace(".java", "").replace("\\", ".").substring(path.indexOf("Programming"));
    try {
      result = Class.forName(pathForClass);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
}
