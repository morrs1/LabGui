package Programming.FourthSemLab.SeventhLab.fifthTask;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
  private final JLabel upperLabel;
  private final JLabel downLabel;
  private final JLabel centerLabel;

  public CustomPanel() {
    setLayout(new BorderLayout());

    // Создание меток
    upperLabel = new JLabel("dialog");
    downLabel = new JLabel("dialog");
    centerLabel = new JLabel("dialog");

    // Установка шрифта для upperLabel и downLabel
    Font font = new Font("Dialog", Font.BOLD | Font.ITALIC, 10);
    upperLabel.setFont(font);
    downLabel.setFont(font);

    // Установка шрифта для centerLabel
    font = new Font("Dialog", Font.BOLD, 18);
    centerLabel.setFont(font);

    // Установка горизонтального выравнивания текста
    upperLabel.setHorizontalAlignment(SwingConstants.LEFT);
    downLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    centerLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Добавление меток на панель
    add(upperLabel, BorderLayout.NORTH);
    add(downLabel, BorderLayout.SOUTH);
    add(centerLabel, BorderLayout.CENTER);
  }

  // Метод для установки цвета фона панели
  public void setPanelBackgroundColor(Color color) {
    setBackground(color);
  }

  // Метод для установки цвета текста метки
  public void setLabelTextColor(String labelName, Color color) {
    if ("upperLabel".equals(labelName)) {
      upperLabel.setForeground(color);
    } else if ("downLabel".equals(labelName)) {
      downLabel.setForeground(color);
    } else if ("centerLabel".equals(labelName)) {
      centerLabel.setForeground(color);
    }
  }



}
