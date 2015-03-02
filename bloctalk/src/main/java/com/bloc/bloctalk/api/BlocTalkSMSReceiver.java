package com.bloc.bloctalk.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * BlocTalkSMSReceiver
 *
 * This BroadcastReceiver, already declared in this template's
 * Android Manifest is required for intercepting SMS messages
 * received by the device. See
 * http://developer.android.com/reference/android/provider/Telephony.html
 * for details.
 *
 */
public class BlocTalkSMSReceiver extends BroadcastReceiver {

    private static final String TAG = ".BlocTalkSMSReciever.java";

    private SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.d(TAG, "senderNum: " + senderNum + "; message: " + message);


                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e(TAG, "Exception smsReceiver" + e);

        }
    }
}
