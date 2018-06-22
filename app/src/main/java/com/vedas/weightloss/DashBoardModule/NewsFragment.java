package com.vedas.weightloss.DashBoardModule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.vedas.weightloss.Controllers.NewsFeedController;
import com.vedas.weightloss.Models.NewsFeedObject;
import com.vedas.weightloss.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class NewsFragment extends Fragment {
    Toolbar toolbar;
    TextView tool_text;

    ArrayList<NewsFeedObject> data;

    RecyclerView newsRecyclerView;
    static int selectedPosition = -1;
    NewsFeed adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_fragment, container, false);

        ButterKnife.bind(this, view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        tool_text = (TextView) toolbar.findViewById(R.id.toolbar_text);
        tool_text.setText("News Feed");

        init();
        return view;
    }

    private void init() {
        data = new ArrayList<>();
        data = NewsFeedController.getInstance().newsarraylist;
        Log.e("domainui", "" + NewsFeedController.getInstance().newsarraylist.size());


        newsRecyclerView = (RecyclerView) view.findViewById(R.id.newsfeed);
        adapter = new NewsFeed(data, getActivity());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsRecyclerView.setLayoutManager(horizontalLayoutManager);
        newsRecyclerView.setAdapter(adapter);
        newsRecyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }


    // Step 1:-
    public class NewsFeed extends RecyclerView.Adapter<NewsFeed.ViewHolder> {

        // step 3:-
        Context ctx;
        ArrayList<NewsFeedObject> newsArrayList = new ArrayList<>();


        public NewsFeed(ArrayList<NewsFeedObject> domainArrayList, Context ctx) {
            this.ctx = ctx;
            this.newsArrayList = domainArrayList;
        }

        // step 5:-
        @Override
        public NewsFeed.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslist, parent, false);

            NewsFeed.ViewHolder myViewHolder = new NewsFeed.ViewHolder(view, ctx);
            return myViewHolder;


        }

        //step 6:-
        @Override
        public void onBindViewHolder(final NewsFeed.ViewHolder holder, final int position) {
            holder.title.setText(newsArrayList.get(position).getTitle());
            holder.id.setText(newsArrayList.get(position).getId());
            holder.discription.setText(newsArrayList.get(position).getDescription());

            //// Vedio View  and image view seperation ///
            if (newsArrayList.get(position).getFiletype().equals("video")) {
                holder.videoview.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.GONE);
                holder.play.setVisibility(view.VISIBLE);


                String videourl = newsArrayList.get(position).getUrl();
                Uri uri = Uri.parse(videourl);
                holder.videoview.setMediaController(holder.mediacontroller);
                holder.videoview.setVideoURI(uri);
                String internetUrl = newsArrayList.get(position).getThumbnail();

                Glide.with(getActivity())
                        .load(internetUrl)
                        .thumbnail(0.1f)
                        .into(holder.thumb);


            } else {
                holder.videoview.setVisibility(View.GONE);
                holder.image.setVisibility(View.VISIBLE);
                holder.play.setVisibility(view.GONE);

            }
            if (selectedPosition == position) {
                Log.e("ifnews", "called");
            } else {
                Log.e("elsenews", "called");

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition != position) {
                        selectedPosition = position;


                    } else {
                        selectedPosition = -1;
                    }
                }
            });
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // holder.isContinuously = true;
                    String videourl = newsArrayList.get(position).getUrl();
                    Uri uri = Uri.parse(videourl);
                    holder.play.setVisibility(view.GONE);
                    holder.progressBar.setVisibility(View.VISIBLE);
                    holder.videoview.setMediaController(holder.mediacontroller);
                    holder.videoview.setVideoURI(uri);
                    holder.videoview.requestFocus();
                    holder.videoview.start();
                }
            });

            holder.videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //close the progress dialog when buffering is done
                    // pd.dismiss();
                    holder.thumb.setVisibility(View.GONE);
                    holder.progressBar.setVisibility(View.GONE);

                }
            });
            String videourl = newsArrayList.get(position).getUrl();
            Glide.with(ctx)
                    .load(videourl)
                    .asBitmap()
                    .into(new BitmapImageViewTarget(holder.image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            //Play with bitmap
                            super.setResource(resource);
                        }
                    });


        }

        // step 4:-
        @Override
        public int getItemCount() {

            Log.e("regionlist", "" + newsArrayList.size());
            return newsArrayList.size();
        }


        // Step 2:-
        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView title, id, discription;
            VideoView videoview;
            ImageView image, play, thumb;
            MediaController mediacontroller;
            boolean isContinuously = false;
            ProgressDialog pd;
            private ProgressBar progressBar;


            public ViewHolder(View itemView, Context ctx) {
                super(itemView);


                title = (TextView) itemView.findViewById(R.id.title);
                image = (ImageView) itemView.findViewById(R.id.image);
                play = (ImageView) itemView.findViewById(R.id.play);
                videoview = (VideoView) itemView.findViewById(R.id.video);
                id = (TextView) itemView.findViewById(R.id.id);
                discription = (TextView) itemView.findViewById(R.id.discription);
                progressBar = (ProgressBar) itemView.findViewById(R.id.progrss);
                thumb = (ImageView) itemView.findViewById(R.id.thumb);


                mediacontroller = new MediaController(getActivity());
                mediacontroller.setAnchorView(videoview);


                videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //close the progress dialog when buffering is done
                        //pd.dismiss();
                        progressBar.setVisibility(View.GONE);


                    }
                });
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {


            }


        }
    }


}

