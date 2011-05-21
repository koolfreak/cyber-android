/**
 * 
 */
package com.cybernetics;

import java.text.NumberFormat;
import java.text.ParseException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Emmanuel Nollase
 * @created Mar 24, 2011 - 1:19:55 PM
 */
public class SavingsActivity extends Activity {

	static final String EMPTY = "";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savings_tab);
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final AlertDialog ad = adb.create();
		ad.setCancelable(true);
		ad.setCanceledOnTouchOutside(true);
		
		final EditText principal = (EditText) findViewById(R.id.editTargetAmount);
		final EditText saved = (EditText) findViewById(R.id.editSaved);
		final EditText rate = (EditText) findViewById(R.id.editRate);
		final EditText yr2save = (EditText) findViewById(R.id.editYr2Save);
		final EditText sms_number = (EditText) findViewById(R.id.phone_number);

		final TextView mon = (TextView) findViewById(R.id.monthly);

		final Button compute = (Button) findViewById(R.id.calculate);
		final Button reset = (Button) findViewById(R.id.reset);
		final Button sms_send = (Button) findViewById(R.id.btn_send);
		
		final View sms_view = (View) findViewById(R.id.sms_view);
		sms_view.setVisibility(View.INVISIBLE);

		compute.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String sp = principal.getText().toString();
				String ss = saved.getText().toString();
				String sr = rate.getText().toString();
				String sy = yr2save.getText().toString();

				// get currency formatter
				NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();

				try {
					if (sp.indexOf("$") == -1) {
						sp = "$" + sp;
					}
					if (sr.indexOf("$") == -1) {
						sr = "$" + sr;
					}
					if (ss.indexOf("$") == -1) {
						ss = "$" + ss;
					}
					double p = nf.parse(sp).floatValue();
					double s = nf.parse(ss).floatValue();
					double r = nf.parse(sr).floatValue() / 100;
					int y = Integer.valueOf(sy) * 12;
					double b = p - s;
					
					if( b <=  0 ) {
						ad.setMessage("You have enough money!");
						ad.show();
						return;
					}
					double i = (b/y/12) + (b *(r/12));
					mon.setText(nf.format(i));
					sms_view.setVisibility(View.VISIBLE);
				} catch (ParseException e) {
					ad.setMessage("Please enter correct amount!");
					ad.show();
					
				} catch (Exception e) {
					ad.setMessage("Unwanted error occurred!");
					ad.show();
				}
			}
		});

		reset.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				principal.setText(EMPTY);
				rate.setText(EMPTY);
				yr2save.setText(EMPTY);
				mon.setText(EMPTY);
				saved.setText(EMPTY);
				sms_number.setText(EMPTY);
				sms_view.setVisibility(View.INVISIBLE);
			}
		});
        
		sms_send.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String number = sms_number.getText().toString();
				if(number.length() > 0){
					StringBuilder message = new StringBuilder();
					message.append("For the target amount of ").append(principal.getText().toString());
					message.append(" with the savings of ").append(saved.getText().toString()).append('.');
					message.append(" Rate ").append(rate.getText().toString()).append(',');
					message.append("Years to save ").append(yr2save.getText().toString()).append('.');
					message.append("Monthly amount needed to reach your goal: ").append(mon.getText().toString()).append('.');
					
					sendSMS(number, message.toString());
				}
			}
		});
    }
	
	//---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {        
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, MortgageActivity.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);        
    }
	
}
