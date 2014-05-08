package com.muhammedabuali.app;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhammedabuali.app.data.Comic;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class CustomAdapter extends ArrayAdapter<Comic> {

    private Context context;
    private ArrayList<Comic> comics;
    private DisplayImageOptions options;

    public CustomAdapter(Context context, int list_item_resource,
            ArrayList<Comic> comics) {
        super(context, list_item_resource, comics);
        Log.d("data", "size" + comics.size() + "");
        this.context = context;
        this.comics = comics;
        this.options = Downloader.getDisplayOptions();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("click", "position1 "+ position);
        Comic comic = comics.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
        	Log.d("click", "position2 "+ position);
            // inflater construct item view
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            
        }
        holder = new ViewHolder();
        holder.thumbView = (ImageView) convertView
                .findViewById(R.id.thumb_image);
        holder.thumbView.setOnClickListener(new OnClickListener() {
        	
            @Override
            public void onClick(View v) {
                Log.d("click", "position"+ position);
                Intent comicIntent = new Intent(context,
                        ComicActivity.class);
                // comicIntent.putExtra("comics", comics);
                comicIntent.putExtra("comic", comics.get(position).getComicUrl());
                comicIntent.putExtra("pp", comics.get(position).getProfilePic());
                comicIntent.putExtra("pname", comics.get(position).getUserName());
                comicIntent.putExtra("caption", comics.get(position).getCaption());
                comicIntent.putExtra("likes", comics.get(position).getLikes());
                comicIntent.putExtra("rank", comics.get(position).getRankUrl());
                comicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(comicIntent);
            }
        });
        holder.prName = (TextView) convertView
                .findViewById(R.id.profile_name);
        holder.likes = (TextView) convertView
                .findViewById(R.id.likes);
        holder.rank = (ImageView) convertView
                .findViewById(R.id.rank);
        holder.prpic = (ImageView) convertView
                .findViewById(R.id.profile_pic);
        holder.caption = (TextView) convertView.findViewById(R.id.caption);
        convertView.setTag(holder);

        if (comic != null) {
            holder.thumbView.setImageResource(R.drawable.ess);
            holder.prpic.setImageResource(R.drawable.ess);
            Downloader.getInstance().displayImage(comic.getImageUrl(),
                    holder.thumbView, options);
            Downloader.getInstance().displayImage(comic.getProfilePic(),
                    holder.prpic, options);
            holder.rank.setImageResource(R.drawable.ess);
            Downloader.getInstance().displayImage(comic.getRankUrl(),
                    holder.rank, options);
            if (comic.getCaption() != null && comic.getCaption().length() > 0)
                holder.caption.setText(comic.getCaption());
            else
                holder.caption.setText("No Caption");
            holder.likes.setText(comic.getLikes());
            holder.prName.setText(comic.getUserName());
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView thumbView;
        public TextView prName;
        public ImageView prpic;
        public TextView caption;
        public TextView likes;
        public ImageView rank;
    }
}