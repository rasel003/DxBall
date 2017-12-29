package com.rasel.dxball;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnLevelOneCliked(View view) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("level","1");
        startActivity(intent);
    }

    public void btnLevelTwoCliked(View view) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("level","2");
        startActivity(intent);
    }

    public void btnQuitCliked(View view) {
        System.exit(0);
    }
}