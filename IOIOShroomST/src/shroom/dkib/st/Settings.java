package shroom.dkib.st;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;

import java.text.DateFormat;
import java.util.Date;

import shroom.dkib.st.Timer.Looper;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ToggleButton;

public class Settings extends IOIOActivity {

	public ToggleButton LightSensor1_;
	public ToggleButton TempSensor1_;
	public ToggleButton Timer1_;
	public ToggleButton LightSensor2_;
	public ToggleButton TempSensor2_;
	public ToggleButton Timer2_;
	public ToggleButton LightSensor3_;
	public ToggleButton TempSensor3_;
	public ToggleButton Timer3_;
	public ToggleButton LightSensor4_;
	public ToggleButton TempSensor4_;
	public ToggleButton Timer4_;
	private final Handler mHandler;
	private final Handler timeHandler;
	
	SharedPreferences sharedpreferences;
	
	public Settings() {
        mHandler = new Handler();
        timeHandler = new Handler();
	}

	private void start() {
		mHandler.post(mRunnable);
		timeHandler.post(timeRunnable);
	}
	
	private final Runnable mRunnable = new Runnable() {
        public void run() {

    	    Editor editor = sharedpreferences.edit();
            editor.putBoolean("LightSensor1Save", LightSensor1_.isChecked());
            editor.putBoolean("TempSensor1Save", TempSensor1_.isChecked());
            editor.putBoolean("Timer1Save", Timer1_.isChecked());
            editor.putBoolean("LightSensor2Save", LightSensor2_.isChecked());
            editor.putBoolean("TempSensor2Save", TempSensor2_.isChecked());
            editor.putBoolean("Timer2Save", Timer2_.isChecked());
            editor.putBoolean("LightSensor3Save", LightSensor3_.isChecked());
            editor.putBoolean("TempSensor3Save", TempSensor3_.isChecked());
            editor.putBoolean("Timer3Save", Timer3_.isChecked());
            editor.putBoolean("LightSensor4Save", LightSensor4_.isChecked());
            editor.putBoolean("TempSensor4Save", TempSensor4_.isChecked());
            editor.putBoolean("Timer4Save", Timer4_.isChecked());

            editor.commit();
            
        }
    };
    
