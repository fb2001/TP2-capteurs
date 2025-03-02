package com.example.capteurs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapterex1frag extends RecyclerView.Adapter<Adapterex1frag.viewholderex1frag> {

    ArrayList<datamodelex1> dataholder;
    private OnItemClickListener listener;

    public interface OnItemClickListener { void onClick(int position);}

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Adapterex1frag(ArrayList<datamodelex1> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public viewholderex1frag onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.designe_une_ligne_ex1, parent, false);
        return new viewholderex1frag(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderex1frag holder, int position) {
        datamodelex1 model = dataholder.get(position);
        holder.img.setImageResource(model.getImage());
        holder.top.setText(model.getTop());
        holder.bottom.setText(model.getBottom());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class viewholderex1frag extends RecyclerView.ViewHolder {
        ImageView img;
        TextView top, bottom;

        public viewholderex1frag(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            top = itemView.findViewById(R.id.text1);
            bottom = itemView.findViewById(R.id.text2);
        }
    }
}