package Programming.FourthSemLab.SeventhLab.ThirdTask;

import Programming.FourthSemLab.SixthLab.seventhTask.BouncingBall;
import Programming.FourthSemLab.SixthLab.seventhTask.BouncingBallApp;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import lombok.Setter;

public class BallAndLinePanel extends BouncingBallApp {
private int amountOfBalls = 0;
@Setter
private int maxAmountOfBalls = 5;
  public BallAndLinePanel() {
    super();
    removeMouseListener(ma);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(amountOfBalls<maxAmountOfBalls){
          int x = new Random().nextInt(getWidth() - 20);
          int y = new Random().nextInt(getHeight() - 20);
          Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
          balls.add(new BouncingBall(x, y, 20, color));
          amountOfBalls++;
        }
      }
    });
  }

  public void stopTimer(){
    timer.stop();
  }
  public void resumeTimer(){
    timer.start();
  }

}
