package com.flex.android.logging;

import org.apache.commons.logging.Log;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.flex.android.framework.logging.log.LogFactory;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log log = LogFactory.getLog(MainActivity.class);
		log.info("loggging.....");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
