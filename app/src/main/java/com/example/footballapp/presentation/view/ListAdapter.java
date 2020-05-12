package com.example.footballapp.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.footballapp.R;
import com.example.footballapp.presentation.model.psgTeam;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<psgTeam> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(psgTeam item);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeader;
        TextView txtFooter;
        ImageView image;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            image= (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, psgTeam item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<psgTeam> myDataset, OnItemClickListener listener) {
        this.values =myDataset;
        this.listener = listener;
    }



    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType
    ) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final psgTeam currentpsgTeam = values.get(position);
        holder.txtHeader.setText(currentpsgTeam.getName());
        holder.txtFooter.setText(currentpsgTeam.getNationality());
        Picasso.get().load(currentpsgTeam.getUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentpsgTeam);
            }
        });


    }

        @Override
        public int getItemCount () {
            return values.size();
        }

    }

