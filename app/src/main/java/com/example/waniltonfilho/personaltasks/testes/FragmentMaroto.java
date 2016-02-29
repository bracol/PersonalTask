package com.example.waniltonfilho.personaltasks.testes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by Wanilton on 27/02/2016.
 */
public class FragmentMaroto extends Fragment {

    private WebView mWebView;
    private String mUrl;

    private static FragmentMaroto fragmentMaroto;

    public static FragmentMaroto getInstance(){
        if (fragmentMaroto == null){
            fragmentMaroto = new FragmentMaroto();
        }
        return fragmentMaroto;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString("a");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        mWebView = (WebView) v.findViewById(R.id.webView);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);
    }
}
