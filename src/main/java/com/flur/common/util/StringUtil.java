package com.flur.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类主要为为string的辅助类,一些公共的方法
 */
public class StringUtil {

	public static String stringNotNullReturn(Object str) {
		if (str != null) {
			return str.toString();
		} else {
			return "";
		}
	}

	/**
	 * 将字符串进行不同编码之间进行转换
	 * 
	 * @param str
	 * @param sourceEncode
	 *            ISO8859-1 GBK
	 * @param targetEncode
	 *            ISO8859-1 GBK
	 */
	public static String changeEncode(String str, String sourceEncode,
			String targetEncode) {
		String tempStr = null;
		try {
			tempStr = new String(str.getBytes(sourceEncode), targetEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {

		}
		return tempStr;
	}

	/**
	 * 
	 * 将字符串转换为特定编码字节
	 * 
	 * @param str
	 * @param encodingName
	 *            ISO8859-1 GBK 等
	 * @return
	 */
	public static byte[] getStringSpecialEncode(String str, String encodingName) {
		byte[] bt = null;
		try {
			bt = str.getBytes(encodingName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bt;
	}

	/**
	 * 
	 * 将字节转为UTF-8编码的字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String newStringUtf8(byte[] bytes) {
		String str = null;
		try {
			str = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 替换所有字符串中的空格
	 */
	public static String replaceAllSpace(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s");
			Matcher m = p.matcher(str);
			return m.replaceAll("");
		} else {
			return null;
		}

	}

	/**
	 * MD5加密
	 * 
	 * @param strSrc
	 * @return
	 */
	public static String md5Encrypt(String strSrc) {
		MessageDigest md = null;
		// 加密后的字符串
		String strDes = null;
		// 要加密的字符串字节型数组
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(bt);
			// 通过执行诸如填充之类的最终操作完成哈希计算
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm./n" + e.getMessage());
			return null;
		}
		return strDes;
	}

	/**
	 * 将字节数组转换成16进制的字符串
	 * 
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 格式化消息字符串
	 * 
	 * @param message
	 * @param args
	 *            可变参数，可以为多个
	 * @return
	 */
	public static String formatMessage(String message, String... args) {
		for (int i = 0; i < args.length; i++) {
			message = message.replace("{" + i + "}", args[i]);
		}
		return message;
	}

	/**
	 * 将\r\n换行符替换成html中的<br/>
	 * ，用于显示 用于在textare中输入的值在前台显示时
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBreakToHtml(String str) {
		if (Validator.notEmpty(str)) {
			return str.replace("\r\n", "<br/>").replace("\n", "<br/>");
		} else {
			return "";
		}
	}

	public static String replaceBreakToNullStr(String str) {
		if (Validator.notEmpty(str)) {
			return str.replace("\r\n", "").replace("\n", "");
		} else {
			return "";
		}
	}

	/**
	 * 获取字符串编码
	 * 
	 * @param str
	 *            根据中文2个字节，英文1个字节计算长度
	 */
	public static int getByteSize(String str) {
		if (Validator.isEmpty(str)) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (isChinese(ch)) {
				count += 2;
			} else {
				count += 1;
			}
		}
		return count;
	}

	/**
	 * 判断字符是否是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否有中文
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串按字节长度的截取 中文2个长度
	 * 
	 * @param str
	 * @param length
	 * @param suffix
	 *            后缀，默认为省略号
	 * @return
	 */
	public static String getSubString(String str, int length, String suffix) {
		if (getByteSize(str) <= length) {
			return str;
		} else {
			int tempeLength = 0;
			String retutnStr = "";
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (isChinese(ch)) {
					tempeLength += 2;
				} else {
					tempeLength += 1;
				}
				if (tempeLength <= length) {
					retutnStr += String.valueOf(ch);
				}
			}
			if (Validator.isEmpty(suffix)) {
				suffix = "...";
			}
			return retutnStr + suffix;
		}
	}

	/**
	 * 字符串按字节长度的截取 中文1个长度
	 * 
	 * @param str
	 * @param length
	 * @param suffix
	 *            后缀，默认为省略号
	 * @return
	 */
	public static String subString(String str, int length, String suffix) {
		if (str == null) {
			return "";
		} else {
			if (str.length() <= length) {
				return str;
			} else {
				return str.substring(0, length) + suffix;
			}
		}
	}

	/**
	 * 比较两个字符串，不必担心null的问题
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compare(Object str1, Object str2) {
		if (Validator.notEmpty(str1)) {
			return str1.equals(str2);
		} else {
			return false;
		}
	}

}
