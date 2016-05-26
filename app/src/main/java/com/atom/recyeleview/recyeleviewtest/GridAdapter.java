package com.atom.recyeleview.recyeleviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 2016/5/23.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<HashMap<String, Object>> mList;

    public GridAdapter(Context context, ArrayList<HashMap<String, Object>> list) {
        this.mCtx = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        View view = LayoutInflater.from(mCtx).inflate(R.layout.grid_view_layout, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt.setText((String) mList.get(position).get("name"));
        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
        final ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String k) {
                return cache.get(k);
            }

            @Override
            public void putBitmap(String k, Bitmap bitmap) {
                cache.put(k, bitmap);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.img, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get((String) mList.get(position).get("testUrl"), listener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }
}
