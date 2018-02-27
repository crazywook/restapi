package restapi.util;

public class HtmlConverter {

	public static String decode(String str){
		if(str == null) return null;
		String returnStr = str;
		returnStr = returnStr.replaceAll("&gt;", ">");
		returnStr = returnStr.replaceAll("&lt;", "<");
		returnStr = returnStr.replaceAll("&amp;", "&");
		returnStr = returnStr.replaceAll("&quot;", "\"");
		returnStr = returnStr.replaceAll("&apos;", "\'");
		return returnStr;
	}
}