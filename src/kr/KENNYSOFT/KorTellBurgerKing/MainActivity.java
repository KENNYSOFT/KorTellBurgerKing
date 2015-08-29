package kr.KENNYSOFT.KorTellBurgerKing;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity
{
	boolean isProcessing;
	Button mButton;
	EditText mEditText;
	KorTellBurgerKingSQLite sql;
	ProgressBar mProgressBar;
	String surveyCode;
	WebView mWebView;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sql=new KorTellBurgerKingSQLite(this,"history.db",null,1);
		
		mEditText=(EditText)findViewById(R.id.surveycode);
		mButton=(Button)findViewById(R.id.start);
		mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
		mWebView=(WebView)findViewById(R.id.webview);
		try
		{
			mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString()+" KorTellBurgerKing/"+this.getPackageManager().getPackageInfo(this.getPackageName(),PackageManager.GET_META_DATA).versionName);
		}
		catch(Exception e)
		{
		}
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new KorTellBurgerKingInterface(this),"KorTellBurgerKing");
		mWebView.setWebViewClient(new WebViewClient()
		{
			public void onReceivedSslError(WebView view,SslErrorHandler handler,SslError error)
			{
				handler.proceed();
			}
			
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
						mWebView.loadUrl("javascript:window.KorTellBurgerKing.onProgressChanged(parseInt(document.getElementById('ProgressBar').innerText));");
						mWebView.loadUrl("javascript:document.getElementById('NextButton').click();");
					}
					else
					{
						mWebView.evaluateJavascript("window.KorTellBurgerKing.onProgressChanged(parseInt(document.getElementById('ProgressBar').innerText));",null);
						mWebView.evaluateJavascript("document.getElementById('NextButton').click();",null);
					}
				}
				else if(url.startsWith("https://kor.tellburgerking.com/Finish.aspx?c="))
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)mWebView.loadUrl("javascript:window.KorTellBurgerKing.onFinish(document.getElementsByClassName('ValCode')[0].innerHTML.substring(7));");
					else mWebView.evaluateJavascript("window.KorTellBurgerKing.onFinish(document.getElementsByClassName('ValCode')[0].innerHTML.substring(7));",null);
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
			public void afterTextChanged(Editable s)
			{
				mButton.setEnabled(s.toString().length()==16);
			}
			
			public void beforeTextChanged(CharSequence s,int start,int count,int after)
			{
			}
			
			public void onTextChanged(CharSequence s,int start,int before,int count)
			{
			}
		});
		
		mEditText.setOnEditorActionListener(new OnEditorActionListener()
		{
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
		new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle(R.string.finish_title).setMessage(validationCode).setPositiveButton(R.string.ok,null).show();
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
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
}

class KorTellBurgerKingInterface
{
	MainActivity mActivity;
	
	KorTellBurgerKingInterface(MainActivity activity)
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