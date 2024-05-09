package Programming.FourthSemLab.SeventhLab.eighthTask;


import Programming.FourthSemLab.SeventhLab.sixthTask.Dice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DiceGame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new RollDicePanel();
      }
    });
  }
}

class RollDicePanel extends JFrame {

  private static final long serialVersionUID = 1L;

  private final DiceGameModel model;

  private Dice myLeftDie;
  private Dice myRightDie;

  private InnerSquarePanel[] innerPanels;

  private JTextField scoreField1;
  private JTextField scoreField2;

  public RollDicePanel() {
    this.model = new DiceGameModel();

    this.setTitle("Dice Game");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(createOuterPanel(), BorderLayout.CENTER);
    this.pack();
    this.setLocationByPlatform(true);
    this.setVisible(true);
  }

  private JPanel createOuterPanel() {
    int length = model.getBoardSquares().size();
    this.innerPanels = new InnerSquarePanel[length];
    for (int index = 0; index < length; index++) {
      innerPanels[index] = new InnerSquarePanel(
          model.getBoardSquares().get(index));
    }

    JPanel panel = new JPanel(new GridLayout(0, 3));
    panel.add(innerPanels[1].getPanel());
    panel.add(innerPanels[2].getPanel());
    panel.add(innerPanels[3].getPanel());
    panel.add(innerPanels[0].getPanel());
    panel.add(createCenterPanel());
    panel.add(innerPanels[4].getPanel());
    panel.add(innerPanels[7].getPanel());
    panel.add(innerPanels[6].getPanel());
    panel.add(innerPanels[5].getPanel());

    innerPanels[0].getPlayer1Label().setText("Player 1");
    innerPanels[0].getPlayer2Label().setText("Player 2");

    return panel;
  }

  private JPanel createCenterPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    Font font = new Font("Arial", Font.BOLD, 12);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridwidth = 1;

    gbc.gridx = 0;
    gbc.gridy = 0;
    JButton rollButton1 = new JButton("Player 1");
    rollButton1.setFont(font);
    rollButton1.addActionListener(new RollListener1());
    panel.add(rollButton1, gbc);

    gbc.gridx++;
    JButton rollButton2 = new JButton("Player 2");
    rollButton2.setFont(font);
    rollButton2.addActionListener(new RollListener2());
    panel.add(rollButton2, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    myLeftDie = new Dice();
    panel.add(myLeftDie, gbc);

    gbc.gridx++;
    myRightDie = new Dice();
    panel.add(myRightDie, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    JLabel label = new JLabel("Score");
    label.setFont(font);
    panel.add(label, gbc);

    gbc.gridx++;
    label = new JLabel("Score");
    label.setFont(font);
    panel.add(label, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    scoreField1 = new JTextField(4);
    scoreField1.setEditable(false);
    scoreField1.setFont(font);
    scoreField1.setHorizontalAlignment(JTextField.CENTER);
    panel.add(scoreField1, gbc);

    gbc.gridx++;
    scoreField2 = new JTextField(4);
    scoreField2.setEditable(false);
    scoreField2.setFont(font);
    scoreField2.setHorizontalAlignment(JTextField.CENTER);
    panel.add(scoreField2, gbc);

    return panel;
  }

  private class RollListener1 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      int position = model.getPlayer1position();
      int roll = myLeftDie.roll();

      model.incrementPlayer1position(roll);
      BoardSquare boardSquare = model.getBoardSquares().get(
          model.getPlayer1position());
      int score = boardSquare.execute(model.getPlayer1score());
      model.setPlayer1score(score);

      innerPanels[position].getPlayer1Label().setText(" ");
      innerPanels[model.getPlayer1position()].getPlayer1Label().
          setText("Player 1");
      scoreField1.setText(Integer.toString(score));
    }
  }

  private class RollListener2 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      int position = model.getPlayer2position();
      int roll = myRightDie.roll();

      model.incrementPlayer2position(roll);
      BoardSquare boardSquare = model.getBoardSquares().get(
          model.getPlayer2position());
      int score = boardSquare.execute(model.getPlayer2score());
      model.setPlayer2score(score);

      innerPanels[position].getPlayer2Label().setText(" ");
      innerPanels[model.getPlayer2position()].getPlayer2Label().
          setText("Player 2");
      scoreField2.setText(Integer.toString(score));
    }
  }

}



class InnerSquarePanel {

  private JLabel player1Label;
  private JLabel player2Label;

  private final JPanel panel;

  public InnerSquarePanel(BoardSquare boardSquare) {
    this.panel =  createInnerPanel(boardSquare);
  }

