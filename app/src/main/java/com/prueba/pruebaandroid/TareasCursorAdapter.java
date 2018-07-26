package com.prueba.pruebaandroid;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TareasCursorAdapter extends CursorAdapter {
    public TareasCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_tarea, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {


        TextView nameText = (TextView) view.findViewById(R.id.tv_name);

        String name = cursor.getString(cursor.getColumnIndex(TareaDet.TareaEntry.NOMBRE));

        nameText.setText(name);


    }

}
