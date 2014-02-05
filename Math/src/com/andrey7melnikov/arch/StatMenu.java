package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class StatMenu extends Activity implements OnClickListener {
	
	TextView StatMenuBack,textView1,reson,reson2;
	
	Button buttonSeeStat10,buttonLast10;
	
	
	//ArrayList<ArrayList<String>> big_array;
	ArrayList<String[]> big_array,arrayLast10;

	ArrayList<String> tempArray;

	Intent intent;
		
	int i;
	
	String[] tempStr;
	
	TableLayout quicklogTable;
	TableRow tableRow;
	LayoutParams params;
	TableRow.LayoutParams param;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_menu);
        Log.d(var.TAG, "����� ����� StatMenu");
        
        buttonSeeStat10 = (Button) findViewById(R.id.buttonSeeStat10);
        buttonSeeStat10.setOnClickListener(this);
        
        buttonLast10 = (Button) findViewById(R.id.buttonLast10);
        buttonLast10.setOnClickListener(this);
        
        textView1 = (TextView) findViewById(R.id.textView1);
        quicklogTable = (TableLayout) findViewById(R.id.quicklogTable);
        
        StatMenuBack = (TextView) findViewById(R.id.StatMenuBack);
        StatMenuBack.setOnClickListener(this);



    	big_array=new ArrayList<String[]>();
    	arrayLast10=new ArrayList<String[]>();

    	//big_array=new ArrayList<ArrayList<String>>();
    	Log.i(var.TAG, "������ ������");
    	read_stat_file();
        
        
	}
	
	
	public void onClick(View v) {
		
		 switch (v.getId()) {
		 
		 case R.id.buttonSeeStat10:
			 Log.i(var.TAG, "buttonSeeStat10");
			 quicklogTable.removeAllViews();
			 top_10();
			 break;
		 
		 
		 case R.id.buttonLast10:
			 Log.i(var.TAG, "buttonLast10");
			 quicklogTable.removeAllViews();
			 last_10();
			
		 break;
		 
	      case R.id.StatMenuBack:
	        	 Intent intent = new Intent(this, MainMenu.class); 
	             startActivity(intent);

	       	 break;
		 }
	 }
	
	
	public void read_stat_file() {
		Log.i(var.TAG, "����� ������ ����� � �������� �������");
		try {
			InputStream inputstream = openFileInput("stat.txt");

			if (inputstream != null) {
				InputStreamReader isr = new InputStreamReader(inputstream);
				BufferedReader reader = new BufferedReader(isr);
				String str;
				StringBuffer buffer = new StringBuffer();
				i=0;
				while ((str = reader.readLine()) != null) {
					Log.i(var.TAG, "������ ������ �����: " +String.valueOf(i));
					Log.i(var.TAG, "������: " + str);
					tempStr=new String[10];
					tempStr = str.split(";");
					big_array.add(tempStr);
					i++;
				}

				inputstream.close();
				//mEdit.setText(buffer.toString());
			}
		} catch (Throwable t) {
			Log.i(var.TAG, "�� ������� ������� ����");
			Toast.makeText(getApplicationContext(),
					"��� ����������", Toast.LENGTH_LONG).show();
		}
	}
	
	public void last_10() {
		Log.i(var.TAG, "�������������� ������");
		arrayLast10=big_array;
		Collections.reverse(arrayLast10);
      /* for(i=1;i<big_array.size();i++) {
        	// �������������� ������
        	arrayLast10.add(big_array.get(big_array.size()-i-1));
        	}
		*/
		Log.i(var.TAG, "parse_array");
		 tableRow = new TableRow(this);
		 params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		 tableRow.setLayoutParams(params);
		 param = new TableRow.LayoutParams();
		 param.setMargins(0, 0, 0, 1);
		 
		 Log.i(var.TAG, "�������� ������� 1");
		 
		 reson = new TextView(this);
		 reson.setText("���");                
		 reson.setLayoutParams(param);
		 reson.setBackgroundColor(Color.WHITE);
		 reson.setTextColor(Color.BLACK);
		// reson.setBackgroundColor(Color.rgb(0, 0, 0));
		 tableRow.addView(reson);
		 
		 Log.i(var.TAG, "�������� ������� 2");
		 
		 reson = new TextView(this);
		 reson.setText("���-�� �����");                
		 reson.setLayoutParams(param);
		 reson.setBackgroundColor(Color.WHITE);
		 reson.setTextColor(Color.BLACK);
		// reson.setBackgroundColor(Color.rgb(0, 0, 0));
		 tableRow.addView(reson);
		 quicklogTable.addView(tableRow); //������ ����� ������. 
		 
		 Log.i(var.TAG, "����� ��������� ����� ��� ���� ������");

		 
		 
		 
		 Log.i(var.TAG, "����� �����");
		 i=0;
		 for (String[] temp:arrayLast10)
		 {
			 Log.i(var.TAG, "String[] arrayLast10");


				 tableRow = new TableRow(this);
				 reson = new TextView(this);
				 reson.setText(temp[0]);                
				 reson.setLayoutParams(param);
				 reson.setBackgroundColor(Color.WHITE);
				 reson.setTextColor(Color.BLACK);
				 tableRow.addView(reson);
				 
				 Log.i(var.TAG, "�������� ������� ������ ����� 1");

				 reson2 = new TextView(this);
				 reson2.setText(temp[1]);                
				 reson2.setLayoutParams(param);
				 reson2.setBackgroundColor(Color.WHITE);
				 reson2.setTextColor(Color.BLACK);
				// reson.setBackgroundColor(Color.rgb(0, 0, 0));
				 tableRow.addView(reson2);
				 Log.i(var.TAG, "�������� ������� ������ ����� 2");

				 quicklogTable.addView(tableRow); //������ ����� ������. 
				 Log.i(var.TAG, "����� �����");
				 if (i==9){return;}
				 i++;



		 }
		 
		

	}
	
	
	public void top_10() {
		
      /* for(i=1;i<big_array.size();i++) {
        	// �������������� ������
        	arrayLast10.add(big_array.get(big_array.size()-i-1));
        	}
		*/
		Log.i(var.TAG, "parse_array");
		 tableRow = new TableRow(this);
		 params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		 tableRow.setLayoutParams(params);
		 param = new TableRow.LayoutParams();
		 param.setMargins(0, 0, 0, 1);
		 
		 Log.i(var.TAG, "�������� ������� 1");
		 
		 reson = new TextView(this);
		 reson.setText("���");                
		 reson.setLayoutParams(param);
		 reson.setBackgroundColor(Color.WHITE);
		 reson.setTextColor(Color.BLACK);
		// reson.setBackgroundColor(Color.rgb(0, 0, 0));
		 tableRow.addView(reson);
		 
		 Log.i(var.TAG, "�������� ������� 2");
		 
		 reson = new TextView(this);
		 reson.setText("���-�� �����");                
		 reson.setLayoutParams(param);
		 reson.setBackgroundColor(Color.WHITE);
		 reson.setTextColor(Color.BLACK);
		// reson.setBackgroundColor(Color.rgb(0, 0, 0));
		 tableRow.addView(reson);
		 quicklogTable.addView(tableRow); //������ ����� ������. 
		 

		 
		 int[][] top10int = new int[big_array.size()][2];
		 String[] top10names = new String[big_array.size()];
		 i=0;
		 int tempInt;
		 Log.i(var.TAG, "String[] temp:big_array");
		 
		 for (String[] temp:big_array)
		 {
			

			 try {
				 tempInt  = Integer.parseInt(temp[1]);
				 Log.i(var.TAG, "tempInt - " + String.valueOf(tempInt));
			 }
			 catch (NumberFormatException e) {
				 Log.e(var.TAG, "�� INT � ��������. ������: " + String.valueOf(i) + " �������� � ����: " + temp[1]);
				 tempInt = 0;
			 }
			 top10int[i][0]=i;
			 top10int[i][1]=tempInt;
			 
			 Log.i(var.TAG, "top10int[i][1] - " + String.valueOf(top10int[i][1]));
			 

			 top10names[i]=temp[0];
			 i++;


		 }

		 

		 for(int i=0;i<big_array.size();i++){
			 Log.i(var.TAG, "����: " + String.valueOf(top10int[i][1]) + " ID " + String.valueOf(top10int[i][0]));
		}
		 
		 Log.i(var.TAG, "���������� ���������");
		 // ���������� ���������
		 for(int i=0;i<big_array.size()-1;i++){
			 for(int j=i+1;j<top10int.length;j++){
			  if(top10int[i][1]<top10int[j][1]){
			       int tmp=top10int[i][1];
			       top10int[i][1]=top10int[j][1];
			       top10int[j][1]=tmp;
			       
			       tmp=top10int[i][0];
			       top10int[i][0]=top10int[j][0];
			       top10int[j][0]=tmp;
			  }
			 }
			}
		 
		 for(int i=0;i<big_array.size();i++){
			 Log.i(var.TAG, "����: " + String.valueOf(top10int[i][1]) + " ID " + String.valueOf(top10int[i][0]));
			 }
		 
		 
		 for(int i=0;i<big_array.size();i++){
		
			 Log.i(var.TAG, "������� ���10");
			 Log.i(var.TAG, "���� �����: " + String.valueOf(i));


				 tableRow = new TableRow(this);
				 reson = new TextView(this);
				 reson.setText(top10names[top10int[i][0]]);                
				 reson.setLayoutParams(param);
				 reson.setBackgroundColor(Color.WHITE);
				 reson.setTextColor(Color.BLACK);
				 tableRow.addView(reson);
				 

				 reson2 = new TextView(this);
				 reson2.setText(String.valueOf(top10int[i][1]));                
				 reson2.setLayoutParams(param);

				 reson2.setBackgroundColor(Color.WHITE);
				 reson2.setTextColor(Color.BLACK);
				// reson.setBackgroundColor(Color.rgb(0, 0, 0));

				 tableRow.addView(reson2);

				 quicklogTable.addView(tableRow); //������ ����� ������. 
				 Log.i(var.TAG, "����� �����");
				 if (i==9){return;}



		 }
		 
		 

	}
	
	
}