  private JPanel createInnerPanel(BoardSquare boardSquare) {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(boardSquare.getBackgroundColor());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 10, 10 ,10);
    gbc.gridwidth = 2;
    gbc.weightx = 1.0;

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.gridx = 0;
    gbc.gridy = 0;
    JLabel label = new JLabel(boardSquare.getText());
    label.setFont(new Font("Arial", Font.BOLD, 24));
    label.setForeground(boardSquare.getForegroundColor());
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label, gbc);

    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.gridwidth = 1;
    gbc.gridy++;
    player1Label = new JLabel(" ");
    player1Label.setForeground(boardSquare.getForegroundColor());
    player1Label.setHorizontalAlignment(JLabel.LEADING);
    player1Label.setVerticalAlignment(JLabel.BOTTOM);
    panel.add(player1Label, gbc);

    gbc.anchor = GridBagConstraints.LINE_END;
    gbc.gridx++;
    player2Label = new JLabel(" ");
    player2Label.setForeground(boardSquare.getForegroundColor());
    player2Label.setHorizontalAlignment(JLabel.TRAILING);
    player2Label.setVerticalAlignment(JLabel.BOTTOM);
    panel.add(player2Label, gbc);

    return panel;
  }

  public JLabel getPlayer1Label() {
    return player1Label;
  }

  public JLabel getPlayer2Label() {
    return player2Label;
  }

  public JPanel getPanel() {
    return panel;
  }

}

class DiceGameModel {

  private int player1position;
  private int player2position;
  private int player1score;
  private int player2score;

  private final List<BoardSquare> boardSquares;

  public DiceGameModel() {
    this.player1position = 0;
    this.player2position = 0;
    this.player1score = 0;
    this.player2score = 0;

    this.boardSquares = new ArrayList<>();
    boardSquares.add(new FinishSquare());
    boardSquares.add(new Square1());
    boardSquares.add(new Square2());
    boardSquares.add(new Square3());
    boardSquares.add(new Square4());
    boardSquares.add(new Square5());
    boardSquares.add(new Square6());
    boardSquares.add(new Square7());
  }

  public int getPlayer1position() {
    return player1position;
  }

  public void incrementPlayer1position(int increment) {
    this.player1position = (player1position + increment) %
        boardSquares.size();
  }

  public int getPlayer2position() {
    return player2position;
  }

  public void incrementPlayer2position(int increment) {
    this.player2position = (player2position + increment) %
        boardSquares.size();
  }

  public int getPlayer1score() {
    return player1score;
  }

  public void setPlayer1score(int player1score) {
    this.player1score = player1score;
  }

  public int getPlayer2score() {
    return player2score;
  }

  public void setPlayer2score(int player2score) {
    this.player2score = player2score;
  }

  public List<BoardSquare> getBoardSquares() {
    return boardSquares;
  }

}

class FinishSquare extends BoardSquare {

  public FinishSquare() {
    super("Finish!", 0, new Color(0, 203, 101), Color.WHITE);
  }

  @Override
  public int execute(int score) {
    return score;
  }

}

class Square1 extends BoardSquare {

  public Square1() {
    super("+20", 1, new Color(152, 254, 152), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score + 20;
  }

}

class Square2 extends BoardSquare {

  public Square2() {
    super("Try Again", 2, new Color(252, 252, 201), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score;
  }

}

class Square3 extends BoardSquare {

  public Square3() {
    super("-50", 3, new Color(254, 152, 152), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score - 50;
  }

}

class Square4 extends BoardSquare {

  public Square4() {
    super("Lost All", 4, new Color(252, 48, 99), Color.WHITE);
  }

  @Override
  public int execute(int score) {
    return 0;
  }

}

class Square5 extends BoardSquare {

  public Square5() {
    super("+30", 5, new Color(49, 253, 0), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score + 30;
  }

}

class Square6 extends BoardSquare {

  public Square6() {
    super("+10", 6, new Color(203, 254, 203), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score + 10;
  }

}

class Square7 extends BoardSquare {

  public Square7() {
    super("-10", 7, new Color(254, 203, 203), Color.BLACK);
  }

  @Override
  public int execute(int score) {
    return score - 10;
  }

}

abstract class BoardSquare {

  private final int position;

  private final Color backgroundColor;
  private final Color foregroundColor;

  private final String text;

  public BoardSquare(String text, int position,
      Color backgroundColor,  Color foregroundColor) {
    this.text = text;
    this.position = position;
    this.backgroundColor = backgroundColor;
    this.foregroundColor = foregroundColor;
  }

  public abstract int execute(int score);

  public int getPosition() {
    return position;
  }

  public String getText() {
    return text;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public Color getForegroundColor() {
    return foregroundColor;
  }

}
