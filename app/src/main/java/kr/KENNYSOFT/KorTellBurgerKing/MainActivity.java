package kr.KENNYSOFT.KorTellBurgerKing;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	final static int[] barcodeToSurveyCode={20,11,4,19,2,7,18,1,17,6,9,3,8,10,-1,5};

	boolean isProcessing,autoSelect;
	Button mButton;
	EditText mEditText;
	HistorySQLiteOpenHelper sql;
	ProgressBar mProgressBar;
	SharedPreferences mPref;
	String surveyCode;
	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sql=new HistorySQLiteOpenHelper(this,"history.db",null,2);
		mPref=PreferenceManager.getDefaultSharedPreferences(this);
		autoSelect=mPref.getBoolean("autoSelect",false);

		mEditText=(EditText)findViewById(R.id.surveycode);
		mButton=(Button)findViewById(R.id.start);
		mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
		mWebView=(WebView)findViewById(R.id.webview);

		mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString()+" "+getString(R.string.app_name)+"/"+BuildConfig.VERSION_NAME);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new AndroidJavascriptInterface(this),"android");
		mWebView.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onReceivedSslError(WebView view,SslErrorHandler handler,SslError error)
			{
				handler.proceed();
			}

			@Override
			public void onPageFinished(WebView view,String url)
			{
				if(url.startsWith("https://kor.tellburgerking.com/Index.aspx?c="))
				{
					if(isProcessing)
					{
						ContentValues values=new ContentValues();
						values.put("surveyCode",surveyCode);
						values.put("validationCode",getString(R.string.error_title));
						sql.getWritableDatabase().insert("history",null,values);
						new AlertDialog.Builder(MainActivity.this).setIcon(R.drawable.ic_action_warning).setTitle(R.string.error_title).setMessage(R.string.error_message).setPositiveButton(R.string.ok,null).show();
						isProcessing=false;
						mProgressBar.setVisibility(View.GONE);
					}
					else
					{
						isProcessing=true;
						if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
						{
							mWebView.loadUrl("javascript:document.getElementById('CN1').value='"+surveyCode.substring(0,3)+"';");
							mWebView.loadUrl("javascript:document.getElementById('CN2').value='"+surveyCode.substring(3,6)+"';");
							mWebView.loadUrl("javascript:document.getElementById('CN3').value='"+surveyCode.substring(6,9)+"';");
							mWebView.loadUrl("javascript:document.getElementById('CN4').value='"+surveyCode.substring(9,12)+"';");
							mWebView.loadUrl("javascript:document.getElementById('CN5').value='"+surveyCode.substring(12,15)+"';");
							mWebView.loadUrl("javascript:document.getElementById('CN6').value='"+surveyCode.substring(15,16)+"';");
							mWebView.loadUrl("javascript:document.getElementById('NextButton').click();");
						}
						else
						{
							mWebView.evaluateJavascript("document.getElementById('CN1').value='"+surveyCode.substring(0,3)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('CN2').value='"+surveyCode.substring(3,6)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('CN3').value='"+surveyCode.substring(6,9)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('CN4').value='"+surveyCode.substring(9,12)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('CN5').value='"+surveyCode.substring(12,15)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('CN6').value='"+surveyCode.substring(15,16)+"';",null);
							mWebView.evaluateJavascript("document.getElementById('NextButton').click();",null);
						}
					}
				}
				else if(url.startsWith("https://kor.tellburgerking.com/Survey.aspx?c="))
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
					{
						mWebView.loadUrl("javascript:window.android.onProgressChanged(parseInt(document.getElementById('ProgressBar').innerText));");
						mWebView.loadUrl("javascript:document.getElementById('NextButton').click();");
					}
					else
					{
						mWebView.evaluateJavascript("window.android.onProgressChanged(parseInt(document.getElementById('ProgressBar').innerText));",null);
						mWebView.evaluateJavascript("document.getElementById('NextButton').click();",null);
					}
				}
				else if(url.startsWith("https://kor.tellburgerking.com/Finish.aspx?c="))
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)mWebView.loadUrl("javascript:window.android.onFinish(document.getElementsByClassName('ValCode')[0].innerHTML.substring(7));");
					else mWebView.evaluateJavascript("window.android.onFinish(document.getElementsByClassName('ValCode')[0].innerHTML.substring(7));",null);
					isProcessing=false;
					mProgressBar.setVisibility(View.GONE);
				}
				else
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)mWebView.loadUrl("javascript:document.getElementById('NextButton').click();");
					else mWebView.evaluateJavascript("document.getElementById('NextButton').click();",null);
				}
			}
		});

		mEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				mButton.setEnabled(s.toString().length()==16);
			}

			@Override
			public void beforeTextChanged(CharSequence s,int start,int count,int after)
			{
			}

			@Override
			public void onTextChanged(CharSequence s,int start,int before,int count)
			{
			}
		});

		mEditText.setOnEditorActionListener(new OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView v,int actionId,KeyEvent event)
			{
				if(actionId==EditorInfo.IME_ACTION_DONE&&v.getEditableText().toString().length()==16)
				{
					mButton.performClick();
					return false;
				}
				return true;
			}
		});

		mButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				isProcessing=false;
				surveyCode=mEditText.getText().toString();
				CookieSyncManager cookieSyncManager=CookieSyncManager.createInstance(MainActivity.this);
				CookieManager cookieManager=CookieManager.getInstance();
				cookieManager.setAcceptCookie(true);
				cookieManager.removeSessionCookie();
				cookieSyncManager.sync();
				mWebView.loadUrl("https://kor.tellburgerking.com");
				mProgressBar.setProgress(0);
				mProgressBar.setVisibility(View.VISIBLE);
				((InputMethodManager)MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});

		FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				new IntentIntegrator(MainActivity.this).setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES).setBeepEnabled(false).initiateScan();
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		autoSelect=mPref.getBoolean("autoSelect",false);
	}

	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
		if(result!=null)
		{
			if(result.getContents()!=null)
			{
				String barcode=result.getContents();
				if(autoSelect)
				{
					surveyCode="";
					for(int pos : barcodeToSurveyCode)
					{
						if(pos>0)surveyCode=surveyCode+barcode.charAt(pos-1);
						else surveyCode=surveyCode+new Random().nextInt(10);
					}
					mEditText.setText(surveyCode);
					mEditText.setSelection(surveyCode.length());
					mButton.performClick();
				}
				else
				{
					final String[] surveyCodes=new String[10];
					for(int i=0;i<10;++i)
					{
						surveyCodes[i]="";
						for(int pos : barcodeToSurveyCode)
						{
							if(pos>0)surveyCodes[i]=surveyCodes[i]+barcode.charAt(pos-1);
							else surveyCodes[i]=surveyCodes[i]+i;
						}
					}
					new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle(R.string.select_title).setItems(surveyCodes,new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog,int which)
						{
							surveyCode=surveyCodes[which];
							mEditText.setText(surveyCode);
							mEditText.setSelection(surveyCode.length());
							mButton.performClick();
							dialog.dismiss();
						}
					}).show();
				}
			}
		}
		else
		{
			super.onActivityResult(requestCode,resultCode,data);
		}
	}

	public void onProgressChanged(int progress)
	{
		mProgressBar.setProgress(progress);
	}

	public void onFinish(String validationCode)
	{
		ContentValues values=new ContentValues();
		values.put("surveyCode",surveyCode);
		values.put("validationCode",validationCode);
		sql.getWritableDatabase().insert("history",null,values);
		new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle(R.string.finish_title).setMessage(validationCode).setPositiveButton(R.string.ok,null).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_history:
			startActivity(new Intent(this,HistoryActivity.class));
			return true;
		case R.id.action_settings:
			startActivity(new Intent(this,SettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	class AndroidJavascriptInterface
	{
		MainActivity mActivity;

		AndroidJavascriptInterface(MainActivity activity)
		{
			mActivity=activity;
		}

		@JavascriptInterface
		public void onProgressChanged(int progress)
		{
			mActivity.onProgressChanged(progress);
		}

		@JavascriptInterface
		public void onFinish(String validationCode)
		{
			mActivity.onFinish(validationCode);
		}
	}
}