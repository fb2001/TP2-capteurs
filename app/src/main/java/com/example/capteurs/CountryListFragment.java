package com.example.capteurs;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class CountryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<datamodelex1> dataholder;
    private OnCountrySelectedListener callback;

    public interface OnCountrySelectedListener {
        void onCountrySelected(int image, String name, String description);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCountrySelectedListener) {
            callback = (OnCountrySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCountrySelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataholder = new ArrayList<>();
        dataholder.add(new datamodelex1(R.drawable.france, "France", "Culture, gastronomie et paysages variés."));
        dataholder.add(new datamodelex1(R.drawable.espagne, "Espagne", "Plages ensoleillées et culture vibrante."));
        dataholder.add(new datamodelex1(R.drawable.portugal, "Portugal", "Villes historiques et gastronomie."));
        dataholder.add(new datamodelex1(R.drawable.morroco, "Maroc", "Villes impériales et souks animés."));
        dataholder.add(new datamodelex1(R.drawable.algerie, "Algerie", "Désert du Sahara et culture berbère."));
        dataholder.add(new datamodelex1(R.drawable.liban, "Liban", "Culture méditerranéenne et histoire."));
        dataholder.add(new datamodelex1(R.drawable.tunisie, "Tunisie", "Plages méditerranéennes et culture arabo-berbère."));

        Adapterex1frag adapter = new Adapterex1frag(dataholder);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            datamodelex1 selectedCountry = dataholder.get(position);
            if (callback != null) {
                callback.onCountrySelected(selectedCountry.getImage(), selectedCountry.getTop(), selectedCountry.getBottom());
            }
        });

        return view;
    }
}
