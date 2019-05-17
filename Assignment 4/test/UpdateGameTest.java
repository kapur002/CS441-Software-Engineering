import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.psnbtech.Clock;
import org.psnbtech.TileType;
import org.psnbtech.Tetris;

public class UpdateGameTest 
{
	

	//making tetrisObject behave as a field variable so all methods can access it.
	private Tetris tetrisObject;
	
	//creating and initializing tetrisObject
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

	//testing the following function: public void updateGame()  
	// testing game speed after a single update
	// resetGame will set the game speed to 1.0
	// so after updating the game, the gameSpeed because 0.35 + initial gameSpeed
	// thus, gameSpeed should be 1.035
	@Test
	public void gameSpeedShouldBe1PT035() 
	{
		//first reset the game to remove any anomalies
		tetrisObject.resetGame();
		//generate an object
		tetrisObject.spawnPiece();
		//set the current row to 20
		tetrisObject.currentRow = 20;
		//call updateGame
		tetrisObject.updateGame();
		//check if gameSpeed matches actual speed
		assertEquals(1.035f, tetrisObject.gameSpeed, 0.0f);
	}
	
	// testing the following function: public void updateGame()  
	// this will test what the drop cool down is after a single update
	// calling the updateGame function, cool down is set to 25. Thus, this method should ensure that cooldown is
	// in fact 25.
	@Test
	public void dropCooldownShouldBe25() 
	{
		tetrisObject.resetGame();
		tetrisObject.spawnPiece();
		tetrisObject.currentRow = 20;
		tetrisObject.updateGame();
		assertEquals(25, tetrisObject.dropCooldown);
	}
		
	// testing the following function:
	// public int checkLines()  
	// this will test how many lines on board after reset. There should be zero lines.
	@Test
	public void linesShouldBeZero() 
	{
		//make sure to reset the game to remove any anomalies
		tetrisObject.resetGame();
		//generate an object
		tetrisObject.spawnPiece();
		//set the current row to 20
		tetrisObject.currentRow = 20;
		//after clearing, lines should be zero
		assertEquals(0, tetrisObject.board.checkLines());
	}
	
	// testing the following function: public void updateGame()
	//tests upon reset what the score should be to make sure score is resetting after games. Expecting score to be zero
	@Test
	public void newScoreShouldBeZero() 
	{
		//This will make sure the game resets to remove any anomalies
		tetrisObject.resetGame();
		//generate a piece
		tetrisObject.spawnPiece();
		//set the current row to 20
		tetrisObject.currentRow = 0;
		//update the game
		tetrisObject.updateGame();
		//make sure that current score is zero
		assertEquals(0, tetrisObject.score);
	}
	
	// testing the following function: public void updateGame()
		//tests upon reset what the score should after the first piece lands at the bottom. Expecting score to be zero
		//beacuse the user only scores on cleared rows.
	@Test
		public void FirstPieceLandsShouldBeZero() 
		{
			//This will make sure the game resets to remove any anomalies
			tetrisObject.resetGame();
			//generate a piece
			tetrisObject.spawnPiece();
			//set the current row to 20
			tetrisObject.currentRow = 20;
			//update the game
			tetrisObject.updateGame();
			//make sure that current score is zero
			assertEquals(0, tetrisObject.score);
		}
	
	// testing the following function: public void updateGame()
	// test if the new score is as expected after 1 update
	// should clear 4 rows and be 800 points
	@Test
	public void updateScoreShouldBe800() 
	{
		tetrisObject.resetGame();
		tetrisObject.spawnPiece();
		tetrisObject.currentRow = 20;
		// fill the entire bottom 4 rows with pieces
		for(int i = -2 ; i <= 7 ; i++)
		{
			tetrisObject.board.addPiece(TileType.TypeI, i, 18, 1);
		}
		
		tetrisObject.updateGame();
		assertEquals(800, tetrisObject.score);
	}
	
	// testing the following function: public void updateGame()
	// test if the level value is as expected after one update
	@Test
	public void levelShouldBe() 
	{
		tetrisObject.resetGame();
		tetrisObject.spawnPiece();
		tetrisObject.currentRow = 20;
		tetrisObject.updateGame();
		int expectedLevel = (int)(1.035f * 1.70f);
		assertEquals(expectedLevel, tetrisObject.level);
	}
	
	// testing the following function: public void updateGame()
	// test if the timer cycles is as expected after 1 update
	@Test
	public void logicTimerCyclesShouldBe() 
	{
		tetrisObject.resetGame();
		tetrisObject.spawnPiece();
		tetrisObject.currentRow = 20;
		tetrisObject.updateGame();
		assertEquals(((1.0f / 1.035f) * 1000), tetrisObject.logicTimer.millisPerCycle, 0.0f);
	}
	
	// testing the following function: public void updateGame()
	// test if the current row is as expected after 1 update
	// row starts at 0 and is incremented by 1
	@Test
	public void currentRowShouldBe() 
	{
		tetrisObject.resetGame();
		tetrisObject.currentType = TileType.TypeI;
		tetrisObject.updateGame();
		assertEquals(1,tetrisObject.currentRow);
	}

}
