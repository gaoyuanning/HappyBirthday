package com.example.zhangbeta2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TheEnd extends Activity{
	private Intent intentMusic = new Intent("com.angel.Android.MUSIC");
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end);
		setCompenent();
	}
	private void setCompenent(){
		Button callExitBtn = (Button)findViewById(R.id.call_exit);

		callExitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_CALL, Uri.parse("tel:15954096931"));	//自动拨号，由于隐私，改成10086.。。
				stopService(intentMusic);
				startActivity(intent);				
			}			
		});
		Button exitCallBtn = (Button)findViewById(R.id.exit_call);
		exitCallBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_CALL, Uri.parse("tel:15954096931"));
				stopService(intentMusic);
				startActivity(intent);				
			}			
		});	
		
	}
}