package com.andrey7melnikov.arch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener {

	TextView textViewGame;
	TextView textViewStat;
	TextView textViewSet;
	TextView textViewExit;
	TextView textViewLogin;
	Intent intent;
	
	static SharedPreferences mSettings;

	 // final String SAVED_TEXT = "saved_text";

	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.d(var.TAG, "Старт экрана MainMenu");
	        setContentView(R.layout.main_menu);
	        

	        
	        textViewGame = (TextView) findViewById(R.id.textViewGame);
	        textViewStat = (TextView) findViewById(R.id.textViewStat);
	        textViewSet = (TextView) findViewById(R.id.textViewSet);
	        textViewExit = (TextView) findViewById(R.id.textViewExit);
	        textViewLogin = (TextView) findViewById(R.id.textViewLogin);
	        
	        textViewGame.setOnClickListener(this);
	        textViewStat.setOnClickListener(this);
	        textViewSet.setOnClickListener(this);
	        textViewExit.setOnClickListener(this);
	        textViewLogin.setOnClickListener(this);
	        
	        read_settings();

	 }

	@Override
	public void onClick(View v) {
		
		 switch (v.getId()) {
	   	  
         case R.id.textViewGame:
        	 Log.i(var.TAG, "textViewGame");
        	 intent = new Intent(this, GameMenu.class); 
             startActivity(intent);
        	 break;
        	 
         case R.id.textViewStat:
        	 Log.i(var.TAG, "textViewStat");
        	 intent = new Intent(this, StatMenu.class); 
             startActivity(intent);
        	 
        	 break;
        
         case R.id.textViewSet:
        	 intent = new Intent(this, SettingsMenu.class); 
             startActivity(intent); 
             break;

        
         case R.id.textViewLogin:
        	 Log.i(var.TAG, "textViewLogin");
        	Intent intentLogin = new Intent(this, LogActivity.class); 
             startActivityForResult(intentLogin, 0);
        	 break;
        	 
        	 
         case R.id.textViewExit:
        	 Log.i(var.TAG, "textViewExit");
        	 finish();
        	 break;
        	 
		 }
        	 
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {   
      menu.add("About");
      return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	String title = (String) item.getTitle();
    	
    	if (title == "About" ) {
	    	Log.i(var.TAG, "Выбран пункт About в меню");
       	 intent = new Intent(this, AboutActivity.class); 
         startActivity(intent);  
         }
      return super.onOptionsItemSelected(item);
    }

	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    if (data == null) {return;}
		    String name = data.getStringExtra("name");
		    textViewLogin.setText(name);
		  }
	
	 
	 public void read_settings() {
		 
	    	Log.i(var.TAG, "Начало чтения статистики");
	        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
	        
	    	Log.i(var.TAG, "Начало построение mapBool");
	    	
	    	readBool("no_write_stat_bool");
	    	readBool("save_name");
	    	readBool("minus_game");
	    	readBool("hard_game");


	    	Log.i(var.TAG, "Считываем имя пользователя");
	    	var.UserName = mSettings.getString("user_name", "Гость");
	    	var.mapString.put("user_name", var.UserName);

		}
	 
	 public void readBool(String key) {
	        var.mapBool.put(key ,mSettings.getBoolean(key, false) );
	    	Log.i(var.TAG, "Считано, Ключ: " + key + " значение: "+ mSettings.getBoolean(key, false));



	 }
	 
	 public void readString(String key) {
	        Log.i(var.TAG, "readBool");
	        var.mapString.put(key ,mSettings.getString(key, "") );
	    	Log.i(var.TAG, "Считано, Ключ: " + key + " значение: "+ String.valueOf(var.write_stat_bool));



	 }
	 
	
	 public void onResume() {
	        Log.i(var.TAG, "onResume MainMenu");
			super.onResume();
	        mSettings = getSharedPreferences("set.xml", Context.MODE_PRIVATE);
	    	var.UserName = mSettings.getString("user_name", "Гость");
	    	Log.i(var.TAG, "Считано user_name: " + String.valueOf(var.UserName));
	    	var.mapString.put("user_name", var.UserName);
		    textViewLogin.setText(var.UserName);
		    
		}
	 
	 
	 /* Пока убрано, не нужно  public void myStart() {
	        Log.i(var.TAG, "myStart");

			super.onResume();
		    if (var.myStart) 
	        {
		    	Log.i(var.TAG, "myStart = true, первоначальная инициализация");

	        }

		}
	 */
}
