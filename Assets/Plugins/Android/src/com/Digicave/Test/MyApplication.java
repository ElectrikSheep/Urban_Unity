

package com.Digicave.Test;

import android.app.Application;
import android.util.Log;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate() ;
        //Activity _a = UnityPlayer.currentActivity ;
		
		//AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions( _a.getBaseContext() );
        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this) ;

	        options.developmentAppKey 		= "whhtUG1fQ96W8mp1rYnESA";
	        options.developmentAppSecret	= "zeEqETmBS9OiBWAtaCOHkg";
	        options.productionAppKey 		= "Your Development App Key";
	        options.productionAppSecret 	= "Your Development App Secret";
	        options.gcmSender 				= "709020472268" ;
	        options.transport 				= "gcm" ;
	        options.analyticsEnabled 	= false ;
	        options.inProduction 		= false;
	        options.minSdkVersion 		= 4 ;
	        
	        Log.v("Plugin", "Values :" + "\n" 
	        + options.developmentAppKey + "\n" 
	        + options.developmentAppSecret + "\n" 
	        + options.gcmSender);
	        
        UAirship.takeOff(this, options);
        PushManager.enablePush() ;
        
        Logger.logLevel = Log.VERBOSE;
    }  
}
