package com.mgosu.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.viewHolder> {
    private Context context;
    private List<DataModel> list;
    private onClick onClick;

    public void setPlay(int pos) {
        for (DataModel dataModel : list) {
            dataModel.setPlay(false);
        }
        list.get(pos).setPlay(true);
    }

    public void setOnClick(com.mgosu.demo.onClick onClick) {
        this.onClick = onClick;
    }

    public AdapterData(Context context, List<DataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycy, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int i) {
        viewHolder.progressBar.setProgress(list.get(i).getPosProgress());
            if (list.get(i).isPlay()) {
                viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_pause_item));
            } else {
                viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_play_item));
            }
        viewHolder.progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(viewHolder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private CircularProgressBar progressBar;
        private ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
