package com.example.todotask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;



public class ForEditing extends Activity {
	Button btn1; // to save the data //
	private NoteTools temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_2);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE); 

		
		// Intent used for multiple activities//
		Intent intent = this.getIntent();
		temp = new NoteTools();
		temp.setKey(intent.getStringExtra("key"));
		temp.setText(intent.getStringExtra("text"));
		
		EditText et = (EditText) findViewById(R.id.noteText); // connecting java and xml id//
		btn1 = (Button) findViewById(R.id.button1);
		et.setText(temp.getText());
		et.setSelection(temp.getText().length());

	// saving item with the save button // 	
		
		btn1.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				saveAndfinish();
			}
		});
			
	}
	
	// saving item with back press as well //
	@Override
	public void onBackPressed()
	{
		saveAndfinish();
	}
		
	
	private void saveAndfinish() // save and finish returning to first activity after saving //
	{
		EditText et = (EditText) findViewById(R.id.noteText);
		String noteText = et.getText().toString();
		Intent intent = new Intent();
		intent.putExtra("key", temp.getKey());
		intent.putExtra("text", noteText);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	@Override  // for back press n save
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if (item.getItemId() == android.R.id.home)
		{
			saveAndfinish();
		}
		return false;
	}

}
