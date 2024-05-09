package Programming.FourthSemLab.SeventhLab;


import Programming.FourthSemLab.SeventhLab.ThirdTask.BallAndLineApp;
import Programming.FourthSemLab.SeventhLab.fifthTask.CustomPanel;
import Programming.FourthSemLab.SeventhLab.firstTask.App;
import Programming.FourthSemLab.SeventhLab.fourthTask.GraphApp;
import Programming.FourthSemLab.SeventhLab.secondTask.ImageViewerApp7Lab;
import java.awt.Color;
import javax.swing.JFrame;

public class Main {
  public static String firstTask(String ignoredUnused){
    new App();
    return "";
  }
  public static String secondTask(String ignoredUnused){
    new ImageViewerApp7Lab();
    return "";
  }
  public static String thirdTask(String ignoredUnused){
      new BallAndLineApp();
    return "";
  }

  public static String fourthTask(String ignoredUnused){
    new GraphApp();
    return "";
  }

  public static String fifthTask(String ignoredUnused){
    // Создание экземпляра JFrame
    JFrame frame = new JFrame("Custom Panel Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    // Создание экземпляра CustomPanel
    CustomPanel customPanel = new CustomPanel();

    // Установка цвета фона панели
    customPanel.setPanelBackgroundColor(Color.LIGHT_GRAY);

    // Установка цвета текста для каждой метки
    customPanel.setLabelTextColor("upperLabel", Color.RED);
    customPanel.setLabelTextColor("downLabel", Color.BLUE);
    customPanel.setLabelTextColor("centerLabel", Color.GREEN);


    // Добавление CustomPanel на JFrame
    frame.add(customPanel);

    // Делаем JFrame видимым
    frame.setVisible(true);
    return "";
  }



}
