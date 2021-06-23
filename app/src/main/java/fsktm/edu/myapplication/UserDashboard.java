package fsktm.edu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;


import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.telephony.SmsManager;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import android.os.Bundle;
import android.provider.Settings;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.IOException;

import java.util.List;
import java.util.Locale;


public class UserDashboard extends AppCompatActivity implements View.OnClickListener {

    //layout changes
    LinearLayout ml;




    private TextView latitude, longitude, address;
    TextView lockstatus, unlockstatus, engineonstatus, engineoffstatus, trackstatus;
    private FloatingActionButton button;
    FloatingActionButton floatingActionButtonSettings, notificationbutton;
    CardView clicklock, clickunlock, clickengineon, clickengineoff,clicktrack,clicksetings;

    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //layout
        ml = findViewById(R.id.userDashboardLayout);

        Load_setting();
        //intialize status
        lockstatus = findViewById(R.id.lockStatus);
        unlockstatus = findViewById(R.id.unlockStatus);
        engineonstatus = findViewById(R.id.engineOnStatus);
        engineoffstatus = findViewById(R.id.engineOffStatus);
        trackstatus = findViewById(R.id.trackStatus);
        floatingActionButtonSettings = findViewById(R.id.floatingActionButtonSettings);
        notificationbutton = findViewById(R.id.notificationbutton);


        //initialize click
        clicklock = findViewById(R.id.clickLock);
        clickunlock = findViewById(R.id.clickUnlock);
        clickengineon = findViewById(R.id.clickEngineOn);
        clickengineoff = findViewById(R.id.clickEngineOff);
        clicktrack = findViewById(R.id.clickTrack);
        //clicksetings = findViewById(R.id.clickSettings);

        clicklock.setOnClickListener(this);
        clickunlock.setOnClickListener(this);
        clickengineon.setOnClickListener(this);
        clickengineoff.setOnClickListener(this);
        clicktrack.setOnClickListener(this);
       // clicksetings.setOnClickListener(this);

        notificationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
            }
        });

        //settings button
        floatingActionButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Preference.class);
                startActivity(i);
            }
        });


        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.tvDeviceLocation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }else {
                    getLocation();
                }
            }
        });


    }

    private void Load_setting(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        boolean chk_night = sp.getBoolean("NIGHT",false);
        if (chk_night){
            ml.setBackgroundColor(Color.parseColor("#222222"));
        }else {
            ml.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        String orien = sp.getString("ORIENTATION","false");
        if ("1".equals(orien)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

        }else if ("2".equals(orien)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }else if("3".equals(orien)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
    }

    private void getLocation() {
        if(ActivityCompat.checkSelfPermission(
                UserDashboard.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(
                        UserDashboard.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(UserDashboard.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else{

            Location locationGPS     = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(locationGPS!= null){
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

                TextView latitude = (TextView) findViewById(R.id.valueLat);
                TextView longitude = (TextView) findViewById(R.id.valueLong);
                TextView address = (TextView) findViewById(R.id.tvCity);

                latitude.setText(String.valueOf(lat));
                longitude.setText(String.valueOf(longi));
                String addressLine = getCompleteAddressString(lat,longi);
                address.setText(String.valueOf(addressLine));
         //       address.setTextColor(Color.parseColor("#8AB663"));

            }else{
                Toast.makeText(UserDashboard.this, "Unable to find current location", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE){
        String strAdd="";
        Geocoder geocoder = new Geocoder(this,Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE,LONGITUDE,1);
            if (addresses != null){
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for(int i=0; i<= returnedAddress.getMaxAddressLineIndex(); i++){
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
            }else{

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strAdd;
    }

    @Override
    public void onClick(View v) {
        String phoneNo = "0164362154";
        String messageLock = "*LOCK*";
        String messageUnlock = "*ULOCK*";
        String messageengineOn = "*EGON*";
        String messageengineOff = "*EGOFF*";
        String messageTrack = "*TRACK*";
        String messageAlarm = "*ALARM*";

        switch (v.getId()){

            case R.id.clickLock:
                if(ContextCompat.checkSelfPermission(UserDashboard.this,Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    //initialize sms manager
                    SmsManager smsManager = SmsManager.getDefault();
                    //send text message
                    smsManager.sendTextMessage(phoneNo,null,messageLock,null,null);

                    lockstatus.setText("ACTIVE");
                    lockstatus.setTextColor(Color.parseColor("#8AB663"));
                    unlockstatus.setText("INACTIVE");
                    unlockstatus.setTextColor(Color.parseColor("#D14646"));

                    //display toast
                    Toast.makeText(getApplicationContext(),"Your car has been locked!!",Toast.LENGTH_SHORT).show();
                }else{
                    //when permission is not granted
                    //request permission
                    ActivityCompat.requestPermissions(UserDashboard.this
                            ,new String[]{Manifest.permission.SEND_SMS},100);
                }
                break;
            case R.id.clickUnlock:
                if(ContextCompat.checkSelfPermission(UserDashboard.this,Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    //initialize sms manager
                    SmsManager smsManager = SmsManager.getDefault();
                    //send text message
                    smsManager.sendTextMessage(phoneNo,null,messageUnlock,null,null);

                    unlockstatus.setText("ACTIVE");
                    unlockstatus.setTextColor(Color.parseColor("#8AB663"));
                    lockstatus.setText("INACTIVE");
                    lockstatus.setTextColor(Color.parseColor("#D14646"));

                    //display toast
                    Toast.makeText(getApplicationContext(),"Your car has been unlocked!!",Toast.LENGTH_SHORT).show();
                }else{
                    //when permission is not granted
                    //request permission
                    ActivityCompat.requestPermissions(UserDashboard.this
                            ,new String[]{Manifest.permission.SEND_SMS},100);
                }
                break;
            case R.id.clickEngineOn:
                if(ContextCompat.checkSelfPermission(UserDashboard.this,Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    //initialize sms manager
                    SmsManager smsManager = SmsManager.getDefault();
                    //send text message
                    smsManager.sendTextMessage(phoneNo,null,messageengineOn,null,null);

                    engineonstatus.setText("ACTIVE");
                    engineonstatus.setTextColor(Color.parseColor("#8AB663"));
                    engineoffstatus.setText("INACTIVE");
                    engineoffstatus.setTextColor(Color.parseColor("#D14646"));

                    //display toast
                    Toast.makeText(getApplicationContext(),"Engine ON!",Toast.LENGTH_SHORT).show();
                }else{
                    //when permission is not granted
                    //request permission
                    ActivityCompat.requestPermissions(UserDashboard.this
                            ,new String[]{Manifest.permission.SEND_SMS},100);
                }
                break;
            case R.id.clickEngineOff:
                if(ContextCompat.checkSelfPermission(UserDashboard.this,Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    //initialize sms manager
                    SmsManager smsManager = SmsManager.getDefault();
                    //send text message
                    smsManager.sendTextMessage(phoneNo,null,messageengineOff,null,null);

                    engineoffstatus.setText("ACTIVE");
                    engineoffstatus.setTextColor(Color.parseColor("#8AB663"));
                    engineonstatus.setText("INACTIVE");
                    engineonstatus.setTextColor(Color.parseColor("#D14646"));

                    //display toast
                    Toast.makeText(getApplicationContext(),"Engine is OFF!",Toast.LENGTH_SHORT).show();
                }else{
                    //when permission is not granted
                    //request permission
                    ActivityCompat.requestPermissions(UserDashboard.this
                            ,new String[]{Manifest.permission.SEND_SMS},100);
                }
                break;
            case R.id.clickTrack:
                if(ContextCompat.checkSelfPermission(UserDashboard.this,Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    //initialize sms manager
                    SmsManager smsManager = SmsManager.getDefault();
                    //send text message
                    smsManager.sendTextMessage(phoneNo,null,messageTrack,null,null);

                    trackstatus.setText("ACTIVE");
                    trackstatus.setTextColor(Color.parseColor("#8AB663"));

                    //display toast
                    Toast.makeText(getApplicationContext(),"Tracking your vehicle..",Toast.LENGTH_SHORT).show();
                }else{
                    //when permission is not granted
                    //request permission
                    ActivityCompat.requestPermissions(UserDashboard.this
                            ,new String[]{Manifest.permission.SEND_SMS},100);
                }
                break;
//            case R.id.clickSettings:
//                Toast.makeText(this,"Let's go to Settings",Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    @Override
    protected void onResume() {
        Load_setting();
        super.onResume();
    }

    //    private void sendMessage() {
//        String phoneNo = "01123575653";
//        String message = "*LOCK*";
//
//
//        //initialize sms manager
//        SmsManager smsManager = SmsManager.getDefault();
//        //send text message
//        smsManager.sendTextMessage(phoneNo,null,message,null,null);
//        //display toast
//        Toast.makeText(getApplicationContext(),"Your car has been locked!!",Toast.LENGTH_SHORT).show();
//
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //check condition
//        if(requestCode == 100 && grantResults.length > 0 && grantResults[0]
//        == PackageManager.PERMISSION_GRANTED){
//            //when permission is granted
//            // call method
//            sendMessage();
//        }else{
//            //when permission is denied
//            //display toast
//            Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
//        }
//    }
}