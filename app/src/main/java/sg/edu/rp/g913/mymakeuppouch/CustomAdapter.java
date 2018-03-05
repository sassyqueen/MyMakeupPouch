package sg.edu.rp.g913.mymakeuppouch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static sg.edu.rp.g913.mymakeuppouch.R.styleable.View;

/**
 * Created by 15004557 on 30/10/2016.
 */

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Makeup> makeupList;

    public CustomAdapter(Context context, int resource, ArrayList<Makeup> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        makeupList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //Obtain layoutinflater obj
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate a new view hierarchy from specified xml resource (layout_id) & return root view of inflated hierarchy
        View rowView = inflater.inflate(layout_id, parent, false);
        //return view corresponding to the data at the specified position

        TextView title = (TextView)rowView.findViewById(R.id.textViewTitle);
        ImageView ivMakeup = (ImageView)rowView.findViewById(R.id.imageViewMakeup);
        TextView desc = (TextView) rowView.findViewById(R.id.textViewDescription);

        Makeup currentItem = makeupList.get(position);
        title.setText(currentItem.makeupGroup);
        desc.setText(currentItem.description);

        if (currentItem.selectedCategory.equalsIgnoreCase("001")){
            ivMakeup.setImageResource(R.drawable.lipstick);
        }
        else if (currentItem.selectedCategory.equalsIgnoreCase("002")){
            ivMakeup.setImageResource(R.drawable.foundation);
        }
        else if (currentItem.selectedCategory.equalsIgnoreCase("003")){
            ivMakeup.setImageResource(R.drawable.eyemakeup);
        }
        else if (currentItem.selectedCategory.equalsIgnoreCase("004")){
            ivMakeup.setImageResource(R.drawable.skincare);
        }
        else if  (currentItem.selectedCategory.equalsIgnoreCase("005")){
            ivMakeup.setImageResource(R.drawable.tools);
        }
        return rowView;


    }



}
