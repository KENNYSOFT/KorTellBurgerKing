package kr.KENNYSOFT.KorTellBurgerKing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class KorTellBurgerKingSQLite extends SQLiteOpenHelper
{
	KorTellBurgerKingSQLite(Context context,String name,CursorFactory factory,int version)
	{
		super(context,name,factory,version);
	}
	
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table history(_id INTEGER PRIMARY KEY AUTOINCREMENT, date DATETIME DEFAULT CURRENT_TIMESTAMP, surveyCode TEXT, validationCode TEXT);");
	}
	
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
	{
	}
}