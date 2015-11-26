package com.example.zhangbeta2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends Activity {
	private Intent intent = new Intent("com.angel.Android.MUSIC");
	Button buttonNext = null;
	Button buttonExit = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
        startService(intent);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new NextButtonListener());
        buttonExit = (Button)findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new ExitButtonListener());            
    }
    
    class NextButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(Main.this, SePintu.class);
			startActivity(intent);	//开启背景音乐
		}    	
    }
    
    class ExitButtonListener implements OnClickListener{

		private Toast toast;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			toast = Toast.makeText(getApplicationContext(), "无论如何，都希望你快乐幸福", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			LinearLayout toastView = (LinearLayout)toast.getView();
			ImageView imageCodeProject = new ImageView(getApplicationContext());
			imageCodeProject.setImageResource(R.drawable.pic3);
			toastView.addView(imageCodeProject, 0);
			toast.show();
			finish();
			stopService(intent);	//关闭背景音乐
//			android.os.Process.killProcess(android.os.Process.myPid());
		}    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
