package edu.hm.androidsweeper.graphics;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import edu.hm.androidsweeper.R;
import edu.hm.androidsweeper.gamelogic.Field;
import edu.hm.androidsweeper.gamelogic.Playground;

public class PlaygroundViewUtil {
    
    public static View createPlayGroundView(final Context context, final Playground playground) {
        int xSize = playground.getDifficulty().getWidth();
        int ySize = playground.getDifficulty().getHeight();
        Field[] fields = playground.getFieldsArray();
        View layout = LayoutInflater.from(context).inflate(R.layout.activity_game, null);
        View view = layout.findViewById(R.id.layout_playground);
        LinearLayout parent = null;
        if (view instanceof LinearLayout) {
            parent = (LinearLayout)view;
            
            LinearLayout linLayout = null;
            
            int i = 0;
            for (int y = 0; y < ySize; y++) {
                linLayout = new LinearLayout(context);
                linLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                linLayout.setOrientation(LinearLayout.HORIZONTAL);
                linLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                for (int x = 0; x < xSize; x++) {
                    fields[i].removeViewFromParent();
                    linLayout.addView(fields[i].getView());
                    i++;
                }
                parent.addView(linLayout);
            }
        }
        return layout;
    }
    
}
