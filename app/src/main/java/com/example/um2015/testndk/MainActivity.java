package com.example.um2015.testndk;

import android.app.Activity;
import android.app.NativeActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

static{
	System.loadLibrary("myLib");
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	try
	{
		Process process = Runtime.getRuntime().exec( "logcat -d" );
		BufferedReader bufferedReader = new BufferedReader(
				                                                  new InputStreamReader( process.getInputStream() ) );
		StringBuilder log = new StringBuilder();
		String line = "";
		while( ( line = bufferedReader.readLine() ) != null ) {
			log.append( line );
		}
		// Output available via log.toString().. do whatever with it
	}
	catch( IOException e ) {}
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.menu_main, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if (id == R.id.action_settings) {
		return true;
	}

	return super.onOptionsItemSelected(item);
}

public native String getStringFromNative();
}
