package com.team3.cs.util;

import java.util.UUID;

public class CommonUtils {
	
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
