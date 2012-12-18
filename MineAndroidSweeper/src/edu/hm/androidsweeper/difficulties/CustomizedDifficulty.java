package edu.hm.androidsweeper.difficulties;

import edu.hm.androidsweeper.R;

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
    
    private final int witdh;
    private final int heigth;
    private final int bombs;
    
    /**
     * No-args constructor, needed for Serialization.
     */
    protected CustomizedDifficulty() {
        witdh = DEFAULT_SIZE;
        heigth = DEFAULT_SIZE;
        bombs = DEFAULT_BOMBS;
    }
    
    /**
     * Creates a new instance of {@link CustomizedDifficulty}.
     * 
     * @param width
     *            field width
     * @param heigth
     *            field height
     * @param bombs
     *            number of bombs
     * @throws InvalidConfigException
     *             if the config is not valid
     */
    public CustomizedDifficulty(final int width, final int heigth, final int bombs)
            throws InvalidConfigException {
        witdh = width;
        this.heigth = heigth;
        this.bombs = bombs;
        checkConfig();
    }
    
    private void checkConfig() throws InvalidConfigException {
        int numberOfFields = witdh * heigth;
        if (numberOfFields < bombs) {
            throw new InvalidConfigException("Too many bombs.", R.string.toast_max_bombs);
        }
        if (bombs <= 0) {
            throw new InvalidConfigException("Minimum one bomb.", R.string.toast_min_bombs);
        }
        if (witdh > MAX_SIZE) {
            throw new InvalidConfigException("Field length to high", R.string.toast_max_length,
                    MAX_SIZE);
        }
        if (heigth > MAX_SIZE) {
            throw new InvalidConfigException("Field width to high", R.string.toast_max_width,
                    MAX_SIZE);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getXSize()
     */
    @Override
    public int getWidth() {
        return witdh;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getYSize()
     */
    @Override
    public int getHeight() {
        return heigth;
    }
    
    /*
     * (non-Javadoc)
     * @see edu.hm.mineandroidsweeper.difficults.IDifficult#getNumberOfBombs()
     */
    @Override
    public int getBombs() {
        return bombs;
    }
    
    
    
}
