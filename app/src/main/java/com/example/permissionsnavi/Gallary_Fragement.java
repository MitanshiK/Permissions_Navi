package com.example.permissionsnavi;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Gallary_Fragement extends Fragment {

    View view;
    ImageView glryImg;
    Uri selectedImgUri;

    public Gallary_Fragement(Uri selectedImageUri) {
        this.selectedImgUri=selectedImageUri;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gallary__fragement, container, false);
        initial();
        return view;
    }

    private void initial() {
        glryImg=view.findViewById(R.id.gallaryImg);
        glryImg.setImageURI(selectedImgUri);
    }
}