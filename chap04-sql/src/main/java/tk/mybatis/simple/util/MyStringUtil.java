package tk.mybatis.simple.util;

/**
 * ognl中@class@method(args) 类名调静态方法
 */
public class MyStringUtil {

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static void print(Object parameter) {
		System.out.println("参数是parameter = "+parameter);
	}
}
