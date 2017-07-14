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
* Main panel that holds the BoardPanel on one side and the SidePanel on the other.
*@author Douglas Sigelbaum
*@version 1.0 4/21/2012
*/
public class MainPanel extends JPanel {

	private BoardPanel boardPanel;
	private SidePanel sidePanel;
	public MainPanel() {
		boardPanel = new BoardPanel(10, 25);
		sidePanel = new SidePanel();
		add(boardPanel,BorderLayout.WEST);
		add(sidePanel,BorderLayout.EAST);
		boardPanel.start();
		Timer timer = new Timer(10, new TimeListener());
		timer.start();
	}
	
		/**
		* Action listener for timer. Updates the size of the panels to adjust to the
			size of the panel.
		*/
	private class TimeListener implements	ActionListener {
	
	/**
	* actionPerformed function for the timer listener.
	*/	
		public void	actionPerformed(ActionEvent event) {
				//remove(boardPanel);
				//remove(sidePanel);
				//boardPanel.setSize(getWidth()/2-10,getHeight());
				//sidePanel.setSize(getWidth()/2-10,getHeight());
				//add(boardPanel,BorderLayout.WEST);
				//add(sidePanel,BorderLayout.EAST);
				boardPanel.requestFocus();
		}
	}
}