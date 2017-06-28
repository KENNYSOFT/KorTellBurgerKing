package kr.KENNYSOFT.KorTellBurgerKing;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;

@SuppressWarnings("deprecation")
public class SettingsActivity extends PreferenceActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		findPreference("about").setOnPreferenceClickListener(mAboutClick);
		findPreference("opensource").setOnPreferenceClickListener(mOpensourceClick);
		findPreference("update").setOnPreferenceClickListener(mUpdateClick);
	}

	OnPreferenceClickListener mAboutClick=new OnPreferenceClickListener()
	{
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			new AlertDialog.Builder(SettingsActivity.this).setIcon(R.mipmap.ic_launcher).setTitle(R.string.full_name).setMessage(R.string.information).setPositiveButton(R.string.ok,null).show();
			return true;
		}
	};

	OnPreferenceClickListener mOpensourceClick=new OnPreferenceClickListener()
	{
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			new AlertDialog.Builder(SettingsActivity.this).setTitle(R.string.preference_opensource).setMessage(R.string.opensource).setPositiveButton(R.string.ok,null).show();
			return true;
		}
	};

	OnPreferenceClickListener mUpdateClick=new OnPreferenceClickListener()
	{
		@Override
		public boolean onPreferenceClick(Preference preference)
		{
			new AlertDialog.Builder(SettingsActivity.this).setTitle(R.string.preference_update).setMessage(R.string.update).setPositiveButton(R.string.ok,null).show();
			return true;
		}
	};
}