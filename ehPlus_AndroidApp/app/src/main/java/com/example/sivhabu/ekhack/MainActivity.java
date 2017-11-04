package com.example.sivhabu.ekhack;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sivhabu.ekhack.fragments.BookAppointmentFragment;
import com.example.sivhabu.ekhack.fragments.FeedbackFragment;
import com.example.sivhabu.ekhack.fragments.IndoorNavigationFragment;
import com.example.sivhabu.ekhack.fragments.LoginFragment;
import com.example.sivhabu.ekhack.fragments.NearByAmbulanceFragment;
import com.example.sivhabu.ekhack.fragments.NearByHospitalFragment;
import com.example.sivhabu.ekhack.fragments.PreviousMedicalInfoFragment;
import com.example.sivhabu.ekhack.fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    final DatabaseReference userTable = ref.child("User");
    private FirebaseAuth firebaseAuth;

    public static NavigationView getNavigationView() {
        return navigationView;
    }

    private static NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        String stud_session =  pref.getString("studentId", null);
        //firebase initialise
        firebaseAuth = FirebaseAuth.getInstance();
        //if user already signed in
        if (firebaseAuth.getCurrentUser() == null)
        {
            notSignedIn();
            fragment = new NearByHospitalFragment();
            fragmentManager.beginTransaction().replace(R.id.main, fragment).commit();
        }
        else
        {
            setNavImg(pref.getString("pp", null));
            setNavTitle("Signed in: " + pref.getString("username",null));
            if (pref.getInt("user_role",-1) == 3)//patient
            {
                signedInPatient();
            }
            else if (pref.getInt("user_role",-1) == 4)//ambulance driver
            {
                signedInAmbulanceDriver();
            }
            fragment = new NearByHospitalFragment();
            fragmentManager.beginTransaction().replace(R.id.main, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment[] fragment = {new NearByHospitalFragment()};
        if (id == R.id.nav_near_hospitals) {
            fragment[0] = new NearByHospitalFragment();
        } else if (id == R.id.nav_near_ambulance) {
            fragment[0] = new NearByAmbulanceFragment();
        } else if (id == R.id.nav_qr_scanner) {

        } else if (id == R.id.nav_previous_medical_info) {
            fragment[0] = new PreviousMedicalInfoFragment();
        } else if (id == R.id.nav_view_profile) {
            fragment[0] = new ProfileFragment();
        } else if (id == R.id.nav_book_appointment) {
            fragment[0] = new BookAppointmentFragment();
        }
        else if (id == R.id.nav_indoor_navigation) {
            fragment[0] = new IndoorNavigationFragment();
        }
        else if (id == R.id.nav_feedback) {
            fragment[0] = new FeedbackFragment();
        }
        else if (id == R.id.nav_login) {
fragment[0] = new LoginFragment();
        }
        else if (id == R.id.nav_logout)
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            final SharedPreferences.Editor editor = pref.edit();
            editor.remove("user_role").remove("username").remove("pp");
            editor.clear();
            editor.apply();
            notSignedIn();
            setNavTitle("Not signed in");
            setNavImg(null);
            Toast.makeText(getApplicationContext(),"Logging out",Toast.LENGTH_LONG).show();
            userTable.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        userTable.child("firebaseToken").removeValue();
                        firebaseAuth.signOut();
                        fragment[0] = new LoginFragment();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(),"An error occurred during logout",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        fragmentManager.beginTransaction().replace(R.id.main, fragment[0]).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void setNavImg(final String link)
    {
        View view = navigationView.getHeaderView(0);
        final ImageView imgView =  (ImageView)view.findViewById(R.id.imageView);

        new AsyncTask<Void, Void, Void>() {
            public Bitmap bmp;
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = new URL(link).openStream();
                    bmp = BitmapFactory.decodeStream(in);

                } catch (Exception e) {
                    // log error
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (bmp != null)
                    imgView.setImageBitmap(bmp);
                else
                {
                    imgView.setImageResource(R.drawable.default_pp);
                    Log.i("null","is null");
                }
            }

        }.execute();
    }
    public static void setNavTitle(String text)
    {
        View view = navigationView.getHeaderView(0);
        TextView tv =  (TextView)view.findViewById(R.id.txtLoggedIn);
        tv.setText(text);
    }


    public static void notSignedIn()
    {
        navigationView.getMenu().getItem(0).setVisible(true);//nearby hosipital
        navigationView.getMenu().getItem(1).setVisible(true);//nearby ambulance
        navigationView.getMenu().getItem(2).setVisible(false);//saerch patient info
        navigationView.getMenu().getItem(3).setVisible(false);//previous medical infomation (viewed by user and ambulance drivers)
        navigationView.getMenu().getItem(4).setVisible(false);//view profile (view my profile)
        navigationView.getMenu().getItem(5).setVisible(false);//book appoitment
        navigationView.getMenu().getItem(6).setVisible(false);//join queue
        navigationView.getMenu().getItem(7).setVisible(true);//indoor navigation
        navigationView.getMenu().getItem(8).setVisible(false);//feedback
        navigationView.getMenu().getItem(9).setVisible(true);//login
        navigationView.getMenu().getItem(10).setVisible(false);//logout
    }

    private void signedInPatient()
    {
        navigationView.getMenu().getItem(0).setVisible(true);//nearby hosipital
        navigationView.getMenu().getItem(1).setVisible(true);//nearby ambulance
        navigationView.getMenu().getItem(2).setVisible(false);//search patient info
        navigationView.getMenu().getItem(3).setVisible(true);//previous medical infomation
        navigationView.getMenu().getItem(4).setVisible(true);//view profile
        navigationView.getMenu().getItem(5).setVisible(true);//book appoitment
        navigationView.getMenu().getItem(6).setVisible(true);//join queue
        navigationView.getMenu().getItem(7).setVisible(true);//indoor navigation
        navigationView.getMenu().getItem(8).setVisible(true);//feedback
        navigationView.getMenu().getItem(9).setVisible(false);//login
        navigationView.getMenu().getItem(10).setVisible(true);//logout
    }

    private void signedInAmbulanceDriver()
    {
        navigationView.getMenu().getItem(0).setVisible(true);//nearby hosipital
        navigationView.getMenu().getItem(1).setVisible(true);//nearby ambulance
        navigationView.getMenu().getItem(2).setVisible(true);//search patient info
        navigationView.getMenu().getItem(3).setVisible(true);//previous medical infomation
        navigationView.getMenu().getItem(4).setVisible(true);//view profile
        navigationView.getMenu().getItem(5).setVisible(true);//book appoitment
        navigationView.getMenu().getItem(6).setVisible(true);//join queue
        navigationView.getMenu().getItem(7).setVisible(true);//indoor navigation
        navigationView.getMenu().getItem(8).setVisible(false);//feedback
        navigationView.getMenu().getItem(9).setVisible(false);//login
        navigationView.getMenu().getItem(10).setVisible(true);//logout
    }

}
