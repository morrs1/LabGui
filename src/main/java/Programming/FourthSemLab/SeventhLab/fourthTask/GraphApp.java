package Programming.FourthSemLab.SeventhLab.fourthTask;

import Programming.FourthSemLab.SixthLab.thirdTask.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphApp extends JFrame{
public GraphApp(){
    setTitle("График функции");
    setSize(600, 600);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    add(new GraphPanel());
//    add(new GraphControlPanel());
    setVisible(true);
}


}
