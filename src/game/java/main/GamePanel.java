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
    private BufferedImage[] adventurerIdleSword;
    private int aniTick, aniIndex, aniSpeed = 20;
    public GamePanel(){
        setPanelSize();
        this.mouseInputs = new MouseInputs(this);
        loadAnimations();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        adventurerIdleSword = new BufferedImage[4];
        for (int i = 0; i < adventurerIdleSword.length; ++i){
            adventurerIdleSword[i] = importImg("/idlesword/adventurer-idle-2-0" + i + ".png");
        }
    }

    private BufferedImage importImg(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(640, 400);
        setPreferredSize(size);
    }

    private void updateAnimation(){
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            aniIndex %= adventurerIdleSword.length;
        }
    }

    public void paintComponent(Graphics g ){
        super.paintComponent(g);

        updateAnimation();

        g.drawImage(adventurerIdleSword[aniIndex], (int) xDelta, (int) yDelta, null);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }
    public void changeYDelta(int value) {
        this.yDelta += value;
    }
    public void setPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
}
