package edu.hm.androidsweeper.test.gamelogic;

import java.util.Map;

import junit.framework.TestCase;
import edu.hm.androidsweeper.difficulties.CustomizedDifficulty;
import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.gamelogic.Coordinate;
import edu.hm.androidsweeper.gamelogic.Field;
import edu.hm.androidsweeper.gamelogic.Playground;

public class PlaygroundTest extends TestCase {
	
	private Playground playground;
	private IDifficulty difficulty;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		this.difficulty = new CustomizedDifficulty(100, 100, 9998);
		this.playground = new Playground(null, difficulty);
		playground.init();
	}

	public void testNumberOfFields(){
		int expectedNumberOfFields = difficulty.getWidth() * difficulty.getHeight();
		Map<Coordinate, Field> fieldsMap = playground.getFieldsMap();
		
		assertEquals(expectedNumberOfFields, fieldsMap.size());
	}
	
	public void testNumberOfBombs(){
		int expectedNumberOfBombs = difficulty.getBombs();
		Map<Coordinate, Field> fieldsMap = playground.getFieldsMap();
		
		int actualNumberOfBombs = 0;
		for(Field field : fieldsMap.values()){
			if(field.isBomb())
				actualNumberOfBombs++;
		}
		
		assertEquals(expectedNumberOfBombs, actualNumberOfBombs);
	}
	
	public void testNeighborValues(){
		Map<Coordinate, Field> fieldsMap = playground.getFieldsMap();
		Field[] bombs;
		Coordinate[] bombNeighborCoords = null;
		Field bombNeighbor = null;
		
		bombs = getBombs(fieldsMap);
		
		for(Field bomb : bombs){
			bombNeighborCoords = bomb.getNeighborCoords8();
			for(Coordinate bombNeighborCoord : bombNeighborCoords){
				bombNeighbor = fieldsMap.get(bombNeighborCoord);
				int bombCount = 0;
				if(bombNeighbor != null && !bombNeighbor.isBomb()){
					bombCount = getNumberOfBombsInNeighborhood(fieldsMap, bombNeighbor);
					assertEquals(bombCount, bombNeighbor.getValue());
				}
			}
		}
	}
	
	private int getNumberOfBombsInNeighborhood(Map<Coordinate, Field> map, Field field){
		int numberOfBombs = 0;
		Coordinate[] neighborCoords = null;
		Field neighbor = null;
		neighborCoords = field.getNeighborCoords8();
		
		for(Coordinate neighborCoord : neighborCoords){
			neighbor = map.get(neighborCoord);
			if(neighbor != null && neighbor.isBomb()){
				numberOfBombs++;
			}
		}
		
		return numberOfBombs;
	}
	
	private Field[] getBombs(Map<Coordinate, Field> map){
		Field[] bombs = new Field[difficulty.getBombs()];
		int bombCount = 0;
		for(Field field : map.values()){
			if(field.isBomb()){
				bombs[bombCount] = field;
				bombCount++;
			}
		}
		return bombs;
	}

}
