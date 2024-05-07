package Programming.FourthSemLab.SeventhLab.ThirdTask;

import Programming.FourthSemLab.SixthLab.eightTask.RunningText;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BallAndLineApp extends JFrame {

  public BallAndLineApp() {
    JFrame frame = new JFrame("Bouncing Balls");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Добавление элементов управления
    JButton buttonPause = new JButton("Pause");
    JButton buttonResume = new JButton("Resume");
    JTextField textField = new JTextField(20);
    JButton buttonText = new JButton("✔");

    JPanel panel = new JPanel();
    panel.add(buttonPause);
    panel.add(buttonResume);
    panel.add(textField);
    panel.add(buttonText);
    frame.add(panel, BorderLayout.NORTH);

    //Добавление бегущей строки
    RunningText runningText = new RunningText();
    frame.add(runningText, BorderLayout.SOUTH);

    //Добавление самих шариков
    BallAndLinePanel ballAndLinePanel = new BallAndLinePanel();
    frame.add(ballAndLinePanel);
    //Обработчики событий для кнопок
    buttonPause.addActionListener(e -> {
      ballAndLinePanel.stopTimer();
    });
    buttonResume.addActionListener(e -> {
      ballAndLinePanel.resumeTimer();
    });

    buttonText.addActionListener(e -> {
      if (!textField.getText().isEmpty()) {
        ballAndLinePanel.setMaxAmountOfBalls(Integer.parseInt(textField.getText()));
      }
    });

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);


  }
}
