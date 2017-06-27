package kr.KENNYSOFT.KorTellBurgerKing;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HistoryActivity extends AppCompatActivity
{
	HistorySQLiteOpenHelper sql;
	List<FavoriteItem> mList=new ArrayList<>();
	RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		sql=new HistorySQLiteOpenHelper(this,"history.db",null,2);
		Cursor c=sql.getReadableDatabase().query("history",null,null,null,null,null,"_id DESC");
		while(c.moveToNext())
		{
			FavoriteItem item=new FavoriteItem();
			item.surveyCode=c.getString(c.getColumnIndex("surveyCode"));
			item.validationCode=c.getString(c.getColumnIndex("validationCode"));
			item.date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault()).format(new Date(Timestamp.valueOf(c.getString(c.getColumnIndex("date"))).getTime()+TimeZone.getDefault().getRawOffset()+TimeZone.getDefault().getDSTSavings()));
			mList.add(item);
		}
		c.close();
		mRecyclerView.setAdapter(new HistoryAdapter(mList));
	}

	class FavoriteItem
	{
		String surveyCode,validationCode,date;
	}

	class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>
	{
		List<FavoriteItem> mDataSet;

		HistoryAdapter(List<FavoriteItem> dataSet)
		{
			mDataSet=dataSet;
		}

		class HistoryViewHolder extends RecyclerView.ViewHolder
		{
			TextView mSurveyCode,mValidationCode,mDate;

			HistoryViewHolder(View view)
			{
				super(view);
				mSurveyCode=(TextView)view.findViewById(R.id.history_surveyCode);
				mValidationCode=(TextView)view.findViewById(R.id.history_validationCode);
				mDate=(TextView)view.findViewById(R.id.history_date);
			}
		}

		@Override
		public HistoryViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
		{
			return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history,parent,false));
		}

		@Override
		public void onBindViewHolder(HistoryViewHolder holder,int position)
		{
			holder.mSurveyCode.setText(mDataSet.get(position).surveyCode);
			holder.mValidationCode.setText(mDataSet.get(position).validationCode);
			holder.mDate.setText(mDataSet.get(position).date);
		}

		@Override
		public int getItemCount()
		{
			return mDataSet.size();
		}
	}
}