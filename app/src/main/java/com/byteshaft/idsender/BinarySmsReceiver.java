package com.byteshaft.idsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BinarySmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String incomingSmsText = Helpers.decodeIncomingSmsText(intent);
        Log.i("LOG_TAG", "Message Received: " + incomingSmsText);
    }
}
