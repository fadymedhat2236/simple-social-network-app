package com.example.fady.socialnetwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fady on 5/15/2018.
 */

public class UserFieldAdapter extends ArrayAdapter<userField> {
    public UserFieldAdapter(Activity context, ArrayList<userField> fields)
    {
        super(context,0,fields);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        userField currentField=getItem(position);
        View listItemView=convertView;

        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.field_item,parent,false);
        }

        TextView name=listItemView.findViewById(R.id.name_);
        name.setText(currentField.getName());

        TextView property=listItemView.findViewById(R.id.property);
        property.setText(currentField.getProperty());

        TextView rank=listItemView.findViewById(R.id.rank);
        rank.setText(Integer.toString(currentField.getRank()));

        return listItemView;
    }
}
