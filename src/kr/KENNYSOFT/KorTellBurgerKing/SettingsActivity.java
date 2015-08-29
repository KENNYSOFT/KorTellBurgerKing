package kr.KENNYSOFT.KorTellBurgerKing;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;

@SuppressWarnings("deprecation")
public class SettingsActivity extends PreferenceActivity
{
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		
		findPreference("about").setOnPreferenceClickListener(mAboutClick);
		findPreference("update").setOnPreferenceClickListener(mUpdateClick);
	}
	
	OnPreferenceClickListener mAboutClick=new OnPreferenceClickListener()
	{
		public boolean onPreferenceClick(Preference preference)
		{
			new AlertDialog.Builder(SettingsActivity.this).setIcon(R.drawable.ic_launcher).setTitle(R.string.full_name).setMessage(R.string.information).setPositiveButton(R.string.ok,null).show();
			return true;
		}
	};
	
	OnPreferenceClickListener mUpdateClick=new OnPreferenceClickListener()
	{
		public boolean onPreferenceClick(Preference preference)
		{
			new AlertDialog.Builder(SettingsActivity.this).setTitle(R.string.preference_update).setMessage(R.string.update).setPositiveButton(R.string.ok,null).show();
			return true;
		}
	};
}