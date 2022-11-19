/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author cheno
 */
public class Panel extends JPanel {

    Color background = Color.gray;
    int maxSize, size, count, center;

    public Panel(int maxSize, int count) {

        this.maxSize = maxSize;
        this.count = count;
        this.size = maxSize / count;
        this.center = maxSize % count;

    }

    @Override
    public void paint(Graphics painter) {

        super.paint(painter);
        painter.setColor(background);

        //Paint x,y snake
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                
                painter.fillRect(center / 2 + i * size,
                        center / 2 + j * size,
                        size - 1,
                        size - 1);
            }
        }

    }

}
