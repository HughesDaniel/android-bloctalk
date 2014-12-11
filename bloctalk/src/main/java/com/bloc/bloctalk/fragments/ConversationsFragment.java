package com.bloc.bloctalk.fragments;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.bloc.bloctalk.ConversationActivity;
import com.bloc.bloctalk.R;

/**
 * Created by Daniel on 12/1/2014.
 */
public class ConversationsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ".ConversationListFragment.java";

    SimpleCursorAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_2, null,
                new String[] {Telephony.Sms.Conversations.ADDRESS, Telephony.Sms.Conversations.SNIPPET},
                new int[] {android.R.id.text1, android.R.id.text2}, 0);

        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // we get the cursor and retrieve the thread_id of the convo clicked
        Cursor c = ((SimpleCursorAdapter)l.getAdapter()).getCursor();
        c.moveToPosition(position);
        String threadId = c.getString(3);
        // Intent that starts new activity to display the clicked on conversation
        Intent i = new Intent(getActivity(), ConversationActivity.class);
        i.putExtra(getString(R.string.EXTRA_THREAD_ID), threadId);
        startActivity(i);


    }

    // the " as " are because there is a bug in Telephony.Sms.Coversations projection map
    static final String[] CONVERSATIONS_PROJECTION = new String[] {
            Telephony.Sms.Conversations._ID + " as " + Telephony.Sms.Conversations._ID,
            Telephony.Sms.Conversations.ADDRESS + " as " + Telephony.Sms.Conversations.ADDRESS,
            Telephony.Sms.Conversations.SNIPPET,
            Telephony.Sms.Conversations.THREAD_ID
    };

    // Loader callback methods

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Telephony.Sms.Conversations.CONTENT_URI;

        return new CursorLoader(getActivity(), uri, CONVERSATIONS_PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
