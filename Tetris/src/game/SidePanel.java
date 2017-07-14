package game;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import piece.CamelPiece;
import piece.DownStairPiece;
import piece.ElbowPiece;
import piece.LinePiece;
import piece.Piece;
import piece.ShoulderPiece;
import piece.SquarePiece;

import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
* Side panel of the game. Contains score, level, next piece, and save/load buttons.
*@author Douglas Sigelbaum
*@version 1.0
*/
public class SidePanel extends JPanel {
	
	public static JLabel scoreLabel;
	public static JLabel levelLabel;
	private final JLabel scoreTitle;
	private final JLabel nextPieceTitle;
	private final JLabel levelTitle;
	private NextPiecePanel nextPiece;
	private JButton loadButton;
	private JButton saveButton;
	private Scanner scan = new Scanner(System.in);
	/**
	 * Constructor for panel.
	 */
	public SidePanel() {

		scoreTitle = new JLabel("Score");
		scoreTitle.setFont(new Font("Arial", Font.BOLD, 36));
		scoreTitle.setForeground(Color.red);
		scoreLabel = new JLabel(""+BoardPanel.field.getScore());
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 36));
		scoreLabel.setForeground(Color.white);
		
		levelTitle = new JLabel("Level");
		levelTitle.setFont(new Font("Arial", Font.BOLD, 36));
		levelTitle.setForeground(Color.red);
		levelLabel = new JLabel("" + BoardPanel.field.getLevel());
		levelLabel.setFont(new Font("Arial", Font.BOLD, 36));
		levelLabel.setForeground(Color.white);
		
		nextPieceTitle = new JLabel("Next Piece");
		nextPieceTitle.setFont(new Font("Arial", Font.BOLD, 36));
		nextPieceTitle.setForeground(Color.red);
		nextPiece = new NextPiecePanel();
		loadButton = new JButton("Load");
		saveButton = new JButton("Save");		
		setBackground(Color.black);
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(8,1));
		grid.setBackground(Color.black);
		grid.add(scoreTitle);
		grid.add(scoreLabel);
		grid.add(levelTitle);
		grid.add(levelLabel);
		grid.add(nextPieceTitle);
		grid.add(nextPiece);
		grid.add(loadButton);
		grid.add(saveButton);
		add(grid);
		
		loadButton.addActionListener(new LoadListener());
		
		saveButton.addActionListener(new SaveListener());
		
		setPreferredSize(new Dimension(400,500));
	}
	
	/**
	 * Takes in color and outputs an int value.
	 *@param Color color
	 *@param int index number that corresponds to the color.
	 */
	public int colorData(Color color) {
	 	int index = 1;
			if(color == null)
				index = 0;
			else if(color.equals(Color.cyan))
				index = 1;
			else if(color.equals(Color.yellow))
				index = 2;
			else if(color.equals(Color.green))
				index = 3;
			else if(color.equals(Color.orange))
				index = 4;
			else if(color.equals(Color.blue))
				index = 5;
			else if(color.equals(Color.red))
				index = 6;
			else if(color.equals(Color.magenta))
				index = 7;
		return index;
	}
	
	/**
	 * Takes in an integer, outputs a color that corresponds to the integer.
	 * @param int Number
	 * @return Color Color that corresponds to integer parameter.
	 */
	public Color colorOut(int i) {
		Color color = null;
		if(i == 1) color = Color.cyan;
		else if(i == 2) color = Color.yellow;
		else if(i == 3) color = Color.green;
		else if(i == 4) color = Color.orange;
		else if(i == 5) color = Color.blue;
		else if(i == 6) color = Color.red;
		else if(i == 7) color = Color.magenta;
		return color;
	}
	
	/**
	 * Takes in an integer and outputs a piece that corresponds to that integer.
	 * @param int Number
	 * @return Piece that corresponds to number parameter
	 */
	public Piece pieceOut(int i) {
		Piece piece	= new	LinePiece(BoardPanel.field, BoardPanel.field.WIDTH / 2, -2);
		switch(i) {
			case 1:
				piece	= new	LinePiece(BoardPanel.field, BoardPanel.field.WIDTH / 2, -2);
				break;
			case 2:
				piece	= new	SquarePiece(BoardPanel.field, BoardPanel.field.WIDTH /	2,	-1);
				break;
			case 3:
				piece	= new	UpStairPiece(BoardPanel.field,	BoardPanel.field.WIDTH	/ 2, -1);
				break;
			case 4:
				piece	= new	ElbowPiece(BoardPanel.field,	BoardPanel.field.WIDTH	/ 2, -1);
				break;
			case 5:
				piece	= new	ShoulderPiece(BoardPanel.field, BoardPanel.field.WIDTH /	2,	-1);
				break;
			case 6:
				piece	= new	DownStairPiece(BoardPanel.field, BoardPanel.field.WIDTH / 2, -1);
				break;
			case 7:
				piece	= new	CamelPiece(BoardPanel.field,	BoardPanel.field.WIDTH	/ 2, -1);
				break;
		}
		return piece;
	}


	/**
	 * Action listener for 'save' button
	 */
	
	private class SaveListener implements ActionListener {
		/**
		 * Actionperformed function for when user clicks save.
		 */	
		public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Enter a file to save to: ");
					String filename = scan.nextLine();
					writeFile(filename);
				}
				catch(IOException er) {
					System.out.println(er.getMessage());
				}
		}
		/**
		 * Writes data to a file
		 *@param String filename File name to save the data to
		 */
		public void writeFile(String filename) throws IOException {
			FileWriter out = new FileWriter(filename);
			String data = "";
			BufferedWriter output = new BufferedWriter(out);
			data += (BoardPanel.field.getScore()+ " ");
			data += (BoardPanel.field.getLevel()+ " ");
			for(int i = 0; i < 25; i++) {
				for(int p = 0; p < 10; p++) {
					data += (colorData(BoardPanel.field.getBoard()[p][i])+" ");
				}
			}
			data += BoardPanel.active.getRotationIndex() + " ";
			data += colorData(BoardPanel.active.getColor()) + " ";
			data += BoardPanel.active.getX() + " ";
			data += BoardPanel.active.getY() + " ";
			data += colorData(BoardPanel.next.getColor());
			output.write(data);
			output.close();
		}
	}
	/**
	 * Action listener for 'load' button.
	 */
	private class LoadListener implements ActionListener {
		
		/**
		 * Actionperformed function for when user clicks load.
		 */	
		public void actionPerformed(ActionEvent e) {
			try {
				readFile();
			}
			catch(IOException er) {
				System.out.println(er.getMessage());
			}
		}
		/**
		 * Reads data from file
		 *@param String filename File name to to load data from
		 */
		public void readFile() throws IOException {
			System.out.println("Enter a file to load:");
			String filename = scan.nextLine();
			FileReader in = new FileReader(filename);
			BufferedReader input = new BufferedReader(in);
			String[] dataString = (input.readLine()).split(" ");
			int[] data = new int[dataString.length];
			for(int i = 0; i < dataString.length; i++) {
				data[i] = Integer.parseInt(dataString[i]);
			}
			int n = 0;
			BoardPanel.field.setScore(data[n]); n++;
			BoardPanel.field.setLevel(data[n]); n++;
			for(int i = 0; i < 25; i++) {
				for(int p = 0; p < 10; p++) {
					BoardPanel.field.setBoard(colorOut(data[n]),p,i);
					n++;
				}
			}
 
			BoardPanel.active.setRotationIndex(data[n]); n++;
			BoardPanel.active = pieceOut(data[n]); n++;
			BoardPanel.active.setX(data[n]); n++;
			BoardPanel.active.setY(data[n]); n++;
			BoardPanel.next = pieceOut(data[n]);
			in.close();
		}
	}
}