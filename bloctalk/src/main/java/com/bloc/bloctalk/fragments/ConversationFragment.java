package com.bloc.bloctalk.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.bloc.bloctalk.R;

/**
 * Created by Daniel on 12/10/2014.
 */
public class ConversationFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ".ConversationFragment.java";
    private static final String KEY_THREAD_ID =
            "com.bloc.bloctalk.fragments.ConversationFragment.key_thread_id";

    String mThreadId;
    SimpleCursorAdapter mAdapter;

    public static ConversationFragment newInstance(String threadId) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putString(KEY_THREAD_ID, threadId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mThreadId = getArguments().getString(KEY_THREAD_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.lv_conversation);

        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.conversation_row, null,
                new String[] {Telephony.Sms.BODY}, new int[] {R.id.tv_conversation_body},0);

        lv.setAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        return rootView;
    }


    static final String[] CONVERSATION_PROJECTION = new String[] {
            Telephony.Sms._ID,
            Telephony.Sms.THREAD_ID,
            Telephony.Sms.BODY
    };

    // Loader callbacks

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Telephony.Sms.CONTENT_URI;
        String selection = "thread_id = ?";
        String[] selectionArgs = new String[] {mThreadId};

        return new CursorLoader(getActivity(), uri, CONVERSATION_PROJECTION, selection,
                selectionArgs, null);
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
