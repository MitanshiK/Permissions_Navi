package com.example.permissionsnavi;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class main_fragement extends Fragment {
    private static final int CONTACT_PERMISSION_REQUEST_CODE = 1;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1888;
    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    //  Uri selectedImageUri;


    View view;
    Button permissions, storage, camera, gallary, contacts;
    ImageView demoImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_fragement, container, false);
        initial();
        return view;
    }

    private void initial() {
        //    NavController navController=new NavController(getContext());
        permissions = view.findViewById(R.id.permissions);
        storage = view.findViewById(R.id.storage);
        camera = view.findViewById(R.id.camera);
        gallary = view.findViewById(R.id.gallary);
        contacts = view.findViewById(R.id.contacts);

        demoImg = view.findViewById(R.id.demoImg);
        click();
    }

    private void click() {

        permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate((R.id.action_main_fragement3_to_permission_Fragment4));
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, CAMERA_PERMISSION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION);
                }
                // Navigation.findNavController(view).navigate((R.id.action_main_fragement3_to_camera_Fragement3));
            }
        });

        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent opengalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(opengalIntent, REQUEST_CODE_PICK_IMAGE);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                }
                // Navigation.findNavController(view).navigate((R.id.action_main_fragement3_to_gallary_Fragement4));
            }
        });

        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                }
                //   Navigation.findNavController(view).navigate((R.id.action_main_fragement3_to_storage_fragement2));
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//                    Intent opengalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(opengalIntent, REQUEST_CODE_PICK_IMAGE);
//                } else {
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
//                }
             // Navigation.findNavController(view).navigate((R.id.action_main_fragement3_to_contacts_fragement));
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==CAMERA_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri capturedImageUri = data.getData();
                demoImg.setImageURI(capturedImageUri);
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                demoImg.setImageBitmap(photo);
//                Uri tempUri = getImageUri(requireContext(), photo);
//                demoImg.setImageURI(tempUri);
            }
        }

       else if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                demoImg.setImageURI(selectedImageUri);
               // openGalFrag(selectedImageUri);
            }
        }

    }

//    private Uri getImageUri(Context context, Bitmap bitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
//        return Uri.parse(path);
//    }

    private void openGalFrag(Uri selectedImageUri) {
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.gallary_Fragement4);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView3, new Gallary_Fragement(selectedImageUri)).addToBackStack(null).commit();
    }


}