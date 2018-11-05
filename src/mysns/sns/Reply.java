package mysns.sns;

/**
 * File : Reply.java
 * Desc : ��� Data Object Ŭ����
 * @author Ȳ����(dinfree@dinfree.com)
 */
public class Reply {
	private int rid;		// ��� ������ id
	private int mid;		// ������ id
	private String uid;		// ��� �ۼ���
	private String date;	// ��� �ۼ�����
	private String rmsg;	// ��� ����

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getRmsg() {
		return rmsg;
	}

	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", mid=" + mid + ", uid=" + uid + ", date=" + date + ", rmsg=" + rmsg + "]";
	}
}
