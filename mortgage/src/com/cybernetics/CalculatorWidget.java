package com.cybernetics;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< .mine
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
=======
>>>>>>> .r8
import android.widget.TabHost;
import android.widget.TextView;

<<<<<<< .mine
import com.mopub.mobileads.MoPubView;
=======
import com.airpush.android.Airpush;

/**
 * @author Emmanuel Nollase
 * @created Mar 24, 2011 - 1:15:27 PM
 */
public class CalculatorWidget extends TabActivity {
>>>>>>> .r8

<<<<<<< .mine
public class CalculatorWidget extends TabActivity{
	private TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MoPubView mpv = (MoPubView) findViewById(R.id.adview);
	    mpv.setAdUnitId("agltb3B1Yi1pbmNyDQsSBFNpdGUYmNfrAQw");
	    mpv.loadAd();
        
        setTabs();
    }
    
    private void setTabs() {
		tabHost = getTabHost();
		
		addTab(R.string.tab_1, R.drawable.ic_tabs_mortgage,MortgageActivity.class);
		addTab(R.string.tab_2, R.drawable.ic_tabs_saving,SavingsActivity.class);
=======
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
>>>>>>> .r8
	}
	
	private void addTab(int labelId, int drawableId,Class<?> klazz) {
		Intent intent = new Intent(this, klazz);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);		
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
		
	}
}