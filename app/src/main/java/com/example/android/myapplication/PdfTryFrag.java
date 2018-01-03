package com.example.android.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;
//import static com.facebook.login.widget.ProfilePictureView.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PdfTryFrag extends Fragment {

//    //the listview
//    ListView listView;
//
//    //database reference to get uploads data
//    DatabaseReference mDatabaseReference;
//
//    //list to store uploads data
//    List<Upload> uploadList;

    public PdfTryFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_pdf_try, container, false);
        rootView.setTag(TAG);


        startActivity(new Intent(getContext(), ViewUploadsActivity.class));
//
//
//        uploadList = new ArrayList<>();
//        listView = (ListView) rootView.findViewById(R.id.listView);
//
//        if(listView==null)
//            Log.e("listview", "nullllllllllllll");
//        else
//            Log.e("listview2","notnulllllllllllllll");
//
//        //adding a clicklistener on listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //getting the upload
//                Upload upload = uploadList.get(i);
//
//                //Opening the upload file in browser using the upload url
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(upload.getUrl()));
//                startActivity(intent);
//            }
//        });
//
//
//        //getting the database reference
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
//
//        //retrieving upload data from firebase database
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Upload upload = postSnapshot.getValue(Upload.class);
//                    uploadList.add(upload);
//                }
//
//                String[] uploads = new String[uploadList.size()];
//
//                for (int i = 0; i < uploads.length; i++) {
//                    uploads[i] = uploadList.get(i).getName();
//                }
//
//                //displaying it to list
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
        return rootView;
    }

}
