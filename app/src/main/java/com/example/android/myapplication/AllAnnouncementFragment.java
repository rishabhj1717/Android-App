package com.example.android.myapplication;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllAnnouncementFragment extends Fragment {


    private static long numlistitems;
    private homeAdapter mAdapter;
    private RecyclerView mNumberList;
    public MainActivity ma = new MainActivity();

    ArrayList<AnnouncementInfo> contacts;

    private static final String TAG = "RecyclerViewFragment";


    public AllAnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.setTag(TAG);

        new StudentOrTeacher(rootView).execute(ma.mDatabaseReference);

        mNumberList = (RecyclerView) rootView.findViewById(R.id.rv_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mNumberList.setLayoutManager(layoutManager);

        mNumberList.setHasFixedSize(false);


        DatabaseReference db = ma.mDatabaseReference.child("announcement");

        new Databasereading().execute(db);


        Log.e("44","00"+ma.type);

        return rootView;
    }

    public class Databasereading extends AsyncTask<DatabaseReference,Void, Void>
    {

        @Override
        protected Void doInBackground(DatabaseReference... params) {

            DatabaseReference dbr= params[0];
            dbr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    numlistitems = dataSnapshot.getChildrenCount();
                    //Toast.makeText(getActivity(), ""+numlistitems, Toast.LENGTH_LONG).show();



                    contacts = new ArrayList<AnnouncementInfo>();
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        //String q= dsp.getValue(String.class);

                        AnnouncementInfo qobj= dsp.getValue(AnnouncementInfo.class);
                            contacts.add(qobj);

                    }

                    mAdapter= new homeAdapter(getContext(), contacts);
                    mNumberList.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            return null;
        }
    }

    public class StudentOrTeacher extends AsyncTask<DatabaseReference, Void, Integer>
    {

        private View mvView;

        StudentOrTeacher(View v)
        {
            mvView=v;
        }

        int typelocal;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            typelocal=3;
        }

        @Override
        protected Integer doInBackground(DatabaseReference... params) {
            DatabaseReference mDbr= params[0];
            mDbr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (dataSnapshot.child("student").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {



                        typelocal=0;

                        Log.e("fragActstud","00"+typelocal);

                        setFAB(typelocal,mvView);




                    }
                    else if(dataSnapshot.child("teacher").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                    {


                        typelocal=1;

                        Log.e("fragActteach","00"+typelocal);

                        setFAB(typelocal,mvView);



                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });

            return typelocal;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

           /* Log.e("frag",""+typelocal);


            if(typelocal==0)
            {

                Toast.makeText(getContext(),"0", Toast.LENGTH_SHORT).show();
            }
            else if(typelocal==1)
            {
                Toast.makeText(getContext(),"1", Toast.LENGTH_SHORT).show();
            }
            else if(typelocal==3)
            {
                Toast.makeText(getContext(),"3", Toast.LENGTH_SHORT).show();
            }*/
        }
    }


    private void setFAB(int typelocal, View v) {

        Log.e("setFAB",""+typelocal);

        FloatingActionButton myFab = (FloatingActionButton) v.findViewById(R.id.fab);

        Log.e("11","00"+ma.type);
        if(typelocal==0)
        {
            Log.e("22","00"+ma.type);

            myFab.setVisibility(View.GONE);
        }
        else {

            Log.e("33","00"+ma.type);

            myFab.setVisibility(View.VISIBLE);
            myFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent i = new Intent(getActivity().getBaseContext(), TeacherAddAnnouncement.class);

                    startActivity(i);

                }
            });
        }



    }



}


