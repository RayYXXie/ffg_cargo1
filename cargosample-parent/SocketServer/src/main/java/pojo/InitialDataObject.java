package pojo;

import java.io.Serializable;

/**
 * 原生数据 pojo
 * 
 * @author zhaorong.liang
 * 
 */
@SuppressWarnings("serial")
public class InitialDataObject implements Serializable{
	
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
	// 公司ID
	private String onbehalfOfCompId;
	// 修改时间
	private String modifyTime;
	// 访问CXF的服务器地址
	private String cxfUrl;

	public String getCxfUrl() {
		return cxfUrl;
	}

	public void setCxfUrl(String cxfUrl) {
		this.cxfUrl = cxfUrl;
	}

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

}
