package game;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import assets.StateVars;
import entity.Entity;
import entity.Entities;
import env.Environment;
import env.Environments;
import window.GUI;

public class Sim implements Runnable
{
    // static flags
    public static boolean noGUI = false;

    // Global variable declaration.
    private final String title;
    private final int width, height;
    private boolean running = false;

    // Global object declarations.
    private GUI ui;
    private Canvas cv;
    private Thread thread;
    private Environment env;
    private Console console;

    // belowKarman vars

    // beyondKarman vars: release module

    // beyondKarman vars: orbit motion

    // lunEntry vars

    // postLunEntry vars

    // nearLunSurface vars

    public Sim(String title, int width, int height)
    {
        ui = null;
        cv = null;
        env = null;
        console = new Console();
        console.writeln("Systems online");
        this.title = title;
        this.width = width;
        this.height = height;
        ui = new GUI(title, width, height);
        if (Sim.noGUI) return;
        cv = ui.create().getCanvas();
        Entities.initiate();
        Environments.initiate();
    }

    // Method responsible for updating values.
    private void update()
    {
        switch (StateVars.activity) {
            case BELOW_KARMAN: {
                if (env != null) break;
                env = Environments.skyGrad(cv, 0, height - Environments.skyGrad(cv).getHeight() +10);
                final int onPadY = height - Entities.lPad().getHeight();
                Entity lPad = Entities.lPad(0, onPadY);
                Entity tower = Entities.tower(width /2 - Entities.tower().getWidth() *2 +32, onPadY - Entities.tower().getHeight());
                lPad.attatch(tower);
                final int onS1Y = onPadY - Entities.rktS1().getHeight();
                final int onS2Y = onS1Y - Entities.rktS2().getHeight();
                final int onS3Y = onS2Y - Entities.rktS3().getHeight();
                final int rktPosnX = width /2 - 16;
                Entity rktS1 = Entities.rktS1(rktPosnX, onS1Y);
                Entity rktS2 = Entities.rktS2(rktPosnX, onS2Y);
                Entity rktS3 = Entities.rktS3(rktPosnX, onS3Y);
                Entity rktCone = Entities.rktCone(rktPosnX, onS3Y - Entities.rktCone().getHeight());
                Entity rktFlameS3 = Entities.rktFlame(width /2, onS3Y + Entities.rktS3().getHeight());
                Entity rktFlameS2 = Entities.rktFlame(width /2, onS2Y + Entities.rktS2().getHeight());
                Entity rktFlameS1 = Entities.rktBurn(rktPosnX, onS1Y + Entities.rktS1().getHeight());
                rktCone.attatch(rktS3);
                rktS3.attatch(rktFlameS3);
                rktS3.attatch(rktS2);
                rktS2.attatch(rktFlameS2);
                rktS2.attatch(rktS1);
                rktS1.attatch(rktFlameS1);
                env.addEntity(rktCone);
                // lPad.flush();
                env.focusOn(rktCone);
                rktFlameS1.hide();
                break;
            }
            default: System.err.println("unimplemented");
        }
    }

    // Method is responsible for drawing on canvas.
    private void render()
    {
        if (env != null) {
            env.defForceUpdate();
            env.render();
        }
    }

    /**
     * Abstract method: Runs the code required to be run on thread
     */
    @Override
    public void run()
    {
        double deltaUpdate = 0,
               deltaFrames = 0;
        long lastTime = System.nanoTime();
        double timePerFrame = 1000000000 / StateVars.FPS;
        double timePerUpdate = 1000000000 / (StateVars.UPS * (StateVars.warpF > 0 ? StateVars.warpF : 1));
        while (running && StateVars.running) {
            long now = System.nanoTime();
            deltaUpdate += (now - lastTime) / timePerUpdate;
            deltaFrames += (now - lastTime) / timePerFrame;
            lastTime = now;
            // Value updater
            if (deltaUpdate >= 1 && StateVars.warpF > 0) {
                // Calls update() to update values.
                update();
                deltaUpdate--;
            }
            // Frames per second, higher frames makes display smooth
            if (deltaFrames >= 1 && !noGUI) {
                // Calls render() to draw to screen.
                render();
                deltaFrames--;
            }
        }
        console.stop();
        this.stop();
    }

    // Method responsible for starting thread.
    public synchronized void start()
    {
        if (running) return;
        StateVars.running = running = true;
        thread = new Thread(this);
        console.start();
        thread.start();
    }

    // Method responsible for stopping thread
    public synchronized void stop()
    {
        if (!running) return;
        StateVars.running = running = false;
        console.stop();
        ui.close();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
