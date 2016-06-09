package fr.sleeptight.ui.user_branch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.sleeptight.R;
import fr.sleeptight.data.traitement.User;
import fr.sleeptight.ui.HomePage;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputUsername;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String username = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!username.isEmpty() && !password.isEmpty()) {
                    // login user
                    login(username,password);

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void login(final String username, String password){
        User user = User.getInstance(username, password)
                .login();

        pDialog.setMessage("Logging in ...");
        showDialog();
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hideDialog();

                /*Toast.makeText(getApplicationContext(),
                        "Welcome "+username, Toast.LENGTH_LONG)
                        .show();*/

                Intent i = new Intent(getApplicationContext(),
                        HomePage.class);
                startActivity(i);
                finish();

            }});
        thread.start();
    }
}