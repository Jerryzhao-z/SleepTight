package fr.sleeptight.ui.page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.APIClass;
import fr.sleeptight.data.acces.APIClient.AsyncCall;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.HomePage;
import fr.sleeptight.ui.simulation.SimulationActivity;

public class ProfileActivity extends BasicPage {


    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private Button simulation;
    User user;
    Thread thread;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        user = User.getInstance();
        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg2 = (RadioGroup)findViewById(R.id.rg2);
        rg3 = (RadioGroup)findViewById(R.id.rg3);
        simulation = (Button) findViewById(R.id.simulation_button);
        gettProfile();

        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProfile();


                pDialog.setMessage("Saving profie ...");
                pDialog.show();


                thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (pDialog.isShowing())
                            pDialog.dismiss();

                        if(User.getInstance().getId().equals("unset")) {
                            showToast("You need to login");


                        }else{
                            showToast("Profile saved");

                            try {
                                thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent i = new Intent(getApplicationContext(),
                                    HomePage.class);
                            startActivity(i);
                            finish();
                        }

                    }});
                thread.start();

            }
        });


    }

    public void setProfile(){
        switch(rg1.getCheckedRadioButtonId()){
            case R.id.fem : user.setGender(true);
                break;
            case R.id.mal : user.setGender(false);
                break;
        }
        Log.d("Gender checked", "rg1.getCheckedRadioButtonId()");


        switch(rg2.getCheckedRadioButtonId()){
            case R.id.yes : user.setNoon(true);
                break;
            case R.id.no : user.setNoon(false);
                break;
        }

        switch(rg3.getCheckedRadioButtonId()){
            case R.id.a17 : user.setAge(17);
                break;
            case R.id.a30 : user.setAge(25);
                break;
            case R.id.a50 : user.setAge(50);
                break;
        }

        //AsyncCall.setProfileCall();




    }

    public void gettProfile(){
        if(user.getGender()) {
            rg1.check(R.id.fem);
        }else{
            rg1.check(R.id.mal);
        }
        Log.d("Gender user", "user.getGender()");


        if(user.getNoon()) {
            rg2.check(R.id.yes);
        }else{
            rg2.check(R.id.no);
        }


        switch(user.getAge()){
            case 17 : rg3.check(R.id.a17);
                break;
            case 25 : rg3.check(R.id.a30);
                break;
            case 50 : rg3.check(R.id.a50);
                break;

         }
    }


    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(ProfileActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
