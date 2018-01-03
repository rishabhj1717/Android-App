package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mahak on 19/9/17.
 */

public class f2Adapter extends RecyclerView.Adapter<f2Adapter.f2ViewHolder> {


    private List<QuestionInfo> mContacts;
    private List<String> mContacts2;
    private Context mContext;
    MainActivity ma= new MainActivity();

    public f2Adapter(Context context, List<QuestionInfo> contacts, List<String> contacts2)
    {
        mContacts= contacts;
        mContacts2=contacts2;
        mContext= context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public f2ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();


        int layoutIdForListItem = R.layout.f2cards;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        f2ViewHolder viewHolder = new f2ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder( f2ViewHolder holder, int position) {

        final QuestionInfo contact = mContacts.get(position);
        final String contact2= mContacts2.get(position);
        TextView tvQ= holder.tv;
        TextView tv2= holder.tv2;
        CardView cv= holder.cv;
        ImageView iv= holder.iv;
        tvQ.setText(contact.getQ());
        String i=contact.getSname();
        tv2.setText(i);
        String t=contact.getTopic();
        if(t.equalsIgnoreCase("isee"))
        {
            iv.setImageResource(R.drawable.isee2);
        }
        if(t.equalsIgnoreCase("TOC"))
        {
            iv.setImageResource(R.drawable.toc2);
        }
        if(t.equalsIgnoreCase("CN"))
        {
            iv.setImageResource(R.drawable.cn2);
        }
        if(t.equalsIgnoreCase("SDL"))
        {
            iv.setImageResource(R.drawable.sdl);
        }

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(),QandADisplay.class);
                    i.putExtra("obj", contact);
                    i.putExtra("key", contact2);
                    getContext().startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    class f2ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        TextView tv2;
        ImageView iv;
        CardView cv ;



        public f2ViewHolder(View itemView)
        {
            super(itemView);


            tv= (TextView)itemView.findViewById(R.id.tvf2);
            tv2=(TextView)itemView.findViewById(R.id.tvf22);
            iv=(ImageView)itemView.findViewById(R.id.iv);
            cv=(CardView) itemView.findViewById(R.id.cv);



        }




    }




}
