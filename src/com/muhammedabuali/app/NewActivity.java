package com.muhammedabuali.app;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.muhammedabuali.app.MyActivity.RequestTask;
import com.muhammedabuali.app.data.Comic;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class NewActivity extends Activity{
	ImageLoader imageLoader;
    ArrayList<Comic> comics;
    CustomAdapter adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Downloader.initialize(getApplicationContext());
        /*WebView webView = new WebView(this);
        webView.loadUrl("http://www.ess.mx/comics/arabic/trending");*/
        setContentView(R.layout.activity_main);
        comics = new ArrayList<>();
        adapter =new CustomAdapter(getApplicationContext(), R.layout.list_item, comics);
        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
      	      (Context.LAYOUT_INFLATER_SERVICE);
        Button button = (Button) inflater.inflate(R.layout.button, null);
        button.setText("show more");
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	String url = Downloader.baseNewest + Downloader.newCount;
            	Downloader.newCount++;
            	new RequestTask().execute(url);
            }
        });
        listView.addFooterView(button);
        new RequestTask().execute(Downloader.baseNewest);
    }

    /************************************************/
    class RequestTask extends AsyncTask<String, String, ArrayList<Comic>> {

        @Override
        protected ArrayList<Comic> doInBackground(String... uri) {
            try {
                Document doc = Jsoup.connect(uri[0]).get();
                Elements comicsElements = doc.select("section.comic");
                for (int i=0; i< comicsElements.size(); i++){
                    Element comic = comicsElements.get(i);
                    String userName = comic.select("a.name").text();
                    String profileUrl = comic.select("a.name").get(0).attr("href");
                    if((comic.select("div.comic-image a")).isEmpty()){
                    	continue;
                    }
                    String pictureUrl = comic.select("div.userpic img")
                            .get(0).attr("src");
                    String imageCaption = comic.select("div.userdata span").get(0).text();
                    String comicUrl = comic.select("div.comic-image a").get(0).attr("href");
                    String imageUrl = comic.select("div.comic-image img").get(0).attr("src");
                    if(imageUrl.startsWith("/img"))
                    	imageUrl =  comic.select("div.comic-image img").get(0).attr("rel");
                    comics.add( new Comic(userName, profileUrl, pictureUrl, comicUrl,
                            imageUrl, imageCaption));
                /*Log.d("data", userName +" a "+ profileUrl +" b "+ imageCaption +" c "+
                pictureUrl+" d "+ comicUrl+" e " + imageUrl);*/
                }
                return comics;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Comic> result) {
            if(result == null)
                return;
            super.onPostExecute(result);
            ListView listView = (ListView) findViewById(R.id.listview1);
            boolean pauseOnScroll = false; // or true
            boolean pauseOnFling = true; // or false
            adapter.notifyDataSetChanged();
            //CustomAdapter adapter =new CustomAdapter(getApplicationContext(), R.layout.list_item, result);
            //listView.setAdapter(adapter);
            //Do anything with respo`nse..
        }
    }
}
