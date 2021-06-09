package com.example.dangnhap;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserRecyler extends BaseAdapter {

    private Context context;
    private ArrayList<User> arrayList;
    private int layout;

    public UserRecyler(Context context, ArrayList<User> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        TextView id =view.findViewById(R.id.Stt);
        TextView name = view.findViewById(R.id.ten);
        TextView age = view.findViewById(R.id.age);
        TextView dep = view.findViewById(R.id.dep);


        id.setText(arrayList.get(position).getId()+"");
        name.setText(arrayList.get(position).getName());
        age.setText(arrayList.get(position).getAge()+"");
        dep.setText(arrayList.get(position).getDep());
        return view;
    }
}
