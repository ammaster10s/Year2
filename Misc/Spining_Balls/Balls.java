package Misc.Spining_Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Balls extends JFrame {
    private int x = 50;
    private int y = 50;
    private int xSpeed = 5;
    private int ySpeed = 2;

    public Balls() {
        setTitle("Bouncing Ball");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                repaint();
            }
        });

        timer.start();
    }

    private void move() {
        x += xSpeed;
        y += ySpeed;

        // Check for collisions with the edges
        if (x < 0 || x > getWidth() - 20) {
            xSpeed = -xSpeed;
        }
        if (y < 0 || y > getHeight() - 20) {
            ySpeed = -ySpeed;
        }

        // Check for collisions with the corners
        if ((x <= 0 && y <= 0) || (x >= getWidth() - 20 && y <= 0) ||
                (x <= 0 && y >= getHeight() - 20) || (x >= getWidth() - 20 && y >= getHeight() - 20)) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 20, 20);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Balls().setVisible(true);
            }
        });
    }
}
