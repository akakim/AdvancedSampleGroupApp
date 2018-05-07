package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class VideoViewActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener{


    @BindView( R.id.videoView)
    VideoView videoView;

    int position = 0;
    ProgressDialog progressDialog;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        ButterKnife.bind( this );
        if( mediaController == null ){
            mediaController = new MediaController( this );

        }

        progressDialog = new ProgressDialog( this );

        progressDialog.setTitle( "로딩중");
        progressDialog.setMessage( "Loading..... " );
        progressDialog.setCancelable( true );

        progressDialog.show();


        try{
            videoView.setMediaController( mediaController );

            Uri.Builder builder = new Uri.Builder();

            Uri uri = Uri.parse("https://youtu.be/gAokW0PNTeg" );
            Log.d(getClass().getSimpleName()," uri value : " + uri.toString() );


            videoView.setVideoURI( Uri.parse("https://youtu.be/gAokW0PNTeg"));
        }catch ( Exception e ){

            e.printStackTrace();
        }

        videoView.requestFocus();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                progressDialog.dismiss();

                //if we have a position on savedInstanceState, the video playback should start from here
//                myVideoView.seekTo(position);

                videoView.seekTo( position );
                if( position == 0){
                    mp.start();
                }else {
                    mp.pause();
                }
            }
        });


        videoView.setOnErrorListener( this );

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("pos", videoView.getCurrentPosition());
        videoView.pause();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt("pos");
        videoView.seekTo( position );
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        if(progressDialog.isShowing() ){
            progressDialog.dismiss();
        }
        return false;


    }
}
