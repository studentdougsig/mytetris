package game;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import javax.swing.JFrame;
import java.io.*;
/**
 * Runs a game of Tetris.
 * 
 * @author Eric
 * @version 1.0
 */
public class Tetris {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CS 1331 Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}