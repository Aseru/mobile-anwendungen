package edu.hm.androidsweeper.gamelogic;

import java.io.Serializable;
import java.util.Map;

import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import edu.hm.androidsweeper.graphics.FieldViewUtil;

/** Represents a field on the game playground.
 */
public class Field implements Serializable {
    
    private static final long serialVersionUID = -4780750269865091787L;
    
    private final Coordinate coord;
    
    private transient View view;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isExploded;
    private boolean isFlag;
    
    private int value;
    
    /** Returns whether this field is flagged or not.
     * @return the isFlag
     */
    public boolean isFlag() {
        return isFlag;
    }
    
    /** Sets the flag variable for this field.
     * @param flagValue value of the flag
     */
    public void setFlag(final boolean flagValue) {
        isFlag = flagValue;
        FieldViewUtil.setFlagView(this);
    }
    
    /** Returns whether this field is exploded or not.
     * @return The isExploded value.
     */
    public boolean isExploded() {
        return isExploded;
    }
    
    /** Sets the exploded variable of this field.
     * @param explodedValue value of exploded
     */
    public void setExploded(final boolean explodedValue) {
        isExploded = explodedValue;
    }
    
    /** No-args constructor needed for Serialization. */
    protected Field() {
        coord = null;
    }
    
    /** Creates a new field from a given coordinate.
     * @param coord the coordinate of this field
     */
    public Field(final Coordinate coord) {
        this.coord = coord;
        
        isBomb = false;
        value = 0;
    }
    
    /** Returns the view for this field.
     * @return A {@link View} object.
     */
    public View getView() {
        return view;
    }
    
    /** Sets the view for this field.
     * @param view the view to set
     */
    public void setView(final View view) {
        this.view = view;
    }
    
    /** Returns whether this field is a bomb or not.
     * @return the isBomb value
     */
    public boolean isBomb() {
        return isBomb;
    }
    
    /** Sets the bomb variable of this field.
     * @param bombValue the isBomb value to set
     */
    public void setBomb(final boolean bombValue) {
        isBomb = bombValue;
    }
    
    /** Returns whether this field is revealed or not.
     * @return the isRevealed value
     */
    public boolean isRevealed() {
        return isRevealed;
    }
    
    /**
     * Sets the field as revealed (or not revealed).<br>
     * If the field is now revealed, the view will be changed.
     * 
     * @param reveal
     *            the isRevealed to set
     */
    public void setRevealed(final boolean reveal) {
        if (reveal) {
            FieldViewUtil.revealView(this);
        }
        isRevealed = reveal;
    }
    
    /** Returns the value of this field.
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    /** Sets the value of this field.
     * @param value the value to set
     */
    public void setValue(final int value) {
        this.value = value;
    }
    
    /** Returns the coordinate of this field.
     * @return A {@link Coordinate} object.
     */
    public Coordinate getCoord() {
        return coord;
    }
    
    /**
     * Increases the field value. <br>
     * Only if this field is not a bomb itself.
     * 
     * @return true if the field value is increased
     */
    public boolean increaseValue() {
        if (isBomb) {
            return false;
        }
        else {
            value++;
            return true;
        }
    }
    
    /**
     * Calculates the neighbor coordinates of the field.
     * 
     * @return the 8 neighbors as a Coordinate array.
     */
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
    
    /**
     * Calculates the neighbor coordinates of the field.
     * 
     * @param map
     *            the Coordinate-Field map.
     * @return the 8 neighbors as a Field array.
     */
    public Field[] getNeighborFields8(final Map<Coordinate, Field> map) {
        Coordinate[] neighborCoords = getNeighborCoords8();
        Field[] neighborFields = new Field[neighborCoords.length];
        for (int i = 0; i < neighborFields.length; i++) {
            neighborFields[i] = map.get(neighborCoords[i]);
        }
        return neighborFields;
    }
    
    /**
     * Removes his own view from the parent of the view.
     */
    public void removeViewFromParent() {
        if (view == null) {
            return;
        }
        ViewParent viewParent = view.getParent();
        LinearLayout parent = null;
        if (viewParent instanceof LinearLayout) {
            parent = (LinearLayout)viewParent;
            parent.removeView(view);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Field)) {
            return false;
        }
        Field another = (Field)o;
        return coord.equals(another.getCoord());
    }
    
    @Override
    public int hashCode() {
        return coord.getX() + coord.getY();
    }
    
}
