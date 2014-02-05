package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LogActivity extends Activity  implements OnClickListener {
	  EditText etText;
	  Button btnSave, btnLoad,LogGuest;
	  Intent intent;
	  
	  CheckBox checkBoxSaveName;
	  
	  static SharedPreferences mSettings;
	  Editor editor;



	  
	  SharedPreferences sPref;
	  
	  final String SAVED_TEXT = "saved_text";
	  
	  
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.log_dialog);
	        Log.d(var.TAG, "Старт экран LogActivity");

	        
	        etText = (EditText) findViewById(R.id.etText);
	        
	        btnSave = (Button) findViewById(R.id.btnSave);
	        btnSave.setOnClickListener(this);
	        
	        LogGuest = (Button) findViewById(R.id.LogGuest);
	        LogGuest.setOnClickListener(this);

	    	Log.i(var.TAG, "load name");
			mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
			String savedText = mSettings.getString("user_name", "Гость");
			etText.setText(savedText);
			Log.i(var.TAG, "Load user name - " + savedText );

		    checkBoxSaveName = (CheckBox)findViewById(R.id.checkBoxSaveName);
		    checkBoxSaveName.setOnClickListener(this);
	        
	        btnLoad = (Button) findViewById(R.id.btnLoad);
	        btnLoad.setOnClickListener(this);
	        
	        if (!var.mapBool.get("save_name")) {
	        	checkBoxSaveName.setChecked(false);
		    	Log.i(var.TAG, "var.save_name= false, убрали галочку");

	        }
	    }
	  @Override
	  public void onClick(View v) {
	    switch (v.getId()) {
	    
	    case R.id.btnSave:
	   	  Log.i(var.TAG, "btnSave");
	      saveText();
	      finish();
	      break;
	      
	    case R.id.btnLoad:
	   	 Log.i(var.TAG, "btnLoad");
	   	etText.setText("");
	      break;
	      
	    case R.id.checkBoxSaveName:
		   	 Log.i(var.TAG, "checkBoxSaveName");
		   	 
		   	 if(checkBoxSaveName.isChecked())
			  {
					 Log.i(var.TAG, "isChecked");
					 set_save_name(true);
						Toast.makeText(getApplicationContext(),
								"Можно изменить в настройках", Toast.LENGTH_LONG).show();
			  }
		      else{
		    	  Log.i(var.TAG, "NOT Checked");
		    	  set_save_name(false);
		      }
		      break;
	      
	    case R.id.LogGuest:
		   	Log.i(var.TAG, "LogGuest");
		   	//intent = new Intent();
			//intent.putExtra("name", "Гость");
			//setResult(RESULT_OK, intent);
		   	var.mapString.put("user_name", "Гость");
		   	etText.setText("Гость");
		    saveText();

			finish();
		      break;
	    
	    default:
	      break;
	    }
	  }
	  
	  void saveText() {
		  
		  Log.i(var.TAG, "Начало записи в статистику");
	        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
	        editor = mSettings.edit();
	        editor.putString("user_name", etText.getText().toString());
	    	editor.commit();
	    	//var.UserName = etText.getText().toString();
	    	var.mapString.put("user_name", etText.getText().toString());
	    	Log.i(var.TAG, "Записано name " +etText.getText().toString());
	    	
;
	  }
	  
	  void loadText() {
		  
		Log.i(var.TAG, "loadText");
		mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
		String savedText = mSettings.getString("name", "Гость");
		etText.setText(savedText);
		Log.i(var.TAG, "Load user name - " + savedText );
	  }
	  
	  
	 public void set_save_name (Boolean set) {
		    	Log.i(var.TAG, "Начало записи в статистику");
		        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
		        editor = mSettings.edit();
		    	editor.putBoolean("save_name", set);
		    	editor.commit();
		    	//var.save_name = set;
		    	var.mapBool.put("save_name", set);
		    	Log.i(var.TAG, "Записано save_name " +String.valueOf(set));


			}
		 
	}



