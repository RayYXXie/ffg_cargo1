package util;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import junit.framework.Assert;

import org.apache.log4j.Logger;

import pojo.InitialDataObject;

public class CommonUtils {
	
		

	/**
	 * ��ʼ��case�ɹ�״̬Map value Ĭ����Ϊ1���ɹ���
	 * 
	 * @param list
	 * @param map
	 */
	public static void fillStatusMap(List<InitialDataObject> list,
			ConcurrentMap<String, String> map) {

		for (int i = 0; i < list.size(); i++) {
			String caseId = list.get(i).getDataId();
			if (caseId == null)
				continue;
			map.put(list.get(i).getDataId(), "1");
		}

	}

	/**
	 * �����յĽ����ӡ��log4j����־�ļ���
	 * @param map
	 * @param logger
	 */
	public static void assertStatusMapValue(ConcurrentMap<String, String> map, Logger logger) {
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> e = iterator.next();
			String key = e.getKey();
			String value = e.getValue();
			Assert.assertEquals(value, "1");
			value = value.equals("1")?"�ɹ�":"ʧ��";
			logger.info("caseId Ϊ"+key+"��Case����Խ��Ϊ:"+value);
		}

	}

}
