package id.sch.smktelkom_mlg.learn.yourdailynews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 10/12/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;
    public ArrayList<String> myValuesd;
    public ArrayList<String> myValuesc;
    public ArrayList<String> imgLink;

    IRecyclerViewAdapter mIRecyclerViewAdapter;

    public RecyclerViewAdapter(ArrayList<String> myValues, ArrayList<String> myValuesd, ArrayList<String> myValuesc, ArrayList<String> imgLink, Context context) {
        this.myValues = myValues;
        this.myValuesd = myValuesd;
        this.myValuesc = myValuesc;
        this.imgLink = imgLink;
        mIRecyclerViewAdapter = (IRecyclerViewAdapter) context;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }

    }

    public void setIRecyclerViewAdapter(final IRecyclerViewAdapter iRecyclerViewAdapter) {
        this.mIRecyclerViewAdapter = iRecyclerViewAdapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
        holder.myTextViewc.setText(myValuesc.get(position));
        holder.myTextViewd.setText(myValuesd.get(position));
        //holder.display.setImageBitmap(getBitmapFromURL(imgLink.get(position)));
        holder.display.setBackgroundColor(0);
        holder.display.loadDataWithBaseURL("", "<img src='" + imgLink.get(position) + "' style='display: inline;height: auto;max-width: 100%'/>",
                "text/html", "UTF-8", "");
    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public interface IRecyclerViewAdapter {
        void doClick(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        private TextView myTextViewd;
        private TextView myTextViewc;
        private WebView display;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.text_cardview);
            myTextViewc = (TextView) itemView.findViewById(R.id.text_isi);
            myTextViewd = (TextView) itemView.findViewById(R.id.text_tgl);
            //display = (ImageView) itemView.findViewById(R.id.imageView);
            display = (WebView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mIRecyclerViewAdapter != null) {
                        mIRecyclerViewAdapter.doClick(getAdapterPosition());
                    }
                }
            });

            display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIRecyclerViewAdapter.doClick(getAdapterPosition());
                }
            });

            myTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIRecyclerViewAdapter.doClick(getAdapterPosition());
                }
            });

            myTextViewc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIRecyclerViewAdapter.doClick(getAdapterPosition());
                }
            });

        }
    }
}
