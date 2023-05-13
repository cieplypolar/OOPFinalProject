package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private int frames = 0;
    private long lastCheck = 0;
    private final int FPS = 120;
    public Game (){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; // nanoseconds
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        frames = 0;
        lastCheck = System.currentTimeMillis();
        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                this.frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                this.lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                this.frames = 0;
            }
        }
    }
}
