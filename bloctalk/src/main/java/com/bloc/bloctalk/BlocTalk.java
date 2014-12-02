package com.bloc.bloctalk;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class BlocTalk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_talk);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bloc_talk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final String TAG = "WWWWWWWWWWWWWWWWWWWW";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bloc_talk, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.listView);

            // Create URI
            Uri uri = Telephony.Sms.Conversations.CONTENT_URI;

            // projection
            String[] projection = new String[] { Telephony.Sms.Conversations.ADDRESS,
                                Telephony.Sms.Conversations.SNIPPET};

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getActivity().getContentResolver();

            // selection
            String selection = "address = ?";

            // selection args
            String[] args = {"0006"};

            //
            Cursor c = cr.query(uri, // uri
                    projection, // projection
                    null, // selection
                    null, // args
                    null // sort order
            );

            int total = c.getCount();

/*            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), // context
                    android.R.layout.simple_list_item_2, // layout
                    c, // cursor
                    new String[] {"address", "snippet"}, // from
                    new int[] {android.R.id.text2, android.R.id.text1}, // to
                    0 // flags
            );

            listView.setAdapter(adapter)*/;


            if (c.moveToFirst()) {
                for (int i = 0; i < total; i++) {
                    Log.d(TAG, c.getString(1));
                    c.moveToNext();
                }
            }


            return rootView;
        }
    }
}
