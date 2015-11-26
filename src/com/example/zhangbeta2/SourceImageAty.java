package com.example.zhangbeta2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SourceImageAty extends Activity{
	private ImageView pic1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.source);
		pic1 = (ImageView)findViewById(R.id.pic1);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
		pic1.setImageBitmap(bitmap1);
		pic1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();	//点击屏幕图片即退出当前Activity
			}
			
		});
		
	}
}
