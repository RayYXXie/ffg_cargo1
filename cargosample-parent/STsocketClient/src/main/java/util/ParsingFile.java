package util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.DataObject;

/**
 * 
 * 将file文件数据封装为List
 *
 */
public class ParsingFile {
	
	/**
	 * 解析文件成为list
	 * @param path 文件路径
	 * @return list<DataObjcet>
	 */
    public List<DataObject> parseFileDataToList(String path){
    	List<DataObject> list = new ArrayList<DataObject>();
	    File file = new File(path);
	    BufferedReader reader = null;
	    String tempString = null;
	    int line =1;
        
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        while ((tempString = reader.readLine()) != null) {
	        	DataObject objData = new DataObject();
	        	String arr[] = tempString.split(",");
	        	if(line !=1 ){
	        	  	objData.setId(arr[0]);
		        	objData.setFix_keyword1(arr[1]);
		        	objData.setAccount(arr[2]);
		        	objData.setMsgtype(arr[3]);
		        	objData.setTransactTime(arr[4]);
		        	objData.setOnbehalfofcompid(arr[5]);
		        	objData.setQuotereqid(arr[6]);
		        	objData.setFix_keyword2(arr[7]);
		        	objData.setCurrency(arr[8]);
		        	objData.setOrderqty(arr[9]);
//		        	objData.setSide(arr[10]);
//		        	objData.setSymbol(arr[11]);
//		        	objData.setSettldate(arr[12]);
//		        	objData.setFix_keyword3(arr[13]);
//		        	objData.setOriginalchannel(arr[14]);
//		        	objData.setCincode(arr[15]);
//		        	objData.setIndirectcomptrate(arr[16]);
//		        	objData.setRequesttype(arr[17]);
//		        	objData.setTenorvalue(arr[18]);
		        	list.add(objData);
	        	}
	        	line++;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        if(reader != null){
	            try {
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    System.out.println("parseFing list size = "+list.size());
    	return list;
    	
    }
	
	
	public static void main(String[] args) {
		ParsingFile par =  new ParsingFile();
		List<DataObject> list = par.parseFileDataToList("D:\\TempFile\\FIX_R.csv");
		DataObject obj = (DataObject)list.get(0);
		System.out.println("================size======================="+list.size());
		System.out.println("========Obj 信息============"+obj.getAccount() + obj.getId());
	}

}
