package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class AskQuestion extends AppCompatActivity {

    static int numberofQ;
    String q;
    String a1,a2,a3,a4,name,topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        Intent intent = this.getIntent();
        final MainActivity ma = new MainActivity();
        final DatabaseReference mQuestionsReference = ma.mFirebaseDatabase.getReference().child("question");

  /* Obtain String from Intent  */
        if(intent !=null)
        {
            String strdata = intent.getExtras().getString("number");
            Toast.makeText(this, strdata, Toast.LENGTH_LONG).show();
            numberofQ= Integer.parseInt(strdata);

            final TextView tvq= (TextView)findViewById(R.id.editText);
            final TextView tvs= (TextView)findViewById(R.id.editText2);
            Button post= (Button) findViewById(R.id.button2);



            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    q= tvq.getText().toString();
                    topic= tvs.getText().toString();

                    QuestionInfo obj = new QuestionInfo(q,"0000", "0000", "0000", "0000",ma.mStudentInfo.getName(),topic);
                    mQuestionsReference.push().setValue(obj);

                    finish();



                }
            });



        }
        else
        {
            Toast.makeText(this, "null intent", Toast.LENGTH_SHORT).show();
        }

    }
}
