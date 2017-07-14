package game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

import piece.CamelPiece;
import piece.DownStairPiece;
import piece.ElbowPiece;
import piece.LinePiece;
import piece.Piece;
import piece.ShoulderPiece;
import piece.SquarePiece;

import java.util.Random;

/**
 *	The game	board. Will	start	the game, and respond to user	input.
 *	
 *	@version	2.0
 */
@SuppressWarnings("serial")
public class BoardPanel	extends JPanel{

	 public static	PlayingField field;
	 public static	Piece	active;
	 public static	Piece	next;
	 private final Timer	timer;
	 public static	Dimension tileDimension	= new	Dimension(20,20);
	 
	 private	Random rand	= new	Random();
	 
	 /**
	  * Creates	a game board and starts	the game.
	  * 
	  * @param cols number of columns in the game (width)
	  * @param rows number of rows in the game (height)
	  */
	 public BoardPanel(int cols, int	rows)	{
		  setPreferredSize(new Dimension(cols * tileDimension.width, rows	* tileDimension.height));
		  setFocusable(true);
		  addKeyListener(new	KeyController());
		  field = new PlayingField(cols,	rows);
		  timer = new Timer(500, new FallListener());
		  next =	randomPiece();
		  setBackground(Color.black);
		  generateNewPiece(); //create the active	piece
	 }

	 /**
	  * Start the Tetris	game.
	  */
	 public void start() {
		  timer.start();
	 }

	 /**
	  * Set active	to	a new, random piece.
	  */
	 private	Piece	randomPiece() {
		  Piece piece = null;
		  switch	(rand.nextInt(7))
		  {
			//start the	pieces off with negative y	values so they	enter
			//starting at the	bottom of the piece rather	than the	top
				case 0:
					piece	= new	LinePiece(field, field.WIDTH / 2, -2);
					break;
				case 1:
					piece	= new	SquarePiece(field, field.WIDTH /	2,	-1);
					break;
				case 2:
					piece	= new	ElbowPiece(field,	field.WIDTH	/ 2, -1);
					break;
				case 3:
					piece	= new	ShoulderPiece(field,	field.WIDTH	/ 2, -1);
					break;
				case 4:
					piece	= new	DownStairPiece(field, field.WIDTH /	2,	-1);
					break;
				case 5:
					piece	= new	UpStairPiece(field, field.WIDTH / 2, -1);
					break;
				case 6:
					piece	= new	CamelPiece(field,	field.WIDTH	/ 2, -1);
					break;
			}
			return piece;
	 }
	 
	 private	void generateNewPiece()	{
		active =	next;
		next = randomPiece();
	}

	 /**
	  * Move	the current	piece	down and	checks for a win/loss or placement
	  * situation.	If	the piece fails to move	down anymore (attemptMove returns false),
	  * then	the piece settles.
	  */
	private void moveDownActive() {

	  /* Move the piece down */
		if (!active.attemptMove(active.getX(), active.getY() +	1)) {
			active.settlePiece();
			field.checkForClears();
			SidePanel.scoreLabel.setText(""+field.getScore());
			SidePanel.levelLabel.setText(""+field.getLevel());
			timer.setDelay(timerDelay());
			generateNewPiece();
		}

		/*	Check	if	the game	is	over */
		if (field.isGameOver()) {
			timer.stop();
			int choice = JOptionPane.showConfirmDialog(this, "Play Again?", "You	Lose!", JOptionPane.YES_NO_OPTION);
				
			if	(choice == JOptionPane.YES_OPTION) {
				field.resetBoard();
				generateNewPiece();
				timer.restart();
			}
			else {
				System.exit(0);
		   }
			
			SidePanel.scoreLabel.setText(""+field.getScore());
			SidePanel.levelLabel.setText(""+field.getLevel());
			timer.setDelay(timerDelay());
		  
		  }
	 }
	 
	 /**
	 * Returns delay of timer.
	 *@return delay of timer
	 */
	public int timerDelay() {
		return (500-(field.getLevel()-1)*15);
	}
	
	/**
	* Paints the board. Makes tile dimension proportional to size of board
	*@param Graphics g
	*
	*/
	 public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  //tileDimension.setSize(getWidth()/10,getHeight()/25);
		/*	Pass the	graphics	object and the	current tileDimension */
		  field.drawBoard(g,	tileDimension);	//	The field knows how many rows
											//	and cols	of	tiles	it	has, therefore	it
											//	only needs to know the dimension
											//	of	each tile

		  active.draw(g, tileDimension);
	 }

	 /**
	  * The timer action. Each	time step, move the piece down.
	  */
	 
	 private	class	FallListener implements	ActionListener
	 {		
	 /**
	 * If there is a current piece, it will move it down and repaint.
	 *@param ActionEvent The actual event.
	 */
		public void	actionPerformed(ActionEvent event) {
			if	(active != null)
				moveDownActive();
			repaint();
		}
	}

	 /**
	  * Handle keystrokes and repaint.
	  * 
	  * @author	eric
	  * 
	  */
	private class KeyController extends KeyAdapter	{

	  @Override
	 	public void keyPressed(final KeyEvent key)	{
			if	(active != null) {

				 int oldX =	active.getX();
				 int oldY =	active.getY();

				 switch (key.getKeyCode())	{
					  case KeyEvent.VK_RIGHT:
							active.attemptMove(oldX	+ 1, oldY);	//move right
							break;
					  case KeyEvent.VK_LEFT:
							active.attemptMove(oldX	- 1, oldY);	//move left
							break;
					  case KeyEvent.VK_SPACE:
							active.attemptRotation(); //rotate
							break;
					  case KeyEvent.VK_DOWN:
							moveDownActive();	//move down
							break;
					  case KeyEvent.VK_ESCAPE:	//quit game
							System.exit(0);
							break;
				 }

				 repaint();
			}
	  }
 }
}
