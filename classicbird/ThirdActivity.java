package test.yusuf.com.classicbird;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import static test.yusuf.com.classicbird.SecondActivity_xhdpi.SAVE;

public class ThirdActivity extends AppCompatActivity {
    int k;

    MediaPlayer button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third);

        button = MediaPlayer.create(this, R.raw.button);

        TextView textView = (TextView) findViewById(R.id.textView2);

        ImageView lock = (ImageView) findViewById(R.id.imageView);
        ImageView lock_beg = (ImageView) findViewById(R.id.imageView1);
        ImageView lock_pro = (ImageView) findViewById(R.id.imageView2);
        ImageView lock_leg = (ImageView) findViewById(R.id.imageView3);

        SharedPreferences loadGame = getSharedPreferences(SAVE, 0);
        k = loadGame.getInt("k", 0);

        textView.setText("BEST: "+k);



        lock.setVisibility(View.VISIBLE);
        lock_beg.setVisibility(View.INVISIBLE);
        lock_pro.setVisibility(View.INVISIBLE);
        lock_leg.setVisibility(View.INVISIBLE);


        if( k>=50 ) {
            lock_leg.setVisibility(View.VISIBLE);
            lock.setVisibility(View.INVISIBLE);
            lock_pro.setVisibility(View.INVISIBLE);
            lock_beg.setVisibility(View.INVISIBLE);
        }
        else if ( k>=30 ) {
            lock_pro.setVisibility(View.VISIBLE);
            lock.setVisibility(View.INVISIBLE);
            lock_beg.setVisibility(View.INVISIBLE);
            lock_leg.setVisibility(View.INVISIBLE);
        }
        else if( k>=10 ) {
            lock_beg.setVisibility(View.VISIBLE);
            lock.setVisibility(View.INVISIBLE);
            lock_pro.setVisibility(View.INVISIBLE);
            lock_leg.setVisibility(View.INVISIBLE);
        }


    }

    public void mainActivity(View view) {

        button.start();
        finish();

    }

    @Override
    public void onBackPressed(){

        button.start();
        finish();

    }

}

