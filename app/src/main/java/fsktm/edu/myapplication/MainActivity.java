package fsktm.edu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    Button getstarted;
    LottieAnimationView lottieAnimationView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getstarted = findViewById(R.id.buttongetstarted);
        lottieAnimationView1 = findViewById(R.id.lottie1);
        lottieAnimationView1.animate().translationX(1600).setDuration(1000).setStartDelay(4000);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,biometricauthentication.class));
            }
        });

    }


}