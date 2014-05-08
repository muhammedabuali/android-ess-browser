package com.muhammedabuali.app;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import com.muhammedabuali.app.data.CustomImageView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.muhammedabuali.app.data.ComicPage;
import com.muhammedabuali.app.data.Comment;

public class ComicActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);
        String url = getIntent().getStringExtra("comic");
        url = Downloader.baseUrl + url;
        String profilePic = getIntent().getStringExtra("pp");
        ImageView imageView = (ImageView) findViewById(R.id.com_profile_pic);
        Downloader.getInstance().displayImage(profilePic, imageView, 
                Downloader.getDisplayOptions());
        TextView nameTextView = (TextView) findViewById(R.id.com_profile_name);
        String pnameString = getIntent().getStringExtra("pname");
        nameTextView.setText(pnameString);
        String caption = getIntent().getStringExtra("caption");
        TextView captionView = (TextView) findViewById(R.id.com_caption);
        captionView.setText(caption);
        String likes = getIntent().getStringExtra("likes");
        TextView likesview = (TextView) findViewById(R.id.com_likes);
        likesview.setText(likes);
        String rankUrl = getIntent().getStringExtra("rank");
        ImageView rankView = (ImageView) findViewById(R.id.com_rank);
        Downloader.getInstance().displayImage(rankUrl, rankView,
                Downloader.getDisplayOptions());
        new RequestTask().execute(url);
    }

    
    class RequestTask extends AsyncTask<String, String, ComicPage> {
    	ArrayList<Comment> comments;
        @Override
        protected ComicPage doInBackground(String... uri) {
            try {
                Log.d("data", "background");
                Document doc = Jsoup.connect(uri[0]).get();
                String imageUrl = doc.select("#image-con img").attr("src");
                if(imageUrl.startsWith("/img/nsfw_warning.png"))
                	imageUrl = doc.select("#image-con img").attr("rel");
                Elements elements = doc.select("#comments ul").get(0).children();
                comments = new ArrayList();
                for(int i=0; i< elements.size(); i++){
                	String pp = elements.get(i).select("div.userpic a span img")
                			.attr("src");
                	String pname = elements.get(i).select("a.name")
                			.text();
                	String text = elements.get(i).select("div.text")
                			.text();
                	String likes =   elements.get(i).select("div.actions"
                			+ " a.like span").text() ;
                	String lames =   elements.get(i).select("div.actions"
                			+ " a.lame span").text() ;
                	comments.add(new Comment(pp, pname, text, likes, lames));
                }
                ComicPage page = new ComicPage(imageUrl);
                return page;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void showComments() {
			ListView listView = (ListView) findViewById(R.id.com_comments);
			CommentsAdapter adapter = new CommentsAdapter(getApplicationContext(), 0, comments);
			listView.setAdapter(adapter);
			setListViewHeightBasedOnChildren(listView);
		}

		@Override
        protected void onPostExecute(final ComicPage result) {
            if(result == null)
            {
                Log.d("data", "null");
                return;
            }
            Log.d("daya", "hello");
            super.onPostExecute(result);
            CustomImageView imageView = (CustomImageView) findViewById(R.id.com_view);
            Downloader.getInstance().displayImage(result.getImageUrl(),
                    imageView, Downloader.getDisplayOptions());

            Log.d("comment", "size" + comments.size());
            showComments();
        }
    }
    
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
