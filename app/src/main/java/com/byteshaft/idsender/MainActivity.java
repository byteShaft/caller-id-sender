package com.byteshaft.idsender;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private EditText mPortNumberEntry;
    private EditText mReceiptEntry;
    private EditText mDataSmsTextEntry;
    private Helpers mHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(),
                PhoneStateListener.LISTEN_CALL_STATE);
        //


        mHelpers = new Helpers();
//        mPortNumberEntry = (EditText) findViewById(R.id.port_number_entry);
//        mReceiptEntry = (EditText) findViewById(R.id.outgoing_number_entry);
//        mDataSmsTextEntry = (EditText) findViewById(R.id.sms_text_entry);
//        Button sendButton = (Button) findViewById(R.id.button_send);
//        sendButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button_send:
//                if (mHelpers.isInputBoxEmpty(mDataSmsTextEntry) ||
//                        mHelpers.isInputBoxEmpty(mReceiptEntry)) {
//                    mHelpers.makeLongToast(
//                            getApplicationContext(),
//                            "At least one input box was empty. Make sure to fill everything.");
//                    return;
//                }
//                mHelpers.sendDataSms(
//                        mHelpers.getInputBoxTextAsString(mReceiptEntry),
//                        "6742",
//                        mHelpers.getInputBoxTextAsString(mDataSmsTextEntry)
//                );
//                Toast.makeText(
//                        getApplicationContext(), "Binary SMS sent", Toast.LENGTH_LONG).show();
//        }
    }

    class TeleListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    // CALL_STATE_IDLE;
                    Toast.makeText(getApplicationContext(), "CALL_STATE_IDLE",
                            Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // CALL_STATE_OFFHOOK;
                    Toast.makeText(getApplicationContext(), "CALL_STATE_OFFHOOK",
                            Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // CALL_STATE_RINGING
                    mHelpers.sendDataSms( "03006860746", "6742" ," got call");
                    break;
                default:
                    break;
            }
        }

    }
}
