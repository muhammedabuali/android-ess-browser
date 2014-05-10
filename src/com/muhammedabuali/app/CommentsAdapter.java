package com.muhammedabuali.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.muhammedabuali.app.data.Comment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

public class CommentsAdapter extends ArrayAdapter<Comment> {

    Context context;
    private ArrayList<Comment> comments;
    private DisplayImageOptions options;

    public CommentsAdapter(Context context, int i,
                           ArrayList<Comment> comments) {
        super(context, i, comments);
        this.comments = comments;
        this.context = context;
        this.options = Downloader.getDisplayOptions();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("click", "position1 " + position);
        Comment comment = comments.get(position);
        ViewHolder holder;
        if (convertView == null) {
            // inflater construct item view
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment, parent, false);

        }
        holder = new ViewHolder();
        holder.text = (TextView) convertView
                .findViewById(R.id.cmt_text);
        holder.prName = (TextView) convertView
                .findViewById(R.id.cmt_profile_name);
        holder.prpic = (ImageView) convertView
                .findViewById(R.id.cmt_profile_pic);
        convertView.setTag(holder);

        if (comment != null) {
            Log.d("comment", "holder " + String.valueOf(holder == null));
            Log.d("comment", "holder pic" + String.valueOf(holder.prpic == null));
            holder.prpic.setImageResource(R.drawable.ess);
            Downloader.getInstance().displayImage(comment.getProfilePic(),
                    holder.prpic, options);
            holder.prName.setText(comment.getUserName());
            holder.text.setText(comment.getText());
        }
        return convertView;
    }

    class ViewHolder {
        public TextView text;
        public TextView prName;
        public ImageView prpic;
    }

}
