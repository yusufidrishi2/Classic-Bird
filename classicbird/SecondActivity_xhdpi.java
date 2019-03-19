package test.yusuf.com.classicbird;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import java.io.IOException;


public class SecondActivity_xhdpi extends AppCompatActivity {

    int x2,y2 = 450,x4,x5 = -720,falling_speed = 0;
    int x3 = 0,y4,y5,y6 = -525,y7,count = 0,press_count,index = 0;
    int i = 0,j = 0,index1 = 0,k = 0,l = 1,m = 0,n = 0,p = 0;

    int tap_index = 0,tapping_index = 0,finger_index = 0,wait = 3;
    int k_old = -1,l_old,m_old,n_old,p_old,new_index = 0,new_wait = 0;
    int menu_x,menu_y,circle_x,circle_y;

    int canh,canh_mid,canw_mid,ready_x,ready_y,bg_y;

    boolean flag = false,flag_point_x4 = true,flag_point_x5,initial = true;
    boolean point_flag = false,is_alive = true,paused = false,finger_flag = true,new_best_flag = false;
    boolean is_win = false,starting = true;

    Bitmap bg,bg_lower,up_pipe,down_pipe,pause,game_over,new_best,sky;
    Bitmap tap,best,new_show,get_ready,you_win,menu_button,circle,bg_pause;

    Bitmap[] down = new Bitmap[5];
    Bitmap[] up = new Bitmap[3];
    Bitmap[] points = new Bitmap[10];
    Bitmap[] tap_bird = new Bitmap[3];
    Bitmap[] finger = new Bitmap[2];

    MediaPlayer die,hit,point,button;
    MediaPlayer wing = new MediaPlayer();

