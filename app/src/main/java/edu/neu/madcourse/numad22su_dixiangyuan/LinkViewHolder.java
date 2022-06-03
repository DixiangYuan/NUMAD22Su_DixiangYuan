package edu.neu.madcourse.numad22su_dixiangyuan;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTV;
    public TextView linkTV;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTV = itemView.findViewById(R.id.name);
        this.linkTV = itemView.findViewById(R.id.link);
    }

    public void bindThisData(Link theLinkToBind) {
        // sets the name of the person to the name textview of the viewholder.
        nameTV.setText(theLinkToBind.getName());
        // sets the age of the person to the age textview of the viewholder.
        linkTV.setText(String.valueOf(theLinkToBind.getLink()));
    }

}
