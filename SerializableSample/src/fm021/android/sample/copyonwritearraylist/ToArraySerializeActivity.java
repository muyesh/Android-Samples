package fm021.android.sample.copyonwritearraylist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;


/**
 * CopyOnWriteArrayListの中身のみをシリアライズ処理
 * @author F.Makino<fumitaka.makino@gmail.com>
 */
public class ToArraySerializeActivity extends AbstractSerializeActivity {
	private static final String SERIALIZE_FILENAME = "serialize_array.dat";

	@Override
	protected boolean loadList() {
		try {
		    FileInputStream fis = this.openFileInput(SERIALIZE_FILENAME);
		    ObjectInputStream ois = new ObjectInputStream(fis);
	        int cap = ois.readInt();
	        SampleDto[] objectArray = new SampleDto[cap];
	        for (int i = 0; i < cap; i++) {
	        	objectArray[i] = (SampleDto) ois.readObject();
	        }
	        ois.close();
	        dataList=new CopyOnWriteArrayList<SampleDto>(Arrays.asList(objectArray));
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
			Object[] objectArray = dataList.toArray();
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(objectArray.length);
	        for (int i = 0; i < objectArray.length; i++) {
	        	oos.writeObject(objectArray[i]);
	        }
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