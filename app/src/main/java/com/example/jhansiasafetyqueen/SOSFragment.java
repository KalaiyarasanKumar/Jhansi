package com.example.jhansiasafetyqueen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SOSFragment extends Fragment {


    CardView b1,b2,b3,b4,b5,b6,b7,b8,b9;


    public SOSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_s_o_s, container, false);

        b1 = v.findViewById(R.id.nationalemergency);
        b2 = v.findViewById(R.id.police);
        b3 = v.findViewById(R.id.fire);
        b4 = v.findViewById(R.id.ambulence);
        b5 = v.findViewById(R.id.womenhelpline);
        b6 = v.findViewById(R.id.lpg);
        b7 = v.findViewById(R.id.railway);
        b8 = v.findViewById(R.id.pregnancy);
        b9 = v.findViewById(R.id.road);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling National Helpline No.", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Police Helpline No", Toast.LENGTH_SHORT).show();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:101"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Fire Helpline No", Toast.LENGTH_SHORT).show();
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Ambulence Helpline No", Toast.LENGTH_SHORT).show();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1091"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Women Helpline No", Toast.LENGTH_SHORT).show();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1906"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling LPG Helpline No", Toast.LENGTH_SHORT).show();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:139"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Railway Helpline No", Toast.LENGTH_SHORT).show();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:102"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Pregnancy Helpline No", Toast.LENGTH_SHORT).show();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1073"));
                startActivity(intent);
                Toast.makeText(getContext(), "Calling Road accident  Helpline No", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }
}