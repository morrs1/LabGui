package Programming.FourthSemLab.SeventhLab.firstTask;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.TableColumn;

public class App extends JFrame {

  public App() {
    setTitle("1 задание");
    setSize(700, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    //Установка layout
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    //Создание комбобокса и настройка его размеров
    LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
    JComboBox<Object> comboBox = new JComboBox<>(Stream.of(lookAndFeels).map(
        LookAndFeelInfo::getName).toArray(String[]::new));
    comboBox.setMaximumSize(new Dimension(Short.MAX_VALUE, getHeight() / 20));
    panel.add(comboBox);

    //Создание JLabel
    JLabel label = new JLabel();
    label.setText("Новая метка");
    panel.add(label);

    //Создание кнопки
    JButton button = new JButton();
    button.setText("Новая кнопка");
    panel.add(button);

    //Создание checkBox
    JCheckBox checkBox = new JCheckBox("Новый флажок с независимой фиксацией");
    panel.add(checkBox);

    //Создание радиокнопок
    JRadioButton radioButton1 = new JRadioButton("Первый флажок");
    JRadioButton radioButton2 = new JRadioButton("Первый флажок");
    ButtonGroup group = new ButtonGroup();
    group.add(radioButton1);
    group.add(radioButton2);
    panel.add(radioButton1);
    panel.add(radioButton2);

    //Создание таблицы
    JTable table = new JTable();
    String[] columnNames = {"Язык", "Автор", "Год"};
    Object[][] data = {
        {"Си", "Деннис Ритчи", 1972},
        {"C++", "Бьерн Страуструп", 1983},
        {"Python", "Гвидо ван Россум", 1991},
        {"Java", "Джеймс Гослинг", 1995},
        {"JavaScript", "Брендон Айк", 1995},
        {"C#", "Андерс Хейлсберг", 2001},
        {"Scala", "Мартин Одерски", 2003}
    };
    table = new JTable(data, columnNames);
    panel.add(table);


    comboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String lafName = (String) comboBox.getSelectedItem();
        try {
          for (LookAndFeelInfo laf : lookAndFeels) {
            if (laf.getName().equals(lafName)) {
              UIManager.setLookAndFeel(laf.getClassName());
              SwingUtilities.updateComponentTreeUI(App.this);
              break;
            }
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    add(panel);
    setVisible(true);

  }
}
