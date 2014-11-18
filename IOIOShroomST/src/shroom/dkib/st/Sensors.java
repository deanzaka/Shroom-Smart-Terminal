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
import android.widget.TextView;

public class Sensors extends ActionBarActivity {
	
	public TextView luxValue_;
	public TextView tempValue_;
	public NumberPicker pickerLux_;
	public NumberPicker pickerTemp_;
	
	private final Handler mHandler;
	SharedPreferences sharedpreferences;
	
	private void start() {
        mHandler.post(mRunnable);
    }
	
	public Sensors() {
        mHandler = new Handler();
	}
	
	private final Runnable mRunnable = new Runnable() {
        public void run() {

    	    Editor editor = sharedpreferences.edit();
            editor.putInt("userLuxValue", pickerLux_.getValue());
            editor.putInt("userTempValue", pickerTemp_.getValue());
            editor.commit();
            
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensors);
		
		sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		
		String[] nums = new String[41];
		for(int i=0; i<nums.length; i++)
		nums[i] = Integer.toString(i*500);
		pickerLux_ = (NumberPicker) findViewById(R.id.userLux);
		pickerLux_.setMaxValue(nums.length - 1);
        pickerLux_.setMinValue(0);
        pickerLux_.setDisplayedValues(nums);
        for( int i=0; i<nums.length ; i++ )
            if(nums[i].equals("9000"))
                 pickerLux_.setValue(sharedpreferences.getInt("userLuxValue", i));
        pickerLux_.setWrapSelectorWheel(false);
        pickerLux_.setOnLongPressUpdateInterval(50);
        pickerTemp_ = (NumberPicker) findViewById(R.id.userTemp);
        pickerTemp_.setMaxValue(100);
        pickerTemp_.setMinValue(0);
        pickerTemp_.setValue(sharedpreferences.getInt("userTempValue", 30));
        pickerTemp_.setWrapSelectorWheel(false);
        pickerTemp_.setOnLongPressUpdateInterval(50);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        start();
	}
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    Editor editor = sharedpreferences.edit();
	    editor.putInt("userLuxValue", pickerLux_.getValue());
        editor.putInt("userTempValue", pickerTemp_.getValue());
        editor.commit();
	}
	
	public void onResume() {
		super.onResume();
		String[] nums = new String[41];
		for(int i=0; i<nums.length; i++)
		nums[i] = Integer.toString(i*500);
		pickerLux_ = (NumberPicker) findViewById(R.id.userLux);
		pickerLux_.setMaxValue(nums.length - 1);
        pickerLux_.setMinValue(0);
        pickerLux_.setDisplayedValues(nums);
        for( int i=0; i<nums.length ; i++ )
            if(nums[i].equals("9000"))
                 pickerLux_.setValue(sharedpreferences.getInt("userLuxValue", i));
        pickerLux_.setWrapSelectorWheel(false);
        pickerLux_.setOnLongPressUpdateInterval(50);
        pickerTemp_ = (NumberPicker) findViewById(R.id.userTemp);
        pickerTemp_.setMaxValue(100);
        pickerTemp_.setMinValue(0);
        pickerTemp_.setValue(sharedpreferences.getInt("userTempValue", 30));
        pickerTemp_.setWrapSelectorWheel(false);
        pickerTemp_.setOnLongPressUpdateInterval(50);
        
	}

}
