package com.andrey7melnikov.arch;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity implements OnClickListener {
	
	Button buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }
    
    public void onClick(View v) {
		
		 switch (v.getId()) {
		 
		 case R.id.buttonAbout:
        	 Intent intent = new Intent(this, MainMenu.class); 
             startActivity(intent);
		 }
    }
    
}
