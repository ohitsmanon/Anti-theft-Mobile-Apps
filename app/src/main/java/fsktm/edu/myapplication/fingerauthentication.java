package fsktm.edu.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class fingerauthentication extends AppCompatActivity {

//    private TextView paralabel;
//    private Button fingerprintauthenticatebutton;
//    private Executor executor;
//    private BiometricPrompt biometricPrompt;
//    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //init UI views
//        paralabel = findViewById(R.id.paralebel);
//        fingerprintauthenticatebutton = findViewById(R.id.buttonauthenticate);
//
//        executor = ContextCompat.getMainExecutor(this);
//        biometricPrompt = new BiometricPrompt(fingerauthentication.this, executor, new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                //error authenticating, stop tasks that requires auth
//                paralabel.setText("Authentication error: " + errString);
//                Toast.makeText(fingerauthentication.this,"Authentication error: " + errString,Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
//                //authentication succeeded, continue tasks that requires auth
//                paralabel.setText("Authentication succeeded..!");
//                Toast.makeText(fingerauthentication.this,"Authentication succeeded..!",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
//
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                //error authenticating, stop tasks that requires auth
//                paralabel.setText("Authentication failed..!");
//                Toast.makeText(fingerauthentication.this,"Authentication failed..!",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //setup title, description on auth dialog
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric Authentication")
//                .setSubtitle("Login using fingerprint authentication")
//                .setNegativeButtonText("User App Password")
//                .build();
//
//        //handle authenticate click, start authentication
//        fingerprintauthenticatebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                biometricPrompt.authenticate(promptInfo);
//            }
//        });
    }
}