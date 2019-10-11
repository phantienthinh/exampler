package com.mgosu.demo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onClick {
    private RecyclerView recyclerView;
    private AdapterData adapterData;
    private List<DataModel> list = new ArrayList<>();
    private boolean d = false;
    private boolean isClick = false;
    private String s1 = "http://192.168.200.216/dev/media/learn_english/mp3/time_1.m4a";
    private String s2 = "http://192.168.200.216/dev/media/learn_english/mp3/time_2.m4a";
    private MediaPlayer player = new MediaPlayer();
    private Runnable runnable;
    private Handler handler;
    private int pos = 102221215;
    private boolean isTurn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        recyclerView = findViewById(R.id.recyclerView);
        for (int i = 0; i < 20; i++) {
            if (d) {
                list.add(new DataModel(s2, false, 0));
                d = false;
            } else {
                list.add(new DataModel(s1, false, 0));
                d = true;
            }
        }
        adapterData = new AdapterData(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterData);
        adapterData.setOnClick(this);
    }

    @Override
    public void onClick(final int pos) {
//        if (this.pos == pos) {
////            player.start();
//        } else {
//            this.pos = pos;
//        }
//
//
//        if (!isClick) {
//            adapterData.setPlay(pos);
////            if (this.pos == pos){
////                player.start();
////                updateSeekbar(pos);
////            }else {
//                if (player != null) {
//                    if (player.isPlaying()) {
//                        player.stop();
//                        player.release();
//                    }
//                    player.release();
//                    player = new MediaPlayer();
//                }
//
//                try {
//                    player.setDataSource(list.get(pos).getLinkMp3());
//                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    player.prepare();
//                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {
//                            isClick = false;
//                            list.get(pos).setPlay(false);
//                            adapterData.notifyItemChanged(pos);
//                        }
//                    });
//                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//                            player.start();
//                            adapterData.setCurItem(pos);
//                            updateSeekbar(pos);
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////            }
//
//            isClick = true;
//        } else {
//            if (player.isPlaying()) {
//                list.get(pos).setCurrentPosition(player.getCurrentPosition());
//                if (this.pos == pos){
//                    player.pause();
//                }else {
//                    player.stop();
//                    adapterData.setPlay(pos);
//                    adapterData.notifyDataSetChanged();
//                }
//            }
//            if (handler != null) {
//                handler.removeCallbacks(runnable);
//            }
//            isClick = false;
//            // adapterData.getList().get(pos).setPlay(true);
//        }}
        if (!list.get(pos).isPlay()) {
            if (this.pos != pos) {
                this.pos = pos;

                for (DataModel dataModel : list) {
                    dataModel.setPlay(false);
                }
                isTurn = false;
                list.get(pos).setPlay(true);
                adapterData.notifyDataSetChanged();


                Log.e("22", "onClick: ");
                if (player != null) {
                    Log.e("22", "onClick:1 ");
                    if (player.isPlaying()) {
                        Log.e("22", "onClick:2 ");
                        adapterData.setPlay(pos);
                        player.stop();
                        player.release();
                        player = new MediaPlayer();
                    } else {
                        player.release();
                        player = new MediaPlayer();
                    }
                }

                try {
                    Log.e("22", "onClick:3 ");
                    player.setDataSource(list.get(pos).getLinkMp3());
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.prepare();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            list.get(pos).setPlay(false);
                            adapterData.notifyItemChanged(pos);
                            Log.e("22", "onClick:4 ");
                        }
                    });
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                            adapterData.setPlay(pos);
                            updateSeekbar(pos);
                            Log.e("22", "onClick:5 ");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (player != null) {
                    isTurn = false;
                    Log.e("22", "onClick: 6");
                    list.get(pos).setPlay(true);
                    player.start();
                    updateSeekbar(pos);
                }
            }
        } else {
            if (!isTurn) {
                Log.e("22", "onClick:7 ");
                if (handler != null) {
                    Log.e("22", "onClick:8 ");
                    handler.removeCallbacks(runnable);
                }
                if (player != null && player.isPlaying()) {
                    player.pause();
                    list.get(pos).setPlay(false);
                    adapterData.notifyItemChanged(pos);
                    Log.e("22", "onClick:9 ");
                }
                isTurn = true;
            } else {
                Log.e("22", "onClick:10 ");
                if (player != null) {
                    player.start();
                    list.get(pos).setPlay(true);
                    updateSeekbar(pos);
                    Log.e("22", "onClick:11 ");
                }
                isTurn = false;
            }

        }
    }

    private void updateSeekbar(final int pos) {
        int progress = (int) (((float) player.getCurrentPosition() / player.getDuration()) * 100);
        list.get(pos).setPosProgress(progress);
        if (player.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    updateSeekbar(pos);
                    adapterData.notifyItemChanged(pos);
                }
            };
            handler.postDelayed(runnable, 30);
        }
    }
}
