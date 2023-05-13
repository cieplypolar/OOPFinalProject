package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS = 120;
    private final int tick = 200; //how many ticks per second
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
/*private class runFrame extends Thread{ // **************** alternative version ****************
        @Override
        public void run(){
            double timePerFrame = 1000000000.0 / FPS; // nanoseconds

            long lastFrame = System.nanoTime();
            long now = System.nanoTime();

            int frames = 0;
            double deltaFrame =0;
            long lastCheck = System.currentTimeMillis();

            while (true) {

                now = System.nanoTime();
                deltaFrame+=(now - lastFrame) / timePerFrame;
                lastFrame=now;

                if(deltaFrame>=1){
                   gamePanel.repaint();
                    frames++;
                    deltaFrame--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        }

        }
        */



    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; // nanoseconds
        double timePerTick = 1000000000.0 / tick;
        long now = System.nanoTime();
        long lastTick = System.nanoTime();
        long lastFrame = System.nanoTime();
        int frames = 0;
        int ticks = 0;

        long lastCheck = System.currentTimeMillis();





        while (true) {

            now = System.nanoTime();

            if(now-lastFrame>=timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
                frames++;
            }
            if(now - lastTick>= timePerTick){
                // update()
                lastTick=now;
                ticks++;

            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " TICK: " + ticks);
                frames = 0;
                ticks=0;
            }
        }


    }
}
