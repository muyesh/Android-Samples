package fm021.android.sample.copyonwritearraylist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * メニューアクティビティ
 * @author F.Makino<fumitaka.makino@gmail.com>
 */
public class MainActivity extends Activity implements
		OnClickListener {
	List<Button> buttonList = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		// List
		buttonList = new ArrayList<Button>();
		// ボタン初期化
		buttonList.add((Button) this.findViewById(R.id.menu1));
		buttonList.add((Button) this.findViewById(R.id.menu2));
		// set listener
		for (Button btn : buttonList) {
			btn.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View view) {
		Class<? extends Activity> targetActivity = null;
		switch (view.getId()) {
		case R.id.menu1:
			targetActivity=NormalSerializeActivity.class;
			break;
		case R.id.menu2:
			targetActivity=ToArraySerializeActivity.class;
			break;
		default:
			break;
		}
		if(targetActivity!=null){
			Intent intent = new Intent(getApplicationContext(),targetActivity);
			startActivity(intent);
		}
	}
}