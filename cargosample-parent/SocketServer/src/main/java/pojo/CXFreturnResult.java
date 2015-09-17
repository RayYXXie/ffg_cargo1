package pojo;

import java.io.Serializable;

/**
 * 调用 CXF返回的结果
 * 
 * @author zhaorong.liang
 * 
 */
@SuppressWarnings("serial")
public class CXFreturnResult implements Serializable {
	
	// 交易返回码
	private String resCode;
	// 交易返回信息
	private String returnMsg;
	// case ID
	private String dataId;
	// case Type
	private String msgType;
	// 账户 ID
	private String account;
	// 交易时间
	private String transactTime;
	// 公司ID
	private String onBehalfOfCompID;
	// 对比结果期望值
	private String expectedValue;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public String getOnBehalfOfCompID() {
		return onBehalfOfCompID;
	}

	public void setOnBehalfOfCompID(String onBehalfOfCompID) {
		this.onBehalfOfCompID = onBehalfOfCompID;
	}

}
