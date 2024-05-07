package Programming.FourthSemLab.SeventhLab.ThirdTask;

import Programming.FourthSemLab.SixthLab.eightTask.RunningText;
import Programming.FourthSemLab.SixthLab.seventhTask.BouncingBall;
import Programming.FourthSemLab.SixthLab.seventhTask.BouncingBallApp;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import lombok.Setter;

public class BallAndLinePanel extends BouncingBallApp {
public int amountOfBalls = 0;
@Setter
private int maxAmountOfBalls = 0;
public RunningText runningText = new RunningText();
  public BallAndLinePanel() {
    super();
    runningText.setCurrentMessage("Кол-во шариков: " + amountOfBalls + " Максимальное кол-во шариков: " + maxAmountOfBalls);
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
          runningText.setCurrentMessage("Кол-во шариков: " + amountOfBalls + " Максимальное кол-во шариков: " + maxAmountOfBalls);
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
