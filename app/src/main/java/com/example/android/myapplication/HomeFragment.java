package com.example.android.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static long numlistitems;
    private homeAdapter mAdapter;
    private RecyclerView mNumberList;
    public MainActivity ma= new MainActivity();

    ArrayList<AnnouncementInfo> contacts;

    private static final String TAG = "RecyclerViewFragment";



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.setTag(TAG);

        new StudentOrTeacher(rootView).execute(ma.mDatabaseReference);


        mNumberList= (RecyclerView)rootView.findViewById(R.id.rv_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mNumberList.setLayoutManager(layoutManager);

        mNumberList.setHasFixedSize(false);



        DatabaseReference db= ma.mDatabaseReference.child("announcement");

        new Databasereading(getActivity()).execute(db);


        return rootView;
    }


    public class Databasereading extends AsyncTask<DatabaseReference,Void, Void>
    {
        ProgressDialog pg;

        public Databasereading(FragmentActivity activity)
        {
             pg= new ProgressDialog(activity);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg.setMessage("Loading..");
            pg.show();
            Log.e("pg","showing");

        }

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

                        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                        Calendar cal = Calendar.getInstance();
                        String d1 = formatter.format(cal.getTime());
                        String d2= qobj.getDate();
                        Date dd1=null,dd2=null;
                        try {
                               dd1= formatter.parse(d1);
                               dd2= formatter.parse(d2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }





                        if(dd1.compareTo(dd2)<=0)
                        {
                            contacts.add(qobj);
                        }
//                        else
//                            contacts.add(qobj);
                    }

                    mAdapter= new homeAdapter(getContext(), contacts);
                    mNumberList.setAdapter(mAdapter);

                    if(pg.isShowing())
                    {
                        pg.dismiss();
                        Log.e("pg","was showing in listener");

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

//            if(pg.isShowing())
//            {
//                pg.dismiss();
//                Log.e("pg","was showing");
//
//            }


            Log.e("pg","onPostExecute");
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

//                DataSnapshot ds= dataSnapshot.child("student").child(mFirebaseAuth.getCurrentUser().getUid());
//
//                Log.d("Checkkkkkkk2", mFirebaseAuth.getCurrentUser().getUid());
//                Log.d("Checkkkk", ""+dataSnapshot.child("student").hasChild(mFirebaseAuth.getCurrentUser().getUid()));
//                Log.d("Checkkkk", ""+dataSnapshot.child("student").child(mFirebaseAuth.getCurrentUser().getUid()));



//                    TextView tvname= (TextView) hView.findViewById(R.id.tv_name);
//                    TextView tvemail= (TextView) hView.findViewById(R.id.tv_email);




                    if (dataSnapshot.child("student").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {



                        typelocal=0;

                        Log.e("fragActstud","00"+typelocal);

                        setFAB(typelocal,mvView);

//                        mStudentInfo= dataSnapshot.child("student").child(mFirebaseAuth.getCurrentUser().getUid()).getValue(StudentInfo.class);
//                        tvname.setText(ma.mStudentInfo.getName());
//                        tvemail.setText(ma.mStudentInfo.getEmail());


                    }
                    else if(dataSnapshot.child("teacher").hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                    {


                        typelocal=1;

                        Log.e("fragActteach","00"+typelocal);

                        setFAB(typelocal,mvView);


//                        mTeacherInfo= dataSnapshot.child("teacher").child(mFirebaseAuth.getCurrentUser().getUid()).getValue(TeacherInfo.class);
//                        tvname.setText( mTeacherInfo.getName());
//
//                        tvemail.setText(mTeacherInfo.getEmail());
//
//                        Log.d("Checking", ""+dataSnapshot.child("teacher").child(mFirebaseAuth.getCurrentUser().getUid()));
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
