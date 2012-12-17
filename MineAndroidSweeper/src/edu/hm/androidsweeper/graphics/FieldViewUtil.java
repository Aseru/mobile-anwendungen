package edu.hm.androidsweeper.graphics;

import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.gamelogic.Coordinate;
import edu.hm.androidsweeper.gamelogic.Field;
import edu.hm.androidsweeper.gamelogic.Game;

public class FieldViewUtil {
    
    public static ImageView[] createFieldViews(final Context context, final Game game) {
        Field[] fields = game.getPlayground().getFieldsArray();
        ImageView[] fieldViews = new ImageView[fields.length];
        ImageView fieldView = null;
        for (int i = 0; i < fieldViews.length; i++) {
            if (fields[i].getView() != null) {
                continue;
            }
            fieldView = new ImageView(context);
            fieldView.setImageResource(R.drawable.tile);
            fieldView.setTag(fields[i]);
            fields[i].setView(fieldView);
            fieldView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    game.reveal(v);
                }
            });
            fieldView.setOnLongClickListener(new OnLongClickListener() {
                
                @Override
                public boolean onLongClick(final View v) {
                    game.setFlag(v);
                    return true;
                }
            });
            if (fields[i].isRevealed()) {
                revealView(fields[i]);
            }
            if (fields[i].isFlag()) {
                setFlagView(fields[i]);
            }
            fieldViews[i] = fieldView;
        }
        return fieldViews;
    }
    
    /**
     * Reveals the passed field. <br>
     * Checks the field state and changes the ImageView to fit the state. TODO:
     * Document method revealView
     * 
     * @param field
     */
    public static void revealView(final Field field) {
        View view = field.getView();
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView)view;
            if (field.isExploded()) {
                imageView.setImageResource(R.drawable.tile_exploded);
                return;
            }
            else if (field.isBomb()) {
                imageView.setImageResource(R.drawable.tile_bomb);
                return;
            }
            else {
                int value = field.getValue();
                switch (value) {
                    case 0:
                        imageView.setImageResource(R.drawable.tile_revealed);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.tile_1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.tile_2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.tile_3);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.tile_4);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.tile_5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.tile_6);
                        break;
                    case 7:
                        imageView.setImageResource(R.drawable.tile_7);
                        break;
                    case 8:
                        imageView.setImageResource(R.drawable.tile_8);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    /**
     * Changes the ImageView of a field to a flag or a tile.
     * 
     * @param field
     *            the field where the view needs to be exchanged
     */
    public static void setFlagView(final Field field) {
        boolean isFlag = field.isFlag();
        View view = field.getView();
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView)view;
            if (isFlag) {
                imageView.setImageResource(R.drawable.tile_flag);
            }
            else {
                imageView.setImageResource(R.drawable.tile);
            }
        }
    }
    
    /**
     * Reveals all bombs on the field.
     * 
     * @param map
     *            the map with all bombs.
     */
    public static void revealBombs(final Map<Coordinate, Field> map) {
        for (Field field : map.values()) {
            if (field.isBomb()) {
                revealView(field);
            }
        }
    }
}
