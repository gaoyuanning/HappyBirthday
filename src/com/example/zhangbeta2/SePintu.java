package com.example.zhangbeta2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Button;
import android.widget.LinearLayout;

public class SePintu extends Activity{
	private int levelNow = 4;
	private ImageView mImages[][];	//存放小图片的数组
	private Bitmap mBitmap;			//资源图片
	private int mImageWidth = 0, mImageHeight = 0;	//slot的宽高
	private int mImageNum[];		//图片的顺序
	private int x = 0, y = 0;		//图片的起始位置
	private int clickNum = 0;		//点击参数
	private int windowWidth = 0, windowHeight = 0;	//屏幕参数

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		pic1 = (ImageView)findViewById(R.id.pic1);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
		System.out.println("width = " + mBitmap.getWidth() + "height = " + mBitmap.getHeight());
		//屏幕参数
		WindowManager w = this.getWindowManager();
		windowHeight = w.getDefaultDisplay().getHeight();
		windowWidth = w.getDefaultDisplay().getWidth();
		System.out.println("wWidth = " + windowWidth + "wHeight = " + windowHeight);		
		setImage();
	}
	
	public void setImage() {
		mImageWidth = mBitmap.getWidth() / levelNow;		//切割图片，每一小块的宽度
		mImageHeight = mBitmap.getHeight() / levelNow;
		mImageNum = new int[levelNow * levelNow];
		System.out.println("mIWidth = " + mImageWidth + "mImageHeight = " + mImageHeight);
		erraLen(levelNow * levelNow);	//随机组合切碎的小图片
		readyImage();
		setLayout();	//布局随机组合后的图片
	}
	 private void setLayout() {
			PictureLayout lay = new PictureLayout(this, mImages);	//利用带参数的构造函数来布局小图片
			lay.setOrientation(LinearLayout.VERTICAL);
			lay.setGravity(Gravity.CENTER_HORIZONTAL);
			setContentView(lay);		//显示lay布局，SePintu的Activity
			
			Button methodButton  = new Button(this);  
			methodButton.setText(R.string.method_btn_label); 
			methodButton.setOnClickListener(new MethodBtnClick());	//添加监听器
			
			Button showSourceImageBtn = new Button(this);
			showSourceImageBtn.setText(R.string.show_source_image_btn_label);
			showSourceImageBtn.setOnClickListener(new SourceBtnClick());
 		  
			LinearLayout linear = new LinearLayout(this);  
			//注意，对于LinearLayout布局来说，设置横向还是纵向是必须的！否则就看不到效果了。  
			linear.setOrientation(LinearLayout.HORIZONTAL);  
			//此处相当于布局文件中的Android：gravity属性  
			linear.setGravity(Gravity.CENTER_HORIZONTAL); 
			methodButton.setWidth(200);
			showSourceImageBtn.setWidth(200);
			linear.addView(methodButton);			//通过addView将两按钮添加到布局中
			linear.addView(showSourceImageBtn);
			lay.addView(linear);			//把linear当作子child添加到lay布局中
	 }
	 
	 class MethodBtnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(SePintu.this, Method.class);
			startActivity(intent);		//跳转Activity
		}		 
	 }
	 class SourceBtnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SePintu.this, SourceImageAty.class);
			startActivity(intent);		//跳转Activity
		}
		 
	 }
	 /**
     * @see 把一个有序数组通过随机取数打乱
     * @param a
     */
    public void erraLen(int a) {

    	int errInt[] = new int[a];
    	for (int i = 0; i < a; i++) {
    		errInt[i] = i;
    	}
    	
    	int len = a;// 设置随机数的范围
    	for (int i = 0; i < a; i++) {
    		int index = (int) Math.floor((Math.random() * len));
    		mImageNum[i] = errInt[index];
    		
    		for (int j = index; j < errInt.length - 1; j++) {
    			// 把选中的数之后的数一次向前移一位，因为index选中的数已经存放在相应的mImageNum里面了，
    			errInt[j] = errInt[j + 1];
    		}
    		len--;// 随机数的范围减一
    	}
    }

    /**
     * @see 准备图片 把一张图片截成几张小的通过打乱的数组来取cache里的图片放到View里面就成打乱二维数组
     */
    private void readyImage() {
    	Matrix matrix = new Matrix();
    	mImages = new ImageView[levelNow][levelNow];

    	// 设置缩放比例
//    	float scaleW = ((float) mBitmap.getWidth()) / mBitmap.getWidth();
//    	float scaleH = ((float) mBitmap.getHeight()) / mBitmap.getHeight();
    	float scaleW = ((float) mBitmap.getWidth()) / (windowWidth + 180);
    	float scaleH = ((float) mBitmap.getHeight()) / (windowHeight + 180);
    	System.out.println("scaleW = " + scaleW +" scaleH" + scaleH);

    	float scale = scaleW > scaleH ? 1 / scaleW : 1 / scaleH;	//scale是缩放比例，取最小比例的进行缩放
    	System.out.println("scale = " + scale);
    	matrix.postScale(scale, scale);		

    	Bitmap bitss[][] = new Bitmap[levelNow][levelNow];
    	ImageView[][] cache = new ImageView[levelNow][levelNow];
    	int cont = 1;
    	for (int i = 0; i < levelNow; i++) {
    		for (int j = 0; j < levelNow; j++) {
    			int x = i * mImageWidth;
    			int y = j * mImageHeight;
    			// 第一个是要在那个图片上截取 x,y是要在这个图的那个位置截取
    			// mImageWidth，mImageHeight是截取的长和宽， matrix是缩放比例
    			Bitmap mapi = Bitmap.createBitmap(mBitmap, x, y, mImageWidth,
    					mImageHeight, matrix, true);

    			bitss[i][j] = mapi;
    			ImageView img = new ImageView(this);
    			BitmapDrawable draw = new BitmapDrawable(bitss[i][j]);
    			img.setImageDrawable(draw);
    			img.setId(cont);
    			img.setScaleType(ScaleType.FIT_XY);
    			img.setOnClickListener(OnClickImageView1);
    			cache[i][j] = img;		//cache存放着整张图切割后的小图片
    			cont++;
    		}
    	}

		for (int i = 0; i < mImageNum.length; i++) {
			int x = mImageNum[i] / levelNow;	//确定第几行
			int y = mImageNum[i] % levelNow;	//确定第几列
			int x1 = i / levelNow;
			int y1 = i % levelNow;
			mImages[x1][y1] = cache[x][y];	//将cache里面的小图片随机放入mImages数组里面
		}
    }
    private android.view.View.OnClickListener OnClickImageView1 = new ImageView.OnClickListener() {
    	@Override
    	public void onClick(View v) {
    	    if (clickNum == 0) {// 即需要交换的第一个图片
    	    	for (int i = 0; i < mImages.length; i++) {
    	    		boolean f = false;
    	    		for (int j = 0; j < mImages[i].length; j++) {
    	    			ImageView imgg = mImages[i][j];
    	    			if (imgg == v) {	//所点击的刚好是指定的小图片区域
    	    				x = i;
    	    				y = j;
    	    				clickNum++;		//点击了一次
    	    				f = true;
    	    				break;
    	    			}
    	    		}
    	    		if (f) {
    	    			break;
    	    		}
    	    	}
    	    } else {// 即需要交换的第二个图片
    	    			for (int i = 0; i < mImages.length; i++) {
    	    				for (int j = 0; j < mImages[i].length; j++) {
    	    					ImageView imgg = mImages[i][j];
    	    					if (imgg == v) {
    	    						if (clickNum == 1) {
    	    							changePosition(i, j, x, y);
    	    							x = 0;
    	    							y = 0;
    	    							clickNum = 0;
    	    						}
    	    					}
    	    				}
    	    			}
    	    		}
    	}
    };
    private void changePosition(int x1, int y1, int x2, int y2) {
    	// 判断宽和高差的绝对值是否是1，如果是1的话交换两张图片，不是1的话提示用户
    	if (Math.abs(x1 - x2) + Math.abs(y1 - y2) != 1) {
    		System.out.println("not link....");
    		Builder bul = new AlertDialog.Builder(this);	//弹出相应对话框
    	    bul.setTitle(R.string.dialog);
    	    bul.setMessage(R.string.cannot_change);
    	    bul.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    		}
    	    }).show();
    	} else {	//相邻，两张图片进行交换
    		System.out.println("link....");
    	    String str = "";
    	    ImageView bitF = null;
    	    bitF = mImages[x1][y1];
    	    mImages[x1][y1] = mImages[x2][y2];
    	    mImages[x2][y2] = bitF;

    	    for (int i = 0; i < mImages.length; i++) {
    	    	for (int j = 0; j < mImages[i].length; j++) {
    	    		ImageView img = mImages[i][j];
    	    		//得到ImageView的父控件
    	    		LinearLayout pa = (LinearLayout) img.getParent();
    	    		// 再移除ImageView使其父控件没有，移除父控件，重新用setLayout()进行布局
    	    		pa.removeView(img);
    	    	}
    	    }
    	    setLayout();	//将进行变换操作的图片显示出来
    	    
    	    for (int i = 0; i < mImages.length; i++) {
    	    	for (int j = 0; j < mImages[i].length; j++) {
    	    		str += mImages[i][j].getId();		//取对应小图片的ID，相当于R.id.mImages[i][j]
    	    	}
    	    }
    	    //根据具体切割次数levelNow，判断最后一次变换是否已经拼好
    	    switch (levelNow) {
    	    case 2:
    	    	if (str.equals("1324")) {
    	    		// “1324”的意思是，将图片2*2地切割，有四块，标号先竖着，然后再横着来1234，根据getId取值互相比较
    	    		this.success();
    	    	}
    	    	break;
    	    case 3:
    	    	if (str.equals("147258369")) {
    	    		this.success();
    	    	}
    	    	break;
    	    case 4:
    	    	if (str.equals("15913261014371115481216")) {
    	    		this.success();
    	    	}
    	    	break;
    	    case 5:
    	    	if (str.equals("16111621271217223813182349141924510152025")) {
    	    		this.success();
    	    	}
    	    	break;
    	    }
    	}
    }
    public void success() {
    	Builder bul = new AlertDialog.Builder(this);
    	bul.setTitle(R.string.dialog);
    	bul.setMessage(R.string.congratulation);
    	bul.setPositiveButton(R.string.next_Label, new DialogInterface.OnClickListener() {

    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
        		Intent intent = new Intent(SePintu.this, TheEnd.class);
        		SePintu.this.startActivity(intent);		//点击确定后跳转到下一个Activity
    	    }
    	});
    	bul.show();
    	System.out.println("success");
        }
}