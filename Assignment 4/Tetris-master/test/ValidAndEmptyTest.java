import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.Clock;
import org.psnbtech.Tetris;
import org.psnbtech.TileType;

public class ValidAndEmptyTest 
{

	//making tetrisObject behave as a field variable so all methods can access it.
	private Tetris tetrisObject;
	
	//creating an initializing tetrisObject
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

	//adding a piece to the board and testing if the location is occupied or not
	@Test
	public void shouldBeValidAndEmpty() 
	{
		//first reset the game to remove any anomalies
		tetrisObject.resetGame();
		//adding a piece to the board
		tetrisObject.board.addPiece((TileType.TypeI), 1, 2, 1);
		//checking to see if adding a different piece to the board is possible (which it should be
		//since we only added one differing location piece).
		assertEquals(true, tetrisObject.board.isValidAndEmpty((TileType.TypeI), 4, 5, 1));
	}
	
	@Test
	public void shouldNotBeValidAndEmpty()
	{
		//first reset the game to remove any anomalies
		tetrisObject.resetGame();
		//adding a piece to the board
		tetrisObject.board.addPiece((TileType.TypeI), 2, 3, 1);
		//checking to see if the piece we just added to the board makes the isValidAndEmpty function return false
		assertEquals(false, tetrisObject.board.isValidAndEmpty((TileType.TypeI), 2, 3, 1));
	}

}
