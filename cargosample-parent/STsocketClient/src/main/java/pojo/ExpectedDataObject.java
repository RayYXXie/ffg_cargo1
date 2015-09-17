package pojo;
/**
 * 期望数据 POJO
 * @author zhaorong.liang
 *
 */
public class ExpectedDataObject {
	// 主键
	private int id;
	// case ID　
	private String dataId;
	// 账户
	private String account;
	// case 类型
	private String msgType;
	// 交易时间
	private String transactTime;
	// 公司 id
	private String onbehalfOfCompId;
	// 数据改动时间
	private String modifyTime;
	// 对比期望值
	private float expectedValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getTransactTime() {
		return transactTime;
	}
	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}
	public String getOnbehalfOfCompId() {
		return onbehalfOfCompId;
	}
	public void setOnbehalfOfCompId(String onbehalfOfCompId) {
		this.onbehalfOfCompId = onbehalfOfCompId;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public float getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	

}
