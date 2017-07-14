package game;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

import java.util.Random;

/**
* JPanel that shows the next piece
*/
public class NextPiecePanel extends JPanel{
	private Timer timer;
	
	/**
	* Constructor for JPanel
	*/
	public NextPiecePanel() {
		setBackground(Color.black);
		timer = new Timer(500, new TimeListener());
		timer.start();
	}
	
	/**
	 * Paints panel
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BoardPanel.next.drawNext(g, BoardPanel.tileDimension);
   }
	
	/**
	* ActionListener class for timer.
	*/
	private class	TimeListener implements	ActionListener{	
	
	/**
	* actionPerformed method that repaints the next piece
		*/	
		public void	actionPerformed(ActionEvent event) {
			if	(BoardPanel.active != null) {
				repaint();
			}
		}
	}
}
	