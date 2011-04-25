/**
 * 
 */
package com.cybernetics;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.airpush.android.Airpush;

/**
 * @author Emmanuel Nollase
 * @created Mar 24, 2011 - 1:15:27 PM
 */
public class CalculatorWidget extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    new Airpush(getApplicationContext(),"172","airpush", true);
	    setContentView(R.layout.main);
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    intent = new Intent().setClass(this, MortgageActivity.class);
	    spec = tabHost.newTabSpec("mortage").setIndicator("Mortgage",
	                      res.getDrawable(R.drawable.ic_tabs_mortgage))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, SavingsActivity.class);
	    spec = tabHost.newTabSpec("savings").setIndicator("Savings",
	                      res.getDrawable(R.drawable.ic_tabs_saving))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	   
	    tabHost.setCurrentTab(0);
	}
}
