package Programming.FourthSemLab.SixthLab.fifthTask;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageViewerPanel extends JPanel {
  protected BufferedImage image;
  private boolean flipped = false;
  private Character keyPressed = 'o';
  protected boolean flagG = false;
  protected KeyListener k;
  protected MouseListener ml;

  public ImageViewerPanel() {
    try {
      // Загрузка изображения из файла
      File imageFile = new File("src/main/resources/Images/photo_2024-04-29_22-24-09.jpeg");
      image = ImageIO.read(imageFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        flipped = !flipped;
        repaint();
      }
    };
    addMouseListener(ml);
    setFocusable(true);
    requestFocusInWindow();

    k = new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        keyPressed = e.getKeyChar();
      }
    };
    addKeyListener(k);

  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);


    if(keyPressed == 'r' || keyPressed == 'R') {
      image = reverseImage();
    }
    if(keyPressed == 'b' || keyPressed == 'B') {
      image = blurImage(image);
    }
    if(keyPressed == 'g' || keyPressed == 'G') {
      if (!flagG){
        image = convertToGrayscale(image);
        flagG = true;
      }
    }


    double scale = Math.min(getWidth() / (double) image.getWidth(), getHeight() / (double) image.getHeight());
    int width = (int) (image.getWidth() * scale);
    int height = (int) (image.getHeight() * scale);
    int x = (getWidth() - width) / 2;
    int y = (getHeight() - height) / 2;
    g.drawImage(image, x, y+50, width, height-50, this);
  }


protected BufferedImage reverseImage(){
  AffineTransform transform = new AffineTransform();
  transform.rotate(Math.PI, image.getWidth() / 2, image.getHeight() / 2);
  AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
  return op.filter(image, null);
}


  protected BufferedImage blurImage(BufferedImage image) {
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
  protected BufferedImage convertToGrayscale(BufferedImage originalImage) {
    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    BufferedImage grayscaleImage = new BufferedImage(width, height, originalImage.getType());

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = originalImage.getRGB(x, y);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int average = (r + g + b) / 3;
        grayscaleImage.setRGB(x, y, (average << 16) | (average << 8) | average);
      }
    }

    return grayscaleImage;
  }
}
