package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

	/**
	* Class that represents an active playing field for Tetris.
	*@author Douglas Sigelbaum
	*/
public class PlayingField {

    /*
     * Game board. Colors are settled pieces, nulls are empty spots.
     * Each element in the array corresponds to a 2-D grid of "tiles"
     */
    private final Color[][] board;
    
    private Color emptyTile = Color.GRAY;

	public final int WIDTH, HEIGHT; //can be public because constants can't be changed
	 
	private int score;
	 
	private int level;
    /**
     * Create a playing field.
     * 
     * @param cols number of columns in the field (width)
     * @param rows number of rows in the width (height)
     */
	public PlayingField(int cols, int rows) {

		WIDTH = cols;
		HEIGHT = rows;
		level = 1;
		score = 0;
		
		board = new Color[WIDTH][HEIGHT]; //board arrange by x,y / width,height
        
    }

    /**
     * Checks if the tile at position is free.
     * 
     * @param x the x position to check
     * @param y the y position to check
     * @return true if piece is legal and vacant to move to.
     */
    public boolean isTileVacant(int x, int y) {
        if (x < 0 || x >= board.length || y >= board[0].length)
            return false;

        if (y < 0) //this implementation allows pieces to exist above the board
            return true;

        return board[x][y] == null; //if a slot is null, then it is considered empty
    }

    /**
     * Set the tile at a given position. Tiles wont be set if they are off-sceen
     * 
     * @param x the x position of the tile
     * @param y the y position of the tile
     * @param color color to set tile to.
     */
    public void setTileColor(int x, int y, Color color) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length)
            board[x][y] = color;
    }

    /**
     * Check the top row for a loss condition.
     * 
     * @return true if loss detected, false otherwise.
     */
    public boolean isGameOver() {
        for (int x = 0; x < board.length; x++) {
            if (board[x][0] != null) //check top row
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Clear the board.
     */
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
		  score = 0;
		  level = 1;
    }
	/**
	 * Gets the state of the board
	 *@return Color[][] state 2D array of current board
	 */
	public Color[][] getBoard() {
		Color[][] state = new Color[10][25];
		for(int i = 0; i < 25; i++) {
			for(int p = 0; p < 10; p++) {
				state[p][i] = board[p][i];
			}
		}
		return state;	
	}
	
	public int getLevel() {
	 	return level;
	}
	/**
	 * Checks if a full line is made my player
	 */
	public void checkForClears() {
		int lines = 0;
		for(int i = 0; i<HEIGHT; i++) {
			int count = 0;
			for(int p = 0; p < WIDTH; p++) {
				if(board[p][i] != null) {
					count++;
				}
			}
			if(count == WIDTH) {
				lines++;
				for(int j = i; j >= 0; j--) {
					for(int q = 0; q < WIDTH; q++) {
						if(j == 0) {
							board[q][j] = null;
						}
						else {
							board[q][j] = board[q][j-1];
						}
					}
				}
			}
		}
		switch(lines) {
			case 1:
				score+=(int)(100*(level-1)/4.0+100);
				break;
			case 2:
				score+=(int)(200*(level-1)/4.0+200);
				break;
			case 3:
				score+=(int)(400*(level-1)/4.0+400);
				break;
			case 4:
				score+=(int)(800*(level-1)/4.0+800);
				break;
		}
		if(score >= 1500*level) {
			level++;
		}
	}
	
	/**
	* Gets the score of the game
	*@return int score Score of the game
	*/
	public int getScore() {
		return score;
	}

    /**
     * Draw the game board
     * 
     * @param g Graphics context to draw on (from BoardPanel)
     * @param tileDim the dimensions of an individual tile
     */
    public void drawBoard(Graphics g, Dimension tileDim) {

		boolean drawGrid = false;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
               if (board[x][y] == null) {
                	g.setColor(emptyTile);
	               if (drawGrid) {
							g.draw3DRect(x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, true);
	               }
						else {
							g.drawRect(x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height);
						}
               }
					else {
	            	g.setColor(board[x][y]);
	            	g.fill3DRect(x * tileDim.width, y * tileDim.height, tileDim.width, tileDim.height, true);
	            }
            }
        }
    }
	 /**
	 * sets the score of the game
	 *@param int newScore New score
	 */
	 public void setScore(int newScore) {
	 	score = newScore;
	}
	/**
	 * sets the level of the game
	 *@param int newLevel New level
	 */
	public void setLevel(int newLevel) {
		score = newLevel;
	}
	
	/**
	 * Sets the element of the board.
	 *@param Color color Value of the element of the board
	 *@param int p Column
	 *@param int i Row
	 */
	public void setBoard(Color color, int p, int i) {
		board[p][i] = color;
	}
}
