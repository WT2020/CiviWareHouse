package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.bean.OutThing;

import java.util.List;

/**
 * 项目名称：WarehouseDroid
 * 创建人:吴小雪
 * 创建时间:2017/12/25  11:48
 */

public class AddOutThingAdapter extends BaseAdapter {
    private Context context;
    List<OutThing> list;
    private Boolean isIn;

    public void setObjects(List<OutThing> objects){
        this.list = objects;
        this.notifyDataSetChanged();
    }

    public AddOutThingAdapter(Context context, List<OutThing>list,Boolean isIn) {
        this.context = context;
        this.list  = list;
        this.isIn = isIn;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int position, View convertView,  ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        OutThing thing = list.get(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.addthingitem, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ThingName = view.findViewById(R.id.thing_name);
            viewHolder.ThingNumber = view.findViewById(R.id.thing_number);
            //viewHolder.Numder = view.findViewById(R.id.number);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(thing!=null){
            viewHolder.ThingName.setText(thing.getName());
            viewHolder.ThingNumber.setText(thing.getRfidCode());
            //viewHolder.Numder.setText(String.valueOf(thing.getNum()));
        }
       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InOutThingAddLookUpNew1Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thing",list.get(position));
                bundle.putBoolean("flag",isIn);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });*/
        return view;
    }
    class ViewHolder{
        TextView ThingNumber;
        TextView ThingName;
        //TextView Numder;
    }
}
