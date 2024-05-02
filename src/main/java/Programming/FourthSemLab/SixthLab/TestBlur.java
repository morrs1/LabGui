package Programming.FourthSemLab.SixthLab;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class TestBlur {
    public static void main(String[] args) {
        // Загрузите ваше изображение
        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/photo_2024-04-29_22-24-09.jpeg");

        // Создайте новый объект ImageIcon из изображения
        ImageIcon imageIcon = new ImageIcon(image);

        // Получите объект BufferedImage из ImageIcon
        BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        // Примените эффект размытия
        float[] matrix = {
                1f / 273f, 4f / 273f, 7f / 273f, 4f / 273f, 1f / 273f,
                4f / 273f, 16f / 273f, 26f / 273f, 16f / 273f, 4f / 273f,
                7f / 273f, 26f / 273f, 41f / 273f, 26f / 273f, 7f / 273f,
                4f / 273f, 16f / 273f, 26f / 273f, 16f / 273f, 4f / 273f,
                1f / 273f, 4f / 273f, 7f / 273f, 4f / 273f, 1f / 273f
        };
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
        BufferedImage blurredImage = op.filter(bufferedImage, null);

        // Отобразите размытое изображение
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(blurredImage)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


