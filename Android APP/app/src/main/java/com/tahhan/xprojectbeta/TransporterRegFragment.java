package com.tahhan.xprojectbeta;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingLayout;

import java.io.IOException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.tahhan.xprojectbeta.view.ui.login.RegisterFragment.isEmpty;


public class TransporterRegFragment extends Fragment {

    private static final String TAG = "TransporterRegFragment";
    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView frontIDImage;
    private ImageView backIDImage;
    private ImageView frontDriverLicenseImage;
    private ImageView backDriverLicenseImage;
    private ImageView frontVehicleLicenseImage;
    private ImageView backVehicleLicenseImage;
    private Uri filePath;
    private Bitmap bitmap;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    //SSN
    private FloatingActionButton frontIDFab;
    private FloatingActionButton backIDFab;
    //Driver License
    private FloatingActionButton frontDriverLicenseFab;
    private FloatingActionButton backDriverLicenseFab;
    //Vehicle License
    private FloatingActionButton frontVehicleLicenseFab;
    private FloatingActionButton backVehicleLicenseFab;
    private String whichButtonIsClicked;

    //camera fabs
    //SSN
    private FloatingActionButton frontIDCameraFab;
    private FloatingActionButton backIDCameraFab;
    //Driver License
    private FloatingActionButton frontDriverLicenseCameraFab;
    private FloatingActionButton backDriverLicenseCameraFab;
    //Vehicle License
    private FloatingActionButton frontVehicleLicenseCameraFab;
    private FloatingActionButton backVehicleLicenseCameraFab;
    // Button TAGS
    private static final String FRONT_SSN_TAG = "f_ssn";
    private static final String BACK_SSN_TAG = "b_ssn";
    private static final String FRONT_DRIVER_TAG = "f_driver";
    private static final String BACK_DRIVER_TAG = "b_driver";
    private static final String FRONT_LICENSE_TAG = "f_vehicle";
    private static final String BACK_LICENSE_TAG = "b_vehicle";
    private FirebaseAuth mAuth;
    private EditText mailET;
    private Button registerButton ;
    private EditText passwordET;
    private EditText passwordConfirmET;
    ImageView imageView2 ;

//    // floating Layouts
//    private FloatingLayout ssnFrontLayout;
//    private FloatingLayout ssnBackLayout;
//    private FloatingLayout driverFrontLayout;
//    private FloatingLayout driverBackLayout;
//    private FloatingLayout vehicleFrontLayout;
//    private FloatingLayout vehicleBackLayout;


    public TransporterRegFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trasporter_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
        frontIDFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_SSN_TAG;
            chooseImage();
        });

        backIDFab.setOnClickListener(v -> {
            whichButtonIsClicked = BACK_SSN_TAG;
            chooseImage();
        });

        frontDriverLicenseFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_DRIVER_TAG;
            chooseImage();
        });

        backDriverLicenseFab.setOnClickListener(v -> {
            whichButtonIsClicked = BACK_DRIVER_TAG;
            chooseImage();
        });

        frontVehicleLicenseFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_LICENSE_TAG;
            chooseImage();
        });

        backVehicleLicenseFab.setOnClickListener(v -> {
            whichButtonIsClicked = BACK_LICENSE_TAG;
            chooseImage();
        });

        frontIDCameraFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_SSN_TAG;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                takeAPic();
            }
        });
                backIDCameraFab.setOnClickListener(v -> {
                    whichButtonIsClicked = BACK_SSN_TAG;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        takeAPic();
                    }
                });
        frontDriverLicenseCameraFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_DRIVER_TAG;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                takeAPic();
            }
        });
                backDriverLicenseCameraFab.setOnClickListener(v -> {
                    whichButtonIsClicked = BACK_DRIVER_TAG;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        takeAPic();
                    }
                });
        frontVehicleLicenseCameraFab.setOnClickListener(v -> {
            whichButtonIsClicked = FRONT_LICENSE_TAG;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                takeAPic();
            }
        });
                backVehicleLicenseCameraFab.setOnClickListener(v -> {
                    whichButtonIsClicked = BACK_LICENSE_TAG;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        takeAPic();
                    }
                });
        registerButton.setOnClickListener(v -> {
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


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void takeAPic() {
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }


    private void loadViews(View view) {
        //Fabs
        frontIDFab = view.findViewById(R.id.ssn_front_gallery_id);
        backIDFab = view.findViewById(R.id.ssn_back_gallery_id);
        frontDriverLicenseFab = view.findViewById(R.id.driver_front_gallery_id);
        backDriverLicenseFab = view.findViewById(R.id.driver_back_gallery_id);
        frontVehicleLicenseFab = view.findViewById(R.id.vehicle_front_gallery_id);
        backVehicleLicenseFab = view.findViewById(R.id.vehicle_back_galley_id);
        //images
        frontIDImage = view.findViewById(R.id.front_ssn_imageID);
        backIDImage = view.findViewById(R.id.back_ssn_imageID);
        frontDriverLicenseImage = view.findViewById(R.id.front_driver_imageID);
        backDriverLicenseImage = view.findViewById(R.id.back_driver__imageID);
        frontVehicleLicenseImage = view.findViewById(R.id.front_vehicle__imageID);
        backVehicleLicenseImage = view.findViewById(R.id.back_vehicle__imageID);

        // camera fabs
        frontIDCameraFab = view.findViewById(R.id.ssn_front_camera_id);
        backIDCameraFab = view.findViewById(R.id.ssn_back_camera_id);
        frontDriverLicenseCameraFab = view.findViewById(R.id.driver_front_camera_id);
        backDriverLicenseCameraFab = view.findViewById(R.id.driver_back_camera_id);
        frontVehicleLicenseCameraFab = view.findViewById(R.id.vehicle_front_camera_id);
        backVehicleLicenseCameraFab = view.findViewById(R.id.vehicle_back_camera_id);
        mAuth = FirebaseAuth.getInstance();
        mailET = view.findViewById(R.id.txte_mail);
        passwordET = view.findViewById(R.id.txtpass);
        passwordConfirmET = view.findViewById(R.id.txtconfirmpass);
        registerButton = view.findViewById(R.id.signUpTransporterBtnID);
//        // floating layouts
//        ssnFrontLayout = view.findViewById(R.id.ssn_floating_layout);
//        ssnBackLayout = view.findViewById(R.id.back_ssn_floating_layout);
//        driverFrontLayout = view.findViewById(R.id.driver_floating_layout);
//        driverBackLayout = view.findViewById(R.id.back_driver_floating_layout);
//        vehicleFrontLayout = view.findViewById(R.id.vehicle_floating_layout);
//        vehicleBackLayout = view.findViewById(R.id.back_floating_layout);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), filePath);
                setImage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            setImage();

        }
    }

    private void setImage() {
        switch (whichButtonIsClicked) {
            case FRONT_SSN_TAG:
                frontIDImage.setImageBitmap(bitmap);
                break;
            case BACK_SSN_TAG:
                backIDImage.setImageBitmap(bitmap);
                break;
            case FRONT_DRIVER_TAG:
                frontDriverLicenseImage.setImageBitmap(bitmap);
                break;
            case BACK_DRIVER_TAG:
                backDriverLicenseImage.setImageBitmap(bitmap);
                break;
            case FRONT_LICENSE_TAG:
                frontVehicleLicenseImage.setImageBitmap(bitmap);
                break;
            case BACK_LICENSE_TAG:
                backVehicleLicenseImage.setImageBitmap(bitmap);
                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
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
}
