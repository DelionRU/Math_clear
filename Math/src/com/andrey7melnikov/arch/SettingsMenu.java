package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import java.io.File;


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
import android.widget.TextView;
import android.widget.Toast;

public class SettingsMenu extends Activity implements OnClickListener {
	
	TextView SetMenuBack,SetName;
	
	Button ClearStat,buttonSaveName;
	
	CheckBox WriteStatBox, checkBoxSetSaveName, checkBoxHard, checkBoxMinusGame;

	static SharedPreferences mSettings;
	Editor editor;

	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);
        Log.d(var.TAG, "Старт экран SetttingsMenu");
        


    	ClearStat = (Button) findViewById(R.id.ClearStat);
    	ClearStat.setOnClickListener(this);
    	
    	buttonSaveName = (Button) findViewById(R.id.buttonSaveName);
    	buttonSaveName.setOnClickListener(this);
        
      
    	checkBoxHard = (CheckBox)findViewById(R.id.checkBoxHard);
    	checkBoxHard.setOnClickListener(this);
    	
    	checkBoxMinusGame = (CheckBox)findViewById(R.id.checkBoxMinusGame);
    	checkBoxMinusGame.setOnClickListener(this);
    	
    	WriteStatBox = (CheckBox)findViewById(R.id.WriteStat);
    	WriteStatBox.setOnClickListener(this);
    	
    	checkBoxSetSaveName = (CheckBox)findViewById(R.id.checkBoxSetSaveName);
    	checkBoxSetSaveName.setOnClickListener(this);
        
    	SetName = (TextView) findViewById(R.id.SetName);
    	SetName.setOnClickListener(this);

        SetMenuBack = (TextView) findViewById(R.id.SetMenuBack);
        SetMenuBack.setOnClickListener(this);

        
        
        if (!var.mapBool.get("no_write_stat_bool")) {
        	WriteStatBox.setChecked(false);
	    	Log.i(var.TAG, "var.no_write_stat_bool= false, убрали галочку");

        }
    	
    	
        if (!var.mapBool.get("minus_game")) {
        	checkBoxMinusGame.setChecked(false);
	    	Log.i(var.TAG, "var.minus_game= false, убрали галочку");

        }
        
        if (!var.mapBool.get("hard_game")) {
        	checkBoxHard.setChecked(false);
	    	Log.i(var.TAG, "var.hard_game= false, убрали галочку");

        }
        
        if (!var.mapBool.get("save_name")) {
        	checkBoxSetSaveName.setChecked(false);
	    	Log.i(var.TAG, "var.save_name= false, убрали галочку");

        }
        
		mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
		String savedText = mSettings.getString("user_name", "Гость");
		SetName.setText(savedText);
		Log.i(var.TAG, "Load user name - " + savedText );
		
	}
	
	
	
	public void clear_settings() {
		File dir = getFilesDir();
		File file = new File(dir, "stat.txt");
		boolean deleted = file.delete();
		
		if (deleted) 
		{
	    	Log.i(var.TAG, "Статистика удалена");
	    	Toast.makeText(getApplicationContext(),
					"Статистика удалена", Toast.LENGTH_LONG).show();

		}
		else {
			Log.i(var.TAG, "Статистика НЕ удалена, возможно файла нет");
		}
		

	}
	
	
	public void onClick(View v) {
		 switch (v.getId()) {
		
		 
	      case R.id.SetMenuBack:
	    	 Intent intent = new Intent(this, MainMenu.class); 
		     startActivity(intent);
	       	 break;
		 	 
		 case R.id.buttonSaveName:
			 
			  Log.i(var.TAG, "Начало записи в статистику");
			  write_settings("user_name", SetName.getText().toString());

			 break;
			 
		 case R.id.ClearStat:
			 Log.i(var.TAG, "ClearStat");
			 clear_settings();
			 break;
   	 
	 
		 case R.id.checkBoxMinusGame:
			 Log.i(var.TAG, "checkBoxMinusGame");

			  if(checkBoxMinusGame.isChecked())
			  {
					 Log.i(var.TAG, "isChecked");
					 write_settings("minus_game", true);
			  }
		      else{
		    	  Log.i(var.TAG, "NOT Checked");
		    	  write_settings("minus_game", false);
		      }
		 	break;
		 	
		 case R.id.WriteStat:
			 Log.i(var.TAG, "WriteStat");

			  if(WriteStatBox.isChecked())
			  {
					 Log.i(var.TAG, "isChecked");
					 write_settings("no_write_stat_bool", true);

			  }
		      else{
		    	  Log.i(var.TAG, "NOT Checked");
					 write_settings("no_write_stat_bool", false);
		      }
		 	break;
		 	
		 	
		 case R.id.checkBoxHard:
			 Log.i(var.TAG, "checkBoxHard");

			  if(checkBoxHard.isChecked())
			  {
					 Log.i(var.TAG, "isChecked");
					 write_settings("hard_game", true);
			  }
		      else{
		    	  Log.i(var.TAG, "NOT Checked");
					 write_settings("hard_game", false);
		      }
		 	break;
		 	
		 	
		 case R.id.checkBoxSetSaveName:
			 Log.i(var.TAG, "checkBoxSetSaveName");

			  if(checkBoxSetSaveName.isChecked())
			  {
					 Log.i(var.TAG, "isChecked");
					 write_settings("save_name", false);
			  }
		      else{
		    	  Log.i(var.TAG, "NOT Checked");
					 write_settings("save_name", false);
					 }
		      
		 	break;
   	 
 }
	}
	
	
	
	 
	  public void write_settings (String name,Boolean set) {
	    	Log.i(var.TAG, "Начало записи в статистику");
	        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
	        editor = mSettings.edit();
	    	editor.putBoolean(name, set);
	    	editor.commit();
	    	var.mapBool.put(name, set);
	    	Log.i(var.TAG, "Записано name: " + name + " Значение: " + String.valueOf(set));


		}
	 
	 public void write_settings (String name,String set) {
	    	Log.i(var.TAG, "Начало записи в статистику");
	        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
	        editor = mSettings.edit();
	    	editor.putString(name, set);
	    	editor.commit();
	    	var.mapString.put(name, set);
	    	Log.i(var.TAG, "Записано name: " + name + " Значение: " + String.valueOf(set));


		}

}
	