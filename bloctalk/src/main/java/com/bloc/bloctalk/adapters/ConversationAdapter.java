package com.bloc.bloctalk.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.bloc.bloctalk.R;

/**
 * Created by Daniel on 12/11/2014.
 */
public class ConversationAdapter extends SimpleCursorAdapter {

    private static final String TAG = ".ConversationAdapter.java";

    // The columns in our cursor from our projection
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_THREAD_ID = 1;
    private static final int COLUMN_TYPE = 2;
    private static final int COLUMN_BODY = 3;
    private static final int COLUMN_DATE = 4;

    // values of the Telephony.Sms.Type constants
    private static final int TYPE_INBOX = 1;
    private static final int TYPE_SENT = 2;

    public ConversationAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }

    public ConversationAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        View cView = convertView;

        if (cView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cView = inflater.inflate(R.layout.conversation_row, parent, false);
        }

        Cursor cursor = getCursor();
        cursor.moveToPosition(position);

        TextView bodyTV = (TextView) cView.findViewById(R.id.tv_conversation_body);
        bodyTV.setText(cursor.getString(COLUMN_BODY));

        return cView;
    }
}
