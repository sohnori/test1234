package example.textviewtest3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	SoundPool mPool;
	int mDdok;
	AudioManager mAm;
	Vibrator mVib;	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mVib.cancel();
	};
	public void mOnClick(View v) {
		Intent intent = new Intent(this, SubActivity.class);
		startActivity(intent);
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		LinearLayout linear = new LinearLayout(this);
//		linear.setOrientation(LinearLayout.VERTICAL);
//		linear.setBackgroundColor(Color.LTGRAY);
//		
//		TextView text = new TextView(this);
//		text.setText("TextView");
//		text.setGravity(Gravity.CENTER);
//		text.setTextColor(Color.RED);
//		
//		linear.addView(text);
//		setContentView(linear);
		
		setContentView(R.layout.activity_main);
		
		mPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		mDdok = mPool.load(this, R.raw.ddok, 1);
		mAm = (AudioManager)getSystemService(AUDIO_SERVICE);
		
		mVib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);		
		
		Button.OnClickListener mClickListener = new Button.OnClickListener() {
			public void onClick(View v) {
				
				switch(v.getId()) {
				case R.id.btnProcess1:
					Toast.makeText(MainActivity.this, "잠시 나타나는 메시지", Toast.LENGTH_SHORT).show();
					mPool.play(mDdok, 1, 1, 0, 0, 1);
					mVib.vibrate(500);
					break;
				case R.id.btnProcess2:
					Toast.makeText(MainActivity.this, "조금 길게 나타나는 메시지", Toast.LENGTH_LONG).show();
					mAm.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
					mVib.vibrate(new long[] {100, 50, 200, 50}, 0);
					break;
				case R.id.btnProcess3:
					mVib.cancel();
//					Intent intent = new Intent(this, ToastView.class);
//					startActivity(intent);
//					new CountDownTimer(30000,1000) {
//						public void onTick(long millisUntilFinished) {
//							
//						}
//						public void onFinish() {
//							
//						}
//					}.start();
//					setContentView(R.layout.activity_main);
//					LinearLayout linear = (LinearLayout)View.inflate(MainActivity.this,
//							R.layout.toastview, null);
//					Toast t2 = new Toast(MainActivity.this);
//					t2.setView(linear);;
//					t2.show();
					break;
				}
			}
		};
		// 1순위
		View.OnTouchListener ToutchListener = new View.OnTouchListener() {			
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN) {
					Toast.makeText(MainActivity.this, "Touch Event Received", Toast.LENGTH_SHORT).show();
					return true;
				}				
				return false;
			}
		};
		
		findViewById(R.id.btnProcess1).setOnClickListener(mClickListener);
		findViewById(R.id.btnProcess2).setOnClickListener(mClickListener);
		findViewById(R.id.btnProcess3).setOnClickListener(mClickListener);		
		findViewById(R.id.actmain).setOnTouchListener(ToutchListener);		
	}	
	
	protected class MyView extends View{
		public MyView(Context context) {
			super(context);
		}
		// 2순위
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN) {
				Toast.makeText(MainActivity.this, "View : Touch Event Received", Toast.LENGTH_SHORT)
				.show();
				return true;
			}
			return false;
		}
	}
	
	// 3순위
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN) {
			Toast.makeText(MainActivity.this, "Activity : Touch Event Received", Toast.LENGTH_SHORT)
			.show();
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
}
