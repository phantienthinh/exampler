package com.mgosu.demotest;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = new Button(this);
        root = findViewById(R.id.root);
        root.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("///", "onClick: ");
                Intent intent1 = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent1.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(MainActivity.this, AnimSwitchWallPaper.class));
//                intent1.putExtra(KEY_PATH_VIDEO, filePath);
//                Application.path = filePath;
                startActivity(intent1);
            }
        });
    }
}
