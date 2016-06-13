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

public class RegisterActivity extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnRegister;
    private Button btnLinkToLogin;
    private ProgressDialog pDialog;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = (EditText) findViewById(R.id.username);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username = inputUsername.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    register(username,password,email);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
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

        private void register(String username,String password, String email) {
            pDialog.setMessage("Registering ...");
            User user = User.getInstance(username, password)
                    .signup()
                    .login();

            user.setEmail(email);

            showDialog();


            thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        thread.sleep(4000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    hideDialog();

                    if(User.getInstance().getId().equals("unset")) {
                        showToast("Sign up Faild");

                        try {
                            thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(getApplicationContext(),
                                RegisterActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        showToast("Sign up Succeeded");

                        try {
                            thread.sleep(700);
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


            public void showToast(final String toast)
            {
                runOnUiThread(new Runnable() {
                    public void run()
                    {
                        Toast.makeText(RegisterActivity.this, toast, Toast.LENGTH_SHORT).show();
                    }
                });
            }

}









