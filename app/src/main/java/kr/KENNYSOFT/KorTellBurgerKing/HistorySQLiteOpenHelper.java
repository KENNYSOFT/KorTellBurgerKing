package kr.KENNYSOFT.KorTellBurgerKing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HistorySQLiteOpenHelper extends SQLiteOpenHelper
{
	HistorySQLiteOpenHelper(Context context,String name,CursorFactory factory,int version)
	{
		super(context,name,factory,version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE history(_id INTEGER PRIMARY KEY AUTOINCREMENT,date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,surveyCode TEXT,validationCode TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
	{
		switch(oldVersion)
		{
		case 1:
			db.execSQL("CREATE TABLE _history(_id INTEGER PRIMARY KEY,date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,surveyCode TEXT,validationCode TEXT);\n"
				+"INSERT INTO _history(date,surveyCode,validationCode) SELECT strftime('%s',date),surveyCode,validationCode FROM history ORDER BY _id ASC;\n"
				+"DROP TABLE history;\n"
				+"ALTER TABLE _history RENAME TO history;");
			break;
		}
	}
}