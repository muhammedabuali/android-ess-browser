package com.muhammedabuali.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
    }
	
	public void showCommics(View view) 
	{
	    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
	    startActivity(intent);
	}
	
	public void showAbout(View view)
	{
		Intent intent = new Intent(WelcomeActivity.this, AboutActivity.class);
	    startActivity(intent);
	}
	
	public void showInstructions(View view)
	{
		Intent intent = new Intent(WelcomeActivity.this, InstructionsActivity.class);
	    startActivity(intent);
	}
}