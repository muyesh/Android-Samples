package fm021.android.sample.copyonwritearraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * CopyOnWriteArrayListシリアライズ時のバグ実証コード用アクティビティ
 * @author F.Makino<fumitaka.makino@gmail.com>
 */
public abstract class AbstractSerializeActivity extends Activity implements OnClickListener{
	/**
	 * 追加削除したオブジェクトを保持するデータリスト
	 */
	protected CopyOnWriteArrayList<SampleDto> dataList;
	List<Button> buttonList = null;
	TextView textArea = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		// get EditText
		textArea = (TextView) this.findViewById(R.id.textArea);
		textArea.setEnabled(false);
		// List
		buttonList = new ArrayList<Button>();
		// ボタン初期化
		buttonList.add((Button) this.findViewById(R.id.createButton));
		buttonList.add((Button) this.findViewById(R.id.delElementButton));
		buttonList.add((Button) this.findViewById(R.id.addElementButton));
		buttonList.add((Button) this.findViewById(R.id.serializeButton));
		buttonList.add((Button) this.findViewById(R.id.deserializeButton));
		buttonList.add((Button) this.findViewById(R.id.removeButton));
		buttonList.add((Button) this.findViewById(R.id.statusButton));
		initButton();
	}

	void initButton() {
		// set listener
		for (Button btn : buttonList) {
			btn.setOnClickListener(this);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		initButton();
	}
	
	@Override
	public void onClick(View view) {
		System.out.println("Clicked: "+view.getId());
		switch (view.getId()) {
		case R.id.createButton:
			createList();
			viewStatus();
			break;
		case R.id.delElementButton:
			delElement();
			viewStatus();
			break;
		case R.id.addElementButton:
			addElement();
			viewStatus();
			break;
		case R.id.serializeButton:
			saveList();
			viewStatus();
			break;
		case R.id.deserializeButton:
			loadList();
			viewStatus();
			break;
		case R.id.removeButton:
			removeList();
			viewStatus();
			break;
		case R.id.statusButton:
			viewStatus();
			break;
		default:
			break;
		}
	}


	void viewStatus() {
		if(dataList!=null){
			textArea.setText("要素の数: "+dataList.size());
		}else{
			textArea.setText("リストはnull");
		}
	}

	void delElement() {
		if (dataList != null) {
			if(dataList.size()>0){
				dataList.remove(0);
			}
		}
	}

	void addElement() {
		SampleDto sampleDto = new SampleDto();
		if (dataList != null) {
			dataList.add(sampleDto);
		}
	}

	void removeList() {
		dataList = null;
	}

	void createList() {
		if (dataList == null) {
			dataList = new CopyOnWriteArrayList<SampleDto>();
		}
	}
	
	/**
	 * @return 成功ならtrue
	 */
	protected abstract boolean loadList();
	
	/**
	 * 内部のCopyOnWriteArrayListをシリアライズする
	 * @return 成功ならtrue
	 */
	protected abstract boolean saveList();
	
	/**
	 * シリアライズ対象のファイル名を返す
	 * @return ファイル名
	 */
	protected abstract String getSerializeFileName();
}