package com.huutrung.exercisesqlite;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 10/22/2017.
 */


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private List<Contact> contacts;
    private MainActivity context;

    public ContactAdapter(List<Contact> contacts, MainActivity context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item_contact, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.mTvName.setText(contact.getName());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        ImageView mImgDelete, mImgEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mImgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
            mImgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            mImgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contact contact=contacts.get(getAdapterPosition());
                    context.diaglogDelete(contact.getName(), contact.getId());
                }
            });
        }
    }

}