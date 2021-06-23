package fsktm.edu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class biometricauthentication extends AppCompatActivity {

    private TextView label;
    private Button buttonauthenticate;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometricauthentication);

        label = findViewById(R.id.label);
        buttonauthenticate = findViewById(R.id.button);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(biometricauthentication.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //error authenticating, stop tasks that requires auth
                label.setText("Authentication error: " + errString);
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                Toast.makeText(biometricauthentication.this,"Authentication error: " + errString,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //authentication succeeded, continue tasks that requires auth
                label.setText("Authentication succeeded..!");
                Toast.makeText(biometricauthentication.this,"Authentication succeeded..!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UI.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //error authenticating, stop tasks that requires auth
                label.setText("Authentication failed..!");
                Toast.makeText(biometricauthentication.this,"Authentication failed..!",Toast.LENGTH_SHORT).show();
            }
        });

        //setup title, description on auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint authentication")
                .setNegativeButtonText("NAVIGALERT")
                .build();

        buttonauthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });




    }
}