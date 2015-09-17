package pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResultDataObject implements Serializable {

	// 主键
	private int id;
	// case ID
	private String dataId;
	// 账户ID
	private String account;
	// case 类型
	private String msgType;
	// 交易时间
	private String transactTime;
	// 公司ID
	private String onbehalfOfCompId;
	// 期望值
	private float expectedValue;
	// 实际交易结果
	private float actuallyValue;
	// 修改(插入)时间
	private String modifyTime;
	// 对比结果 1 对比通过 2 对比不通过 3其他
	private String result;

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

	public float getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	public float getActuallyValue() {
		return actuallyValue;
	}

	public void setActuallyValue(float actuallyValue) {
		this.actuallyValue = actuallyValue;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
