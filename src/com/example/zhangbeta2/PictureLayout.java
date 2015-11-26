package com.example.zhangbeta2;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PictureLayout extends LinearLayout{

	public PictureLayout(Context context, ImageView[][] images) {
    	super(context);
    	setLayout(context, images);
    	setBackgroundColor(0xffff7777);		//为了和照片边缘区分开，我添加了背景颜色
    }

    private void setLayout(Context context, ImageView[][] view) {
    	
		LinearLayout linralayout = new LinearLayout(context);
		linralayout.setOrientation(LinearLayout.VERTICAL);
		linralayout.setPadding(0, 0, 0, 0);
		for (int i = 0; i < view.length; i++) {
		    LinearLayout liner = new LinearLayout(context);
		    liner.setOrientation(LinearLayout.HORIZONTAL);
		    int leng = view[i].length;
		    for (int j = 0; j < leng; j++) {
			ImageView img = (ImageView) view[i][j];
			liner.addView(img);
		    }
		    linralayout.addView(liner);
		    liner = null;
	}
	this.addView(linralayout);
    }
}