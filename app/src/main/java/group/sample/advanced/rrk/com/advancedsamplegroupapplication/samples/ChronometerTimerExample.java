package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class ChronometerTimerExample extends AppCompatActivity {


    @BindView( R.id.chronometer)
    Chronometer chronometer;

    @BindView(R.id.btnStart)
    Button btnStart;

    @BindView(R.id.btnStop)
    Button btnStop;

    @BindView(R.id.btnReset)
    Button btnReset;


    @BindView( R.id.tvMiddleResult)
    TextView tvMiddleResult;


    @BindView( R.id.tvResult)
    TextView tvResult;

    int count =  0;
    Long startTime;

    CountDownTimer countDownTimer;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer_timer_example2);

        ButterKnife.bind( this );



        chronometer.setCountDown( true );


//        Log.d(getClass().getSimpleName()," description" +                 chronometer.getContentDescription());
//
//
//        Log.d(getClass().getSimpleName(),"getFormat" + chronometer.getFormat());

        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                Log.d("onTick()","millisUntilFinished : "+millisUntilFinished );
                if( millisUntilFinished >= 4000){
                    tvMiddleResult.setText( "4초");
                }else if( millisUntilFinished >= 3000){
                    tvMiddleResult.setText( "3초");
                }else if(millisUntilFinished >= 2000){
                    tvMiddleResult.setText( "2초");
                }else {

                }
            }

            @Override
            public void onFinish() {
                tvResult.setText( count++ + "번 끝");
            }
        };



        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {


                Log.d("chonometerTick",chronometer.getBase()+"");


//                if (                 chronometer.isTheFinalCountDown() )               }
//                if(chronometer.getBase() <= 0L){
//                    chronometer.stop();
//                    chronometer.setBase(SystemClock.elapsedRealtime() );
//                }

//                if( chronometer.getBase() == startTime )
            }
        });

    }


    @OnClick( { R.id.btnStart,R.id.btnStop,R.id.btnReset })
    public void controlButton(View v){

        switch ( v.getId( ) ){
            case R.id.btnStart:
//                startTime    = SystemClock.elapsedRealtime();
//
//                chronometer.setBase( SystemClock.elapsedRealtime() + 5000L);
//                chronometer.start();
//                Log.d(getClass().getSimpleName()," description : " +                 chronometer.getContentDescription());
//                Log.d(getClass().getSimpleName(),"getFormat : " + chronometer.getFormat());
                countDownTimer.start();
                break;
            case R.id.btnStop:
                countDownTimer.cancel();
//                chronometer.stop();
                break;
            case R.id.btnReset:
                countDownTimer.start();
//                chronometer.setBase(SystemClock.elapsedRealtime() + 5000L);
                break;
        }
    }
}
