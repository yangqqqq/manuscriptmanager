package utils;

public class StringUtils {
	private final static int SUMMARY_LENGTH = 35; 
	
	private StringUtils()
	{
		//禁止实例化
	}
	
	public static String getSummary(String content)
	{
		if (!hasText(content))
		{
			return "";
		}
		
		String firstLine = content;
		int summaryIndex = content.indexOf("\r");
		if (summaryIndex != -1)
		{
			firstLine = content.substring(0, summaryIndex);
		}
		int length = SUMMARY_LENGTH < firstLine.length() ? SUMMARY_LENGTH : firstLine.length();
		
		return firstLine.substring(0, length);
	}
	
	public static boolean hasText(String str)
	{
		if (str == null || str.equals(""))
		{
			return false;
		}
		
		return true;
	}
	
	public static String parseJavaStr2Html(String textStr) {
		textStr = textStr.replaceAll("&acute;", "\'");
		textStr = textStr.replaceAll("&quot;", "\"");
		textStr = textStr.replaceAll("&lt;", "<");
		textStr = textStr.replaceAll("&gt;", ">");
		textStr = textStr.replaceAll("&nbsp;", " ");
		textStr = textStr.replaceAll("&amp;", "&");
		
		textStr = textStr.replaceAll("&acute;", "\'");
		textStr = textStr.replaceAll("&quot;", "\"");
		textStr = textStr.replaceAll("&lt;", "<");
		textStr = textStr.replaceAll("&gt;", ">");
		textStr = textStr.replaceAll("&nbsp;", " ");
		textStr = textStr.replaceAll("&amp;", "&");
		return textStr;
	}
	
	public static String getFirstStr(String str, int length)
	{
		if (str.length() <= length)
		{
			return str;
		}
		if (length <= 0)
		{
			return "";
		}
		return str.substring(0, length);
	}
}
