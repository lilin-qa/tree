package com.dt.tree.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	
	public static void main(String[] args) {
		/**System.out.println(md5("31119@qq.com"+"123456"));
		System.out.println(md5("mj1"));**/
//		Scanner scan=new Scanner(System.in);
//       int nums[]={11,22,3};
//		List<Integer> liInt=new ArrayList<Integer>();
//
//       int target=scan.nextInt();
//
//		 boolean flag=liInt.contains(target);
//		if (){
//
//		}
//       for (int i=0;i<nums.length;i++){
//          if (target==nums[i]){
//          	System.out.println("符合得下标为："+);
//
//		  }
//
//	   }
//		liInt.add(target);

	}
	/**
	 * 
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
