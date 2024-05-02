package Programming.FourthSemLab.SixthLab.fifthTask;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class ImageViewerPanel extends JPanel {
  private BufferedImage image;
  private boolean flipped = false;
  private Character keyPressed = 'o';

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
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        keyPressed = e.getKeyChar();
        System.out.println(keyPressed);
      }
    });
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(keyPressed == 'r' || keyPressed == 'R') {
      paintReverse(g);
      System.out.println("r");
    }
    if(keyPressed == 'b' || keyPressed == 'B') {
      BufferedImage blurredImage = blurImage(image);
      g.drawImage(blurredImage, 0, 0, this);
      System.out.println("b");
    }
    if(keyPressed == 'o') {
      paintReverse(g);
      System.out.println("o");
    }



  }


private void paintReverse(Graphics g){
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


  private BufferedImage blurImage(BufferedImage image) {
    BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        Color avgColor = calculateAverageColor(image, i, j);
        blurredImage.setRGB(i, j, avgColor.getRGB());
      }
    }
    return blurredImage;
  }

  private Color calculateAverageColor(BufferedImage image, int x, int y) {
    int sumR = 0, sumG = 0, sumB = 0;
    for (int i = x - 3; i <= x + 3; i++) {
      for (int j = y - 3; j <= y + 3; j++) {
        // Use the reflected pixel if the coordinate is outside the image bounds
        int reflectedI = Math.min(Math.max(i, 0), image.getWidth() - 1);
        int reflectedJ = Math.min(Math.max(j, 0), image.getHeight() - 1);
        Color pixelColor = new Color(image.getRGB(reflectedI, reflectedJ));
        sumR += pixelColor.getRed();
        sumG += pixelColor.getGreen();
        sumB += pixelColor.getBlue();
      }
    }
    return new Color(sumR / 49, sumG / 49, sumB / 49);
  }
}
