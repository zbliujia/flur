package com.flur.common.util;

import java.util.Random;

/**
 * 此类主要为为数字类型的辅助类,一些公共的方法
 */
public class NumberUtil {
	
	/**
	 * 将float保留N位小数
	 * @param f
	 * @param decimalSize 小数点后保留N位
	 * @return
	 */
	public static float getNumberDecimalSize(Float f, int decimalSize){
		String floatStr = f.toString();
		int endIndex = 0;
		if(Float.parseFloat(floatStr) > 0){
			if(decimalSize > 0){
				endIndex = floatStr.indexOf(".") + 1 + decimalSize;
			}else{
				endIndex = floatStr.indexOf(".");
			}
		}else{
			endIndex = 1;
		}
		String newFloatStr = floatStr.substring(0, endIndex);
		return Float.parseFloat(newFloatStr);
	}
	
	/**
	 * 将小数转为整数，舍去小数部分
	 * @param f
	 * @return
	 */
	public static int floatToInt(Float f){
		String floatStr = f.toString();
		String newFloatStr = floatStr.substring(0, floatStr.indexOf("."));
		return Integer.parseInt(newFloatStr);
	}
	
	/**
	 * 将小数转为整数，舍去小数部分
	 * @param f
	 * @return
	 */
	public static int doubleToInt(Double d){
		String doubleStr = d.toString();
		String newDoubleStr = doubleStr.substring(0, doubleStr.indexOf("."));
		return Integer.parseInt(newDoubleStr);
	}
	
	/**
	 * 计算百分比，四舍五入
	 * @param divisor 除数
	 * @param dividend 被除数
	 * @return
	 */
	public static int getPercent(long divisor, long dividend){
		if(dividend <= 0){
			return 0;
		}
		float result = Float.parseFloat(String.valueOf(divisor)) / dividend;
		return Math.round(result * 100);
	}
	
	 /**
	  * java生成随机数字和字母组合
	  * @param length[生成随机数的长度]
	  * @return
	  */
	 public static String getCharAndNumr() {
		  int length = 5;
		  String val = "";
		  Random random = new Random();
		  for (int i = 0; i < length; i++) {
		   // 输出字母还是数字
		   String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
		   // 字符串
		   if ("char".equalsIgnoreCase(charOrNum)) {
		// 取得大写字母还是小写字母
		    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
		    val += (char) (choice + random.nextInt(26));
		   } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
		    val += String.valueOf(random.nextInt(10));
		   }
		  }
		  return val;
	 }
	
}
