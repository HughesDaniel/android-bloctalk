package com.bloc.bloctalk;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * ConversationActivity
 *
 * This may represent the Activity where an actual
 * conversation takes place. This Activity is registered
 * in the Android Manifest as capable of receiving
 * intents with the ACTION_SENDTO action.
 *
 * This action is one used by other applications to
 * share data with someone via text message. It may
 * launch your Activity with details regarding the
 * message which needs to be sent and optionally
 * the intended recipient of said message.
 */
public class ConversationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Uri data = getIntent().getData();
        if (data != null) {
            Resources resources = getResources();
            String scheme = data.getScheme();
            if (resources.getString(R.string.sms_scheme).equals(scheme)) {
                // SEND AN SMS
            } else if (resources.getString(R.string.sms_to_scheme).equals(scheme)) {
                // SEND AN SMS WITH RECIPIENT
            } else if (resources.getString(R.string.mms_scheme).equals(scheme)) {
                // SEND AN MMS
            } else if (resources.getString(R.string.mms_to_scheme).equals(scheme)) {
                // SEND AN MMS WITH RECIPIENT
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.conversation, menu);
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
            return rootView;
        }
    }
}
