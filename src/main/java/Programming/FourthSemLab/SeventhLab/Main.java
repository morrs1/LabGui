package Programming.FourthSemLab.SeventhLab;


import Programming.FourthSemLab.SeventhLab.ThirdTask.BallAndLineApp;
import Programming.FourthSemLab.SeventhLab.eighthTask.DiceGame;
import Programming.FourthSemLab.SeventhLab.fifthTask.CustomPanel;
import Programming.FourthSemLab.SeventhLab.firstTask.App;
import Programming.FourthSemLab.SeventhLab.fourthTask.GraphApp;
import Programming.FourthSemLab.SeventhLab.secondTask.ImageViewerApp7Lab;
import Programming.FourthSemLab.SeventhLab.sixthTask.Dice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

  public static String sixthTask(String ignoredUnused){
    JFrame frame = new JFrame("Dice Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200); // Устанавливаем размер окна

    // Создаем панель для отображения Dice
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(100, 100)); // Устанавливаем размер панели
    panel.setBackground(Color.WHITE); // Устанавливаем цвет фона панели

    // Создаем экземпляр класса Dice и добавляем его в панель
    Dice dice = new Dice(50);
    panel.add(dice);
panel.addMouseListener(new MouseAdapter() {
  @Override
  public void mouseClicked(MouseEvent e) {
    dice.roll();
  }
});
    // Добавляем панель в окно
    frame.add(panel);

    // Делаем окно видимым
    frame.setVisible(true);
    return "";
  }

  public static String seventhTask(String ignoredUnused){
    return "";
  }

  public static String eighthTask(String ignoredUnused){
    DiceGame.run();
    return "";
  }


}
