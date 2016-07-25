package com.example.guest.gobal.adapters;

import android.app.Activity;

import com.example.guest.gobal.ui.RowItem;

/**
 * Created by tomjones on 7/25/16.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItem;
}

private class ViewHolder {
    ImageView icon;
    TextView title;
}


@Override public getView(int position, View convertView, ViewGroup parent) {

    ViewHolder holder = null;

    LayoutInflater mInflater = (LayoutInflater) context
            .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
        convertView = mInflater.inflate(R.layout.list_item, null);
        holder = new ViewHolder();
        holder.icon = (ImageView) convertView.findViewById.(R.id.icon);
        holder.title = (TextView) convertView.findViewById(R.id.title);

        RowItem row_pos = rowItem.get(position);
    }


}