package edu.neu.madcourse.numad22su_dixiangyuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdaptor extends RecyclerView.Adapter<LinkViewHolder> {
    private final List<Link> linkList;
    private final Context context;

    public LinkAdaptor(List<Link> linkList, Context context) {
        this.linkList = linkList;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an instance of the viewholder by passing it the layout inflated as view and no root.
        return new LinkViewHolder(LayoutInflater.from(context).inflate(R.layout.item_link, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        holder.bindThisData(linkList.get(position));
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return linkList.size();
    }
}
