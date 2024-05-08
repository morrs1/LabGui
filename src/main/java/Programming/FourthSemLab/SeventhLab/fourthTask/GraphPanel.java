package Programming.FourthSemLab.SeventhLab.fourthTask;

import Programming.FourthSemLab.SixthLab.secondTask.Curve;
import Programming.FourthSemLab.SixthLab.thirdTask.Axis;
import Programming.FourthSemLab.SixthLab.thirdTask.Graph;
import Programming.FourthSemLab.SixthLab.thirdTask.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class GraphPanel extends Graph {
    public GraphPanel() {
        super();
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
        double[] xValues = new double[360];
        double[] yValues = new double[360];
        for (int i = 0; i < 360; i++) {
            double x = i * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = Math.sin(x);
        }
        Curve curve = new Curve(xValues, yValues);
        addCurve(curve);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Path2D path = new Path2D.Double();
        double scaleX = getWidth() / (4 * Math.PI);
        double scaleY = getHeight() / 4;
        axis = new Axis(getWidth(), getHeight());

        axis.draw(path);
        grid = new Grid();
        grid.draw(path, getWidth(), getHeight());
        g2d.draw(path);
        g2d.setColor(Color.RED);
        path.reset();
        for (var c : curves) {
            c.draw(path, scaleX, scaleY, getWidth(), getHeight());
        }
        g2d.draw(path);
    }
}
