package com.example.shiva.post;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

import java.sql.Wrapper;
import java.util.ArrayList;

/**
 * Created by shiva on 7/23/2017.
 */

public class CustomAdapter extends BaseAdapter {

    int val;

FirebaseDatabase mDatabse;
    int likesCount,dislikesCount;


int likes=0,dislikes=0;
    private ArrayList<MessageDetails> data;
    Context c;
    CustomAdapter(ArrayList<MessageDetails> data,Context c) {
        this.data = data;
        this.c = c;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v==null){
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.lists,null);


        }
         MessageDetails msg1=data.get(position);


        final TextView thisIsName = (TextView)v.findViewById(R.id.nameOfPoster);
        ImageView dealImage1=(ImageView)v.findViewById(R.id.images1);
        final TextView noOfLikes=(TextView)v.findViewById(R.id.likeCount);
        final TextView noOfDislikes=(TextView)v.findViewById(R.id.dislikeCount);
        ImageView dealImage2=(ImageView)v.findViewById(R.id.images2);
        ImageView dealImage3=(ImageView)v.findViewById(R.id.images3);
        final ImageButton likeButton=(ImageButton)v.findViewById(R.id.like);
        final ImageButton dislikeButton=(ImageButton)v.findViewById(R.id.dislike);
        //TextView thisIsArea = (TextView)v.findViewById(R.id.areafield);
        final TextView thisIsNews = (TextView)v.findViewById(R.id.postingNews);
        TextView thisIsTime=(TextView)v.findViewById(R.id.timeOfPosting);


        final MessageDetails msg = data.get(position);
        thisIsName.setText(msg.getMyName());


        mDatabse=FirebaseDatabase.getInstance();
        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        final DatabaseReference mReference=mDatabse.getReference();



        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetails msg1=data.get(position);


                val=msg1.getLikesC();

               // Toast.makeText(c, msg1.getMyPost(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(c, "val is "+val, Toast.LENGTH_SHORT).show();


                mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        MessageDetails msg2=data.get(position);
                     //  Toast.makeText(c, ""+mAuth.getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();
                        //if(dataSnapshot.child("RootNode").child(msg2.getMyPost()).child("likers").child(mAuth.getCurrentUser().toString()).exists()){
                    //    //  Toast.makeText(c, "adei you already liked this da", Toast.LENGTH_SHORT).show();
                        //}

                        mReference.child("RootNode").child(msg.getMyPost()).child("dislikes").setValue(msg.getLikesC());
                        // Toast.makeText(c, msg.getLikesC(), Toast.LENGTH_SHORT).show();
                        val= Integer.parseInt(dataSnapshot.child("RootNode").child(msg2.getMyPost()).child("dislikes").getValue().toString());
                        //Toast.makeText(c, "Already"+val, Toast.LENGTH_SHORT).show();
                        val++;
                        dataSnapshot.getRef().child("RootNode").child(msg2.getMyPost()).child("dislikes").setValue(val);
                    //    Toast.makeText(c, "value= "+val, Toast.LENGTH_SHORT).show();
                        msg2.setDislikesC(val);
                        noOfDislikes.setText(String.valueOf(msg.getDislikesC()));
                    //    Toast.makeText(c, "gettin likseC "+msg2.getDislikesC(), Toast.LENGTH_SHORT).show();
                    //    //val=msg1.getDislikesC();
                        //msg.setLikesC(val++);
                        //Toast.makeText(c, "Lets try this "+msg.getLikesC(), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



               // Toast.makeText(c, "You like this", Toast.LENGTH_SHORT).show();

            }
        });


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDetails msg1=data.get(position);


                val=msg1.getLikesC();

               // Toast.makeText(c, msg1.getMyPost(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(c, "val is "+val, Toast.LENGTH_SHORT).show();


              mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       MessageDetails msg2=data.get(position);
                   //  Toast.makeText(c, ""+mAuth.getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();
                     //if(dataSnapshot.child("RootNode").child(msg2.getMyPost()).child("likers").child(mAuth.getCurrentUser().toString()).exists()){
                       //  Toast.makeText(c, "adei you already liked this da", Toast.LENGTH_SHORT).show();
                     //}

                       mReference.child("RootNode").child(msg.getMyPost()).child("likes").setValue(msg.getLikesC());
                      // Toast.makeText(c, msg.getLikesC(), Toast.LENGTH_SHORT).show();
                       val= Integer.parseInt(dataSnapshot.child("RootNode").child(msg2.getMyPost()).child("likes").getValue().toString());
                       //Toast.makeText(c, "Already"+val, Toast.LENGTH_SHORT).show();
                       val++;
                       dataSnapshot.getRef().child("RootNode").child(msg2.getMyPost()).child("likes").setValue(val);
                    //   Toast.makeText(c, "value= "+val, Toast.LENGTH_SHORT).show();
                       msg2.setLikesC(val);
                     noOfLikes.setText(String.valueOf(msg.getLikesC()));
                     //  Toast.makeText(c, "gettin likseC "+msg2.getLikesC(), Toast.LENGTH_SHORT).show();
                       //val=msg1.getDislikesC();
                       //msg.setLikesC(val++);
                       //Toast.makeText(c, "Lets try this "+msg.getLikesC(), Toast.LENGTH_SHORT).show();


                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });



              //  Toast.makeText(c, "You like this", Toast.LENGTH_SHORT).show();

            }
        });


        //Toast.makeText(c, ""+msg1.getLikesC(), Toast.LENGTH_SHORT).show();
       // thisIsArea.setText(msg.getMyArea());
        noOfDislikes.setText(String.valueOf(msg.getDislikesC()));
        noOfLikes.setText(String.valueOf(msg.getLikesC()));
        thisIsNews.setText(msg.getMyPost());
        thisIsTime.setText(msg.getTime());

        return v;
    }
}
