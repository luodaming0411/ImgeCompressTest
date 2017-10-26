package com.leon.imgecompresstest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import me.iwf.photopicker.PhotoPicker;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        button = (Button) findViewById(R.id.choose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(true)
                        .start(MainActivity.this,Constant.UPLOAD_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case Constant.UPLOAD_PHOTO:
                    if (data != null) {
                        try {
                            ArrayList<String> strings = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                            loadImages(strings,0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    private void loadImages(ArrayList<String> strings,int type) throws IOException {

        String prefix = Environment.getExternalStorageDirectory() + "/isqun/dynamic/";
        Bitmap bitmap;

        if (strings != null) {

            for (int i = 0; i<strings.size();i++){
                String name = UUID.randomUUID().toString();
                bitmap = BitmapFactory.decodeFile(strings.get(i));
                BitmapUtil.compressBitmapByJni(bitmap,30,prefix+name);
                //  BitmapUtils.compressImage(strings.get(i),prefix+name,100);
            }
        }
    }

    public native static int compressBitmapByJni(Object bitmap, int quality, String fileName);

}
