package com.byteshaft.idsender;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Helpers {

    private static Context context = AppGlobals.getContext();
    static String originatingAddress;

    void sendDataSms(String phoneNumber, String port, String smsCommand) {
        SmsManager smsManager = getSmsManager();
        Log.i("BinarySMS", getSmsFeedbackFormattedMessage(phoneNumber, port, smsCommand));
        smsManager.sendDataMessage(
                phoneNumber, null, Short.valueOf(port), smsCommand.getBytes(), null, null
        );
    }

    static String decodeIncomingSmsText(Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages;
        String messageText = "";

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            messages = new SmsMessage[pdus.length];
            originatingAddress = "";

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                originatingAddress += messages[i].getOriginatingAddress();

                byte[] data = messages[i].getUserData();
                for (byte aData : data) {
                    messageText += Character.toString((char) aData);
                }
            }
        }

        return messageText;
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    private SmsManager getSmsManager() {
        return SmsManager.getDefault();
    }

    private String getSmsFeedbackFormattedMessage(String number, String port, String command) {
        return String.format(
                "Sending data SMS \"%s\" to %s on port number: %s",
                command, number, String.valueOf(port)
        );
    }

    boolean isInputBoxEmpty(EditText inputBox) {
        return TextUtils.isEmpty(inputBox.getText().toString());
    }

    void makeLongToast(Context context, String text) {
        Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    String getInputBoxTextAsString(EditText inputBox) {
        return inputBox.getText().toString();
    }
}