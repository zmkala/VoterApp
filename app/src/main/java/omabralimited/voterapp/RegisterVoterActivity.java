package omabralimited.voterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import omabralimited.voterapp.databases.VoterDbHelper;

public class RegisterVoterActivity extends AppCompatActivity {
  ImageView image;
  EditText etName,
          etidNum,
          etdob,
          etphone,
          etRegArea;
  Button bnRegisterVoter;
  Context c;
  VoterDbHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registervoter);


    d("Register activity born");


    c = this;

    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();

    etName = (EditText) findViewById(R.id.etName);
    etidNum = (EditText) findViewById(R.id.etidNum);
    etdob = (EditText) findViewById(R.id.etdob);
    etphone = (EditText) findViewById(R.id.etphone);
    etRegArea = (EditText) findViewById(R.id.etRegArea);

    bnRegisterVoter = (Button) findViewById(R.id.bnRegisterVoter);

    bnRegisterVoter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        registerVoter();
      }
    });
  }

  private void registerVoter(){

    String name = etName.getText().toString();
    String idNum = etidNum.getText().toString();
    String dob = etdob.getText().toString();
    String phone = etphone.getText().toString();
    String regArea = etRegArea.getText().toString();
    String Profpic = "picture";
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    String formattedDate = df.format(calendar.getTime());

    d("Doing form validation");

    if(name.length() < 2 ){
      Toast.makeText(c, "Name!", Toast.LENGTH_SHORT);
      return;
    }

    if(idNum.isEmpty()){
      Toast.makeText(c, "ID Number is required", Toast.LENGTH_SHORT);
      return;
    }

   if(phone.isEmpty()){
      Toast.makeText(c, "Phone Number required!", Toast.LENGTH_SHORT );
      return;
    }
    if (!isPhoneNumberCorrect(phone)) {
      Toast.makeText(c, "Invalid phone number", Toast.LENGTH_SHORT);
      return;
    }

    d("Form validation passed");
//        Make the registration
    dbHelper = new VoterDbHelper(this);

    if(dbHelper.insertVoter(name, Profpic, dob, idNum, regArea, formattedDate, phone)) {
      Toast.makeText(getApplicationContext(), "Voter Registered", Toast.LENGTH_SHORT).show();
    }
    else{
      Toast.makeText(getApplicationContext(), "Could not register voter", Toast.LENGTH_SHORT).show();
    }
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  private boolean isPhoneNumberCorrect(String pPhoneNumber) {

    Pattern pattern = Pattern
            .compile("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})?)?\\d{8,13}");
    Matcher matcher = pattern.matcher(pPhoneNumber);

    if (matcher.matches()) return true;


    return false;
  }

  private void d(String s){
    try {
      Log.d("RegisterVoterActivity", s);
    }catch(NullPointerException e){
      Log.d("RegisterVoterActivity", e.getMessage());
    }
  }

}
