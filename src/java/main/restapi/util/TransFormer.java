package restapi.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TransFormer {
	public static String upperInit(String letter) {

		int  firstCode = (int)letter.charAt(0);
		if(firstCode >=97 && firstCode <=122) {
			firstCode -= 32;
		}

		return (char)firstCode+letter.substring(1);
	}

	public static Map convertToMap(Object obj) throws Exception{

		Map map = new HashMap();
		Field[] fields = obj.getClass().getFields();

		for(Field field : fields) {
			String key = field.getName();

			if(field.get(obj) != null) {
				map.put(key, field.get(obj).toString());
			}

		}

		return map;
	}

	public static String getBigUnderScore(String letter) {

		return letter.replaceAll("(.)(\\p{Upper})", "$1_$2")
					.toUpperCase();
	}
}
