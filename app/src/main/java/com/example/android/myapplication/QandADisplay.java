package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QandADisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_adisplay);


        final Intent i =getIntent();
        Bundle extras= i.getExtras();
        final QuestionInfo qobj= (QuestionInfo) extras.getSerializable("obj"); //reading parmeters from the intenet
        final String keyq= extras.getString("key");


       // Toast.makeText(this,qobj.getQ()+"  "+qobj.getSname(),Toast.LENGTH_LONG).show();



        TextView tvQues= (TextView) findViewById(R.id.quesEnter);
        TextView tvAuthor= (TextView) findViewById(R.id.textView3);
        TextView tvsub= (TextView) findViewById(R.id.textView4);
        tvQues.setText(qobj.getQ());
        tvsub.setText(qobj.getTopic());
        tvAuthor.setText(qobj.getSname());


        TextView tv1= (TextView) findViewById(R.id.tvf11);
        TextView tv2= (TextView) findViewById(R.id.tvf21);
        TextView tv3= (TextView) findViewById(R.id.tvf31);
        TextView tv4= (TextView) findViewById(R.id.tvf41);

        int f1=0,f2=0,f3=0,f4=0;
        if(!qobj.getA1().equals("0000"))
        {
            tv1.setText(qobj.getA1());
            f1=1;
        }


        if(!qobj.getA2().equals("0000") && f1==1)
        {
            tv2.setText(qobj.getA2());
            f2=1;
        }
        else if(!qobj.getA2().equals("0000"))
        {
            tv1.setText(qobj.getA2());
            f1=1;
        }


        if(!qobj.getA3().equals("0000") && f2==1)
        {
            tv3.setText(qobj.getA3());
            f3=1;
        }
        else if(!qobj.getA3().equals("0000") && f1==1)
        {
            tv2.setText(qobj.getA3());
            f2=1;
        }
        else if(!qobj.getA3().equals("0000"))
        {
            tv1.setText(qobj.getA3());
            f1=1;
        }

        if(!qobj.getA4().equals("0000") && f3==1)
        {
            tv4.setText(qobj.getA4());
            f4=1;

        }
        else if(!qobj.getA4().equals("0000") && f2==1)
        {
            tv3.setText(qobj.getA4());
            f3=1;
        }
        else if(!qobj.getA4().equals("0000") && f1==1)
        {
            tv2.setText(qobj.getA4());
            f2=1;
        }
        else if(!qobj.getA4().equals("0000"))
        {
            tv1.setText(qobj.getA4());
            f1=1;
        }

        if(f1==0)
        {
            tv1.setText("No answer available");
            tv2.setText("No answer available");
            tv3.setText("No answer available");
            tv4.setText("No answer available");

        }
        else if(f2==0)
        {
            tv2.setText("No answer available");
            tv3.setText("No answer available");
            tv4.setText("No answer available");
        }
        else if(f3==0)
        {
            tv3.setText("No answer available");
            tv4.setText("No answer available");
        }
        else if(f4==0)
        {
            tv4.setText("No answer available");
        }

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);

        if(new MainActivity().type == 1)
        {

            myFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"fab", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getBaseContext(), TeacherAnswer.class);
                    i.putExtra("obj", qobj);
                      i.putExtra("key",keyq);
                    startActivity(i);
                    finish();

                }
            });
        }
        else
        {
            myFab.setVisibility(View.GONE);
        }









    }
}
