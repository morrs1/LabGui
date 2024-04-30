package Programming.FourthSemLab.SixthLab.fifthTask;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class ImageViewerPanel extends JPanel {
  private BufferedImage image;
  private boolean flipped = false;

  public ImageViewerPanel() {
    try {
      // Загрузка изображения из файла
      File imageFile = new File("src/main/resources/Images/photo_2024-04-29_22-24-09.jpeg");
      image = ImageIO.read(imageFile);
    } catch (Exception e) {
      e.printStackTrace();
    }

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        flipped = !flipped;
        repaint();
      }
    });
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      int width = getWidth();
      int height = getHeight();
      int imageWidth = image.getWidth(null);
      int imageHeight = image.getHeight(null);

      // Подстраиваем изображение под размер панели, сохраняя пропорции
      if (width * imageHeight < height * imageWidth) {
        height = width * imageHeight / imageWidth;
      } else {
        width = height * imageWidth / imageHeight;
      }

      // Создаем объект Graphics2D для работы с трансформациями
      Graphics2D g2d = (Graphics2D) g;

      // Переворачиваем изображение при необходимости
      if (flipped) {
        // Создаем трансформацию для переворачивания изображения
        AffineTransform transform = AffineTransform.getScaleInstance(-1, -1);
        transform.translate(-image.getWidth(null), -image.getHeight(null));

        // Применяем трансформацию к изображению
        AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage rotatedImage = operation.filter(image, null);
        g2d.drawImage(rotatedImage, (getWidth() - width) / 2, 0, width, height, null);
      } else {
        g2d.drawImage(image, (getWidth() - width) / 2, (getHeight() - height) / 2, width, height, null);
      }
    }
  }

}
