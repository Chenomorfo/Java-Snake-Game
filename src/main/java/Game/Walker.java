/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cheno
 */
public class Walker implements Runnable {

    SnakePanel snake;

    public Walker(SnakePanel snake) {
        this.snake = snake;
    }

    boolean walking = true;

    @Override
    public void run() {
        while (walking) {

            snake.forward();
            snake.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Walker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void stop() {
        this.walking=false;
    }
}
