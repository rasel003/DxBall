package com.rasel.dxball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements OnKeyListener {
    //StringBuilder builder = new StringBuilder();
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        switch(event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                //builder.append("down, ");
                break;
            case KeyEvent.ACTION_UP:
                //builder.append("up, ");
                break;
        }
    	/*builder.append(event.getKeyCode());
    	builder.append(", ");
    	builder.append((char) event.getUnicodeChar());
    	String text = builder.toString();*/
        Log.d("KeyTest", keyCode+"");
        //return event.getKeyCode() != KeyEvent.KEYCODE_BACK;
        return true;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnLevelOneCliked(View view) {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }

    public void btnLevelTwoCliked(View view) {
    }

    public void btnQuitCliked(View view) {
    }
}