    private final Runnable timeRunnable = new Runnable() {
        public void run() {
    		
    		Editor editor = sharedpreferences.edit();
    		if(!(sharedpreferences.getInt("userHourValue", 0) == 0 && sharedpreferences.getInt("userMinValue", 0) == 0 && sharedpreferences.getInt("userSecValue", 0) == 0)) {
        		if(sharedpreferences.getBoolean("StartTimerSave", false)) {
        			editor.putInt("userSecValue", sharedpreferences.getInt("userSecValue", 0)-1);
	            	if(sharedpreferences.getInt("userHourValue", 0) == 0 && sharedpreferences.getInt("userMinValue", 0) == 0 && sharedpreferences.getInt("userSecValue", 0) == 0) {
	            		editor.putBoolean("StartTimerSave", false);
	                }
	            	else if(sharedpreferences.getInt("userSecValue", 0) == 59) {
	            		editor.putInt("userMinValue", sharedpreferences.getInt("userMinValue", 0)-1);
	            		if(sharedpreferences.getInt("userMinValue", 0) == 59) {
	            			editor.putInt("userMinValue", sharedpreferences.getInt("userMinValue", 0)-1);
	                	}
	            	}
	            }
        	}
    		
    		
            editor.commit();
            
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		
		LightSensor1_ = (ToggleButton) findViewById(R.id.LightSens1);
		TempSensor1_ = (ToggleButton) findViewById(R.id.TempSens1);
		Timer1_ = (ToggleButton) findViewById(R.id.Timer1);
		LightSensor2_ = (ToggleButton) findViewById(R.id.LightSens2);
		TempSensor2_ = (ToggleButton) findViewById(R.id.TempSens2);
		Timer2_ = (ToggleButton) findViewById(R.id.Timer2);
		LightSensor3_ = (ToggleButton) findViewById(R.id.LightSens3);
		TempSensor3_ = (ToggleButton) findViewById(R.id.TempSens3);
		Timer3_ = (ToggleButton) findViewById(R.id.Timer3);
		LightSensor4_ = (ToggleButton) findViewById(R.id.LightSens4);
		TempSensor4_ = (ToggleButton) findViewById(R.id.TempSens4);
		Timer4_ = (ToggleButton) findViewById(R.id.Timer4);
		
		if(sharedpreferences.getBoolean("LightSensor1Save", false) != LightSensor1_.isChecked()) LightSensor1_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor1Save", false) != TempSensor1_.isChecked()) TempSensor1_.performClick();
	    if(sharedpreferences.getBoolean("Timer1Save", false) != Timer1_.isChecked()) Timer1_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor2Save", false) != LightSensor2_.isChecked()) LightSensor2_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor2Save", false) != TempSensor2_.isChecked()) TempSensor2_.performClick();
	    if(sharedpreferences.getBoolean("Timer2Save", false) != Timer2_.isChecked()) Timer2_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor3Save", false) != LightSensor3_.isChecked()) LightSensor3_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor3Save", false) != TempSensor3_.isChecked()) TempSensor3_.performClick();
	    if(sharedpreferences.getBoolean("Timer3Save", false) != Timer3_.isChecked()) Timer3_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor4Save", false) != LightSensor4_.isChecked()) LightSensor4_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor4Save", false) != TempSensor4_.isChecked()) TempSensor4_.performClick();
	    if(sharedpreferences.getBoolean("Timer4Save", false) != Timer4_.isChecked()) Timer4_.performClick();

		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		start();
	}
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    Editor editor = sharedpreferences.edit();
        editor.putBoolean("LightSensor1Save", LightSensor1_.isChecked());
        editor.putBoolean("TempSensor1Save", TempSensor1_.isChecked());
        editor.putBoolean("Timer1Save", Timer1_.isChecked());
        editor.putBoolean("LightSensor2Save", LightSensor2_.isChecked());
        editor.putBoolean("TempSensor2Save", TempSensor2_.isChecked());
        editor.putBoolean("Timer2Save", Timer2_.isChecked());
        editor.putBoolean("LightSensor3Save", LightSensor3_.isChecked());
        editor.putBoolean("TempSensor3Save", TempSensor3_.isChecked());
        editor.putBoolean("Timer3Save", Timer3_.isChecked());
        editor.putBoolean("LightSensor4Save", LightSensor4_.isChecked());
        editor.putBoolean("TempSensor4Save", TempSensor4_.isChecked());
        editor.putBoolean("Timer4Save", Timer4_.isChecked());
        editor.commit();
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    
	    if(sharedpreferences.getBoolean("LightSensor1Save", false) != LightSensor1_.isChecked()) LightSensor1_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor1Save", false) != TempSensor1_.isChecked()) TempSensor1_.performClick();
	    if(sharedpreferences.getBoolean("Timer1Save", false) != Timer1_.isChecked()) Timer1_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor2Save", false) != LightSensor2_.isChecked()) LightSensor2_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor2Save", false) != TempSensor2_.isChecked()) TempSensor2_.performClick();
	    if(sharedpreferences.getBoolean("Timer2Save", false) != Timer2_.isChecked()) Timer2_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor3Save", false) != LightSensor3_.isChecked()) LightSensor3_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor3Save", false) != TempSensor3_.isChecked()) TempSensor3_.performClick();
	    if(sharedpreferences.getBoolean("Timer3Save", false) != Timer3_.isChecked()) Timer3_.performClick();
	    if(sharedpreferences.getBoolean("LightSensor4Save", false) != LightSensor4_.isChecked()) LightSensor4_.performClick();
	    if(sharedpreferences.getBoolean("TempSensor4Save", false) != TempSensor4_.isChecked()) TempSensor4_.performClick();
	    if(sharedpreferences.getBoolean("Timer4Save", false) != Timer4_.isChecked()) Timer4_.performClick();

	}
	
	@Override
	public void onStop() {
	    super.onStop();  // Always call the superclass method first

	}
	
	class Looper extends BaseIOIOLooper {
		public DigitalOutput led_;
		public DigitalOutput terminal1_;
		public DigitalOutput terminal2_;
		public DigitalOutput terminal3_;
		public DigitalOutput lamp_;
		
		@Override
		public void setup() throws ConnectionLostException {
			led_ = ioio_.openDigitalOutput(IOIO.LED_PIN, true);
			lamp_ = ioio_.openDigitalOutput(10, true);
			terminal1_ = ioio_.openDigitalOutput(9, true);
			terminal2_ = ioio_.openDigitalOutput(8, true);
			terminal3_ = ioio_.openDigitalOutput(7, true);
			
		}
		
		@Override
		public void loop() throws ConnectionLostException, InterruptedException {
			led_.write(sharedpreferences.getBoolean("led_", true));
			lamp_.write(sharedpreferences.getBoolean("lamp_", true));
			terminal1_.write(sharedpreferences.getBoolean("terminal1_", true));
			terminal2_.write(sharedpreferences.getBoolean("terminal2_", true));
			terminal3_.write(sharedpreferences.getBoolean("terminal3_", true));
			
			Thread.sleep(10);
		}
		
	}
	
	@Override
	protected IOIOLooper createIOIOLooper() {
		return new Looper();
	}
	
}
