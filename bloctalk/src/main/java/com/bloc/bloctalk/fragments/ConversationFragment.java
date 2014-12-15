package com.bloc.bloctalk.fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bloc.bloctalk.R;
import com.bloc.bloctalk.adapters.ConversationAdapter;

/**
 * Created by Daniel on 12/10/2014.
 */
public class ConversationFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ".ConversationFragment.java";
    private static final String KEY_THREAD_ID =
            "com.bloc.bloctalk.fragments.ConversationFragment.key_thread_id";
    private static final String KEY_ADDRESS =
            "com.bloc.bloctalk.fragments.ConversationFragment.key_address";

    String mThreadId;
    String mAddress;

    ConversationAdapter mAdapter;

    public static ConversationFragment newInstance(String threadId, String address) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putString(KEY_THREAD_ID, threadId);
        args.putString(KEY_ADDRESS, address);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mThreadId = getArguments().getString(KEY_THREAD_ID);
        mAddress = getArguments().getString(KEY_ADDRESS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);

        ListView lv = (ListView) rootView.findViewById(R.id.lv_conversation);

        mAdapter = new ConversationAdapter(getActivity(), R.layout.conversation_row, null,
                new String[] {}, new int[] {}, 0); // empty to and from as I handle that in adapter

        lv.setAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        final EditText composeText = (EditText) rootView.findViewById(R.id.et_convorsation_compose);
        composeText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mAddress, null, composeText.getText().toString(),
                            null, null);
                    composeText.getText().clear();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }


    static final String[] CONVERSATION_PROJECTION = new String[] {
            Telephony.Sms._ID,
            Telephony.Sms.THREAD_ID,
            Telephony.Sms.TYPE,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE,
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
