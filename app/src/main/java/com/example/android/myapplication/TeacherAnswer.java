package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherAnswer extends AppCompatActivity {

    MainActivity ma= new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_answer);

       Intent i = getIntent();
        Bundle extras= i.getExtras();

        final QuestionInfo qobj= (QuestionInfo) extras.getSerializable("obj");
        final String keyq= extras.getString("key");

        final TextView tv6= (TextView)findViewById(R.id.textView6);
        Button b = (Button) findViewById(R.id.button3);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans= tv6.getText().toString();
                if(ans==null)
                {
                    Toast.makeText(getApplicationContext(),"Please enter an answer and then submit", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(qobj.getA1().equals("0000"))
                    {
                            ma.mDatabaseReference.child("question").child(keyq).child("a1").setValue(ans);
                    }
                    else if(qobj.getA2().equals("0000"))
                    {
                        ma.mDatabaseReference.child("question").child(keyq).child("a2").setValue(ans);
                    }
                    else if(qobj.getA3().equals("0000"))
                    {
                        ma.mDatabaseReference.child("question").child(keyq).child("a3").setValue(ans);
                    }
                    else if(qobj.getA4().equals("0000"))
                    {
                        ma.mDatabaseReference.child("question").child(keyq).child("a4").setValue(ans);
                    }

                    finish();

                }
            }
        });

        



    }
}

