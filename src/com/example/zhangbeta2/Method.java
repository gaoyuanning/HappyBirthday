package com.example.zhangbeta2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Method extends Activity{
	Button backBtn = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.method);
		backBtn = (Button)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(new backOnClickListener());
	}
	class backOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
}