package com.example.sanjana.indianreceipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sanjana.indianreceipe.R;
import com.example.sanjana.indianreceipe.main.MainActivity;
import com.example.sanjana.indianreceipe.main.WebViewActivity;
import com.example.sanjana.indianreceipe.model.Album;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static java.security.AccessController.getContext;

/**
 * Created by sanjana on 20/3/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private List<Album> albumList;
    private Context mContext;
    WebViewActivity webViewActivity;

    AlbumAdapter(){}

    public AlbumAdapter(List<Album> albumList, Context mContext) {
        this.albumList = albumList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card,parent,false);

        return new MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + "songs");

        //loading album cover using glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album album = albumList.get(holder.getAdapterPosition());
                showPopMenu(holder.overflow ,album.getUrl());
            }
        });
    }


    // showing popup

    private void showPopMenu(View view , String url){
        //inflate menu layout
        Log.d(TAG, "showPopMenu:"+url);
        PopupMenu popupMenu = new PopupMenu(mContext,view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_album,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener(url));
        popupMenu.show();
    }

    // click listener for popup menu items

    public class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        String load_uri = "www.google.com";


        MyMenuItemClickListener(String uri){
            Log.d(TAG, "MyMenuItemClickListener: " +uri);
            this.load_uri = uri;
        }

        public String getLoad_uri() {
            return load_uri;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_add_favourite :
                    Toast.makeText(mContext, "Add to favarite", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, WebViewActivity.class);
                    i.putExtra("url_key", load_uri);
                    mContext.startActivity(i);
                    //webview.getSettings().setJavaScriptEnabled(true);
                    //webview.loadUrl("http://www.google.com");
                    //  webViewActivity.startWebView(load_uri);
                    return true;
                case R.id.action_play_next :
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, count;
        public ImageView overflow, thumbnail;
        public WebView webview;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            webview = (WebView) itemView.findViewById(R.id.web_view);
        }
    }

}
