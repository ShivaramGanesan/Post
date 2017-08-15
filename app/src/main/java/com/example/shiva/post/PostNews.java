package com.example.shiva.post;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

public class PostNews extends AppCompatActivity {
EditText news;
    FloatingActionButton postNews;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
   // Button camera,imagePick;
    Model model;
    SharedPreferences shared;
    boolean isIm;
    ImageView im;
    ImageView im1,im2,im3;

    ImageButton deleteImage,newImage;
    int imag1=0,imag2,
    imag3;
  //  SharedPreferences shared;
    String newsnow,userValue;// area taken from here

private static final int GALLERY_INTENT=2;
    private static final int CAMERA_INTENT=3;
    StorageReference mStorage;
    SharedPreferences.Editor edio;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);

        deleteImage = (ImageButton)findViewById(R.id.delete);
        newImage = (ImageButton)findViewById(R.id.newImage);

        shared = getSharedPreferences("fileName",Context.MODE_PRIVATE);
        im1 = (ImageView)findViewById(R.id.image1);
        im2 = (ImageView)findViewById(R.id.image2);
        im3 = (ImageView)findViewById(R.id.image3);
        mStorage = FirebaseStorage.getInstance().getReference();

       // SharedPreferences.Editor edio = shared.edit();
        edio = shared.edit();
      //  camera = (Button)findViewById(R.id.cam);
      //  imagePick =(Button)findViewById(R.id.imagepick);
        im = new ImageView(this);
        //areaText = (EditText)findViewById(R.id.area);
        news = (EditText)findViewById(R.id.newPost);
        postNews = (FloatingActionButton)findViewById(R.id.postnow);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("RootNode");
//        useredit = (EditText)findViewById(R.id.userE);



    /*    camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCam = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(toCam,CAMERA_INTENT);
            }
        });
        imagePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGallery = new Intent(Intent.ACTION_PICK);
                toGallery.setType("image/*");
                startActivityForResult(toGallery,GALLERY_INTENT);
            }
        });
        */



