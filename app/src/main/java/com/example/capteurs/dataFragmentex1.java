package com.example.capteurs;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class dataFragmentex1 extends Fragment {

    RecyclerView recyclerView;
    ArrayList<datamodelex1> dataholder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_fragmentex1, container, false);
        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        datamodelex1 pays1 = new datamodelex1(R.drawable.france, "France", "La France, pays d'Europe de l'Ouest, est célèbre pour sa culture, sa gastronomie et ses paysages variés.");
        datamodelex1 pays2 = new datamodelex1(R.drawable.espagne, "Espagne", "L'Espagne, située dans la péninsule ibérique, est connue pour ses plages ensoleillées, sa culture vibrante et son patrimoine historique riche.");
        datamodelex1 pays3 = new datamodelex1(R.drawable.portugal, "Portugal", "Le Portugal, situé dans la péninsule ibérique, est réputé pour ses villes historiques, ses plages et sa gastronomie.");
        datamodelex1 pays4 = new datamodelex1(R.drawable.morroco, "Maroc", "Le Maroc, situé en Afrique du Nord, est réputé pour ses villes impériales, ses souks animés et ses paysages désertiques.");
        datamodelex1 pays5 = new datamodelex1(R.drawable.algerie, "Algerie", "L'Algérie, le plus grand pays d'Afrique, est célèbre pour son désert du Sahara, son histoire riche et sa culture berbère.");
        datamodelex1 pays6 = new datamodelex1(R.drawable.liban, "Liban", "Le Liban, situé au Moyen-Orient, est connu pour sa culture méditerranéenne, ses montagnes et son histoire millénaire.");
        datamodelex1 pays7 = new datamodelex1(R.drawable.tunisie, "Tunisie", "La Tunisie, située en Afrique du Nord, est célèbre pour ses plages méditerranéennes, son histoire ancienne et sa culture arabo-berbère.");
        dataholder.add(pays1);
        dataholder.add(pays2);
        dataholder.add(pays3);
        dataholder.add(pays4);
        dataholder.add(pays5);
        dataholder.add(pays6);
        dataholder.add(pays7);
        Adapterex1frag adapter = new Adapterex1frag(dataholder);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Adapterex1frag.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                datamodelex1 selectedCountry = dataholder.get(position);
                Intent intent = new Intent(getActivity(), CountryDetailActivity.class);
                intent.putExtra("image", selectedCountry.getImage());
                intent.putExtra("name", selectedCountry.getTop());
                intent.putExtra("description", selectedCountry.getBottom());
                startActivity(intent);
            }
        });

        return view;
    }
}