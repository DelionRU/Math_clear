package com.andrey7melnikov.arch;

import com.andrey7melnikov.arch.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;
import android.widget.Toast;


public class MyStat {
	static int rr;
	
	
	
	static int count_right = 0;
	static int count_wrong = 0;
	static int count_skip = 0;
	static int count_all = 0;
	double rating;
	static int koff=30000; // начальное кол-во очков, определяется в raiting();
	// определяем стат переменные
	
	
	public static void add_right (){
		count_right++;
		count_all++;

	

	}
	
	public static void add_wrong (){
		count_wrong++;
		count_all++;
		
		

	}
	
	
		
		public static String see_stat (){
			String stat = "stat";
			stat = "Всего: " + String.valueOf(count_all)+ " из них верно: "+ String.valueOf(count_right)+System.getProperty("line.separator") + " Общее время: " +  String.valueOf(var.timetimerResult)+System.getProperty("line.separator") + " Ваш Рейтинг: " +  String.valueOf(var.RatingInt);
			return stat;
		}
	  
		public static void write_stat() {
			if (var.mapBool.get("no_write_stat_bool")) {
	              Log.i(var.TAG, "В настройках указано не записывать, не записываем");
	              return;
			}
			
			String dirPath = var.dirToFile + File.separator + "Stat";
        	File projDir = new File(dirPath);
        	if (!projDir.exists())
        	{
        	    projDir.mkdirs();
        	}
			
			 String dirPath2 = var.dirToFile + File.separator + "stat.txt";
			 File testfile = new File(dirPath2);

        	    
			try {
				
				 BufferedWriter bw = new BufferedWriter(new FileWriter(testfile, true));
	              // пишем данные
	              bw.write(  String.valueOf(var.UserName) + ";" +String.valueOf(var.RatingInt) + ";"+String.valueOf(count_right) + ";" + String.valueOf(var.timetimerResult)+ ";"+ String.valueOf(count_all)  +System.getProperty("line.separator"));
	              // закрываем поток
	              bw.close();
	              Log.i(var.TAG, "статистика записана");			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 Log.i(var.TAG, "статистика не записана");
			}
		}
	  

	//=========================================================================
	  
	
	static public void clear (){
		count_wrong=0;
		count_all=0;
		count_right=0;

}
	
	static public void rating ()
	{
		 Log.i(var.TAG, "Подсчет рейтинга");
		 koff=30000;
		 if (var.mapBool.get("hard_game")) {
			 koff=50000;
			 Log.i(var.TAG, "hard_game true koff = 50 000");

		 }
		 if (var.mapBool.get("minus_game")){
			 Log.i(var.TAG, "minus_game true, koff*1.25");
			 koff=(int) (koff*1.25);
			 }
		 
		 Log.i(var.TAG, "koff для расчета = " + koff );
	

		// только для 5 ответов
		if (count_wrong == 0) {
			var.Rating = koff - var.timetimerResult*1000;
		}
		else if (count_wrong == 1) {
			var.Rating = koff - var.timetimerResult*1.25*1000;
		}
		else if (count_wrong == 2) {
			var.Rating = koff - var.timetimerResult*1.5*1000;
		}
		else {
			var.Rating = 0;
		}
		
		if (var.Rating<0){
			var.Rating = 0;
		}
	
		var.RatingInt = (int) var.Rating;
		Log.i(var.TAG, "Рейтинг, var.Rating: "+ String.valueOf(var.Rating) +" var.RatingInt: "+ String.valueOf(var.RatingInt));
}

	
	
	/*
	 * Мусорка
	 
	public static void write_stat_old_android () {
		
		String dirPath = var.dirToFile + File.separator + "Stat";
    	File projDir = new File(dirPath);
    	if (!projDir.exists())
    	{
    	    projDir.mkdirs();
    	}
		Log.i(var.TAG, var.dirToFile);
		
		 String dirPath2 = dirPath +File.separator+ var.UserName;
		 File testfile = new File(dirPath2);

    	    
		try {
			
			 BufferedWriter bw = new BufferedWriter(new FileWriter(testfile, true));
              // пишем данные
              bw.write(  String.valueOf(count_right) + ";" + String.valueOf(count_all)+ ";" + String.valueOf(var.timetimerResult)+ ";" + String.valueOf(var.RatingInt) +System.getProperty("line.separator"));
              // закрываем поток
              bw.close();
              Log.i(var.TAG, "Ура записано");
              Log.i(var.TAG, dirPath2);
		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 Log.i(var.TAG, "Что то не так");
		}
	}
	*/
	
}