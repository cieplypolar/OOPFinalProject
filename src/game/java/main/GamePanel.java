package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private final Random random;
    private float xDelta = 0;
    private float yDelta = 200;
    private float xDir = 1f;
    private float yDir = 1f;
    private Color color = new Color(37, 48, 203);
    public GamePanel(){
        this.random = new Random();
        this.mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void paintComponent(Graphics g ){
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 50, 50);
    }

    private void updateRectangle() {
        this.xDelta += this.xDir;
        if (this.xDelta > 400 || this.xDelta < 0) {
            this.xDir *= -1;
            this.color = getRandColor();
        }
        this.yDelta += this.yDir;
        if (this.yDelta > 400 || this.yDelta < 0) {
            this.yDir *= -1;
            this.color = getRandColor();
        }
    }

    private Color getRandColor() {
        int r = random.nextInt(0,255);
        int g = random.nextInt(0,255);
        int b = random.nextInt(0,255);
        return new Color(r, g, b);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
}
