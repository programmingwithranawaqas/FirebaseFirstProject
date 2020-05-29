package com.example.firebasefirstproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends FirebaseRecyclerAdapter<Contact, ContactAdapter.ContactViewHolder> {


    public ContactAdapter(@NonNull FirebaseRecyclerOptions<Contact> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull Contact model) {
        holder.tvContactName.setText(model.getUsername());
        holder.tvContactNumber.setText(model.getContact());
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvContactName, tvContactNumber;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
        }
    }
}
