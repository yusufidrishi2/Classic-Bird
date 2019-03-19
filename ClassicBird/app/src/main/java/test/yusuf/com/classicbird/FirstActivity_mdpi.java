package test.yusuf.com.classicbird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FirstActivity_mdpi extends AppCompatActivity {

    int x = 0,y,canH,tap_index = 0,tapping_index = 0,canH_mid,x2,y2,canW_mid;
    int flappyX,flappyY,play_x,play_y,high_x,high_y,quit_x,quit_y;

    boolean initial = true;

    Bitmap bg,bg_lower,flappy_bird;
    Bitmap sky,high_score,play,quit;

    Bitmap[] tap_bird = new Bitmap[3];

    MediaPlayer button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new FirstActivity_mdpi.BallView(this));

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bg_lower = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lower);

        tap_bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up);
        tap_bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wing_bird_2);
        tap_bird[2] = BitmapFactory.decodeResource(getResources(), R.drawable.wing_bird_1);

        flappy_bird = BitmapFactory.decodeResource(getResources(), R.drawable.flapy_bird);

        sky = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        play = BitmapFactory.decodeResource(getResources(), R.drawable.play);
        high_score = BitmapFactory.decodeResource(getResources(), R.drawable.high_score);
        quit = BitmapFactory.decodeResource(getResources(), R.drawable.quit);

        button = MediaPlayer.create(this, R.raw.button);

    }

    public void update() { setContentView(new FirstActivity_mdpi.BallView(this)); }

    class BallView extends View {
        public BallView(Context context){super(context);}
        @Override
        public void onDraw(Canvas canvas){
            if(initial) {
                canW_mid = canvas.getWidth()/2;
                canH = canvas.getHeight() - (canvas.getHeight() / 8);
                canH_mid = canH/2;
                y = canH-100;
                x2 = canW_mid - (canW_mid/8);
                y2 = canH/3;
                flappyX = (canW_mid/2)+20;
                flappyY = canH/8;
                play_x = canW_mid-53;
                play_y = canH_mid-20;
                high_x = canW_mid-75;
                high_y = canH_mid+70;
                quit_x = canW_mid-50;
                quit_y = canH_mid+160;
            }
            initial = false;

            canvas.drawBitmap(sky, 0, 0, null);

            canvas.drawBitmap(bg, x, y, null);
            canvas.drawBitmap(flappy_bird, flappyX, flappyY, null);
            canvas.drawBitmap(bg_lower, x, canH, null);
            canvas.drawBitmap(tap_bird[tap_index], x2, y2, null);


            canvas.drawBitmap(play, play_x, play_y, null);
            canvas.drawBitmap(high_score, high_x, high_y, null);
            canvas.drawBitmap(quit, quit_x, quit_y, null);

            if ( tapping_index == 10 ) {
                if ( tap_index < 2 )
                    tap_index++;
                else
                    tap_index = 0;
                tapping_index = 0;
            }
            x -= 5;
            if (x < -500)
                x = 0;
            tapping_index++;

            update();

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        int pressX = (int) event.getX();
        int pressY = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if( pressX>play_x && pressX<play_x+99 && pressY>play_y && pressY<play_y+47 ) {
                button.start();
                Intent intent = new Intent(this, SecondActivity_mdpi.class);
                startActivity(intent);
            }
            else if( pressX>high_x && pressX<high_x+149 && pressY>high_y && pressY<high_y+50 ) {
                button.start();
                Intent intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
            }
            else if( pressX>quit_x && pressX<quit_x+98 && pressY>quit_y && pressY<quit_y+50 ) {
                button.start();
                finish();
            }
            //Toast.makeText(this, "canw_mid = "+canW_mid+"canh_mid = "+canH_mid+"\nx = "+pressX+" y = "+pressY, Toast.LENGTH_LONG).show();
            //Toast.makeText(this, ""+x, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onBackPressed(){

        button.start();
        finish();

    }

}



