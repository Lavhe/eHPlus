package com.example.sivhabu.ekhack.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.sivhabu.ekhack.MainActivity;
import com.example.sivhabu.ekhack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    final DatabaseReference userTable = ref.child("User");
    final DatabaseReference appointmentTable = ref.child("Appointment");
    SharedPreferences pref = null;
    SharedPreferences.Editor editor = null;
    Fragment fragment = null;
    android.support.v4.app.FragmentManager fragmentManager = null;
    private View view;
    private ArrayList<String> hospitalNames = new ArrayList<String>();

    private SearchableSpinner spnrHospitals;
    private RatingBar ratingBarHospital;
    private EditText txtMsg;
    private Button btnSubmit;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        ((MainActivity) getActivity()).setTitle("Rate a Hospital/Clinic");//sets action bar title

        //firebase initialise
        firebaseAuth = FirebaseAuth.getInstance();

        //if user already signed in
        if (firebaseAuth.getCurrentUser() == null)
        {
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
        }

        //gui inits
        spnrHospitals = (SearchableSpinner) view.findViewById(R.id.spnrHospitals) ;
        ratingBarHospital = (RatingBar) view.findViewById(R.id.ratingBarHospital);
        txtMsg = (EditText) view.findViewById(R.id.txtMsg);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        ratingBarHospital.setMax(5);
        ratingBarHospital.setNumStars(5);

        appointmentTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if (!hospitalNames.contains(postSnapshot.child("hospital").getValue(String.class)))
                    {
                        hospitalNames .add(postSnapshot.child("hospital").getValue(String.class));
                    }
                }
                spnrHospitals.setTitle("Select a hospital/clinic");
                spnrHospitals.setPositiveButton("OK");
                ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, hospitalNames);
                spnrHospitals.setAdapter(timeAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* DatabaseReference newFeedback = ref.child("Feedback").push();
                newFeedback.child("hospital").setValue(spnrHospitals.getSelectedItem().toString());
                newFeedback.child("message").setValue(txtMsg.getText().toString());
                newFeedback.child("stars").setValue(ratingBarHospital.getRating() + "");
                newFeedback.child("uid").setValue(firebaseAuth.getCurrentUser().getUid());
*/

                ratingBarHospital.setNumStars(5);
                /*                                                   Log.i("txtOptMsg.getT", ratingBar + " : " + v + " : " + b);

                Toast.makeText(getContext(),"Thank you!",Toast.LENGTH_LONG).show();
                Fragment fragment = new NearByHospitalFragment();
                FragmentManager fragmentManager =  getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main, fragment).commit();*/
            }
        });



        //gui init

        return view;
    }

}
