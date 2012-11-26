package edu.hm.mineandroidsweeper.graphics;

import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Coordinate;
import edu.hm.mineandroidsweeper.gamelogic.Field;
import edu.hm.mineandroidsweeper.gamelogic.Game;

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
            fieldViews[i] = fieldView;
        }
        return fieldViews;
    }
    
    public static void revealView(final Field field) {
        ImageView view = (ImageView)field.getView();
        if (view == null) {
            return;
        }
        if (field.isExploded()) {
            view.setImageResource(R.drawable.tile_exploded);
            return;
        }
        else if (field.isBomb()) {
            view.setImageResource(R.drawable.tile_bomb);
            return;
        }
        else {
            int value = field.getValue();
            switch (value) {
                case 0:
                    view.setImageResource(R.drawable.tile_revealed);
                    break;
                case 1:
                    view.setImageResource(R.drawable.tile_1);
                    break;
                case 2:
                    view.setImageResource(R.drawable.tile_2);
                    break;
                case 3:
                    view.setImageResource(R.drawable.tile_3);
                    break;
                case 4:
                    view.setImageResource(R.drawable.tile_4);
                    break;
                case 5:
                    view.setImageResource(R.drawable.tile_5);
                    break;
                case 6:
                    view.setImageResource(R.drawable.tile_6);
                    break;
                case 7:
                    view.setImageResource(R.drawable.tile_7);
                    break;
                case 8:
                    view.setImageResource(R.drawable.tile_8);
                    break;
            }
        }
    }
    
    public static void setFlagView(final Field field) {
        boolean isFlag = field.isFlag();
        ImageView view = (ImageView)field.getView();
        if (isFlag) {
            view.setImageResource(R.drawable.tile_flag);
        }
        else {
            view.setImageResource(R.drawable.tile);
        }
    }
    
    public static void revealBombs(final Map<Coordinate, Field> map) {
        for (Field field : map.values()) {
            if (field.isBomb()) {
                revealView(field);
            }
        }
    }
    
}
