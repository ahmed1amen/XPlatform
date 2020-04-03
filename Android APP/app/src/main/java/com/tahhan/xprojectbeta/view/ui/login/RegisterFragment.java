package com.tahhan.xprojectbeta.view.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tahhan.xprojectbeta.R;
import com.tahhan.xprojectbeta.TransporterRegFragment;

import java.util.Objects;

import static com.tahhan.xprojectbeta.view.MainActivity.TRANSPORTER_REG_FRAGMENT_TAG;


public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private FirebaseAuth mAuth;
    Button registerBtn;
    EditText mailET;
    EditText passwordET;
    EditText passwordConfirmET;
    Button btnlogface;
    Button btnloggoogle;
    private Button registerAsTransporter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize Firebase Auth
        registerBtn = view.findViewById(R.id.signUpBtn);
        btnlogface = view.findViewById(R.id.btnlogface);
        btnloggoogle = view.findViewById(R.id.btnloggoogle);
        mailET = view.findViewById(R.id.EmailET);
        passwordET = view.findViewById(R.id.register_password);
        passwordConfirmET = view.findViewById(R.id.passwordConfirm);
        registerAsTransporter = view.findViewById(R.id.signUpTransporterBtn);
        mAuth = FirebaseAuth.getInstance();

        registerAsTransporter.setOnClickListener(v -> loadRegAsTransporterFragment());
        registerBtn.setOnClickListener(v -> {
            Log.d(TAG, "onClick: attempting to register.");
            //check for null valued EditText fields
            if (!isEmpty(mailET.getText().toString())
                    && !isEmpty(passwordET.getText().toString())
                    && !isEmpty(passwordConfirmET.getText().toString())) {
                //check if passwords match
                if (passwordET.getText().toString().equals(passwordConfirmET.getText().toString())) {
                    //Initiate registration task
                    register(mailET.getText().toString(), passwordET.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "Passwords do not Match", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getActivity(), "You must fill out all the fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getActivity(), "Authentication succssessful.", Toast.LENGTH_SHORT)
                                    .show();
                            //   FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public static boolean isEmpty(String string) {
        return string.equals("");
    }

    private void loadRegAsTransporterFragment() {
        TransporterRegFragment tr = new TransporterRegFragment();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, tr, TRANSPORTER_REG_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }


}