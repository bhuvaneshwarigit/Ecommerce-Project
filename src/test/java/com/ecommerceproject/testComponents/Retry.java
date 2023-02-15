package com.ecommerceproject.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
     int c = 0;
     int maxtry= 1;
	public boolean retry(ITestResult result) {
		if (c<maxtry) {
			c++;
			return true;
		}
		return false;
	}

}
