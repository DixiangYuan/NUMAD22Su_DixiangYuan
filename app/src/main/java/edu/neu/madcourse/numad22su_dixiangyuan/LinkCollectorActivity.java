package edu.neu.madcourse.numad22su_dixiangyuan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity
        implements FABDialog.NoticeDialogListener {
    RecyclerView linkListRecyclerView;
    List<Link> linkList;
    FloatingActionButton floatingActionButton;
    View dialogView;

    private EditText inputName;
    private EditText inputURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_collector);

        linkList = new ArrayList<>();

        linkListRecyclerView = findViewById(R.id.link_recycle_view);
        linkListRecyclerView.setHasFixedSize(true);
        linkListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkListRecyclerView.setAdapter(new LinkAdaptor(linkList, this));

        // Set up fab
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLink();
            }
        });
    }

    public void addLink() {
        DialogFragment newFragment = new FABDialog();
        newFragment.show(getSupportFragmentManager(), "Enter link");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name, String url) {
        Link addOneLink = new Link(name, url);
        if (name.isEmpty() || name == null || url.isEmpty() || url == null) {
            Snackbar.make(linkListRecyclerView,"Neither name or URL can be empty",Snackbar.LENGTH_SHORT).show();
        } else if (addOneLink.isValid() != false) {
            linkList.add(addOneLink);
            Snackbar.make(linkListRecyclerView,"A new link uploaded",Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(linkListRecyclerView,"The URL is invalid",Snackbar.LENGTH_SHORT).show();
        }
    }

}