    public static final String SAVE = "Points";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new BallView(this));

        hit = MediaPlayer.create(this, R.raw.hit);
        point = MediaPlayer.create(this, R.raw.point);
        die = MediaPlayer.create(this, R.raw.die);
        button = MediaPlayer.create(this, R.raw.button);


        down[0] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up_1);
        down[1] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up_2);
        down[2] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up_3);
        down[3] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up_4);
        down[4] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_down);

        up[0] = BitmapFactory.decodeResource(getResources(), R.drawable.flappy_up);
        up[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wing_bird_2);
        up[2] = BitmapFactory.decodeResource(getResources(), R.drawable.wing_bird_1);

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bg_lower = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lower);

        up_pipe = BitmapFactory.decodeResource(getResources(), R.drawable.up_pipe);
        down_pipe = BitmapFactory.decodeResource(getResources(), R.drawable.down_pipe);

        points[0] = BitmapFactory.decodeResource(getResources(), R.drawable.zero);
        points[1] = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        points[2] = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        points[3] = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        points[4] = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        points[5] = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        points[6] = BitmapFactory.decodeResource(getResources(), R.drawable.six);
        points[7] = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        points[8] = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
        points[9] = BitmapFactory.decodeResource(getResources(), R.drawable.nine);

        pause = BitmapFactory.decodeResource(getResources(), R.drawable.pause);

        game_over = BitmapFactory.decodeResource(getResources(), R.drawable.game_over);
        new_best = BitmapFactory.decodeResource(getResources(), R.drawable.new_best);
        best = BitmapFactory.decodeResource(getResources(), R.drawable.best);
        tap = BitmapFactory.decodeResource(getResources(), R.drawable.tap);

        tap_bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.tap_bird_1);
        tap_bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.tap_bird_3);
        tap_bird[2] = BitmapFactory.decodeResource(getResources(), R.drawable.tap_bird_2);

        finger[0] = BitmapFactory.decodeResource(getResources(), R.drawable.finger_1);
        finger[1] = BitmapFactory.decodeResource(getResources(), R.drawable.finger_2);


        new_show = BitmapFactory.decodeResource(getResources(), R.drawable.new_show);

        get_ready = BitmapFactory.decodeResource(getResources(), R.drawable.get_ready);
        you_win = BitmapFactory.decodeResource(getResources(), R.drawable.you_win);

        circle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        menu_button = BitmapFactory.decodeResource(getResources(), R.drawable.menu);

        sky = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        bg_pause = BitmapFactory.decodeResource(getResources(), R.drawable.bg_pause);

    }

    public void update() { setContentView(new BallView(this)); }

    class BallView extends View {
        public BallView(Context context){
            super(context);
        }
        @Override
        public void onDraw(Canvas canvas) {

            if( starting ) {

                canh = canvas.getHeight() - (canvas.getHeight() / 8);

                x2 = (canvas.getWidth() / 2) - 46;

                canh_mid = canh/2;
                canw_mid = canvas.getWidth()/2;

                x4 = canvas.getWidth()+30;
                y4 = canh-1567;

                y2 = canh/3;

                menu_x = canw_mid-188;
                menu_y = canh_mid+184;
                circle_x = canw_mid+120;
                circle_y = canh_mid+184;

                ready_x = canw_mid/2-24;
                ready_y =canh_mid-((3*canh_mid)/4);

                starting = false;

                bg_y = canh-230;

            }

            canvas.drawBitmap(sky, 0, 0, null);
            canvas.drawBitmap(bg, x3, bg_y, null);


            if( initial ) {

                canvas.drawBitmap(get_ready, ready_x, ready_y, null);
                canvas.drawBitmap(tap_bird[tap_index], x2, y2, null);
                canvas.drawBitmap(tap, x2-140, y2+120, null);
                if ( tapping_index == 10 ) {
                    if ( tap_index < 2 )
                        tap_index++;
                    else
                        tap_index = 0;
                    tapping_index = 0;
                }
                if( finger_flag ) {
                    if (finger_index >= 50) {
                        finger_flag = false;
                        finger_index = 0;
                        wait = 3;
                    }
                    else
                        canvas.drawBitmap(finger[0], (x2+86) - finger_index, (y2+325) - finger_index, null);
                }
                else {
                    if( wait>0 ) {
                        canvas.drawBitmap(finger[1], (x2+31), y2+235, null);
                        finger_index--;
                    }
                    else {
                        canvas.drawBitmap(finger[0], (x2+36) + finger_index, (y2+275) + finger_index, null);
                        if ( finger_index >= 50 ) {
                            finger_flag = true;
                            finger_index = 0;
                        }
                    }
                }
                tapping_index++;
                finger_index++;
                wait--;
            }
            else {
                if ( !paused && is_alive ) {
                    if ( x4 < 150 && x5 < (-up_pipe.getWidth()) ) {
                        x5 = canvas.getWidth() + 2;
                        flag_point_x5 = true;
                        y6 = -((int) ((Math.random() * 1000)));
                        if (y6 < -750)
                            y6 = -750;
                        if (y6 > (canh-1367) )
                            y6 = (canh-1367);
                    }
                    else if ( x5 < 150 && x4 < (-up_pipe.getWidth()) ) {
                        x4 = canvas.getWidth() + 2;
                        flag_point_x4 = true;
                        y4 = -((int) (Math.random() * 1000));
                        if ( y4 < -750 )
                            y4 = -750;
                        if ( y4 > (canh-1367) )
                            y4 = (canh-1367);
                    }
                    if ( flag_point_x4 && x4 < 330 ) {
                        point.start();
                        flag_point_x4 = false;
                        point_flag = true;
                    }
                    else if ( flag_point_x5 && x5 < 330 ) {
                        point.start();
                        flag_point_x5 = false;
                        point_flag = true;
                    }
                    y5 = 1100 + y4;
                }
                canvas.drawBitmap(up_pipe, x4, y4, null);
                canvas.drawBitmap(down_pipe, x4, y5, null);
                if ( !paused && is_alive ) {
                    x4 -= 5;
                    y7 = 1100 + y6;
                }
                canvas.drawBitmap(up_pipe, x5, y6, null);
                canvas.drawBitmap(down_pipe, x5, y7, null);
                if ( !paused && is_alive )
                    x5 -= 5;

                if ( !paused && is_alive ) {
                    if (k < 10) {
                        canvas.drawBitmap(points[k], canw_mid, 25, null);
                    } else if (k < 100) {
                        canvas.drawBitmap(points[l], canw_mid - 29, 25, null);
                        canvas.drawBitmap(points[m], canw_mid +17, 25, null);
                    } else if (k < 1000) {
                        canvas.drawBitmap(points[l], canw_mid - 46,25, null);
                        canvas.drawBitmap(points[m], canw_mid, 25, null);
                        canvas.drawBitmap(points[n], canw_mid + 46, 25, null);
                    }else{
                        canvas.drawBitmap(points[l], canw_mid -74, 25, null);
                        canvas.drawBitmap(points[m], canw_mid -29, 25, null);
                        canvas.drawBitmap(points[n], canw_mid +17, 25, null);
                        canvas.drawBitmap(points[p], canw_mid +62, 25, null);
                    }

                    if ( point_flag ) {
                        if (k > 9 && k < 100) {
                            m++;
                            if (m == 10) {
                                l++;
                                if (l == 10)
                                    l = 1;
                                m = 0;
                            }
                        } else if (k > 99 && k < 1000) {
                            n++;
                            if (n == 10) {
                                m++;
                                if (m == 10) {
                                    l++;
                                    if (l == 10)
                                        l = 1;
                                    m = 0;
                                }
                                n = 0;
                            }
                        } else {
                            p++;
                            if (p == 10) {
                                n++;
                                if (n == 10) {
                                    m++;
                                    if (m == 10) {
                                        l++;
                                        if (l == 10)
                                            l = 1;
                                        m = 0;
                                    }
                                    n = 0;
                                }
                                p = 0;
                            }
                        }
                        if( k == 9999 ){
                            is_alive = false;
                            is_win = true;
                            l = 9; m = 9; n = 9; p = 9;
                            k = 9998;
                        }
                        k++;
                        point_flag = false;
                    }
                }
                if ( !flag ) {
                    canvas.drawBitmap(down[i], x2, y2, null);
                    if ( !paused && is_alive ) {
                        y2 += falling_speed;
                        if (index % 4 == 0) {
                            if (i < 4)
                                i++;
                            falling_speed++;
                        }
                        index1 = 0;
                    }
                }
                else {
                    canvas.drawBitmap(up[j], x2, y2, null);

                    if ( !paused && is_alive ) {
                        if (index1 % 4 == 0) {
                            if (j < 2)
                                j++;
                            else
                                j = 0;
                        }
                        index = 0;
                        falling_speed = 0;
                        i = 0;
                    }
                }
                if ( !paused && is_alive ) {
                    if (count == 15)
                        flag = false;
                    count++;
                    index++;
                    index1++;
                }
            }
            canvas.drawBitmap(bg_lower, x3, canh, null);

            if ( ( !paused  && is_alive ) || initial ) {
                x3 -= 5;
                if (x3 < -1010)
                    x3 = 0;
                canvas.drawBitmap(pause, 5, 7, null);
            }
            else {
                SharedPreferences loadGame = getSharedPreferences(SAVE, 0);
                k_old = loadGame.getInt("k", 0);
                l_old = loadGame.getInt("l", 0);
                m_old = loadGame.getInt("m", 0);
                n_old = loadGame.getInt("n", 0);
                p_old = loadGame.getInt("p", 0);

                if ( k > k_old ) {

                    SharedPreferences saveGame = getSharedPreferences(SAVE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = saveGame.edit();
                    editor.putInt("k", k).putInt("l", l).putInt("m", m).putInt("n", n).putInt("p", p);
                    editor.apply();

                }

                canvas.drawBitmap(bg_pause, 0, 0, null);

                if( k<=k_old && !new_best_flag )
                    canvas.drawBitmap(best, canw_mid-190, canh_mid-190, null);
                else {
                    canvas.drawBitmap(new_best, canw_mid-190, canh_mid-190, null);
                    if (new_index == 5) {
                        if (new_wait <= 5) {
                            canvas.drawBitmap(new_show, canw_mid-190, canh_mid-190, null);
                            new_wait++;
                        } else {
                            new_index = 0;
                            new_wait = 0;
                        }
                    }
                    if (new_wait == 0)
                        new_index++;
                    k_old = k;
                    l_old = l;
                    m_old = m;
                    n_old = n;
                    p_old = p;
                    new_best_flag = true;
                }

                canvas.drawBitmap(menu_button, menu_x, menu_y, null);
                canvas.drawBitmap(circle, circle_x, circle_y, null);

                if( is_win )
                    canvas.drawBitmap(you_win, canw_mid-240, canh_mid-350, null);
                else if( !is_alive )
                    canvas.drawBitmap(game_over, canw_mid-230, canh_mid-313, null);

                if ( k < 10 )
                    canvas.drawBitmap(points[k], canw_mid-8, canh_mid-110, null);
                else if ( k < 100 ) {
                    canvas.drawBitmap(points[l], canw_mid-21, canh_mid-110, null);
                    canvas.drawBitmap(points[m], canw_mid+24, canh_mid-110, null);
                } else if ( k < 1000 ) {
                    canvas.drawBitmap(points[l], canw_mid-54, canh_mid-110, null);
                    canvas.drawBitmap(points[m], canw_mid-8, canh_mid-110, null);
                    canvas.drawBitmap(points[n], canw_mid+38, canh_mid-110, null);
                }else{
                    canvas.drawBitmap(points[l], canw_mid-66, canh_mid-110, null);
                    canvas.drawBitmap(points[m], canw_mid-21, canh_mid-110, null);
                    canvas.drawBitmap(points[n], canw_mid+24, canh_mid-110, null);
                    canvas.drawBitmap(points[p], canw_mid+70, canh_mid-110, null);
                }

                if ( k_old < 10 )
                    canvas.drawBitmap(points[k_old], canw_mid-12, canh_mid+77, null);
                else if ( k_old < 100 ) {
                    canvas.drawBitmap(points[l_old], canw_mid-25, canh_mid+77, null);
                    canvas.drawBitmap(points[m_old], canw_mid+20, canh_mid+77, null);
                } else if ( k_old < 1000 ) {
                    canvas.drawBitmap(points[l_old], canw_mid-58, canh_mid+77, null);
                    canvas.drawBitmap(points[m_old], canw_mid-12, canh_mid+77, null);
                    canvas.drawBitmap(points[n_old], canw_mid+34, canh_mid+77, null);
                }else{
                    canvas.drawBitmap(points[l_old], canw_mid-70, canh_mid+77, null);
                    canvas.drawBitmap(points[m_old], canw_mid-25, canh_mid+77, null);
                    canvas.drawBitmap(points[n_old], canw_mid+20, canh_mid+77, null);
                    canvas.drawBitmap(points[p_old], canw_mid+66, canh_mid+77, null);
                }
            }
            if ((((x4 < (x2 + 70)) && (((x4 + up_pipe.getWidth())) > x2) && (y2 < (y4 + up_pipe.getHeight() - 14))) ||
                    ((x5 < (x2 + 70)) && (((x5 + up_pipe.getWidth())) > x2) && (y2 < (y6 + up_pipe.getHeight() - 14))) ||
                    ((x4 < (x2 + 70)) && (((x4 + down_pipe.getWidth()) - 50) > x2) && ((y2 + 60) > y5)) ||
                    ((x5 < (x2 + 70)) && (((x5 + down_pipe.getWidth()) - 50) > x2) && ((y2 + 60) > y7)) ||
                    (y2 >= (canh-98))) && is_alive ) {
                hit.start();
                die.start();
                is_alive = false;
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
            }
            update();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int pressX = (int) event.getX();
        int pressY = (int) event.getY();

        int x = event.getAction();

        if (x == MotionEvent.ACTION_DOWN) {
            if( initial )
                initial = false;
            else if ( (pressX < 89 && pressY < 91) && !paused ) {
                button.start();
                paused = true;
            }
            else if ( paused && pressX>circle_x && pressX<(circle_x+89) && pressY>circle_y && pressY<(circle_y+84) ){
                button.start();
                paused = false;
            }
            else if ( !is_alive && pressX>circle_x && pressX<(circle_x+89) && pressY>circle_y && pressY<(circle_y+84) ){

                y2 = 450; x5 = -720; falling_speed = 0;
                x3 = 0; y6 = -525; count = 0; index = 0;
                i = 0; j = 0; index1 = 0; k = 0; l = 1; m = 0; n = 0; p = 0;

                tap_index = 0; tapping_index = 0; finger_index = 0; finger_index = 0; wait = 3;
                new_index = 0; new_wait = 0;

                flag = false; flag_point_x4 = true; initial = true;
                point_flag = false; is_alive = true; paused = false; finger_flag = false;flag_point_x5 = false;
                new_best_flag = false; is_win = false; starting = true;

                button.start();

            }
            else if( (!is_alive || paused ) && pressX>menu_x && pressX<(menu_x+198) && pressY>menu_y && pressY<(menu_y+99) ){

                button.start();
                finish();

            }
            else if ( !paused && is_alive ){
                if ( flag )
                    press_count += 10;
                else
                    press_count = 80;
                y2 = y2 - press_count;
                count = 0;
                flag = true;

                if ( wing.isPlaying() )
                    wing.stop();
                try {
                    wing.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("wing.wav");
                    wing.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    wing.prepare();
                    wing.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Toast.makeText(this, "canh_mid = "+canh_mid+" canw_mid = "+canw_mid+"\nx = "+pressX+" y = "+pressY, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onBackPressed(){}

}
