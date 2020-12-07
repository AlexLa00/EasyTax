package app.cyberzen.easytax;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MenuHelp extends Fragment {

    public MenuHelp() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.menu_help, container, false);
        TextView login = (TextView) v.findViewById(R.id.Help2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.google.com/?hl=en/"));
                startActivity(myIntent);
            }
        });
        return v;
    }
}