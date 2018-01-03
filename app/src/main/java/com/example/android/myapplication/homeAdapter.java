package com.example.android.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mahak on 3/10/17.
 */

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.homeViewHolder> {

    private List<AnnouncementInfo> mContacts;
    private Context mContext;

    public homeAdapter(Context context, List<AnnouncementInfo> contacts)
    {
        mContacts= contacts;
        mContext= context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public homeAdapter.homeViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.home_cards;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        homeAdapter.homeViewHolder viewHolder = new homeAdapter.homeViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(homeAdapter.homeViewHolder holder, int position) {

        final AnnouncementInfo contact = mContacts.get(position);
        TextView tvQ= holder.tv;
        TextView tv2= holder.tv2;
       TextView tv3=holder.tv3;
        CardView cv= holder.cv;
        ImageView iv= holder.iv;




            tvQ.setText(contact.getAnn());
            String i = contact.gettUid();
            tv2.setText(i);

            String d = contact.getDate();
            tv3.setText(d);
            String t = contact.getSub();
            if (t.equalsIgnoreCase("isee")) {
                iv.setImageResource(R.drawable.iseeannounce);
            }
            if (t.equalsIgnoreCase("TOC")) {
                iv.setImageResource(R.drawable.tocannounce);
            }
            if (t.equalsIgnoreCase("CN")) {
                iv.setImageResource(R.drawable.cnannounce);
            }
            if (t.equalsIgnoreCase("SDL")) {
            iv.setImageResource(R.drawable.sdlannounce);
            }


            

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


    class homeViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        TextView tv2;
        ImageView iv;
        CardView cv ;
        TextView tv3;


        public homeViewHolder(View itemView)
        {
            super(itemView);


            tv= (TextView)itemView.findViewById(R.id.tvf2);
            tv2=(TextView)itemView.findViewById(R.id.tvf22);
            tv3=(TextView)itemView.findViewById(R.id.tvd);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            cv=(CardView) itemView.findViewById(R.id.cv);



        }




    }





}
