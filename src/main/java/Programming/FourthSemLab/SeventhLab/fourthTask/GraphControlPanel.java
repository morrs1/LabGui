package Programming.FourthSemLab.SeventhLab.fourthTask;

import Programming.FourthSemLab.SixthLab.thirdTask.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphControlPanel extends JPanel {
    public GraphControlPanel() {
        JTextField textField = new JTextField(10);
        JButton buttonField = new JButton("✔");
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"sin(x)", "sin(x*x) + cos(x*x)", "2*sin(x)+cos(2*x)"});
        JButton button = new JButton("Red");
        JButton button1 = new JButton("Blue");
        JButton button2 = new JButton("Green");
        add(textField);
        add(buttonField);
        add(comboBox);
        add(button);
        add(button1);
        add(button2);

    }
}
