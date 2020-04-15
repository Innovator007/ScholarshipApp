package com.scholarship.utils;

import javax.servlet.http.HttpSession;

public class CheckLoggedIn {
   
	public static Boolean isLoggedIn(HttpSession session) {
		if (session == null) {
			return false;
		} else if ((session.getAttribute("isLoggedIn") == null) || (!(Boolean)session.getAttribute("isLoggedIn"))) {
			return false;
		} else if (session != null && Boolean.compare((Boolean)session.getAttribute("isLoggedIn"), true) == 0){
			return true;
		} else {
			return false;
		}
	}

}
