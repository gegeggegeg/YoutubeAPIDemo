package com.example.peter.youtubeapitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity {
    private static final String TAG = "MainActivity";
    YouTubePlayerView youTubePlayerView;
    Button playBtn;
    YouTubePlayer.OnInitializedListener mInitializedListener;
    String APIkey = "AIzaSyBNDZt3LNUdbxKvOdS1clVBchKbks01ceM";
    private List<String> playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playlist = new ArrayList<String>();
        playlist.add("jt6Z5yo8Ddg");
        playlist.add("-awl4tFFbO4");
        playlist.add("P1-zeMdJRsg");
        youTubePlayerView = findViewById(R.id.YoutubeView1);
        playBtn = findViewById(R.id.playBtn);
        mInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Done Initializing");
                youTubePlayer.loadVideos(playlist);
                //youTubePlayer.loadVideo("W4hTJybfU7s");
                playBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick: Load next video");
                        if(youTubePlayer.hasNext()){
                            youTubePlayer.next();
                            Log.d(TAG, "onClick: Loading success");
                        }
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Fail to initialize");
            }
        };
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!youTubePlayerView.isActivated()) {
                    Log.d(TAG, "onClick: initialize youtubeview");
                    youTubePlayerView.initialize(APIkey, mInitializedListener);
                    playBtn.setText("Next");
                }
            }
        });
    }
}
