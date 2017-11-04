package com.example.sivhabu.ekhack.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.common.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import static com.example.sivhabu.ekhack.common.Methods.hideKeyboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private View view;
    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressBar pbLogin;
    private String email, password;
    private FirebaseAuth firebaseAuth;
    private TextView lblForgot;
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    final DatabaseReference userTable = ref.child("User");

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        //firebase initialise
        firebaseAuth = FirebaseAuth.getInstance();

        //if user already signed in
        if (firebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }

        //UI initialization
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        pbLogin = (ProgressBar) view.findViewById(R.id.pbLogin);
        lblForgot = (TextView)  view.findViewById(R.id.lblForgot);

        //set progress bar to be invisible initially
        pbLogin.setVisibility(View.INVISIBLE);

        //handle login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);*/
                //Get email and password from textbox
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                pbLogin.setVisibility(View.VISIBLE);
                hideKeyboard(v, getContext());
                if (email.equals(""))
                {
                    pbLogin.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"Email cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if (password.equals(""))
                {
                    pbLogin.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Task<AuthResult> authResultTask = firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //pbLogin.setVisibility(View.INVISIBLE);
                                    if (task.isSuccessful())//succesful login
                                    {

                                        userTable.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {//get member usernmae and image
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                int user_role = dataSnapshot.child("user_role").getValue(Integer.class);
                                                String pp = dataSnapshot.child("pp").getValue(String.class);
                                                String name = dataSnapshot.child("name").getValue(String.class);
                                                String surname = dataSnapshot.child("surname").getValue(String.class);
                                                Log.i("user_role",user_role + "");
                                                final SharedPreferences pref = getContext().getApplicationContext().getSharedPreferences("MyPref", 0);
                                                final SharedPreferences.Editor editor = pref.edit();
                                                editor.putInt("user_role",user_role);
                                                editor.putString("pp",pp);
                                                editor.putString("username",surname + " " + name);
                                                editor.apply();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                        pbLogin.setVisibility(View.INVISIBLE);
                                        Fragment fragment = new NearbyHospitalFragment();
                                        android.support.v4.app.FragmentManager fragmentManager =  getFragmentManager();;
                                        fragmentManager.beginTransaction().replace(R.id.main, fragment).commit();
                                        try
                                        {
                                            // String token = new SharedPrefUtil(LogInActivity.this).getString(Constants.ARG_FIREBASE_TOKEN);
                                            String newToken = FirebaseInstanceId.getInstance().getToken();
                                            Log.d("TOKEN_SENT",newToken);
                                            if (!newToken.isEmpty()) {
                                                FirebaseDatabase.getInstance()
                                                        .getReference()
                                                        .child("User")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .child(Constants.ARG_FIREBASE_TOKEN)
                                                        .setValue(newToken);
                                            }

                                        }catch (Exception ex)
                                        {
                                            ex.printStackTrace();
                                            Toast.makeText(getContext(),"This device is unable to receive push notifications",Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                    else
                                    {
                                        pbLogin.setVisibility(View.INVISIBLE);
                                        //Toast.makeText(getApplicationContext(),"Incorrect email/password combination",Toast.LENGTH_LONG).show();
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthInvalidUserException e) {
                                            Toast.makeText(getContext(), "Email doesn't exist",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            Toast.makeText(getContext(), "Invalid Password",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (FirebaseNetworkException e) {
                                            Toast.makeText(getContext(), "Network error!",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(getContext(), e.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            });
                }
            }
        });

        return view;
    }

}
