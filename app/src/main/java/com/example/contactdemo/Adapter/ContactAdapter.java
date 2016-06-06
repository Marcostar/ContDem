package com.example.contactdemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactdemo.Model.Contacts;
import com.example.contactdemo.R;

import java.util.ArrayList;

/**
 * Created by Dzeko on 6/6/2016.
 */
public class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contacts> ContactModel;

    public ContactAdapter(Context context, ArrayList<Contacts> contactModel) {
        this.context = context;
        ContactModel = contactModel;
    }

    @Override
    public int getCount() {
        return ContactModel.size();
    }

    @Override
    public Object getItem(int position) {
        return ContactModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_row,null);
        }

        TextView FirstName = (TextView) convertView.findViewById(R.id.FirstName);
        TextView LastName = (TextView) convertView.findViewById(R.id.LastName);

        FirstName.setText(ContactModel.get(position).getFirstName());
        LastName.setText(ContactModel.get(position).getLastName());

        return convertView;
    }
}
