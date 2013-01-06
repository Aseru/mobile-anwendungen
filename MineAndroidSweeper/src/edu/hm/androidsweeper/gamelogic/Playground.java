package edu.hm.androidsweeper.gamelogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.hm.androidsweeper.difficulties.IDifficulty;
import edu.hm.androidsweeper.graphics.FieldViewUtil;
import edu.hm.androidsweeper.graphics.PlaygroundViewUtil;

/** Contains and manages the fields.
 */
public class Playground implements Serializable {
    
    private static final long serialVersionUID = 3663292402304734906L;
    
    private final IDifficulty difficulty;
    private final Game game;
    
    private boolean isCreated;
    private Map<Coordinate, Field> fieldsMap;
    private Field[] fieldsArray;
    
    /** No-args constructor needed for Serialization. */
    protected Playground() {
        difficulty = null;
        game = null;
    }
    
    /** Creates a new instance of {@link Playground}.
     * @param game The game associated to this playground.
     * @param difficulty The difficulty of this game.
     */
    public Playground(final Game game, final IDifficulty difficulty) {
        this.game = game;
        this.difficulty = difficulty;
        fieldsMap = new HashMap<Coordinate, Field>();
    }
    
    /** Initializes the playground.
     * Must be called before using.
     */
    public void init() {
        fieldsArray = createFields(difficulty.getWidth(), difficulty.getHeight());
        Field[] bombs = setBombs(fieldsArray, difficulty);
        putFieldsInMap(fieldsArray);
        calcFieldValues(fieldsArray, bombs);
    }
    
    private Field[] createFields(final int xSize, final int ySize) {
        Coordinate coord = null;
        Field field = null;
        Field[] fields = new Field[xSize * ySize];
        int count = 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                coord = new Coordinate(x, y);
                field = new Field(coord);
                fields[count] = field;
                count++;
            }
        }
        return fields;
    }
    
    private Field[] setBombs(final Field[] fields, final IDifficulty gameDifficulty) {
        int numberOfBombs = gameDifficulty.getBombs();
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
    
    private void putFieldsInMap(final Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            fieldsMap.put(fields[i].getCoord(), fields[i]);
        }
    }
    
    private void calcFieldValues(final Field[] fields, final Field[] bombs) {
        for (Field bomb : bombs) {
            increaseNeighborValues(bomb);
        }
    }
    
    private void increaseNeighborValues(final Field bomb) {
        Coordinate[] neighborCoords = bomb.getNeighborCoords8();
        Field neighbor = null;
        for (Coordinate neighborCoord : neighborCoords) {
            neighbor = fieldsMap.get(neighborCoord);
            if (neighbor == null || neighbor.isBomb()) {
                continue;
            }
            neighbor.increaseValue();
        }
    }
    
    /** Reveals a given field.
     * @param field The field to reveal.
     */
    public void reveal(final Field field) {
        if (field.isBomb()) {
            handleBomb(field);
        }
        else {
            FloodFill.fill8(fieldsMap, field);
            if (checkIfGameIsWon()) {
                game.setState(GameState.WON);
            }
        }
    }
    
    private boolean checkIfGameIsWon() {
        boolean allRevealed = true;
        Field field;
        for (int i = 0; i < fieldsArray.length; i++) {
            field = fieldsArray[i];
            if (!field.isBomb() && !field.isRevealed()) {
                allRevealed = false;
                break;
            }
            
        }
        return allRevealed;
    }
    
    private void handleBomb(final Field field) {
        game.setState(GameState.LOSE);
        field.setExploded(true);
        FieldViewUtil.revealBombs(fieldsMap);
    }
    
    /** Returns the difficulty of the game.
     * @return the difficulty value
     */
    public IDifficulty getDifficulty() {
        return difficulty;
    }
    
    /** Returns an array of the fields this playground contains.
     * @return the fieldsArray
     */
    public Field[] getFieldsArray() {
        return fieldsArray;
    }
    
    /** Returns a map of the fields this playground contains.
     * @return the fieldsMap
     */
    public Map<Coordinate, Field> getFieldsMap() {
        return fieldsMap;
    }
    
    /** Returns whether this playground has been created or not.
     * @return the isCreated value
     */
    public boolean isCreated() {
        return isCreated;
    }
    
    /**
     * Sets the isCreated to the specified value.
     * 
     * @param createdValue the value to set
     */
    public void setCreated(final boolean createdValue) {
        isCreated = createdValue;
    }
    
    /** Reveals a random field on the playground.
     * The selected field will be unrevealed and not a bomb.
     */
    public void revealRandomField() {
        Field field;
        Random random = new Random();
        do {
            field = fieldsArray[random.nextInt(fieldsArray.length)];
        } while (field.isBomb() || field.isRevealed());
        reveal(field);
        PlaygroundViewUtil.getFieldInViewingArea(field);
    }
}