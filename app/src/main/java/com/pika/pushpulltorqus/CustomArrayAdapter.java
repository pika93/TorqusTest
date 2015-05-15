package com.pika.pushpulltorqus;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * ArrayAdapter with overriden getItem to make ListView behave like a Stack
 *
 * Created by Pika on 5/15/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter{
    public CustomArrayAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(super.getCount() - position - 1);
    }
}
