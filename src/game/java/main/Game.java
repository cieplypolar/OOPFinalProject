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
private class runFrame extends Thread{
    @Override
    public void run(){
        double timePerFrame = 1000000000.0 / FPS; // nanoseconds

        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();

           if(now-lastFrame>=timePerFrame){
            gamePanel.repaint();
            lastFrame=now;
            frames++;
           }



            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

}
private class runTick extends Thread{
        @Override
        public void run() {
            double timePerTick = 1000000000.0 / tick;
            long now = System.nanoTime();
            long lastTick = System.nanoTime();
            int ticks = 0;
            double deltaTick =0;
            long lastCheck = System.currentTimeMillis();

            while(true){

                now = System.nanoTime();
                deltaTick+=(now - lastTick) / timePerTick;
                lastTick=now;
                if(deltaTick>=1){
                    // update() TODO
                     ticks++;
                     deltaTick--;
                }

                if (System.currentTimeMillis() - lastCheck >= 1000) {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("TICK: " + ticks);
                    ticks = 0;
                }

            }

        }


    }

    @Override
    public void run() {
        Thread f = new runFrame();
        Thread t = new runTick();
        f.start();
        t.start();
    }
}
