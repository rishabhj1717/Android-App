package com.example.android.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FirebaseAuth mFirebaseAuth;
    public FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUsername;
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;

    private static final String TAG = "MainActivity";
    public static final String ANONYMOUS = "anonymous";

    public static final int RC_SIGN_IN = 1;
    public FragmentTransaction mFragmentTransaction;
    public NavigationView mNavigationView;
    DrawerLayout drawer;
    static View hView;


    public static StudentInfo mStudentInfo;
    public static TeacherInfo mTeacherInfo;
    public static int type; // 0- student, 1- teacher


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        hView =  mNavigationView.inflateHeaderView(R.layout.nav_header_main);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d("Signed 1111111111111111", "yes");
                    //  onSignedInInitialize(mDatabaseReference);

                } else {
                    // User is signed out
                    onSignedOutCleanup();
                    Log.d("Signed in22222222222222", "no");
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder().setProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()
                                    ))
                                    .setTheme(R.style.GreenTheme)
                                    .build(),
                            RC_SIGN_IN);


                }
            }
        };



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        mFragmentTransaction=  getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.main_container,new HomeFragment());
        mFragmentTransaction.commit();

        getSupportActionBar().setTitle("New Announcements");




    }

    public class StudentOrTeacher extends AsyncTask<DatabaseReference, Void, Integer>
    {

        int typelocal;
        ProgressDialog pg;

        public StudentOrTeacher(MainActivity activity)
        {
            pg= new ProgressDialog(activity);
            pg.setMessage("Loading");
            pg.show();
        }


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



                    TextView tvname= (TextView) hView.findViewById(R.id.tv_name);
                    TextView tvemail= (TextView) hView.findViewById(R.id.tv_email);




                    if (dataSnapshot.child("student").hasChild(mFirebaseAuth.getCurrentUser().getUid())) {


                        Log.e("MainActstud","00"+type);

                        type=0;
                        mStudentInfo= dataSnapshot.child("student").child(mFirebaseAuth.getCurrentUser().getUid()).getValue(StudentInfo.class);
                        tvname.setText(mStudentInfo.getName());
                        tvemail.setText(mStudentInfo.getEmail());


                    }
                    else if(dataSnapshot.child("teacher").hasChild(mFirebaseAuth.getCurrentUser().getUid()))
                    {

                        Log.e("MainActteach","00"+type);

                        type=1;
                        mTeacherInfo= dataSnapshot.child("teacher").child(mFirebaseAuth.getCurrentUser().getUid()).getValue(TeacherInfo.class);
                        tvname.setText( mTeacherInfo.getName());

                        tvemail.setText(mTeacherInfo.getEmail());

                        Log.d("Checking", ""+dataSnapshot.child("teacher").child(mFirebaseAuth.getCurrentUser().getUid()));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });

            return type;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            if(true)
            {
                //Toast.makeText(MainActivity.this,"PostExecute", Toast.LENGTH_SHORT).show();
            }

            if(pg.isShowing())
                pg.dismiss();
        }
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AuthUI.getInstance().signOut(this);
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new HomeFragment());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("New Announcements");
            item.setChecked(true);
            drawer.closeDrawers();



        } else if (id == R.id.nav_gallery) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new Fragment2());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("Forum");
            item.setChecked(true);
            drawer.closeDrawers();

        } else if (id == R.id.nav_slideshow && type==0) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new Resource());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("Resources");
            item.setChecked(true);
            drawer.closeDrawers();

        }
        else if (id == R.id.nav_slideshow && type==1) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new TeacherResource());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("Teacher Resources");
            item.setChecked(true);
            drawer.closeDrawers();

        }
        else if (id == R.id.nav_manage) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new AllAnnouncementFragment());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("All Announcements");
            item.setChecked(true);
            drawer.closeDrawers();

        } else if (id == R.id.nav_share) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new BlankFragment());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("Blank");
            item.setChecked(true);
            drawer.closeDrawers();

        } else if (id == R.id.nav_send) {

            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new AboutUs());
            mFragmentTransaction.commit();
            getSupportActionBar().setTitle("About Us");
            item.setChecked(true);
            drawer.closeDrawers();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void onSignedInInitialize(DatabaseReference dbr) {


        new StudentOrTeacher(this).execute(dbr);


        // attachDatabaseReadListener();
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;

        // mMessageAdapter.clear();
        // detachDatabaseReadListener();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                // Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in

            Log.e("001111111111111", "yes");
            onSignedInInitialize(FirebaseDatabase.getInstance().getReference());

        } else {
            // User is signed out
            onSignedOutCleanup();
            Log.d("009022222222", "no");
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder().setProviders(
                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()
                            ))
                            .setTheme(R.style.GreenTheme)
                            .build(),
                    RC_SIGN_IN);


        }

    }
}
