/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cheno
 */
public class SnakePanel extends JPanel {

    Color snakeColor = Color.blue, foodColor = Color.RED;

    int maxSize, size, count, center;

    List<int[]> snake = new ArrayList<>();

    String direction = "right", nextDir = "right";

    Thread wire;
    Walker walker;

    int[] food = new int[2];

    public SnakePanel(int maxSize, int count) {

        this.maxSize = maxSize;
        this.count = count;
        this.size = maxSize / count;
        this.center = maxSize % count;

        int[] a = {count / 2 - 1, count / 2 - 1};
        int[] b = {count / 2, count / 2 - 1};

        snake.add(a);
        snake.add(b);

        SpawnFood();

        walker = new Walker(this);
        wire = new Thread(walker);
        wire.start();

    }

    @Override
    public void paint(Graphics painter) {

        super.paint(painter);
        painter.setColor(snakeColor);
        //painting snake
        for (int[] par : snake) {
            painter.fillRect(center / 2 + par[0] * size, center / 2 + par[1] * size, size - 1, size - 1);
        }

        //Head
        painter.setColor(Color.WHITE);
        painter.fillRect(center / 2 + snake.get(snake.size() - 1)[0] * size, center / 2 + snake.get(snake.size() - 1)[1] * size, size - 1, size - 1);

        //painting food
        painter.setColor(foodColor);
        painter.fillRect(center / 2 + food[0] * size, center / 2 + food[1] * size, size - 1, size - 1);

    }

    public void forward() {
        checkMove();
        int[] lastPos = snake.get(snake.size() - 1);
        int addX = 0, addY = 0;

        switch (direction) {
            case "left":
                addX = -1;
                break;
            case "right":
                addX = 1;
                break;
            case "up":
                addY = -1;
                break;
            case "down":
                addY = 1;

                break;
            default:
                throw new AssertionError();
        }
        int[] newPos
                = {
                    Math.floorMod(lastPos[0] + addX, count),
                    Math.floorMod(lastPos[1] + addY, count),};

        boolean crash = false;
        for (int i = 0; i < snake.size(); i++) {
            if (newPos[0] == snake.get(i)[0] && newPos[1] == snake.get(i)[1]) {

                crash = true;
                break;
            }
        }

        if (crash) {
            JOptionPane.showMessageDialog(this, "You loose the game");
            wire.stop();
        } else {
            if (newPos[0] == food[0] && newPos[1] == food[1]) {
                snake.add(newPos);
                SpawnFood();
            } else {
                snake.add(newPos);
                snake.remove(0);
            }
        }
    }

    public void SpawnFood() {

        boolean exists = false;
        int a = (int) (Math.random() * 10);
        int b = (int) (Math.random() * 10);

        for (int[] par : snake) {
            if (par[0] == a && par[0] == b) {

                exists = true;
                SpawnFood();
                break;

            }
        }
        if (!exists) {
            this.food[0] = a;
            this.food[1] = b;

        }

    }

    public void move(String newDir) {

        if ((this.direction.equals("right") || this.direction.equals("left")) && (newDir.equals("up") || newDir.equals("down"))) {

            this.nextDir = newDir;
        }
        if ((this.direction.equals("up") || this.direction.equals("down")) && (newDir.equals("left") || newDir.equals("right"))) {
            this.nextDir = newDir;
        }

    }

    public void checkMove() {

        this.direction = this.nextDir;

    }

}
