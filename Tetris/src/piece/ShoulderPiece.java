package piece;
import game.PlayingField;

import java.awt.Color;
import java.awt.Point;

/**
 * A Tetris piece model for the Shoulder piece
 * 
 * @version 2.0
 * @author Douglas Sigelbaum
 */
public class ShoulderPiece extends Piece{

	private final static boolean[][] rotation0;
	private final static boolean[][] rotation1;
	private final static boolean[][] rotation2;
	private final static boolean[][] rotation3;

    static {
		//This model would actually be drawn like:
        //  0 1 0 0
        //  0 1 0 0
        //  0 1 0 0
        //  0 1 0 0
        //since the first index is the x offset, not y
		  rotation3 = new boolean[][] {
          {true,  false, false, false},
          {true,  true,  true,  false},
          {false, false, false, false},
          {false, false, false, false} 
        };
        
        rotation2 = new boolean[][] {
		    {true,  true, false, false},
          {true,  false, false, false},
          {true,  false,  false, false},
          {false, false, false, false}                               
        };
        
        rotation1 = new boolean[][] {
          {true,  true,  true,  false},
          {false, false, true, false},
          {false, false, false, false},
          {false, false, false, false} 
        };
        
        rotation0 = new boolean[][] {
			 {false, true,  false, false},
          {false, true,  false, false},
          {true,  true,  false, false},
          {false, false, false, false}                               
        };
    }
    
    /*
     * The rotation model of the piece
     * If the tile does exist in that position
     * (for that rotation), value = true
     * else false
     * The model is inverted when visualizing as a grid:
     * [rotationIndex][x offset][y offset]
     */
    private final static boolean[][][] model = {rotation0, rotation1, rotation2, rotation3};
        
    public ShoulderPiece(PlayingField field, int x, int y) {
        super(field, x, y, model, Color.BLUE);
    }
}
