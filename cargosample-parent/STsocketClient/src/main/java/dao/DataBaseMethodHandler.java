package dao;
import java.util.List;

import pojo.ExpectedDataObject;
import pojo.InitialDataObject;
import pojo.ResultDataObject;
import pojo.SocketCommunicationConfirmData;

public interface DataBaseMethodHandler{
	//initDBEnvironment 初始化环境
	public void initDBEnvironment();
	//将结果对比数据入库
	public boolean insertDataToDB(ResultDataObject obj);
	//閿熸枻鎷峰閿熸枻鎷烽敓鏂ゆ嫹鑿橀敓鏂ゆ嫹閿熸枻鎷疯笂閿熸枻鎷烽敓锟�	public void initDBEnvironment();
	//鍙栭敓鏂ゆ嫹鍏ㄩ敓鏂ゆ嫹閿熸枻鎷峰師閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
	public List<InitialDataObject> fetchAll();
	//閿熸枻鎷烽敓绲歛seID鍙栭敓鏂ゆ嫹閿熸枻鎷峰簲閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鑽婚敓鏂ゆ嫹閿燂拷
	public ExpectedDataObject getDataObjectById(String id);
	//閿熸枻鎷烽敓鐨嗘瘮鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�	public boolean insertDataToDB(ResultDataObject object);
	//閺屻儴顕楅崙锟絫b_initialdata鐞涖劎娈戦弫鐗堝祦闁诧拷
	public int selectIniCount();
	//鍏抽棴鎵�湁杩炴帴
	public void closeAll();
	//鍒涘缓socket閫氫俊鏁版嵁
	public void createSocketComConData();
	//鏇存柊socket閫氫俊鏁版嵁
	public void updateSocketComConData(String sql);
	//鏌ヨsocket閫氳鏁版嵁
	public SocketCommunicationConfirmData querySCCdata(int id);
	//鏌ヨ鏈�柊鐨剆ocket閫氳锛╋激
	public int getMaxSSCdataID();
	
	
}
