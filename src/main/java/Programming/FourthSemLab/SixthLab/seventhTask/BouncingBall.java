package Programming.FourthSemLab.SixthLab.seventhTask;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BouncingBall {
    private int x;
    private int y;
    private final int radius;
    private int dx, dy;
    private final Color color;

    public BouncingBall(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        // Направление скорости 45 градусов против часовой стрелки
        this.dx = new Random().nextInt(10) - 1; // Случайное значение скорости
        this.dy = new Random().nextInt(10) - 1; // Случайное значение скорости
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void move(int width, int height) {
        x += dx;
        y += dy;

        // Отражение от стен
        if (x < 0 || x > width - radius) {
            dx = -dx;
        }
        if (y < 0 || y > height - radius) {
            dy = -dy;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
    }
}
