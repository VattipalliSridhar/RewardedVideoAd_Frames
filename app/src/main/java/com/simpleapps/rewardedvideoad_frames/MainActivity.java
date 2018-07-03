package com.simpleapps.rewardedvideoad_frames;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.simpleapps.rewardedvideoad_frames.recycler_click_listener.ClickListener;
import com.simpleapps.rewardedvideoad_frames.recycler_click_listener.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private RecyclerView frame_recycler_view;
    private int screen_width, screen_height;
    private Frame_Adapter frame_adapter;


    private int frame_ImageId[] = {R.drawable.frame1, R.drawable.frame2,
            R.drawable.frame3, R.drawable.frame4, R.drawable.frame5,
            R.drawable.frame6, R.drawable.frame7, R.drawable.frame8,
            R.drawable.frame9, R.drawable.frame10, R.drawable.frame11,
            R.drawable.frame12, R.drawable.frame13, R.drawable.frame14,
            R.drawable.frame15, R.drawable.frame16, R.drawable.frame17,
            R.drawable.frame18, R.drawable.frame19, R.drawable.frame20};
    private ArrayList<Integer> valuesList;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private RewardedVideoAd mRewardedVideoAd;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        valuesList = new ArrayList<>(Arrays.asList(Utils.positionValues));


        screen_width = this.getResources().getDisplayMetrics().widthPixels;
        screen_height = this.getResources().getDisplayMetrics().heightPixels;

        getFrameObjectList();

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(MainActivity.this);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        frame_recycler_view = (RecyclerView) findViewById(R.id.frame_recycler_view);
        frame_recycler_view.setHasFixedSize(true);
        frame_recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        frame_adapter = new Frame_Adapter(MainActivity.this, frame_ImageId);
        frame_recycler_view.setAdapter(frame_adapter);
        frame_recycler_view.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this,
                frame_recycler_view, new ClickListener() {

            @Override
            public void onLongClick(View view, int position) {
            }

            @Override
            public void onClick(View view, int position) {
                Log.i("8****", "Position==>" + position);
               Utils.selectpos = position;

                if (Utils.checkStatus.get(position)) {
                    //if (connectionDetector.isConnectingToInternet()) {
                        dialog.show();
                        mRewardedVideoAd.loadAd(getString(R.string.rewarded_id), new AdRequest.Builder().build());
                    //} else {
                      //  No_Internet_Dialouge();
                   // }
                } else {

                    //openCameraGallery();
                    Intent intent=new Intent(MainActivity.this,Edit_Activity.class);
                    startActivity(intent);
                }


            }
        }));


        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                mRewardedVideoAd.show();
                dialog.dismiss();
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

                Log.e("click Position", "" + Utils.selectpos + " *** " + valuesList);
                int tempcount = 0;
                for (int z = 0; z < frame_ImageId.length; z++) {
                    if (valuesList.contains(z)) {
                        if (valuesList.get(tempcount) == Utils.selectpos) {
                            editor.putBoolean(Utils.positionNames[tempcount], false);
                        }
                        tempcount++;
                    }
                }

                editor.commit();
                Utils.checkStatus.set(Utils.selectpos, false);
                frame_adapter.notifyDataSetChanged();




            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                dialog.dismiss();
            }
        });

    }


    public void getFrameObjectList() {
        int k = 0;

        for (int i = 0; i < frame_ImageId.length; i++) {
            if (valuesList.contains(i)) {
                Utils.checkStatus.add(sharedPreferences.getBoolean(Utils.positionNames[k], true));
                k++;
            } else {
                Utils.checkStatus.add(false);
            }
        }

    }
}
