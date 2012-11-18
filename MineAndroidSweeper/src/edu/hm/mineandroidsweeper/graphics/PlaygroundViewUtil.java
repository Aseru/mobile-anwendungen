package edu.hm.mineandroidsweeper.graphics;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import edu.hm.mineandroidsweeper.R;
import edu.hm.mineandroidsweeper.gamelogic.Field;
import edu.hm.mineandroidsweeper.gamelogic.Playground;

public class PlaygroundViewUtil {
	
	public static View createPlayGroundView(Context context, Playground playground){
		int xSize = playground.getDifficulty().getXSize();
		int ySize = playground.getDifficulty().getYSize();
		Field[] fields = playground.getFieldsArray();
		View layout = LayoutInflater.from(context).inflate(R.layout.activity_game, null);
		LinearLayout parent = (LinearLayout) layout.findViewById(R.id.layout_playground);
		LinearLayout linLayout = null;
		
		int i = 0;
		for(int y = 0; y < ySize; y++){
			linLayout = new LinearLayout(context);
			linLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			linLayout.setOrientation(LinearLayout.HORIZONTAL);
			linLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			for(int x = 0; x < xSize; x++){
				linLayout.addView(fields[i].getView());
				i++;
			}
			parent.addView(linLayout);
		}
		return layout;
	}

}
