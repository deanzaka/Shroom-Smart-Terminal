package shroom.dkib.st;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

public class Timer extends ActionBarActivity {
	
	public NumberPicker pickerHour_;
	public NumberPicker pickerMin_;
	public NumberPicker pickerSec_;
	public ToggleButton StartTimer_;
	
	private final Handler timeHandler;
	SharedPreferences sharedpreferences;
	
	private void start() {
        timeHandler.post(timeRunnable);
    }
	
	public Timer() {
        timeHandler = new Handler();
	}
	
	private final Runnable timeRunnable = new Runnable() {
        public void run() {            
        	if(!(pickerSec_.getValue() == 0 && pickerMin_.getValue() == 0 && pickerHour_.getValue() == 0)) {
        		if(StartTimer_.isChecked()) {
	            	pickerSec_.setValue(pickerSec_.getValue()-1);
	            	if(pickerSec_.getValue() == 0 && pickerMin_.getValue() == 0 && pickerHour_.getValue() == 0) {
	                	StartTimer_.performClick();
	                }
	            	else if(pickerSec_.getValue() == 59) {
	            		pickerMin_.setValue(pickerMin_.getValue()-1);
	            		if(pickerMin_.getValue() == 59) {
	                		pickerHour_.setValue(pickerHour_.getValue()-1);
	                	}
	            	}
	            }
        	}
            Editor editor = sharedpreferences.edit();
    	    editor.putBoolean("StartTimerSave", StartTimer_.isChecked());
            editor.putInt("userHourValue", pickerHour_.getValue());
            editor.putInt("userMinValue", pickerMin_.getValue());
            editor.putInt("userSecValue", pickerSec_.getValue());
            editor.commit();
            
            timeHandler.postDelayed(timeRunnable, 1000);
            
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer);
		
		sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		StartTimer_ = (ToggleButton) findViewById(R.id.StartTimer);
		
		pickerHour_ = (NumberPicker) findViewById(R.id.userHour);
	    pickerMin_ = (NumberPicker) findViewById(R.id.userMin);
	    pickerSec_ = (NumberPicker) findViewById(R.id.userSec);
	    
		pickerHour_.setMaxValue(99);
	    pickerHour_.setMinValue(0);
	    pickerHour_.setValue(sharedpreferences.getInt("userHourValue", 0));
	    
	    pickerMin_.setMaxValue(59);
	    pickerMin_.setMinValue(0);
	    pickerMin_.setValue(sharedpreferences.getInt("userMinValue", 0));
	    
	    pickerSec_.setMaxValue(59);
	    pickerSec_.setMinValue(0);
	    pickerSec_.setValue(sharedpreferences.getInt("userSecValue", 0));
	    
	    if(sharedpreferences.getBoolean("StartTimerSave", false) != StartTimer_.isChecked()) StartTimer_.performClick();
		
	    start();
	}

}
