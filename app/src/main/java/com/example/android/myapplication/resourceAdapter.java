package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mahak on 25/9/17.
 */

public class resourceAdapter extends RecyclerView.Adapter<resourceAdapter.rViewholder> {


    //private List<QuestionInfo> mContacts;
    private Context mContext;
    private int mNumberItems;
    private List<Upload> mContacts;

    public resourceAdapter(Context context, List<Upload> contacts)
    {
        //mContacts= contacts;
       // mNumberItems=num;
        mContext= context;
        mContacts= contacts;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public rViewholder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.resource_cards;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        rViewholder viewHolder = new resourceAdapter.rViewholder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(rViewholder holder, int position) {


        final Upload contact = mContacts.get(position);
        TextView tv = holder.myTv;
        CardView cv= holder.myCv;
        ImageView ivv=holder.myIm;

        tv.setText(contact.getName());


        String t=contact.getSub();
        if(t.equalsIgnoreCase("isee"))
        {
            ivv.setImageResource(R.drawable.iseecards);
        }
        if(t.equalsIgnoreCase("TOC"))
        {
            ivv.setImageResource(R.drawable.toccards);
        }
        if(t.equalsIgnoreCase("CN"))
        {
            ivv.setImageResource(R.drawable.cncards);
        }
        if(t.equalsIgnoreCase("SDL"))
        {
            ivv.setImageResource(R.drawable.sdlcards);
        }

       cv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(contact.getUrl()));
               getContext().startActivity(intent);




           }
       });


//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(getContext(),QandADisplay.class);
//                i.putExtra("obj", contact);
//                getContext().startActivity(i);
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }






    class rViewholder extends RecyclerView.ViewHolder
    {


        CardView myCv;
        TextView myTv;
        ImageView myIm;



        public rViewholder(View itemView) {
            super(itemView);


            myTv=(TextView)itemView.findViewById(R.id.myTv);
            myCv=(CardView)itemView.findViewById(R.id.myCv);
            myIm=(ImageView)itemView.findViewById(R.id.myIm);

        }





    }


}
