/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes a
	 * bottleneck in your code, you may wish to maintain a pool of MessageDigest
	 * objects instead of using this method. Every time a user logs in, we
	 * simply take their plain text password, compute the hash, and compare the
	 * generated hash to the stored hash. Since it is almost impossible that two
	 * passwords will generate the same hash, we know if the user gave us the
	 * correct password or not. The only negative to this system is that
	 * password recovery is basically impossible. Therefore, a reset password
	 * method is used instead.
	 * 
	 * @param data
	 *            the String to compute the hash of.
	 * @return a hashed version of the input String
	 */

	public static final String hash(String data) {
		MessageDigest digest = null;
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. "
						+ "Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number.
	 * <p>
	 * Method by Santeri Paavolainen, Helsinki Finland 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param 欲转换的金额�?
	 * @return 大写金额�?
	 */
//	public static String transToUpperOfMoney(String s) {
//		String money = "";// 转换后的结果
//		String num = "零壹贰叁肆伍陆柒捌玖";
//		String[] unit = { "�?, "�?, "�?, "�?, "�?, "拾万", "佰万", "仟万", "�?, "拾亿",
//				"佰亿", "仟亿" };
//		
//		int a = s.indexOf("+");// 判断s是否包含'+',�?.67E+4
//		int e = s.indexOf("E");// 判断s是否包含'E',�?.67E+4
//		// 如果包含'E'(该金额是以科学记数法表示,则转换成普�?表示�?
//		if (e != -1) {
//			int index = 0;// 指数�?
//			if (a == -1)
//				index = Integer.parseInt(s.substring(e + 1));// 取得指数�?
//			else
//				index = Integer.parseInt(s.substring(a + 1));// 取得指数�?
//			String sub1 = s.substring(0, e);// 取得尾数�?
//			int dot = sub1.indexOf(".");// 尾数的小数点位置
//			// 如果不含有小数点,则在后面补index�?0'
//			if (dot == -1) {
//				for (int i = 1; i <= index; i++) {
//					s = sub1 + "0";
//				}
//			} else {// 如果含有小数�?则向后移动小数点index�?
//				String sub11 = sub1.substring(0, dot);// 小数点前面的字串
//				String sub12 = sub1.substring(dot + 1);// 小数点后面的字串
//				if (index >= sub12.length()) {
//					int j = index - sub12.length();
//					for (int i = 1; i <= j; i++) {
//						sub12 = sub12 + "0";
//					}
//				} else {
//					sub12 = sub12.substring(0, index) + "."
//							+ sub12.substring(index);
//				}
//				s = sub11 + sub12;
//			}
//		}
//		int sdot = s.indexOf(".");// s中小数点的位�?
//		String beforeDot = "";// 小数点前面的字串
//		String afterDot = "";// 小数点后面的字串
//		// 如果包含小数�?
//		if (sdot != -1) {
//			beforeDot = s.substring(0, sdot);
//			afterDot = s.substring(sdot + 1);
//		} else {// 不包含小数点
//			beforeDot = s;
//		}
//		int bl = beforeDot.length();
//		boolean zero = false;// 数字是否为零
//		int z = 0;// '0'的个�?
//		// 逐位取数�?
//		for (int j = 0, i = bl - 1; j <= bl - 1; j++, i--) {
//			int number = Integer.parseInt(String.valueOf(beforeDot.charAt(j)));
//			if (number == 0) {
//				zero = true;
//				z++;
//			} else {
//				zero = false;
//				z = 0;
//			}
//			if (zero && z == 1) {
//				money += "�?;
//			} else if (zero) {
//
//			} else if (!zero) {
//				money += num.substring(number, number + 1) + unit[i];
//			}
//		}
//		// 删去多余�?�?�?�?
//		for (int i = 1; i <= 2; i++) {
//			String ss = "";
//			if (i == 1)
//				ss = "�?;
//			else
//				ss = "�?;
//			int last = money.lastIndexOf(ss);
//			if (last != -1) {
//				String moneySub1 = money.substring(0, last);
//				String moneySub2 = money.substring(last, money.length());
//				int last2 = moneySub1.indexOf(ss);
//				while (last2 != -1) {
//					moneySub1 = moneySub1.substring(0, last2)
//							+ moneySub1
//									.substring(last2 + 1, moneySub1.length());
//					last2 = moneySub1.indexOf(ss);
//				}
//				money = moneySub1 + moneySub2;
//			}
//		}
//		// money中是否包�?�?
//		int yuan = money.indexOf("�?);
//		// 如果不包�?�?
//		if (yuan == -1) {
//			int zi = money.lastIndexOf("�?);
//			// 如果�?���?��字符�?�?,且长度大�?（非零）,则删除它
//			if ((zi == money.length() - 1) && (money.length() != 1)) {
//				money = money.substring(0, money.length() - 1);
//			}
//			money += "�?; // 在money�?��加上'�?
//		}
//		// 如果小数点后面的字串不为�?则处�?�?,'�?
//		if (!afterDot.equals("")) {
//			int al = afterDot.length();
//			if (al > 2) {// 如果字串长度大于2,则截�?
//				afterDot = afterDot.substring(0, 2);
//				al = afterDot.length();
//			}
//			// 如果字符串不�?0'�?00',则处�?否则不进行处�?
//			if (!afterDot.equals("0") && !afterDot.equals("00")) {
//				// 逐位取得字符
//				for (int i = 0; i < al; i++) {
//					int number = Integer.parseInt(String.valueOf(afterDot
//							.charAt(i)));
//					if (number != 0 && i == 0) {
//						money += num.substring(number, number + 1) + "�?;
//					} else if (number != 0 && i == 1) {
//						money += num.substring(number, number + 1) + "�?;
//					} else if (number == 0 && i == 0) {
//						money += "�?;
//					}
//				}
//			}
//		}
//		// 如果不包�?�?,'�?则在�?��加上'�?�?
//		if (money.indexOf("�?) == -1 && money.indexOf("�?) == -1)
//			money += "�?;
//		return money;
//	}

}