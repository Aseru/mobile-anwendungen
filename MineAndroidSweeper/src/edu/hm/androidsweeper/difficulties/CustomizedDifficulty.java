package edu.hm.androidsweeper.difficulties;


/**
 * Class for representation a customized difficulty.<br>
 * using this difficulty, the user can choose the size of the field and the
 * number of bombs
 */
public class CustomizedDifficulty implements IDifficulty {
    
    /**
     * Default field size.
     */
    public static final int DEFAULT_SIZE = 15;
    
    /**
     * Default number of bombs.
     */
    public static final int DEFAULT_BOMBS = 35;
    
    /**
     * Minimum field size.
     */
    public static final int MIN_SIZE = 3;
    
    /**
     * Minimum number of Boms.
     */
    public static final int MIN_BOMBS = 1;
    
    /**
     * Maximum field size.
     */
    public static final int MAX_SIZE = 30;
    
    /**
     * Maximum number of bombs.
     */
    public static final int MAX_BOMBS = 720;
    
    private static final long serialVersionUID = 6850885455181308430L;
    
    private final int width;
    private final int height;
    private final int bombs;
    private final int hints;
    
    /**
     * No-args constructor, needed for Serialization.
     */
    protected CustomizedDifficulty() {
        width = DEFAULT_SIZE;
        height = DEFAULT_SIZE;
        bombs = DEFAULT_BOMBS;
        hints = 0;
    }
    
    /**
     * Creates a new instance of {@link CustomizedDifficulty}.
     * 
     * @param width
     *            field width
     * @param height
     *            field height
     * @param bombs
     *            number of bombs
     * @throws InvalidConfigException
     *             if the config is not valid
     */
    public CustomizedDifficulty(final int width, final int height, final int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
        hints = calcHints();
    }
    
    private int calcHints() {
        int hints = (width + height) / 4;
        int freeFields = width * height - bombs;
        if (hints >= freeFields / 2) {
            hints = freeFields / 2;
        }
        return hints;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
     */
    @Override
    public int getWidth() {
        return width;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
     */
    @Override
    public int getHeight() {
        return height;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getBombs() {
        return bombs;
    }
    
    
    
    @Override
    public int getHints() {
        return hints;
    }
    
    @Override
    public String getDifficultyName() {
        return null;
    }
    
}
