package com.cybernetics;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.mopub.mobileads.MoPubView;

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