package kr.KENNYSOFT.KorTellBurgerKing;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	WebView webview;
	String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webview=(WebView)findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebViewClient(new WebViewClient()
		{
			public void onPageFinished(WebView view,String url)
			{
				if(url.contains("https://kor.tellburgerking.com/Survey.aspx?c="))
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
					{
						webview.loadUrl("javascript:if(document.getElementById('R001000.2')!=null)document.getElementById('R001000.2').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R002000.1')!=null)document.getElementById('R002000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R004000.5')!=null)document.getElementById('R004000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R107000.5')!=null)document.getElementById('R107000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R012000.5')!=null)document.getElementById('R012000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R012000.5')!=null)document.getElementById('R012000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R013000.5')!=null)document.getElementById('R013000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R017000.5')!=null)document.getElementById('R017000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R007000.5')!=null)document.getElementById('R007000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R014000.5')!=null)document.getElementById('R014000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R021000.5')!=null)document.getElementById('R021000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R020000.5')!=null)document.getElementById('R020000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R015000.5')!=null)document.getElementById('R015000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R008000.5')!=null)document.getElementById('R008000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R011000.5')!=null)document.getElementById('R011000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R010000.5')!=null)document.getElementById('R010000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R009000.5')!=null)document.getElementById('R009000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R023000.5')!=null)document.getElementById('R023000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R030000.5')!=null)document.getElementById('R030000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R029000.9')!=null)document.getElementById('R029000.9').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R031000.5')!=null)document.getElementById('R031000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R108000.9')!=null)document.getElementById('R108000.9').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R041000.2')!=null)document.getElementById('R041000.2').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R044000.5')!=null)document.getElementById('R044000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R045000.5')!=null)document.getElementById('R045000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R047000.5')!=null)document.getElementById('R047000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R046000.5')!=null)document.getElementById('R046000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R054000.1')!=null)document.getElementById('R054000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R050000.1')!=null)document.getElementById('R050000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R049000.1')!=null)document.getElementById('R049000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R048000.1')!=null)document.getElementById('R048000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R055000.1')!=null)document.getElementById('R055000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R057000.5')!=null)document.getElementById('R057000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R058000.5')!=null)document.getElementById('R058000.5').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R060000.2')!=null)document.getElementById('R060000.2').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R068000.1')!=null)document.getElementById('R068000.1').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R069000.9')!=null)document.getElementById('R069000.9').checked=true;");
						webview.loadUrl("javascript:if(document.getElementById('R070000.9')!=null)document.getElementById('R070000.9').checked=true;");
						webview.loadUrl("javascript:document.getElementById('NextButton').click();");
					}
					else
					{
						webview.evaluateJavascript("if(document.getElementById('R001000.2')!=null)document.getElementById('R001000.2').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R002000.1')!=null)document.getElementById('R002000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R004000.5')!=null)document.getElementById('R004000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R107000.5')!=null)document.getElementById('R107000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R012000.5')!=null)document.getElementById('R012000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R012000.5')!=null)document.getElementById('R012000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R013000.5')!=null)document.getElementById('R013000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R017000.5')!=null)document.getElementById('R017000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R007000.5')!=null)document.getElementById('R007000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R014000.5')!=null)document.getElementById('R014000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R021000.5')!=null)document.getElementById('R021000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R020000.5')!=null)document.getElementById('R020000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R015000.5')!=null)document.getElementById('R015000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R008000.5')!=null)document.getElementById('R008000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R011000.5')!=null)document.getElementById('R011000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R010000.5')!=null)document.getElementById('R010000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R009000.5')!=null)document.getElementById('R009000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R023000.5')!=null)document.getElementById('R023000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R030000.5')!=null)document.getElementById('R030000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R029000.9')!=null)document.getElementById('R029000.9').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R031000.5')!=null)document.getElementById('R031000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R108000.9')!=null)document.getElementById('R108000.9').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R041000.2')!=null)document.getElementById('R041000.2').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R044000.5')!=null)document.getElementById('R044000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R045000.5')!=null)document.getElementById('R045000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R047000.5')!=null)document.getElementById('R047000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R046000.5')!=null)document.getElementById('R046000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R054000.1')!=null)document.getElementById('R054000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R050000.1')!=null)document.getElementById('R050000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R049000.1')!=null)document.getElementById('R049000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R048000.1')!=null)document.getElementById('R048000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R055000.1')!=null)document.getElementById('R055000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R057000.5')!=null)document.getElementById('R057000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R058000.5')!=null)document.getElementById('R058000.5').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R060000.2')!=null)document.getElementById('R060000.2').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R068000.1')!=null)document.getElementById('R068000.1').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R069000.9')!=null)document.getElementById('R069000.9').checked=true;",null);
						webview.evaluateJavascript("if(document.getElementById('R070000.9')!=null)document.getElementById('R070000.9').checked=true;",null);
						webview.evaluateJavascript("document.getElementById('NextButton').click();",null);
					}
				}
				else
				{
					if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
					{
						webview.loadUrl("javascript:document.getElementById('CN1').value='"+string.substring(0,3)+"';");
						webview.loadUrl("javascript:document.getElementById('CN2').value='"+string.substring(3,6)+"';");
						webview.loadUrl("javascript:document.getElementById('CN3').value='"+string.substring(6,9)+"';");
						webview.loadUrl("javascript:document.getElementById('CN4').value='"+string.substring(9,12)+"';");
						webview.loadUrl("javascript:document.getElementById('CN5').value='"+string.substring(12,15)+"';");
						webview.loadUrl("javascript:document.getElementById('CN6').value='"+string.substring(15,16)+"';");
						webview.loadUrl("javascript:document.getElementById('NextButton').click();");
					}
					else
					{
						Log.e("Hello",url);
						webview.evaluateJavascript("document.getElementById('CN1').value='"+string.substring(0,3)+"';",null);
						webview.evaluateJavascript("document.getElementById('CN2').value='"+string.substring(3,6)+"';",null);
						webview.evaluateJavascript("document.getElementById('CN3').value='"+string.substring(6,9)+"';",null);
						webview.evaluateJavascript("document.getElementById('CN4').value='"+string.substring(9,12)+"';",null);
						webview.evaluateJavascript("document.getElementById('CN5').value='"+string.substring(12,15)+"';",null);
						webview.evaluateJavascript("document.getElementById('CN6').value='"+string.substring(15,16)+"';",null);
						webview.evaluateJavascript("document.getElementById('NextButton').click();",null);
					}
				}
			}
		});
		
		((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				string=((EditText)findViewById(R.id.edittext)).getText().toString();
				if(string.length()!=16)Toast.makeText(MainActivity.this,R.string.hello_world,Toast.LENGTH_SHORT);
				else webview.loadUrl("https://kor.tellburgerking.com");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
