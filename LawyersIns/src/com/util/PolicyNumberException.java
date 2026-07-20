package com.util;

public class PolicyNumberException extends Exception {
	
	public PolicyNumberException()
	{
		super("Unable to update Policy Number in database");
	}
}
