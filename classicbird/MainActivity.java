package test.yusuf.com.classicbird;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    float density;

    MediaPlayer button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        density = getResources().getDisplayMetrics().density;

        if (density >= 3.0) {
            Intent intent = new Intent(this, FirstActivity_xxhdpi.class);
            startActivity(intent);
        } else if (density >= 2.0) {
            Intent intent = new Intent(this, FirstActivity_xhdpi.class);
            startActivity(intent);
        } else if (density >= 1.5) {
            Intent intent = new Intent(this, FirstActivity_hdpi.class);
            startActivity(intent);
        } else if (density >= 1.0) {
            Intent intent = new Intent(this, FirstActivity_mdpi.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FirstActivity_ldpi.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

        button = MediaPlayer.create(this, R.raw.button);

    }

    public void close_app(View view){

        button.start();
        finish();

    }

    public void cancel(View view){

        button.start();

        density = getResources().getDisplayMetrics().density;

        if (density >= 3.0) {
            Intent intent = new Intent(this, FirstActivity_xxhdpi.class);
            startActivity(intent);
        } else if (density >= 2.0) {
            Intent intent = new Intent(this, FirstActivity_xhdpi.class);
            startActivity(intent);
        } else if (density >= 1.5) {
            Intent intent = new Intent(this, FirstActivity_hdpi.class);
            startActivity(intent);
        } else if (density >= 1.0) {
            Intent intent = new Intent(this, FirstActivity_mdpi.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FirstActivity_ldpi.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed(){

        button.start();

        density = getResources().getDisplayMetrics().density;

        if (density >= 3.0) {
            Intent intent = new Intent(this, FirstActivity_xxhdpi.class);
            startActivity(intent);
        } else if (density >= 2.0) {
            Intent intent = new Intent(this, FirstActivity_xhdpi.class);
            startActivity(intent);
        } else if (density >= 1.5) {
            Intent intent = new Intent(this, FirstActivity_hdpi.class);
            startActivity(intent);
        } else if (density >= 1.0) {
            Intent intent = new Intent(this, FirstActivity_mdpi.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, FirstActivity_ldpi.class);
            startActivity(intent);
        }

    }

}

