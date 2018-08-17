package com.example.epubreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class bookdisp extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Bitmap z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdisp);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] myDataset=new String[3];
        String[] myDataset1=new String[3];
        myDataset[0]="https://firebasestorage.googleapis.com/v0/b/test-150af.appspot.com/o/temp6.jpeg?alt=media&token=ce44356f-26d9-4571-9669-febde42f4796";
        myDataset[1]="https://firebasestorage.googleapis.com/v0/b/test-150af.appspot.com/o/temp7.jpeg?alt=media&token=fe7230ab-bd01-4847-8c0b-256851606e10";
        myDataset[2]="https://firebasestorage.googleapis.com/v0/b/test-150af.appspot.com/o/temp8.jpeg?alt=media&token=46f0c2ef-2d6b-47cc-a885-e59ce7b630bf";
        myDataset1[0]="test1";
        myDataset1[1]="test2";
        myDataset1[2]="test3";
        mAdapter = new MyAdapter(myDataset,myDataset1);
        mRecyclerView.setAdapter(mAdapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] image;
        private String[] name;


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.album_title);
                imageView=itemView.findViewById(R.id.album);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] image,String[] name) {
            this.image = image;
            this.name=name;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_layout, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
       //     new fetchlang().execute(image[position]);

               // holder.imageView.setImageBitmap(z);
            Picasso.with(getApplicationContext()).load(image[position]).into(holder.imageView);
                holder.mTextView.setText(name[position]);



        }

        @Override
        public int getItemCount() {
            return image.length;
        }
   /*     class fetchlang extends AsyncTask<String,Void,Bitmap> {


            @Override
            protected Bitmap doInBackground(String... urls) {
                try {
                    URL url=new URL(urls[0]);
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    return bmp;
                }
                catch (Exception r)
                {
                    System.err.println(r);
                    return null;
                }



            }
            @Override
            protected void onPostExecute(Bitmap result)
            {
             z=result;
             System.err.println(result);
            }

        } */


    }
}
