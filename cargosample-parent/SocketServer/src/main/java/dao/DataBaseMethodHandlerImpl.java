package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.ExpectedDataObject;
import pojo.InitialDataObject;
import pojo.ResultDataObject;
import pojo.SocketCommunicationConfirmData;
import util.DateFormatUtil;
import util.ReadProperties;

public class DataBaseMethodHandlerImpl implements DataBaseMethodHandler {
	
	final static String url = "jdbc:mysql://192.168.4.61:3306/test";
	final static String userName = "root";
	final static String password = "";
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement statement = null;

	/**
	 * 
	 * 閿熸枻鎷峰閿熸枻鎷烽敓鏂ゆ嫹鑿橀ィ鍑ゆ嫹閿燂拷
	 * 
	 */
	public void initDBEnvironment() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 閿熸枻鎷峰師閿熸枻鎷烽敓鏂ゆ嫹閿熼ズ顐嫹閿熼ズ鈽呮嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熼樁甯嫹姊呰閿熺祪ist閿熸枻鎷�
	 * 
	 */
	public List<InitialDataObject> fetchAll() {
		List<InitialDataObject> list = null;
		try {
			String sql = "select * from TB_INITIALDATA";
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			ReadProperties rp = new ReadProperties();
			//rp.setPropertiesFilePath("//root//ffg_runhome//util//CaseTypeHttp.properties");
			rp.setPropertiesFilePath("CaseTypeHttp.properties");
			//rp.setPropertiesFilePath("D:\\newWorkSpace\\HSBCTestAgent\\src\\util\\CaseTypeHttp.properties");
			if (rs != null) {
				list = new ArrayList<InitialDataObject>();
				while (rs.next()) {
					
					InitialDataObject object = new InitialDataObject();
					int id = rs.getInt("id");
					String dataId = rs.getString("dataid")==null?" ":rs.getString("dataid");
					String account =rs.getString("account")==null?" ":rs.getString("account");
					String msgType = rs.getString("msgtype")==null?" ":rs.getString("msgtype");
					String url = rp.getKeyValue(msgType)==null?" ":rp.getKeyValue(msgType);
					String time = rs.getString("transacttime")==null?" ":rs.getString("transacttime");
					String comId = rs.getString("onbehalfofcompid")==null?" ":rs.getString("onbehalfofcompid");
					String modifyTime = rs.getString("modifytime")==null?" ":rs.getString("modifytime");
					
					object.setId(id);
					object.setDataId(dataId);
					object.setAccount(account);
					object.setCxfUrl(url);
					object.setMsgType(msgType);
					//object.setModifyTime(time);
					object.setModifyTime("20150201");
					object.setOnbehalfOfCompId(comId);
					//object.setTransactTime(modifyTime);
					object.setTransactTime("20150812");
					object.setMsgType(msgType);
					
					list.add(object);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeAll();
		}
		return list;
	}

	
	/**
	 * 閫氶敓鏂ゆ嫹caseId 閿熸枻鎷烽敓鏂ゆ嫹鑿橀敓鏂ゆ嫹鑳侀敓绐栴垽鎷烽敓鏂ゆ嫹閿熸帴锔兼嫹閿熸枻鎷烽敓鏂ゆ嫹閿熺禋ase閿熸枻鎷烽敓锟�	 */
	public ExpectedDataObject getDataObjectById(String id) {
		
		String sql = "select * from TB_EXPECTEDDATA where dataId = ?";
		ExpectedDataObject object= null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			rs = statement.executeQuery();
			if(rs != null){
				object = new ExpectedDataObject();
				while(rs.next()){
					object.setId(rs.getInt("id"));
					object.setDataId(rs.getString("dataid"));
					object.setAccount(rs.getString("account"));
					object.setModifyTime(rs.getString("transacttime"));
					object.setOnbehalfOfCompId(rs.getString("onbehalfofcompid"));
					object.setTransactTime(rs.getString("modifytime"));
					object.setExpectedValue(Float.parseFloat(rs.getString("expectedvalue")));
					object.setModifyTime(rs.getString("modifytime"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			this.closeAll();
		}
		
		return object;
	}
	
	
	/**
	 * 閿熸枻鎷烽敓鐨嗘瘮鏂ゆ嫹閿熸枻鎷烽敓璇埌閿熸枻鎷疯彉閿熸枻鎷稵B_COMPARISONRESULT閿熸枻鎷烽敓鏂ゆ嫹
	 */
	public boolean insertDataToDB(ResultDataObject object) {
		
		boolean retValue = false;
		String sql = "insert into TB_COMPARISONRESULT(dataid,account,msgtype,transacttime,onbehalfofcompid," +
				"expectedvalue,actuallyvalue,modifytime,result) values (?,?,?,?,?,?,?,?,?)";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, object.getDataId());
			statement.setString(2, object.getAccount());
			statement.setString(3, object.getMsgType());
			statement.setString(4, object.getTransactTime());
			statement.setString(5, object.getOnbehalfOfCompId());
			statement.setFloat(6, object.getExpectedValue());
			statement.setFloat(7, object.getActuallyValue());
			statement.setString(8, object.getModifyTime());
			statement.setString(9, object.getResult());
			retValue = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			this.closeAll();
			
		}
		return retValue;
	}

	/**
	 * 
	 * 閿熸枻鎷烽『閿熸枻鎷蜂箛閿熸枻鎷烽敓鏂ゆ嫹姊伴敓鏂ゆ嫹閿熺殕杈炬嫹閿熸枻鎷烽敓锟�	 * 
	 */
	public void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 閸欐牕鍤璗B_INITIALDATA鐞涖劍鏆熼幑顕�櫤閻ㄥ嫬銇囩亸锟�	 */
	public int selectIniCount() {
		int tableSize = 0;
		String sql = "select count(1) from TB_INITIALDATA";
		try {
			statement = conn.prepareStatement(sql);
			rs=statement.executeQuery();
			if(rs != null){
				while(rs.next()){
					
					tableSize = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return tableSize;
	}
	
	
	
	
	/**
	 * 鍒涘缓socket閫氫俊鏁版嵁
	 */
	public void createSocketComConData() {
		String create_Time = DateFormatUtil.getFormatNowTime("yyyy-MM-DD hh:MM:ss");
		String sql = "insert into tb_socketcommunicationconfirm(create_time,communication_status,start_status,success_status) values("
				+ "'"+create_Time+"','0','0','0')";
		try {
			statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 鏇存柊socket閫氫俊鏁版嵁
	 */
	public void updateSocketComConData(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 鏌ヨsocket閫氳鏁版嵁
	 */
	public SocketCommunicationConfirmData querySCCdata(int id) {
		String sql = "select * from tb_socketcommunicationconfirm where id="+id;
		SocketCommunicationConfirmData data = null;
		try {
			statement = conn.prepareStatement(sql);
			rs =statement.executeQuery();
			if(rs  != null){
				data = new SocketCommunicationConfirmData();
				while(rs.next()){
					data.setCommunication_status(rs.getString("communication_status"));
					data.setCreate_time(rs.getString("create_time"));
					data.setId(Integer.parseInt(rs.getString("id")));
					data.setStart_status(rs.getString("start_status"));
					data.setSuccess_status(rs.getString("success_status"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}


	/**
	 * 鏌ヨ鏈�柊鐨剆ocket閫氳锛╋激
	 */
	public int getMaxSSCdataID() {
		String sql = "select max(id) id from tb_socketcommunicationconfirm";
		int maxId = 0;
		try {
			statement = conn.prepareStatement(sql);
			rs =statement.executeQuery();
			if(rs !=null){
				while(rs.next()){
					maxId = rs.getInt("id");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}

	public static void main(String args []){
		
		DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
		handler.initDBEnvironment();
		
		System.out.println(handler.querySCCdata(2).getCommunication_status());

	}

	
}
