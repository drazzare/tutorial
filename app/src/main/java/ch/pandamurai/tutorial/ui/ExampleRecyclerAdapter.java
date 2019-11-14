package ch.pandamurai.tutorial.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.pandamurai.tutorial.R;

public class ExampleRecyclerAdapter extends RecyclerView.Adapter<ExampleRecyclerAdapter.ExampleRecyclerViewHolder> {
    private ArrayList<ExampleRecyclerItem> mExampleRecyclerItemsList;


    public static class ExampleRecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_Recycler_View);
            mTextView1 = itemView.findViewById(R.id.text1_recycler_View);
            mTextView2 = itemView.findViewById(R.id.text2_recycler_view);
        }
    }

    public ExampleRecyclerAdapter(ArrayList<ExampleRecyclerItem> exampleList){
        mExampleRecyclerItemsList = exampleList;
    }
    @NonNull
    @Override
    public ExampleRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_recycler_item, parent, false);
        ExampleRecyclerViewHolder evh = new ExampleRecyclerViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleRecyclerViewHolder holder, int position) {
        ExampleRecyclerItem currentItem = mExampleRecyclerItemsList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView1.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleRecyclerItemsList.size();
    }
}
