package example.textviewtest3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SubActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
	}
	public void mOnClick(View v) {
		finish();
	}
}
