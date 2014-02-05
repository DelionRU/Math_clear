package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener {
	private static final String TAG = "myLogs";
	
	TextView editText1;
	TextView tempText1;
	TextView textView3;
	TextView textView2;
	
	Button tempBut1;
	Button button33;
	Button buttonStart;
	Button buttonOk;
	
	int a,b,c;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Старт приложения");
        setContentView(R.layout.main);
        
        tempText1 = (TextView) findViewById(R.id.tempText1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        editText1 = (TextView) findViewById(R.id.editText1);
        
        tempBut1 = (Button) findViewById(R.id.tempBut1);
        button33 = (Button) findViewById(R.id.button3);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        
        tempBut1.setOnClickListener(this);
        buttonStart.setOnClickListener(this);
        buttonOk.setOnClickListener(this);
        button33.setOnClickListener(this);
        textView3.setOnClickListener(this);
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
	   	  switch (v.getId()) {
	   	  
          case R.id.buttonStart:
        	 a = (int) (Math.random()*10);
     		 b = (int) (Math.random()*10);
     		 c = a + b;
     		
     		textView2.setText("A: " + a + " B: " + b + " Summ ? " );
            break;
            
          case R.id.button3:
            // кнопка Cancel
        	 tempText1.setText("Button 3");
        	//setContentView(R.layout.main2);
            break;
            
          case R.id.tempBut1:
              // кнопка Cancel
        	 Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_LONG).show();
          	 tempText1.append(" add ");
          	//setContentView(R.layout.main2);
              break;
            
          case R.id.buttonOk:
 
      		//String yourAnswer = editText1.getText().toString();
          	
          	
          	String YourAnswerText = editText1.getText().toString();
          	editText1.setText("");
          	//editText1.requestFocus();
          	int yourNum = Integer.parseInt(YourAnswerText);
          	
          	tempText1.setText("Ваш ответ: " + YourAnswerText );
          	
          	
          	if (yourNum == c) tempText1.append(" Правильно!");
          	else tempText1.append(" Не верно!");
          	
             break; 
             
          case R.id.textView3:
        	  Toast.makeText(this, "Нажата кнопка textview", Toast.LENGTH_LONG).show();
        	  break;
            
          
	   	  }
		
	}


}
