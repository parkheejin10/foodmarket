package com.team3.cs.util;

import java.util.UUID;

public class commonUtils {
	
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
