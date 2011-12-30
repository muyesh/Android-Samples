package fm021.android.sample.copyonwritearraylist;

import java.io.Serializable;

/**
 * 実験用のサンプルDTO
 * @author F.Makino<fumitaka.makino@gmail.com>
 */
public class SampleDto implements Serializable {
	private static final long serialVersionUID = 7348816309015909533L;
	private long createdTime;
	private String value;
	
	public SampleDto() {
		this.createdTime=System.currentTimeMillis();
		StringBuffer strBuf = new StringBuffer();
		//1000なのはメモリを消費したいから
		for (int i = 0; i < 1000; i++) {
			strBuf.append(System.currentTimeMillis());
			strBuf.append("/");
		}
		value=strBuf.toString();
	}

	@Override
	public String toString() {
		return "SampleDto [createdTime=" + createdTime + ", value=" + value
				+ "]";
	}

}
