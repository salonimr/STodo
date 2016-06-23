package com.example.todotask;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteTools {
	
	private String key;
	private String text;
	public  String getKey() 
	{
		return key;
	}
	public  void setKey(String key)
	{
		this.key = key;  // helps get the id to update and deleted the list item clicked by user//
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	
@SuppressLint("SimpleDateFormat")

	public static NoteTools getNew()
	{
		Locale localedate = new Locale("en_US"); // this help to get the date&time
		Locale.setDefault(localedate);
  
		String pattern = "yyyy-MM-dd HH:mm:ss Z";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String key = formatter.format(new Date());
	 
		NoteTools temp = new NoteTools();
		temp.setKey(key);
		temp.setText("");
		return temp;
	
	}
@Override
public String toString() 
{
	return this.getText();
}
}
