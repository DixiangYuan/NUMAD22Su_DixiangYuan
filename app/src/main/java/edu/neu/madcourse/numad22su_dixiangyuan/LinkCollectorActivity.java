package edu.neu.madcourse.numad22su_dixiangyuan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity {
    RecyclerView linkListRecyclerView;

    List<Link> linkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_collector);

        //Instantiate the arraylist
        linkList = new ArrayList<>();

        //Adding a new person object to the personList arrayList
        List<String> sampleNames = new ArrayList<>(List.of("Aarav", "Beth","Chun","Dasya"));
        List<String> sampleLinks = new ArrayList<>(List.of("Aarav1", "Beth2","Chun3","Dasya4"));


        for (int i = 0; i < sampleNames.size(); i++) {
            linkList.add(new Link(sampleNames.get(i), sampleLinks.get(i)));
        }

        linkListRecyclerView = findViewById(R.id.link_recycle_view);

        // In this example, the size of the RecyclerView does not change if the content changes,
        // so we let Android know that it will not change, which allows some optimizations
        // when items are added or deleted.
        //  The RecyclerView can still change size for other reasons.
        // (For example, it might inherit size from a parent in the layout that gets resized.)
        linkListRecyclerView.setHasFixedSize(true);

        //This defines the way in which the RecyclerView is oriented
        linkListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        linkListRecyclerView.setAdapter(new LinkAdaptor(linkList, this));
    }
}
