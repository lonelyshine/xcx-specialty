package com.chunyang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密解密（主要应用MD5不可逆特性，但是也会存在暴力破解功能，这里将使用前后切割的形式防止暴力破解）
 * @author qinxuegang
 *
 */
public class MD5Util {
	/**
	 * MD5加密方法
	 * @param str不允许为null  传入的需要进行MD5加密的String字符串,
	 * @return MD5加密后的字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String EncodeByMD5(String str) throws NoSuchAlgorithmException{
		//获取一个信息摘要器
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		//通过将字符串填充到摘要器中并进行hash计算得到一个长度为16的byte数组
		byte[] weitHandleByte = messageDigest.digest(str.getBytes());
		//临时存放weitHandleByte
		int tempWeitHandleByte;
		//创建一个StringBuffer 用于装在载加密后的字符
		StringBuffer sb = new StringBuffer();
		//遍历获取到的byte数组，生成具体的MD5密码
		for (byte b : weitHandleByte) {
			tempWeitHandleByte = b;
			if(tempWeitHandleByte<0)
				tempWeitHandleByte+=265;
			if(tempWeitHandleByte<16)
				sb.append("0");
			sb.append(Integer.toHexString(tempWeitHandleByte));
		}
		return getBegginAndEndStr(sb.toString().substring(8, 24));
	}
	
	/**
	 * @param str不允许为null 需要核对的字符串
	 * @param oldStr str经过MD5加密后为字符串
	 * @return 返回对比结果
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean DecodeByMD5(String str,String oldStr) throws NoSuchAlgorithmException{
		String temp = EncodeByMD5(str); 
		if(temp.startsWith(oldStr.substring(0, 5))&&temp.endsWith(oldStr.substring(11,16)))
			return true;
		else
			return false;
	}
	
	/**
	 * 为了防止暴力破解密码，因此需要经过截取前5个字符和后5个字符以及随机生成的6个字符拼接字符串
	 * @param str MD5加密后的字符串
	 * @return 返回经过截取前5个字符和后5个字符以及随机生成的6个字符拼接而成的字符串
	 */
	private static String getBegginAndEndStr(String str){
		//截取MD5加密后的前5个字符
		String beggin = str.substring(0,5);
		//截取MD5加密后的后5个字符
		String end = str.substring(11,16);
		//创建6个随机的字符
		String middle="";
		for(int i=0;i<3;i++)
			middle = middle+Integer.toHexString((int)(16+Math.random()*(265-16+1)));
		return beggin+middle+end;
	}
	
	public static void main(String args[]) throws NoSuchAlgorithmException{
		System.out.println(EncodeByMD5("adc"));
		System.out.println(DecodeByMD5("adc", "eb0e999e65d4a710"));
	}
}


