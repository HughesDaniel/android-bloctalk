package com.bloc.bloctalk.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This BroadcastReceiver, already declared in this template's
 * Android Manifest is required for intercepting MMS messages
 * received by the device. See
 * http://developer.android.com/reference/android/provider/Telephony.html
 * for details.
 *
 */
public class BlocTalkMMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle incoming MMS messages
    }

}