imag1=0;
        im1.setClickable(true);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostNews.this, "clicked", Toast.LENGTH_SHORT).show();
                im1.buildDrawingCache();
                Bitmap bitmap=im1.getDrawingCache();
                Intent imageIntent=new Intent(PostNews.this,ImageViewer.class);
                imageIntent.putExtra("BitmapImage",bitmap);
                startActivity(imageIntent);
            }
        });


        postNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(news.getText().toString().equals(""))  {
                    Toast.makeText(PostNews.this, "Fields with * are necessary to be filled", Toast.LENGTH_SHORT).show();
                }
                else    {

                   /* mReference.child("news").setValue(news.getText().toString());
                    mReference.child("user").setValue("Shiva");
                    mReference.child("area").setValue(areaText.getText().toString());
                    Toast.makeText(PostNews.this, "Your news has been posted", Toast.LENGTH_SHORT).show();
                    */
                   function();

                    news.setText("");
                    //areaText.setText("");
                    Toast.makeText(PostNews.this, "Your news has been posted", Toast.LENGTH_SHORT).show();
                    Intent toMain = new Intent(PostNews.this,MainActivity.class);
                    startActivity(toMain);
                }

            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PostNews.this,ImageViewer.class);
                im1.buildDrawingCache();
                Bitmap imageBitmap=im1.getDrawingCache();
                intent.putExtra("ImageBitmap",imageBitmap);
                startActivity(intent);
                Toast.makeText(PostNews.this, "selected an image", Toast.LENGTH_SHORT).show();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PostNews.this, "selected an image", Toast.LENGTH_SHORT).show();
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostNews.this, "selected an image", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                Toast.makeText(this, "camera is selected", Toast.LENGTH_SHORT).show();
                Intent toCam = new Intent("android.provider.MediaStore.ACTION_IMAGE_CAPTURE");
                if(imag1>2){
                    Toast.makeText(this, "Cannot add more photos", Toast.LENGTH_SHORT).show();
                    return true;
                }
                startActivityForResult(toCam,CAMERA_INTENT);
                return true;
            case R.id.gallery:
                Intent toGallery = new Intent(Intent.ACTION_PICK);
                toGallery.setType("image/*");
                if(imag1>2){
                    Toast.makeText(this, "Cannot add more photos", Toast.LENGTH_SHORT).show();
                    return true;
                }
                startActivityForResult(toGallery,GALLERY_INTENT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  void function() {
         //area =  areaText.getText().toString();
         newsnow = news.getText().toString();
         //userValue = useredit.getText().toString();
       // SharedPreferences.Editor edio = shared.edit();
        String putName=shared.getString("putName",null);

        //edio.putString("area",area);
        edio.putString("news",newsnow);
        edio.commit();
        //edio.putString("userValue",userValue);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //edio.commit();

        if(!news.getText().toString().equals(""))    {
                String id = mReference.push().getKey();
            Model model = new Model(newsnow,putName,currentDateTimeString,0,0);
            
            mReference.child(newsnow).setValue(model);

        }
    }

////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 2:
            if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
                if(imag1==0) {
                    final Uri uri = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(uri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        im1.setImageBitmap(selectedImage);
                        imag1++;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(imag1==1){
                    final Uri uri = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(uri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        im2.setImageBitmap(selectedImage);
                        imag1++;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(imag1==2){
                    final Uri uri = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(uri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        im3.setImageBitmap(selectedImage);
                        imag1++;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Maximum 3 Images allowed", Toast.LENGTH_SHORT).show();
                }
             /*   StorageReference sReference = mStorage.child("photos").child(uri.getLastPathSegment());
                sReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if(imag1==0){
                            @SuppressWarnings("VisibleForTests") Uri down = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down).into(im1);
                            imag1++;
                        }
                        else if(imag1==1){
                            @SuppressWarnings("VisibleForTests") Uri down = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down).into(im1);
                            @SuppressWarnings("VisibleForTests") Uri down1 = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down1).into(im2);
                            imag1++;
                        }
                        else if(imag1==2){
                            @SuppressWarnings("VisibleForTests") Uri down = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down).into(im1);
                            @SuppressWarnings("VisibleForTests") Uri down1 = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down1).into(im2);
                            @SuppressWarnings("VisibleForTests") Uri down2 = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down2).into(im3);
                            imag1++;
                        }
                        else{
                            Toast.makeText(PostNews.this, "only 3 images can be used", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(PostNews.this, "upload done.", Toast.LENGTH_SHORT).show();



                    }
                });*/
            }
            break;
            case 3:
                if(requestCode==CAMERA_INTENT && resultCode==RESULT_OK){
                    /*Uri uri = data.getData();
                    StorageReference sReference = mStorage.child("photos").child(uri.getLastPathSegment());
                    sReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(PostNews.this, "captured successfully", Toast.LENGTH_SHORT).show();
                    @SuppressWarnings("VisibleForTests") Uri down = taskSnapshot.getDownloadUrl();
                            Picasso.with(PostNews.this).load(down).into(im);
                        }
                    });*/
                    if(imag1==0) {
                        final Uri uri = data.getData();
                        try {
                            InputStream imageStream = getContentResolver().openInputStream(uri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            im1.setImageBitmap(selectedImage);
                            imag1++;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(imag1==1){
                        final Uri uri = data.getData();
                        try {
                            InputStream imageStream = getContentResolver().openInputStream(uri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            im2.setImageBitmap(selectedImage);
                            imag1++;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(imag1==2){
                        final Uri uri = data.getData();
                        try {
                            InputStream imageStream = getContentResolver().openInputStream(uri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            im3.setImageBitmap(selectedImage);
                            imag1++;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "Maximum 3 Images allowed", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
    //////////////////



}
