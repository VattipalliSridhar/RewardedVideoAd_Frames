package com.simpleapps.rewardedvideoad_frames;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

public class Edit_Activity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        screen_width = this.getResources().getDisplayMetrics().widthPixels;
        screen_height = this.getResources().getDisplayMetrics().heightPixels;

        frame_recycler_view = (RecyclerView) findViewById(R.id.frame_recycler_view);
        frame_recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        frame_recycler_view.setLayoutManager(linearLayoutManager);
        frame_adapter = new Frame_Adapter(Edit_Activity.this, frame_ImageId);
        frame_recycler_view.setAdapter(frame_adapter);


    }


    public class Frame_Adapter extends RecyclerView.Adapter<Frame_Adapter.FrameViewHolder> {

        private int frame_imageId[];
        private Context context;

        public Frame_Adapter(Context context, int[] frame_imageId)
        {
            this.context=context;
            this.frame_imageId=frame_imageId;



        }

        @NonNull
        @Override
        public Frame_Adapter.FrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.frames_layout,parent,false);


            return new Frame_Adapter.FrameViewHolder(views);
        }

        @Override
        public void onBindViewHolder(@NonNull Frame_Adapter.FrameViewHolder holder, int position) {
            Glide.with(context).load(frame_imageId[position])
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                    .into(holder.img_frame);

            holder.img_frame.getLayoutParams().width = (int) (screen_width / 3.2);
            holder.img_frame.getLayoutParams().height = (int) (screen_width / 2.2);
            holder.relativeLayout.getLayoutParams().width = (int) (screen_width / 3.2);
            holder.relativeLayout.getLayoutParams().height = (int) (screen_width / 2.2);

            if(Utils.checkStatus.get(position))
            {
                holder.relativeLayout.setVisibility(View.VISIBLE);

            }else
            {
                holder.relativeLayout.setVisibility(View.GONE);

            }
        }

        @Override
        public int getItemCount() {
            return frame_imageId.length;
        }


        class FrameViewHolder  extends RecyclerView.ViewHolder
        {
            ImageView img_frame;
            RelativeLayout relativeLayout;
            public FrameViewHolder(View itemView) {
                super(itemView);
                img_frame = (ImageView) itemView.findViewById(R.id.frame_image_view);
                relativeLayout=(RelativeLayout)itemView.findViewById(R.id.whitespace_rel);


            }
        }
    }
}
