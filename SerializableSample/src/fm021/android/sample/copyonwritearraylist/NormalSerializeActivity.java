package fm021.android.sample.copyonwritearraylist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;

/**
 * CopyOnWriteArrayListをそのままシリアライズ処理
 * @author F.Makino<fumitaka.makino@gmail.com>
 */
public class NormalSerializeActivity extends AbstractSerializeActivity {
	private static final String SERIALIZE_FILENAME = "serialize_normal.dat";
	
	@SuppressWarnings("unchecked")
	@Override
	protected boolean loadList() {
		try {
		    FileInputStream fis = this.openFileInput(SERIALIZE_FILENAME);
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    dataList = (CopyOnWriteArrayList<SampleDto>) ois.readObject();
		    ois.close();
		    return true;
		} catch(FileNotFoundException ee){
			ee.printStackTrace();
			//無ければ新規作成
			dataList = new CopyOnWriteArrayList<SampleDto>();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected boolean saveList() {
		if (dataList == null) {
			return false;
		}
		try {
			FileOutputStream fos = this.openFileOutput(
					SERIALIZE_FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dataList);
			oos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected String getSerializeFileName() {
		return SERIALIZE_FILENAME;
	}
}