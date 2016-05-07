package in.pathri.officeprofile;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
// import android.content.SharedPreferences;
// import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
// import android.preference.PreferenceManager;
import android.provider.Settings;
// import android.provider.Settings.SettingNotFoundException;
import android.view.Menu;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

// import in.pathri.prefeditor.PrivatePreferenceManager;

public class OfficeProfile extends Activity {
      private static AudioManager audio;
    private static TextView textView;
//   	 private static int currentMusicVolume,currentNotificationVolume,currentVibrate,currentBrightness,currentRotation;
//   	 private static boolean currentState;
//   	 final String currentStateKey = "currentState";
//   		String mPackageName = "com.syntellia.fleksy.keyboard";
//      String mKey1 = "vibKeyBox";
//   	  String mKey2 = "vibBtnBox";
//   	 SharedPreferences prefs;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        textView = (TextView) findViewById(R.id.status_view);

//         prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//         currentState = prefs.getBoolean(currentStateKey, false);
      
// 	currentState = !currentState;
     
//       currentMusicVolume = prefs.getInt("currentMusicVolume", 0);
//       currentNotificationVolume = prefs.getInt("currentNotificationVolume", 0);
//       currentVibrate = prefs.getInt("currentVibrate", 0);
//       currentBrightness = prefs.getInt("currentBrightness", 0);
//       currentRotation = prefs.getInt("currentRotation", 0);

disableBT();
        	updateStatusText("BT connection ended");      
		endDataConnection();
      	updateStatusText("data connection ended");
      zeroBrightness();
              updateStatusText("brigtness zero");
        silenceAudio();
              updateStatusText("audio silenced");
  
// 			keyvibToggle(currentState);
//        updateStatusText("keyboard vib changed");
      
//         SharedPreferences.Editor editor = prefs.edit();
//         editor.putInt("currentMusicVolume",currentMusicVolume);
//       editor.putInt("currentNotificationVolume",currentNotificationVolume);
//       editor.putInt("currentVibrate",currentVibrate);
//       editor.putInt("currentBrightness",currentBrightness);
//       editor.putInt("currentRotation",currentRotation);
//       editor.putBoolean(currentStateKey,currentState);
//         editor.commit();
        
    }
    
    private void silenceAudio(){      
      audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);      
        audio.setStreamVolume(AudioManager.STREAM_MUSIC,0,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION,0,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

  
  private void zeroBrightness(){
    ContentResolver cResolver  = this.getApplicationContext().getContentResolver();

      
      Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, 0);        
      
      LayoutParams layoutpars = getWindow().getAttributes();

      layoutpars.screenBrightness = 0 / (float)255;

      getWindow().setAttributes(layoutpars);
    
  }
  
  private void endDataConnection(){
    DataConnectionHelper.setMobileDataState(false,this);
  }
    
  private void disableBT(){
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
if (mBluetoothAdapter != null) {
    mBluetoothAdapter.disable();
}
  }
  
  
//   private void keyvibToggle(boolean silent){
// 		PrivatePreferenceManager privatePreferenceManager = PrivatePreferenceManager.getPreferences(mPackageName,this);
// //       Boolean currentVal = (Boolean)privatePreferenceManager.getValue(mKey);   
// //       Log.d("PREFEDIT","currentPrefVal" + currentVal.toString());
//       privatePreferenceManager.updateValue(mKey1,!silent);    
//     privatePreferenceManager.updateValue(mKey2,!silent);    
// //     Log.d("PREFEDIT","updateresult" + Boolean.toString(res));      
//   }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private  static void updateStatusText(String txt){
        String temp = textView.getText().toString();
        textView.setText(temp + "|" + txt);
    }
}
