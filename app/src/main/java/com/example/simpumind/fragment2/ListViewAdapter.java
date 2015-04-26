package com.example.simpumind.fragment2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by simpumind on 4/19/15.
 */
public class ListViewAdapter extends ArrayAdapter<Category>{

    private Context context;
    private ArrayList<Category> data;
    private static LayoutInflater layoutInflater;
    private int layout;

    public ListViewAdapter(Context context,int layout, ArrayList<Category> data){
        super(context,layout,data);
        this.context = context;
        this.data = data;
        this.layout = layout;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if(convertView  == null){

            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = layoutInflater.inflate(this.layout, null);
            holder = new ViewHolder();
            vi.setTag(holder);
        }else{
            holder = (ViewHolder)vi.getTag();
        }
        holder.guest_goals = (TextView)vi.findViewById(R.id.guest_goals);
        holder.guest_team = (TextView)vi.findViewById(R.id.guest_team);
        holder.host_goals = (TextView)vi.findViewById(R.id.host_goals);
        holder.host_team = (TextView)vi.findViewById(R.id.host_team);
        holder.status = (TextView)vi.findViewById(R.id.status);

        Category c = getItem(position);
        holder.guest_team.setText(c.getMatches().getVistorTeam().getName().toString());
        holder.guest_goals.setText(c.getMatches().getVistorTeam().getGoal().toString());
        holder.host_team.setText(c.getMatches().getLocalTeam().getName().toString());
        holder.host_goals.setText(c.getMatches().getLocalTeam().getGoal().toString());
        holder.status.setText(c.getMatches().getStatus().toString());

        return vi;
    }

    public class ViewHolder{
        public TextView host_team;
        public TextView host_goals;
        public TextView guest_team;
        public TextView guest_goals;
        public TextView status;
    }
}
