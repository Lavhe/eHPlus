package com.example.sivhabu.ekhack.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sivhabu.ekhack.R;
import com.example.sivhabu.ekhack.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private static View view;
    private TextView lblLogout,txtUsername,txtResName,txtStatus,txtAge,txtGender,txtEmail;
    private ImageView imgPP;
    private String username,resName,statusName,gender,bio,ppFull,ppThumb;
    private String ppFullToDelete, ppThumbToDelete;
    private int year,month,day;

    private TextView lblUsernameClick,lblStatusClick,lblBioClick,lblCalendar;
    private TextView lblChangePassword;

    private static Button btnUploadPP;
    private String ppThumbName,ppFullName;
    DatabaseReference ref;
    ProgressDialog pd;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        //GUI INIT
        lblLogout = view.findViewById(R.id.lblLogout);
        txtUsername =  view.findViewById(R.id.txtUsername);
        txtResName = view.findViewById(R.id.txtResName);
        txtStatus  =  view.findViewById(R.id.txtStatus);
        txtAge  = view.findViewById(R.id.txtAge);
        txtGender  =  view.findViewById(R.id.txtGender);
        txtEmail  =  view.findViewById(R.id.txtEmail);
        imgPP =  view.findViewById(R.id.invitePP);
        lblUsernameClick =  view.findViewById(R.id.lblUsernameClick);
        lblStatusClick = view.findViewById(R.id.lblStatusClick);

        btnUploadPP = view.findViewById(R.id.btnUploadPP);

        lblChangePassword = view.findViewById(R.id.lblChangePassword);
        lblCalendar =  view.findViewById(R.id.lblCalendar);
        //show image progress bar


        //hide upload button
        btnUploadPP.setVisibility(View.INVISIBLE);

        //get things from firebase db
        ref = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference userTable = ref.child("User").child(firebaseAuth.getCurrentUser().getUid());
        userTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);

                Log.d("USERS",dataSnapshot.toString());

                    txtUsername.setText(user.surname);
                    txtResName.setText(user.name);
                    txtStatus.setText(String.valueOf(user.id_no));
                    txtAge.setText(user.address);
                    txtEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    txtGender.setText(user.gender);

                Glide.with(getContext())
                        .load(user.pp)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgPP);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;


    }

}
