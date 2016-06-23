package com.example.todotask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import android.content.Context;
import android.content.SharedPreferences;

public class WholeData 
{
	private static final String PKEY = "notes";
	private SharedPreferences tempNotes;

// the way to connect shared preference to class..//
	public WholeData(Context context )
	{
		tempNotes = context.getSharedPreferences(PKEY, Context.MODE_PRIVATE);
	}


	public List<NoteTools> findAll()
	{
		Map<String, ?> notesMap = tempNotes.getAll();
		TreeSet<String> keys = new TreeSet<String>(notesMap.keySet());
		List<NoteTools> ListItem = new ArrayList<NoteTools>();

	for (String key : keys)  // to iterate over the whole data in the list item//
	{  
		NoteTools temp = new NoteTools();
		temp.setKey(key);
		temp.setText((String) notesMap.get(key));
		ListItem.add(temp);
		
	}
	return ListItem;
}

// update or say edit the notes taped by user//

	public boolean update(NoteTools temp)
	{
		SharedPreferences.Editor updating = tempNotes.edit();
		updating.putString(temp.getKey(), temp.getText());
		updating.commit();
		return true;
	}


// delete the notes tapped by user//
	
	public boolean remove(NoteTools temp)
	{
		if (tempNotes.contains(temp.getKey())) {
		SharedPreferences.Editor deleting = tempNotes.edit();
		deleting.remove(temp.getKey());
		deleting.commit();
	}
	return true;
	
	}
}
