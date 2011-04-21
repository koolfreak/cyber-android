/**
 * 
 */
package com.cybernetics;

import java.text.NumberFormat;
import java.text.ParseException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Emmanuel Nollase
 * @created Mar 24, 2011 - 1:44:42 PM
 */
public class MortgageActivity extends Activity {

	static final String EMPTY = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mortage_tab);
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		final AlertDialog ad = adb.create();
		ad.setCancelable(true);
		ad.setCanceledOnTouchOutside(true);
		
		final EditText principal = (EditText) findViewById(R.id.editPrincipal);
		final EditText rate = (EditText) findViewById(R.id.editRate);
		final EditText yr2pay = (EditText) findViewById(R.id.editYr2Pay);

		final TextView mon = (TextView) findViewById(R.id.monthly);
		final TextView intr = (TextView) findViewById(R.id.interest);

		final Button compute = (Button) findViewById(R.id.calculate);
		final Button reset = (Button) findViewById(R.id.reset);

		compute.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String sp = principal.getText().toString();
				String sr = rate.getText().toString();
				String sy = yr2pay.getText().toString();
				 Log.i("android ID", android.os.Build.ID);
				// get currency formatter
				NumberFormat nf = java.text.NumberFormat.getCurrencyInstance();

				try {
					if (sp.indexOf("$") == -1) {
						sp = "$" + sp;
					}
					if (sr.indexOf("$") == -1) {
						sr = "$" + sr;
					}
					double p = nf.parse(sp).floatValue();
					double r = nf.parse(sr).floatValue() / 100;
					int y = Integer.valueOf(sy) * 12;

					double m = m(p, r / 12, y);
					double i = (p * r * y) / (y * 12);
					mon.setText("Monthy payment: " + nf.format(m));
					intr.setText("Interest: " + nf.format(i));
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
				yr2pay.setText(EMPTY);
				mon.setText(EMPTY);
				intr.setText(EMPTY);
			}
		});
	}

	static double m(double p, double r, int y) {

		return fv(p, r, y) / geom(1 + r, 0, y - 1);
	}

	static double fv(double p, double r, int y) {
		return p * Math.pow(1 + r, y);
	}

	static double geom(double z, double m, double n) {
		double amt;
		if (z == 1.0)
			amt = n + 1;
		else
			amt = (Math.pow(z, n + 1) - 1) / (z - 1);
		if (m >= 1)
			amt -= geom(z, 0, m - 1);
		return amt;
	}
}
