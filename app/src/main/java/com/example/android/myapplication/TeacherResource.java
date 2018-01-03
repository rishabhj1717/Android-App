package com.example.android.myapplication;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
//import static com.facebook.login.widget.ProfilePictureView.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherResource extends Fragment {


    private resourceAdapter mAdapter;
    private RecyclerView rv;
    public MainActivity ma= new MainActivity();
    public FragmentTransaction mFragmentTransaction;



    //the listview
    CardView cardView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Upload> uploadList;


    public TeacherResource() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_teacher_resource, container, false);
        rootView.setTag(TAG);

        rv = (RecyclerView)rootView.findViewById(R.id.rv_resource);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);

        rv.setLayoutManager(layoutManager);

        rv.setHasFixedSize(true);


        uploadList = new ArrayList<>();
        cardView= (CardView)rootView.findViewById(R.id.myCv);



        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                }

                mAdapter = new resourceAdapter(getContext(),uploadList);
                rv.setAdapter(mAdapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new BlankFragment());
                mFragmentTransaction.commit();


            }
        });

        return rootView;


    }

}
