package com.example.capteurs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailFragment extends Fragment {

    private static final String ARG_IMAGE = "image";
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";

    private int image;
    private String name;
    private String description;

    public static CountryDetailFragment newInstance(int image, String name, String description) {
        CountryDetailFragment fragment = new CountryDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, image);
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt(ARG_IMAGE);
            name = getArguments().getString(ARG_NAME);
            description = getArguments().getString(ARG_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_detail, container, false);
        ImageView imageView = view.findViewById(R.id.country_image);
        TextView nameTextView = view.findViewById(R.id.country_name);
        TextView descriptionTextView = view.findViewById(R.id.country_description);
        imageView.setImageResource(image);
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        return view;
    }
}
