package Programming.FourthSemLab.SixthLab.fourthTask;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class WindowPanel extends JPanel implements KeyListener {
  private int mouseX = 10;
  private int mouseY = 20;
  private Color textColor = Color.BLACK;

  public WindowPanel() {
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
      }
    });
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(new Font("Arial", Font.BOLD, 14));
    g.setColor(textColor);
    g.drawString("Координаты: (" + mouseX + ", " + mouseY + ")", mouseX, mouseY);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'b'->
        textColor = Color.BLACK;
      case 'w'->
        textColor = Color.WHITE;
      case 'r'->
        textColor = Color.RED;
      case 'g'->
        textColor = Color.GREEN;
      case 'o'->
        textColor = Color.ORANGE;
    }
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {}
}
