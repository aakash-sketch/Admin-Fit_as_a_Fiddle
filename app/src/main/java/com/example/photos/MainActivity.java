package com.example.photos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap;
    public void intentCreation(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if  (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                intentCreation();
            }
        }
    }


    public void create(View view){
        Intent i =new Intent(getApplicationContext(),Create.class);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requesting permision
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1); }
//.....
else{

       intentCreation();
    }}
public void share(View view){
        EditText text =findViewById(R.id.cat1);
        EditText imgTxt=findViewById(R.id.imgText);
        String category;
        String imageName;
        imageName =String.valueOf(imgTxt.getText());
        category =String.valueOf(text.getText());
        if(category!= null){
    ByteArrayOutputStream strem= new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG,100,strem);
    byte[] arri =strem.toByteArray();
    ParseFile file= new ParseFile(imageName,arri);
    ParseObject oject= new ParseObject("Gargi");
    oject.put("imagess",file);
    oject.put("Category",category);
    oject.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
       if(e==null){
           Toast.makeText(MainActivity.this, "Image Shared", Toast.LENGTH_SHORT).show();
           intentCreation();
       }
        }
    });
}else{
            Toast.makeText(this, "Please specify a Category", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode==RESULT_OK && data !=null){
            Uri selectedImage= data.getData();
            try {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), selectedImage);
                 bitmap= ImageDecoder.decodeBitmap(source);

//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                ImageView imageView =findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}