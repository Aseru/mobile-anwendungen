package edu.hm.androidsweeper.difficulties;

import edu.hm.androidsweeper.R;

public class CustomizedDifficulty implements IDifficulty {
    
    public static final DifficultyDescription DESCRIPTION = DifficultyDescription.CUSTOM;
    public static final int DEFAULT_SIZE = 15;
    public static final int DEFAULT_BOMBS = 35;
    public static final int MAX_SIZE = 30;
    public static final int MAX_BOMBS = 720;
    
    private static final long serialVersionUID = 6850885455181308430L;
    
    private final int xSize;
    private final int ySize;
    private final int numberOfBombs;
    
    /* No-args constructor, needed for Serialization. */
    protected CustomizedDifficulty() {
        xSize = DEFAULT_SIZE;
        ySize = DEFAULT_SIZE;
        numberOfBombs = DEFAULT_BOMBS;
    }
    
    public CustomizedDifficulty(final int xSize, final int ySize, final int numberOfBombs)
            throws InvalidConfigException {
        this.xSize = xSize;
        this.ySize = ySize;
        this.numberOfBombs = numberOfBombs;
        checkConfig();
    }
    
    private void checkConfig() throws InvalidConfigException {
        int numberOfFields = xSize * ySize;
        if (numberOfFields < numberOfBombs) {
            throw new InvalidConfigException("Too many bombs.", R.string.toast_max_bombs);
        }
        if (numberOfBombs <= 0) {
            throw new InvalidConfigException("Minimum one bomb.", R.string.toast_min_bombs);
        }
        if (xSize > MAX_SIZE) {
            throw new InvalidConfigException("Field length to high", R.string.toast_max_length,
                    MAX_SIZE);
        }
        if (ySize > MAX_SIZE) {
            throw new InvalidConfigException("Field width to high", R.string.toast_max_width,
                    MAX_SIZE);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
     */
    @Override
    public int getXSize() {
        return xSize;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
     */
    @Override
    public int getYSize() {
        return ySize;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getNumberOfBombs() {
        return numberOfBombs;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getDescription
     */
    @Override
    public DifficultyDescription getDescription() {
        return DESCRIPTION;
    }
    
}
