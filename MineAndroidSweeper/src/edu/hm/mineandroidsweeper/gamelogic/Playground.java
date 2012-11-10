package edu.hm.mineandroidsweeper.gamelogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.hm.mineandroidsweeper.difficulties.IDifficulty;

public class Playground {

	private final IDifficulty difficulty;

	private Map<Coordinate, Field> fieldsMap;

	public Playground(IDifficulty difficulty) {
		this.difficulty = difficulty;
		fieldsMap = new HashMap<Coordinate, Field>();
	}

	public void init() {
		Field[] fields = createFields(difficulty.getXSize(),
				difficulty.getYSize());
		Field[] bombs = setBombs(fields, difficulty);
		putFieldsInMap(fields);
		calcFieldValues(fields, bombs);
	}

	private Field[] createFields(int xSize, int ySize) {
		Coordinate coord = null;
		Field field = null;
		Field[] fields = new Field[xSize * ySize];
		int count = 0;
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				coord = new Coordinate(x, y);
				field = new Field(coord);
				fields[count] = field;
				count++;
			}
		}
		return fields;
	}

	private Field[] setBombs(Field[] fields, IDifficulty difficulty) {
		int numberOfBombs = difficulty.getNumberOfBombs();
		Field[] bombs = new Field[numberOfBombs];
		int random = -1;
		Field field = null;
		int i = 0;
		while (i < numberOfBombs) {
			random = new Random().nextInt(fields.length);
			field = fields[random];
			if (!field.isBomb()) {
				field.setBomb(true);
				bombs[i] = field;
				i++;
			}
		}
		return bombs;
	}

	private void putFieldsInMap(Field[] fields) {
		for (int i = 0; i < fields.length; i++) {
			fieldsMap.put(fields[i].getCoord(), fields[i]);
		}
	}

	private void calcFieldValues(Field[] fields, Field[] bombs) {
		for (Field bomb : bombs) {
			increaseNeighborValues(fields, bomb);
		}
	}

	private void increaseNeighborValues(Field[] fields, Field bomb) {
		Coordinate[] neighborCoords = bomb.getNeighborCoords8();
		Field neighbor = null;
		for (Coordinate neighborCoord : neighborCoords) {
			neighbor = fieldsMap.get(neighborCoord);
			if (neighbor == null || neighbor.isBomb()) {
				continue;
			} else {
				if (!neighbor.increaseValue())
					System.err
							.println("This should never happen - Playground.increaseNeighborValues(Field[] fields, Field bomb)");
			}
		}
	}

	/**
	 * @return the difficulty
	 */
	public IDifficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @return the fieldsMap
	 */
	public Map<Coordinate, Field> getFieldsMap() {
		return fieldsMap;
	}

}