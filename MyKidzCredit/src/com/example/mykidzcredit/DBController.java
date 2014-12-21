/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       10/28/2014
 *  
 *  DBController creates tables in SQLite database
 *
 *----------------------------------------------------------------*/

package com.example.mykidzcredit;

import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper 
	{
	private static final String LOGCAT = null;
	public DBController(Context applicationcontext) {
    super(applicationcontext, "androidsqlite.db", null, 1);
    Log.d(LOGCAT,"Created");
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
	String query;
	String query1;
	String query2;
	String query3;
	
	//query to create 'kids' table
	query = "CREATE TABLE kids (kidId INTEGER PRIMARY KEY, kidName TEXT)";
	
	//query to create 'tasks' table
	query1 = "CREATE TABLE tasks (taskId INTEGER PRIMARY KEY, kidnum INTEGER, taskName TEXT, taskStatus TEXT, taskScore TEXT)";
        
	//query to create 'parents' table
	query2 = "CREATE TABLE parents (parentId INTEGER PRIMARY KEY, parentName TEXT, kidId INTEGER, taskId INTEGER)";
    
	//query to create 'logins' table
	query3 = "CREATE TABLE logins (login Id INTEGER PRIMARY KEY, parentlogin INTEGER, kidlogin INTEGER)";
        
	database.execSQL(query);
	database.execSQL(query1);
	database.execSQL(query2);
	database.execSQL(query3);
    Log.d(LOGCAT,"kids Created");
    Log.d(LOGCAT,"tasks Created");
    Log.d(LOGCAT,"parents Created");
    Log.d(LOGCAT,"logins Created");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) 
		{
		String query;
		String query1;
		String query2;
		String query3;
		query = "DROP TABLE IF EXISTS kids";
		query1 = "DROP TABLE IF EXISTS tasks";
		query2 = "DROP TABLE IF EXISTS parents";
		query3 = "DROP TABLE IF EXISTS logins";
		database.execSQL(query);
		database.execSQL(query1);
		database.execSQL(query2);
		database.execSQL(query3);
        onCreate(database);
		}
	
	public void insertKid(HashMap<String, String> queryValues) 
		{
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("kidName", queryValues.get("kidName"));
		database.insert("kids", null, values);
		database.close();
		}
	
	
	public int updateKid(HashMap<String, String> queryValues) 
		{
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("kidName", queryValues.get("kidName"));
	    return database.update("kids", values, "kidId" + " = ?", new String[] { queryValues.get("kidId") });
		}
	
	
	public void deleteKid(String id) 
		{
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  kids where kidId='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
		}
	
	//functions for kids table
	public ArrayList<HashMap<String, String>> getAllKids() 
		{
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM kids";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) 
	    {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("kidId", cursor.getString(0));
	        	map.put("kidName", cursor.getString(1));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	    return wordList;
		}
	
	public HashMap<String, String> getKidInfo(String id) 
		{
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM kids where kidId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) 
			{
				do 
					{
					wordList.put("kidName", cursor.getString(1));
				    } while (cursor.moveToNext());
				}				    
				return wordList;
			}	
	
	//Task Table functions
	
	//function for inserting a task
	public void insertTask(HashMap<String, String> queryValues) 
		{
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("taskName", queryValues.get("taskName"));
		values.put("kidnum", queryValues.get("kidnum"));
		values.put("taskStatus", queryValues.get("taskStatus"));
		values.put("taskScore", queryValues.get("taskScore"));
		database.insert("tasks", null, values);
		database.close();
		}
	
	//function for updating a task
	public int updateTask(HashMap<String, String> queryValues) 
		{
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("taskStatus", queryValues.get("taskStatus"));
	    values.put("taskScore", queryValues.get("taskScore"));
	    return database.update("tasks", values, "taskId" + " = ?", new String[] { queryValues.get("taskId") });
		}
	
	//function for getting all tasks
	public ArrayList<HashMap<String, String>> getAllTasks() 
		{
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT * FROM tasks";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) 
	    	{
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("taskId", cursor.getString(0));
	        	map.put("taskName", cursor.getString(2));
                wordList.add(map);
	        	} while (cursor.moveToNext());
	    	}
	    return wordList;
		}
	
	//getting all the tasks of the kid
	public HashMap<String, String> getTaskInfo(String id) 
		{
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		//query to select tasks for one kid
		String selectQuery = "SELECT * FROM tasks where taskId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
			{
	        	do {
	        		wordList.put("taskName", cursor.getString(1));
				   } while (cursor.moveToNext());
			}				    
			return wordList;
		}	
	
	//query to get task information of one kid
	public ArrayList<HashMap<String, String>> getOneKidTaskInfo(String id) {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT * FROM tasks where kidnum='"+id+"'";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) 
	    {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("taskId", cursor.getString(0));
	        	map.put("taskName", cursor.getString(2));
	        	map.put("taskStatus",cursor.getString(3));
	        	map.put("taskScore",cursor.getString(4));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	    return wordList;
	}
}
