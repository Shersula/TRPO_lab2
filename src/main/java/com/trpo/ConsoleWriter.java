package com.trpo;

public class ConsoleWriter extends Writer
{
	public void write(String endMsg)
	{
		System.out.println(sBuilder.toString() + endMsg);	
	}
}
