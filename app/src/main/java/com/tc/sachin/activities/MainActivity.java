package com.tc.sachin.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.tc.sachin.R;
import com.tc.sachin.controller.MainBaseApplication;
import com.tc.sachin.fragments.HomePagefragment;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayoutContainer;
    private HomePagefragment homefragment;
    private Stack<Fragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilize();
    }

    private void initilize(){
        frameLayoutContainer = (FrameLayout) findViewById(R.id.container);
        fragmentStack = new Stack<Fragment>();
        loadFragment();
    }

    private void loadFragment(){
            try {
                homefragment = HomePagefragment.newInstance();
                fragmentStack.clear();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(frameLayoutContainer.getId(), homefragment);
                if (fragmentStack.size() > 0) {
                    fragmentStack.lastElement().onPause();
                    ft.hide(fragmentStack.lastElement());
                }
                fragmentStack.push(homefragment);
                ft.commitAllowingStateLoss();
            } catch (Exception e) {
               e.printStackTrace();
            }

    }
}
