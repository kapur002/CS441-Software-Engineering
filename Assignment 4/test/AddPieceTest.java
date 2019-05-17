import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.Clock;
import org.psnbtech.Tetris;
import org.psnbtech.TileType;

public class AddPieceTest 
{
	//making tetrisObject behave as a field variable so all methods can access it.
	private Tetris tetrisObject;
	
	////creating and initializing tetrisObject
	@Before
	public void setUp() throws Exception 
	{
		tetrisObject = new Tetris();
		tetrisObject.random = new Random();
		tetrisObject.gameSpeed = 1.0f;
		tetrisObject.logicTimer = new Clock(tetrisObject.gameSpeed);
	}

	//after tests, tetrisObject should clear the board & be set to null
	@After
	public void tearDown() throws Exception 
	{
		tetrisObject.board.clear();
		tetrisObject = null;
	}

	//adding a piece & then checking to see if the piece we added exists
	@Test
	public void shouldAddPiece() 
	{
		//first reset the game to remove any anomalies
		tetrisObject.resetGame();
		
		//adding a piece to the board
		tetrisObject.board.addPiece(TileType.values()[0], 1, 1, 0);
		
		//check to see if the piece we added exists
		assertEquals(TileType.values()[0], tetrisObject.board.getTile(1,2));

	}
	
	//test to see if piece is out of bounds - should not be added because the array should render that it is out of bounds
	@Test
	public void pieceShouldBeOutOfBounds()
	{
		
		//first reset the game to remove any anomalies
		tetrisObject.resetGame();
		
		//adding a piece to the board
		tetrisObject.board.addPiece(TileType.values()[0], -1, -2, 0);
		
		//check to see if the piece we added exists. In this case, there should be an exception thrown. 
		assertEquals(TileType.values()[0], tetrisObject.board.getTile(-1,-1));
	}
	

}
