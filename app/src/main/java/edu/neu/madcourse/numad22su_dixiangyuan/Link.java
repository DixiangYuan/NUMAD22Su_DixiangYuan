package edu.neu.madcourse.numad22su_dixiangyuan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Link {
    private final String name;
    private final String link;
    public Link(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }

    public void linkClick(Context context) {
        Intent setLink = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        context.startActivity(setLink);
    }

    public boolean isValid() {
        try {
            new URL(link).toURI(); // Check whether the format of the input
        } catch (MalformedURLException | URISyntaxException e) { // Wrong format or invalid error
            return false;
        }
        return Patterns.WEB_URL.matcher(link).matches(); // Confirm a web can be opened from the url
    }
}
