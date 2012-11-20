package edu.hm.mineandroidsweeper.gamelogic;

import java.io.Serializable;
import java.util.Map;

import android.view.View;
import edu.hm.mineandroidsweeper.graphics.FieldViewUtil;

public class Field implements Serializable {
    
    private static final long serialVersionUID = -4780750269865091787L;
    
    private final Coordinate coord;
    
    private transient View view;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isExploded;
    private boolean isFlag;
    
    /**
     * @return the isFlag
     */
    public boolean isFlag() {
        return isFlag;
    }
    
    /**
     * @param isFlag
     *            the isFlag to set
     */
    public void setFlag(final boolean isFlag) {
        this.isFlag = isFlag;
    }
    
    private int value;
    
    /**
     * @return the isExploded
     */
    public boolean isExploded() {
        return isExploded;
    }
    
    /**
     * @param isExploded
     *            the isExploded to set
     */
    public void setExploded(final boolean isExploded) {
        this.isExploded = isExploded;
    }
    
    /* No-args constructor needed for Serialization. */
    protected Field() {
        coord = null;
    }
    
    public Field(final Coordinate coord) {
        this.coord = coord;
        
        isBomb = false;
        value = 0;
    }
    
    /**
     * @return the view
     */
    public View getView() {
        return view;
    }
    
    /**
     * @param view
     *            the view to set
     */
    public void setView(final View view) {
        this.view = view;
    }
    
    /**
     * @return the isBomb
     */
    public boolean isBomb() {
        return isBomb;
    }
    
    /**
     * @param isBomb
     *            the isBomb to set
     */
    public void setBomb(final boolean isBomb) {
        this.isBomb = isBomb;
    }
    
    /**
     * @return the isRevealed
     */
    public boolean isRevealed() {
        return isRevealed;
    }
    
    /**
     * @param isRevealed
     *            the isRevealed to set
     */
    public void setRevealed(final boolean isRevealed) {
        if (isRevealed) {
            FieldViewUtil.revealView(this);
        }
        this.isRevealed = isRevealed;
    }
    
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * @param value
     *            the value to set
     */
    public void setValue(final int value) {
        this.value = value;
    }
    
    /**
     * @return the coord
     */
    public Coordinate getCoord() {
        return coord;
    }
    
    public boolean increaseValue() {
        if (isBomb) {
            return false;
        } else {
            value++;
            return true;
        }
    }
    
    public Coordinate[] getNeighborCoords8() {
        Coordinate[] neighborCoords = new Coordinate[8];
        int x = coord.getX();
        int y = coord.getY();
        
        neighborCoords[0] = new Coordinate(x - 1, y - 1);
        neighborCoords[1] = new Coordinate(x, y - 1);
        neighborCoords[2] = new Coordinate(x + 1, y - 1);
        neighborCoords[3] = new Coordinate(x - 1, y);
        neighborCoords[4] = new Coordinate(x + 1, y);
        neighborCoords[5] = new Coordinate(x - 1, y + 1);
        neighborCoords[6] = new Coordinate(x, y + 1);
        neighborCoords[7] = new Coordinate(x + 1, y + 1);
        
        return neighborCoords;
    }
    
    public Field[] getNeighborFields8(final Map<Coordinate, Field> map) {
        Coordinate[] neighborCoords = getNeighborCoords8();
        Field[] neighborFields = new Field[neighborCoords.length];
        for (int i = 0; i < neighborFields.length; i++) {
            neighborFields[i] = map.get(neighborCoords[i]);
        }
        return neighborFields;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof Field)) {
            return false;
        }
        Field another = (Field) o;
        if (coord.equals(another.getCoord())) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
