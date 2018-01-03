package com.example.android.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {



    private static long numlistitems;
    private f2Adapter mAdapter;
    private RecyclerView mNumberList;
    public MainActivity ma= new MainActivity();

    ArrayList<QuestionInfo> contacts;
    ArrayList<String> contacts2;

    private static final String TAG = "RecyclerViewFragment";
    public Fragment2() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

//        MainActivity ma= new MainActivity();
//        DatabaseReference qReference= ma.mFirebaseDatabase.getReference().child("question");
//        qReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    for (DataSnapshot single : child.getChildren()) {
//
////                        mapQ.put(single.getKey(), (String) single.getValue());
////                        Log.d("aaaa", "SomeText: " + new Gson().toJson(mapQ));
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ProgressDialog pg = new ProgressDialog(getActivity());
        pg.setMessage("Loading..");
        pg.show();
        View rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);
        rootView.setTag(TAG);
        mNumberList= (RecyclerView)rootView.findViewById(R.id.rv_f2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mNumberList.setLayoutManager(layoutManager);

        mNumberList.setHasFixedSize(false);



        DatabaseReference db= ma.mDatabaseReference.child("question");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                numlistitems = dataSnapshot.getChildrenCount();
               // Toast.makeText(getActivity(), ""+numlistitems, Toast.LENGTH_LONG).show();


                contacts2=new ArrayList<String>();
                contacts = new ArrayList<QuestionInfo>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {


                    QuestionInfo qobj= dsp.getValue(QuestionInfo.class);


                    contacts.add(qobj);
                    contacts2.add(dsp.getKey());
                    Log.d("keys", dsp.getKey());
                }

                mAdapter= new f2Adapter(getContext(), contacts,contacts2);
                mNumberList.setAdapter(mAdapter);
                if(pg.isShowing())
                    pg.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getActivity().getBaseContext(), AskQuestion.class);
                i.putExtra("number", numlistitems+"");
                startActivity(i);

            }
        });







        return rootView;
    }



}
