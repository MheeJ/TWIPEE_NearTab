package com.example.neartab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NearAdapter extends BaseAdapter {
    private ArrayList<NearDTO>listNear=new ArrayList<>();
    @Override
    public int getCount() { return listNear.size(); }
    @Override
    public Object getItem(int i) { return listNear.get(i); }
    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        NearViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearitem , null , false);

            holder = new NearViewHolder();
            holder.imageViewnear = (ImageView) convertView.findViewById(R.id.imageView_near);
            holder.textposition = (TextView) convertView.findViewById(R.id.textposition);
            holder.nearimage = (ImageView) convertView.findViewById(R.id.nearimage);

            convertView.setTag(holder);
        } else{
            holder = (NearViewHolder) convertView.getTag();
        }
        NearDTO dto=listNear.get(position);
        holder.imageViewnear.setImageResource(dto.getResId_near());
        holder.textposition.setText(dto.getPosition());
        holder.nearimage.setImageResource(dto.getResld_position());
        return convertView;
    }
    public void addItem(NearDTO dto){listNear.add(dto);}
    class NearViewHolder {
        ImageView imageViewnear;
        TextView textposition;
        ImageView nearimage;

    }
}
