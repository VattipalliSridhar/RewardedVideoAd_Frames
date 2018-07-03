package com.simpleapps.rewardedvideoad_frames;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

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
    public FrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.frames_layout,parent,false);
        views.getLayoutParams().height=(int) ((context.getResources().getDisplayMetrics().heightPixels) / 2.5);
        return new FrameViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameViewHolder holder, int position) {
        Glide.with(context).load(frame_imageId[position])
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(holder.img_frame);
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
