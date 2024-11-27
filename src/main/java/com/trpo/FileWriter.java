package com.trpo;

import java.io.File;

public class FileWriter extends Writer
{
	private final File file;

	public FileWriter(String FileUrl)
	{
		file = new File(FileUrl);
	}

	public void write(String endMsg)
	{
		try(java.io.FileWriter writer = new java.io.FileWriter(file))
		{
			writer.write(sBuilder.toString());

			writer.write(endMsg);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
}
