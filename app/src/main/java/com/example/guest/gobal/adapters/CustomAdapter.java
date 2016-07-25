package com.example.guest.gobal.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.gobal.ui.RowItem;

import java.util.List;

/**
 * Created by tomjones on 7/25/16.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItem;


    public CustomAdapter(Context context, List<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }


    private class ViewHolder {
        ImageView icon;
        TextView title;
    }


    public CustomAdapter(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_main, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);

            RowItem row_pos = rowItem.get(position);
//        setting the img resource file
            holder.icon.setImageResource(row_pos.getIcon());
            holder.title.setText(row_pos.getTitle());
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;

    }

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }


}