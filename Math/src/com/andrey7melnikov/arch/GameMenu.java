package com.andrey7melnikov.arch;


//import com.example.my.arch.Chronometer;
import com.andrey7melnikov.arch.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class GameMenu extends Activity  implements OnClickListener {
	
	
	TextView GameMenuBack;
	TextView gameTextView;
	TextView gameText;
	TextView textViewGameResult;
	TextView textViewResult;
	TextView textViewLogin;
	
	Button buttonStartGame;
	Button buttonGameOk;
	
	LinearLayout gamell;
	
	String tempLog;
	Intent intent;
	boolean tempStop = false;
	
	Chronometer Mchronometer;
	double  timetimer;
	double  timetimerStop;
	public static double  timetimerResult;
	
	InputMethodManager imm;
	
	int a,b,c,a1,b1;
	int i;
	int yourNum;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);
        Log.d(var.TAG, "Старт экран GameMenu");
        
        
        int i = 1;
        
        Mchronometer = (Chronometer) findViewById(R.id.chronometer1);
  
        
        GameMenuBack = (TextView) findViewById(R.id.GameMenuBack);
        gameTextView = (TextView) findViewById(R.id.gameTextView);
        gameText = (TextView) findViewById(R.id.gameText);
        textViewGameResult = (TextView) findViewById(R.id.textViewGameResult);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        
        gamell = (LinearLayout) findViewById(R.id.Gamell);
   
        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonGameOk = (Button) findViewById(R.id.buttonGameOk);
        
        
        GameMenuBack.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
        buttonStartGame.setOnClickListener(this);
        buttonGameOk.setOnClickListener(this);

        
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
  
        if (var.mapBool.get("save_name")) 
        {
            Log.i(var.TAG, "var.mapBool.get(save_name), имя не запрашиваем");
        	textViewLogin.setText(var.UserName);
        }
        else {
            Log.i(var.TAG, "var.mapBool.get(save_name) = FALSE, открываем окно логина");
            intent = new Intent(this, LogActivity.class); 
            startActivity(intent);
        }
        
      
        // обработка Enter
        
        
        gameTextView.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
        	{
        	    if(event.getAction() == KeyEvent.ACTION_DOWN && 
        		    (keyCode == KeyEvent.KEYCODE_ENTER))
        			{
        			    Log.i(var.TAG, "Нажато Enter в поле ввода");
        			    buttonGameOk.performClick();
        			    return true;
        			}
        		return false;
        	}
        }
        );
        
        gameTextView.setOnEditorActionListener(new OnEditorActionListener() {

        	  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        	    if (actionId == EditorInfo.IME_ACTION_DONE) {
        	    	Log.i(var.TAG, "IME_ACTION_DONE");
    			    buttonGameOk.performClick();
    			     
        	      return true;
        	    } else {
        	      return false;
        	    }
        	  }

        	});
}
	@Override
	public void onResume() {
        Log.i(var.TAG, "onResume start");

		super.onResume();

        textViewLogin.setText(var.mapString.get("user_name"));


	}
	
	@Override
	public void onDestroy() {

		super.onDestroy();
		Log.i(var.TAG, "onDestroy start");
		//GameMenu.var.UserName = null;
	}
	public void onClick(View v) {
		
		 switch (v.getId()) {
		 
		 case R.id.textViewLogin:
			 Log.i(var.TAG, "textViewLogin");
	         intent = new Intent(this, LogActivity.class); 
	         startActivity(intent);
			 break;
	   	  
        case R.id.GameMenuBack:
        	 Intent intent = new Intent(this, MainMenu.class); 
             startActivity(intent);

       	 break;
       	 
        case R.id.buttonStartGame:
        	Log.i(var.TAG, "buttonStartGame");
        	clearGameZone();
    		NewValues();
    		i=1;
    		tempStop =false;
    		MyStat.clear();
    		textViewGameResult.setText("Поехали!");
      		textViewResult.setText("");
      		gameTextView.setText("");
      		Mchronometer.setBase(SystemClock.elapsedRealtime());
      		Mchronometer.start();
      		timetimer = System.currentTimeMillis();
      		gameTextView.requestFocus();
      		imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
      		
           break;
           
        case R.id.buttonGameOk:
        	
        	Log.i(var.TAG, "buttonGameok");
        	Log.i(var.TAG, String.valueOf(i));
        	

        	if (i<=var.Cicle) {
        		checkResult();
        		NewValues();
        		i++;
        	
        	}
        	else {
        		
        	}
        		
        	//textViewResult.setText(MyStat.see_stat());
        	
          	
             break; 
           
		 }
	}
	
	
	public void checkResult() {
		
		if (gameTextView.getText().length() == 0) {
			textViewGameResult.setText ("Не введено число ");
      		textViewResult.append(Html.fromHtml("<font color=#CC0000>- </font>"));
      		Log.i(var.TAG, "Пустой ввод");
      		//NewValues();
      		MyStat.add_wrong();
			return; //обработка пустых нажатий
			
		}
		if (i > var.Cicle) {
			 //проверка идиет игра или нет
			Log.i(var.TAG, "i == var.Cicle");
			return;
		}
      	String YourAnswerText = gameTextView.getText().toString();
      	gameTextView.setText("");
      	//int yourNum = Integer.parseInt(YourAnswerText);
      	try {
      		yourNum = Integer.parseInt(YourAnswerText);
      	} catch (NumberFormatException e) {
      		textViewGameResult.setText ("Только целые числа! Не верно!");
      		textViewResult.append(Html.fromHtml("<font color=#CC0000>- </font>"));
      		Log.i(var.TAG, "Введено не int");
      		//NewValues();
      		MyStat.add_wrong();
			return; //обработка пустых нажатий
      	}
      	textViewGameResult.setText(" " + YourAnswerText );
      	
      	
      	
    	Log.i(var.TAG, "Проверка верности ответа");
      	if (yourNum == c){
      		textViewGameResult.append(" Верно");
      		textViewResult.append(Html.fromHtml("<font color=#00FF33>+ </font>"));
      		Log.i(var.TAG, "Правильно");
      		MyStat.add_right();
      		
      	}
      	else {
      		textViewGameResult.append(" Не верно! (" + String.valueOf(c)+") ");
      		textViewResult.append(Html.fromHtml("<font color=#CC0000>- </font>"));
      		Log.i(var.TAG, "Не верно!");
      		MyStat.add_wrong();	
      	}
      	
      	a1=a;
      	b1=b;
	}
	
	
	public void clearGameZone() {
		 
		gamell.setBackgroundResource(R.drawable.untitled3);

		
		Log.i(var.TAG, "set enable button and text");
		buttonGameOk.setEnabled(true);
	    gameTextView.setEnabled(true);
	    gameTextView.setFocusable(true);
	    gameTextView.setFocusableInTouchMode(true);
		
		
		Log.i(var.TAG, "clearGameZone");
	}
	
	public void NewValues() {
		if (i == var.Cicle) {
			//Конец игры
			stopGame();
			return;
		}
		
		
		Log.i(var.TAG, "NewValues");

		
		if (var.mapBool.get("minus_game")){
			Log.i(var.TAG, "minus_game = true, игра с минусом");

			if ((Math.random() < 0.5)) {
				Log.i(var.TAG, "Math.random() < 0.5 играем с плюсом");
				
				if (var.mapBool.get("hard_game")) {
					Log.i(var.TAG, "hard_game играем до 99");

					while (a1==a) a = (int) (Math.random()*99)+10;
					while (b1==b) b = (int) (Math.random()*99)+10;
					c = a + b;
				}
				else {
					while (a1==a) a = (int) (Math.random()*9)+2;
					while (b1==b) b = (int) (Math.random()*9)+1;
					c = a + b;
				}
				
				tempLog = "A: " + String.valueOf(a) + " B: "+  String.valueOf(b) +" C: " + String.valueOf(c);
				Log.i(var.TAG, tempLog);
				gameText.setText(a + " + " + b + " = ? " );
			}
			else {
				
				Log.i(var.TAG, "Math.random() > 0.5 играем с минусом");
				if (var.mapBool.get("hard_game")) {
					Log.i(var.TAG, "hard_game играем до 99");
					while (a1==a) a = (int) (Math.random()*99)+10;
					while (b1==b) b = (int) (Math.random()*(a-1))+1;
					
					
					c = a - b;
				}
				else {
					while (a1==a) a = (int) (Math.random()*9+2);
					while (b1==b) b = (int) (Math.random()*(a-1))+1;
					c = a - b;
				}
				
				tempLog = "A: " + String.valueOf(a) + " B: "+  String.valueOf(b) +" C: " + String.valueOf(c);
				Log.i(var.TAG, tempLog);
				gameText.setText(a + " - " + b + " = ? " );
			}
		}
		else {
			
			
			// ----------------
			if (var.mapBool.get("hard_game")) {
				while (a1==a) a = (int) (Math.random()*99)+10;
				while (b1==b) b = (int) (Math.random()*99)+10;
				c = a + b;
			}
			else {
				while (a1==a) a = (int) (Math.random()*9)+1;
				while (b1==b) b = (int) (Math.random()*9)+1;
				c = a + b;
			}
			
			tempLog = "A: " + String.valueOf(a) + " B: "+  String.valueOf(b) +" C: " + String.valueOf(c);
			Log.i(var.TAG, tempLog);
			gameText.setText(a + " + " + b + " = ? " );
			
			
			//--------------
		}
		
		
	}
	
	public void stopGame() {
		//Log.i(var.TAG, "i == var.Cicle in newValuse");
		var.dirToFile = getFilesDir().getAbsolutePath();
		gameText.setText("Конец игры");
		
		timetimerStop = System.currentTimeMillis();
		var.timetimerResult = ((timetimerStop - timetimer)/1000);
		Mchronometer.stop();
		MyStat.rating();
		Log.i(var.TAG, "timetimerResult: " + var.timetimerResult );
		MyStat.write_stat();
		textViewResult.setText(MyStat.see_stat());
		textViewGameResult.setText("Нажмите старт чтобы начать заново");	
		imm.hideSoftInputFromWindow(gameTextView.getWindowToken(), 0);
	}
	
}
