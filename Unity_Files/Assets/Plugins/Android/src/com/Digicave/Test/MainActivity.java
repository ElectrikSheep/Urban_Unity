
package com.Digicave.Test ;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.InstrumentedActivity;
import com.urbanairship.location.UALocationManager;
import com.urbanairship.push.PushManager;

public class MainActivity extends InstrumentedActivity {


    Button locationButton;

    IntentFilter boundServiceFilter;
    IntentFilter apidUpdateFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apidUpdateFilter = new IntentFilter();
        apidUpdateFilter.addAction(UAirship.getPackageName()+IntentReceiver.APID_UPDATED_ACTION_SUFFIX);

        String apid = PushManager.shared().getAPID();
        Log.v("Plugin", "My Application onCreate - App APID: " + apid);
        Log.v("Plugin", "Resume the App") ;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        try{

        } catch (IllegalArgumentException e) {
        	
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            unregisterReceiver(boundServiceReceiver);
            unregisterReceiver(apidUpdateReceiver);
        } catch (IllegalArgumentException e) {
            Logger.error(e.getMessage());
        }
    }

    private BroadcastReceiver boundServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (UALocationManager.getLocationIntentAction(UALocationManager.ACTION_SUFFIX_LOCATION_SERVICE_BOUND).equals(intent.getAction())) {
                locationButton.setEnabled(true);
            } else {
                locationButton.setEnabled(false);
            }
        }
    };

    private BroadcastReceiver apidUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateApidField();
        }
    };

    private void updateApidField() {
        String apidString = PushManager.shared().getAPID();
        if (!PushManager.shared().getPreferences().isPushEnabled() || apidString == null) {
        	Log.v("Plugin", "The APID IS FUCKING NULL ALL THE FUCKING TIME");
        	Log.v("Plugin", "HERE IS THAT string ... :" + apidString );
        	Log.v("Plugin", "And is Push Enabled ???... :" + PushManager.shared().getPreferences().isPushEnabled() );
            apidString = "";
        }
        else Log.v("plugin", "this is the APID :" + apidString) ;

        // fill in apid text
    //    EditText apidTextField = (EditText)findViewById(R.id.apidText);
      //  if (!apidString.equals(apidTextField.getText())) {
        //    apidTextField.setText(apidString);
     //  }
    }
}
