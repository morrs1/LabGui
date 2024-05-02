package Programming.FourthSemLab.SixthLab.seventhTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallApp extends JPanel {
    private List<BouncingBall> balls = new ArrayList<>();

    public BouncingBallApp() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = new Random().nextInt(getWidth() - 20);
                int y = new Random().nextInt(getHeight() - 20);
                Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                balls.add(new BouncingBall(x, y, 20, color));
            }
        });

        Timer timer = new Timer(1000 / 60, e -> {
            for (BouncingBall ball : balls) {
                ball.move(getWidth(), getHeight());
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (BouncingBall ball : balls) {
            ball.draw(g);
        }
    }

}
