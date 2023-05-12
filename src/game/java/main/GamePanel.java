package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private float xDelta = 0;
    private float yDelta = 200;
    private float xDir = 1f;
    private float yDir = 1f;
    private BufferedImage img;
    public GamePanel(){
        setPanelSize();
        this.mouseInputs = new MouseInputs(this);
        importImg();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/adventurer-Sheet.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(640, 400);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g ){
        super.paintComponent(g);

        g.drawImage(img, 0, 0, null);

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
