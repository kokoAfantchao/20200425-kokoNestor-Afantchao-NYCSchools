package com.push.nycschools.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.push.nycschools.R;
import com.push.nycschools.ui.main.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
