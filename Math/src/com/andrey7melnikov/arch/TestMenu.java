package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestMenu extends Activity implements OnClickListener {
	  
	  EditText etText;
	  Button btnSave, btnLoad,buttonLogin;
	  
	  SharedPreferences sPref;
	  
	  final String SAVED_TEXT = "saved_text";
	  
	  
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.test_menu);
	        
	        etText = (EditText) findViewById(R.id.etText);
	        
	        btnSave = (Button) findViewById(R.id.btnSave);
	        buttonLogin = (Button) findViewById(R.id.buttonLogin);

	        btnSave.setOnClickListener(this);
	        buttonLogin.setOnClickListener(this);



	        
	        btnLoad = (Button) findViewById(R.id.btnLoad);
	        btnLoad.setOnClickListener(this);
	    }
	  @Override
	  public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.btnSave:
	   	 	Log.i(var.TAG, "btnSave");

	      saveText();
	      break;
	    case R.id.btnLoad:
	   	 	Log.i(var.TAG, "btnLoad");

	      loadText();
	      break;
	    case R.id.buttonLogin: 
	   	 	Log.i(var.TAG, "btnLogin");
	      Intent intent = new Intent();
	      String loginName;
	      if (etText.getText().toString() == "") {
	    	  loginName = "Guest";
	    	  }
	      else {
	    	  loginName = etText.getText().toString();
	      }
	      intent.putExtra("name", loginName);
	      setResult(RESULT_OK, intent);
	      finish();

	    default:
	      break;
	    }
	  }
	  
	  void saveText() {
	    sPref = getPreferences(MODE_PRIVATE);
	    Editor ed = sPref.edit();
	    ed.putString(SAVED_TEXT, etText.getText().toString());
	    ed.commit();
   	 	Log.i(var.TAG, "saved text");
	  }
	  
	  void loadText() {
	    sPref = getPreferences(MODE_PRIVATE);
	    String savedText = sPref.getString(SAVED_TEXT, "");
	    etText.setText(savedText);
   	 	Log.i(var.TAG, "Load text");
	  }
	  
	}

