package com.example.waniltonfilho.personaltasks.testes;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by Wanilton on 27/02/2016.
 */
public class ActivityTeste extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment);
        url = "https://www.google.com.br/?gfe_rd=cr&ei=6_vRVvS3EpGw8wfGpazIBA&gws_rd=ssl";
        bindComponents();
    }

    private void bindComponents() {
        mFrameLayout = (FrameLayout) findViewById(R.id.frameFragment);
        openFragment();
    }

    private void openFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMaroto fragmentMaroto = FragmentMaroto.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("a", url);
        fragmentMaroto.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameFragment, fragmentMaroto);
        fragmentTransaction.commit();
    }
}
