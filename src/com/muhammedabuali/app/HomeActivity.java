package com.muhammedabuali.app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HomeActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);


        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("trending");
        tab1.setContent(new Intent(this, MyActivity.class));

        tab2.setIndicator("newest");
        tab2.setContent(new Intent(this, NewActivity.class));

        tab3.setIndicator("lame");
        tab3.setContent(new Intent(this, LameActivity.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }
}
