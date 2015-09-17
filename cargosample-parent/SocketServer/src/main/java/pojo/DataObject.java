package pojo;

import java.io.Serializable;
/**
 * 本地 FIX_R.csv文件 条记录
 *
 */
public class DataObject implements Serializable {
	
	private static final long serialVersionUID = -2098932579225076729L;
	private String id;
	private String fix_keyword1;
	private String account;
	private String msgtype;
	private String transactTime;
	private String onbehalfofcompid;
	private String quotereqid;
	private String fix_keyword2;
	private String currency;
	private String orderqty;
	private String side;
	private String symbol;
	private String settldate;
	private String fix_keyword3;
	private String originalchannel;
	private String cincode;
	private String indirectcomptrate;
	private String requesttype;
	private String tenorvalue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFix_keyword1() {
		return fix_keyword1;
	}

	public void setFix_keyword1(String fix_keyword1) {
		this.fix_keyword1 = fix_keyword1;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getOnbehalfofcompid() {
		return onbehalfofcompid;
	}

	public void setOnbehalfofcompid(String onbehalfofcompid) {
		this.onbehalfofcompid = onbehalfofcompid;
	}

	public String getQuotereqid() {
		return quotereqid;
	}

	public void setQuotereqid(String quotereqid) {
		this.quotereqid = quotereqid;
	}

	public String getFix_keyword2() {
		return fix_keyword2;
	}

	public void setFix_keyword2(String fix_keyword2) {
		this.fix_keyword2 = fix_keyword2;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrderqty() {
		return orderqty;
	}

	public void setOrderqty(String orderqty) {
		this.orderqty = orderqty;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSettldate() {
		return settldate;
	}

	public void setSettldate(String settldate) {
		this.settldate = settldate;
	}

	public String getFix_keyword3() {
		return fix_keyword3;
	}

	public void setFix_keyword3(String fix_keyword3) {
		this.fix_keyword3 = fix_keyword3;
	}

	public String getOriginalchannel() {
		return originalchannel;
	}

	public void setOriginalchannel(String originalchannel) {
		this.originalchannel = originalchannel;
	}

	public String getCincode() {
		return cincode;
	}

	public void setCincode(String cincode) {
		this.cincode = cincode;
	}

	public String getIndirectcomptrate() {
		return indirectcomptrate;
	}

	public void setIndirectcomptrate(String indirectcomptrate) {
		this.indirectcomptrate = indirectcomptrate;
	}

	public String getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}

	public String getTenorvalue() {
		return tenorvalue;
	}

	public void setTenorvalue(String tenorvalue) {
		this.tenorvalue = tenorvalue;
	}

	public String toString() {
		return "DataObject [id=" + id + ", fix_keyword1=" + fix_keyword1
				+ ", account=" + account + ", msgtype=" + msgtype
				+ ", transactTime=" + transactTime + ", onbehalfofcompid="
				+ onbehalfofcompid + ", quotereqid=" + quotereqid
				+ ", fix_keyword2=" + fix_keyword2 + ", currency=" + currency
				+ ", orderqty=" + orderqty + ", side=" + side + ", symbol="
				+ symbol + ", settldate=" + settldate + ", fix_keyword3="
				+ fix_keyword3 + ", originalchannel=" + originalchannel
				+ ", cincode=" + cincode + ", indirectcomptrate="
				+ indirectcomptrate + ", requesttype=" + requesttype
				+ ", tenorvalue=" + tenorvalue + ", getId()=" + getId()
				+ ", getFix_keyword1()=" + getFix_keyword1()
				+ ", getAccount()=" + getAccount() + ", getMsgtype()="
				+ getMsgtype() + ", getTransactTime()=" + getTransactTime()
				+ ", getOnbehalfofcompid()=" + getOnbehalfofcompid()
				+ ", getQuotereqid()=" + getQuotereqid()
				+ ", getFix_keyword2()=" + getFix_keyword2()
				+ ", getCurrency()=" + getCurrency() + ", getOrderqty()="
				+ getOrderqty() + ", getSide()=" + getSide() + ", getSymbol()="
				+ getSymbol() + ", getSettldate()=" + getSettldate()
				+ ", getFix_keyword3()=" + getFix_keyword3()
				+ ", getOriginalchannel()=" + getOriginalchannel()
				+ ", getCincode()=" + getCincode()
				+ ", getIndirectcomptrate()=" + getIndirectcomptrate()
				+ ", getRequesttype()=" + getRequesttype()
				+ ", getTenorvalue()=" + getTenorvalue() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
