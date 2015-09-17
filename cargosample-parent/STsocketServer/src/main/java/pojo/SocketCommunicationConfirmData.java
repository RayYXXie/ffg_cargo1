package pojo;

public class SocketCommunicationConfirmData {

	//socket通讯确认data ID
	private int id;
	//创建时间
	private String create_time;
	//通讯状态
	private String communication_status;
	//是否成功启动客户端
	private String start_status;
	//是否跑完全部TestCase
	private String success_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCommunication_status() {
		return communication_status;
	}
	public void setCommunication_status(String communication_status) {
		this.communication_status = communication_status;
	}
	public String getStart_status() {
		return start_status;
	}
	public void setStart_status(String start_status) {
		this.start_status = start_status;
	}
	public String getSuccess_status() {
		return success_status;
	}
	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}
	
}
