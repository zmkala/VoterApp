package omabralimited.voterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import omabralimited.voterapp.databases.VoterDbHelper;

public class RegisterDeathActivity extends AppCompatActivity {
  ImageView image;
  EditText etvictimName,
          etdeathidNum,
          etdeathdob,
          etdeathTime,
          etDeathRegArea,
          etdeathcause;
  Button bnRegisterDeath;
  Context c;
  VoterDbHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registerdeath);


    d("Register activity born");


    c = this;

    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();

    etvictimName = (EditText) findViewById(R.id.etvictimName);
    etdeathidNum = (EditText) findViewById(R.id.etdeathidNum);
    etdeathdob = (EditText) findViewById(R.id.etdeathdob);
    etdeathTime = (EditText) findViewById(R.id.etdeathTime);
    etDeathRegArea = (EditText) findViewById(R.id.etDeathRegArea);
    etdeathcause = (EditText) findViewById(R.id.etdeathcause);
    bnRegisterDeath = (Button) findViewById(R.id.bnRegDeath);

    bnRegisterDeath.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        registerDeath();
      }
    });
  }

  private void registerDeath(){

    String name = etvictimName.getText().toString();
    String idNum = etdeathidNum.getText().toString();
    String dob = etdeathdob.getText().toString();
    String deathTime = etdeathTime.getText().toString();
    String deathCause = etdeathcause.getText().toString();
    String regArea = etDeathRegArea.getText().toString();
    String Profpic = "picture";
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    String formattedDate = df.format(calendar.getTime());

    d("Doing form validation");

    if(name.length() < 2 ){
      Toast.makeText(c, "Name too short!", Toast.LENGTH_SHORT);
      return;
    }

    if(idNum.isEmpty()){
      Toast.makeText(c, "ID Number is required", Toast.LENGTH_SHORT);
      return;
    }

    if(dob.isEmpty()){
      Toast.makeText(c, "Date of Birth required!", Toast.LENGTH_SHORT);
      return;
    }
    d("Form validation passed");
//        Make the registration
    dbHelper = new VoterDbHelper(this);

    if (dbHelper.getDeadVoter(idNum)=="EXISTS"){
     dbHelper.updateVoterStatus(Integer.parseInt(idNum),"Not Eligible");
    }

    if(dbHelper.insertDeath(name, Profpic, dob, idNum, regArea, formattedDate, deathCause, deathTime)) {
      Toast.makeText(getApplicationContext(), "Death Registered", Toast.LENGTH_SHORT).show();
    }
    else{
      Toast.makeText(getApplicationContext(), "Could not register death", Toast.LENGTH_SHORT).show();
    }
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  private void d(String s){
    try {
      Log.d("RegisterDeathActivity", s);
    }catch(NullPointerException e){
      Log.d("RegisterDeathActivity", e.getMessage());
    }
  }
}
