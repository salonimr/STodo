// Name: Saloni Raythatha //
// Codepath Todo App Project //

package com.example.todotask;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private static final int EDIT_REQUEST = 991; 
	private static final int DELETE_ACCESS = 992;
	private WholeData tempData;
	List<NoteTools> listItem;
	// get the id for the position taped by user//
	private int CurrentPos; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerForContextMenu(getListView());  // when user presses this will help send a request//
		tempData = new WholeData(this);
		SimpleDisplay();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu); // helps with the action bar
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.activate) {
			createNote();
	}
		return super.onOptionsItemSelected(item);
	}

// when the user presses the add button the below code get's it to create the note//
	
	private void createNote() {
		// TODO Auto-generated method stub
		NoteTools temp = NoteTools.getNew();
		Intent intent = new Intent(this, ForEditing.class);
		intent.putExtra("key", temp.getKey());
		intent.putExtra("text", temp.getText());
		startActivityForResult(intent, EDIT_REQUEST); 
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 // to edit // 
     NoteTools temp = listItem.get(position);	
     Intent intent = new Intent(this, ForEditing.class);
		intent.putExtra("key", temp.getKey());
		intent.putExtra("text", temp.getText());
		startActivityForResult(intent, EDIT_REQUEST);
	
	}
	
// This check for the request code in both the activites and let's it display the result//	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
		if (requestCode == EDIT_REQUEST && resultCode == RESULT_OK) 
		{
			NoteTools temp = new NoteTools();
			temp.setKey(data.getStringExtra("key"));
			temp.setText(data.getStringExtra("text"));
			tempData.update(temp);
			SimpleDisplay();

		}
	}
// ____ For deleting the note _____//	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		CurrentPos = (int)info.id;
		menu.add(0, DELETE_ACCESS, 0, "Delete!!!"); // tab for deletion
			
	}
	
	@Override
	
	// to delete the item by passing the id and matching the id//
	public boolean onContextItemSelected(MenuItem item) 
	{
		
		if (item.getItemId() == DELETE_ACCESS) 
		{
			NoteTools note = listItem.get(CurrentPos);
			tempData.remove(note);
			SimpleDisplay();
			Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();

		}
		
		return super.onContextItemSelected(item);
	}
	
// for the listview//	
	private void SimpleDisplay() {
		
		listItem = tempData.findAll();
		ArrayAdapter<NoteTools> adapter = new ArrayAdapter<NoteTools>(this, R.layout.activity_3,listItem);
		setListAdapter(adapter);
	}

	
}


