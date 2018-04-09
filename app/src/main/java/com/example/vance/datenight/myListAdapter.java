package com.example.vance.datenight;

/**
 * Created by Vance on 12/7/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class myListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Location> list = new ArrayList<Location>();
    private Context context;
    private int idNum;
    private int vID;
    private String make;



    public myListAdapter(ArrayList<Location> list, Context context, String make) {
        this.list = list;
        this.context = context;
        this.make=make;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_layout, null);
        }
        idNum = list.get(position).getId();
        vID=list.get(position).getId();

        final TextView idtext=(TextView)view.findViewById(R.id.idTextView);
        idtext.setText(String.valueOf(list.get(position).getId()));

        //Handle TextView and display string from your list
        TextView name = (TextView)view.findViewById(R.id.nameTextView);
        name.setText(list.get(position).getName());



        //Handle buttons and add onClickListeners
        Button edit = (Button)view.findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Intent i = new Intent(context, ViewLocationActivity.class);
                String temp =list.get(position).getName() + "\n" + list.get(position).getAddress() + "\n" + list.get(position).getPhone() + "\n" + list.get(position).getUrl();
                i.putExtra("data", temp);
                context.startActivity(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}