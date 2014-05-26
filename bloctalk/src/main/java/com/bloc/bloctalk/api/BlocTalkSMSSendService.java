package com.bloc.bloctalk.api;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * BlocTalkSMSSendService
 *
 * This service is responsible for "quick response"
 * messages likely sent by a dialer application
 * looking to aide the user in replying to a call
 * with a quick SMS response.
 */
public class BlocTalkSMSSendService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
