package kr.KENNYSOFT.KorTellBurgerKing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity
{
	KorTellBurgerKingSQLite sql;
	List<HistoryItem> mList=new ArrayList<HistoryItem>();
	RecyclerView mRecyclerView;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		
		sql=new KorTellBurgerKingSQLite(this,"history.db",null,1);
		Cursor c=sql.getReadableDatabase().query("history",null,null,null,null,null,"_id DESC");
		while(c.moveToNext())
		{
			HistoryItem item=new HistoryItem();
			item.surveyCode=c.getString(c.getColumnIndex("surveyCode"));
			item.validationCode=c.getString(c.getColumnIndex("validationCode"));
			item.date=c.getString(c.getColumnIndex("date"));
			try
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date date=sdf.parse(item.date);
				sdf.setTimeZone(Calendar.getInstance().getTimeZone());
				item.date=sdf.format(date);
			}
			catch(Exception e)
			{
			}
			mList.add(item);
		}
		c.close();
		mRecyclerView.setAdapter(new HistoryAdapter(mList));
	}
}

class HistoryItem
{
	String surveyCode,validationCode,date;
}

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>
{
	List<HistoryItem> mDataSet;
	
	HistoryAdapter(List<HistoryItem> dataSet)
	{
		mDataSet=dataSet;
	}
	
	class HistoryViewHolder extends RecyclerView.ViewHolder
	{
		TextView mSurveyCode,mValidationCode,mDate;
		public HistoryViewHolder(View view)
		{
			super(view);
			mSurveyCode=(TextView)view.findViewById(R.id.history_surveyCode);
			mValidationCode=(TextView)view.findViewById(R.id.history_validationCode);
			mDate=(TextView)view.findViewById(R.id.history_date);
		}
	}
	
	public HistoryViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
	{
		return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history,parent,false));
	}
	
	public void onBindViewHolder(HistoryViewHolder holder,int position)
	{
		holder.mSurveyCode.setText(mDataSet.get(position).surveyCode);
		holder.mValidationCode.setText(mDataSet.get(position).validationCode);
		holder.mDate.setText(mDataSet.get(position).date);
	}
	
	public int getItemCount()
	{
		return mDataSet.size();
	}
}