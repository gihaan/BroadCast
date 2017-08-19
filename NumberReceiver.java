package com.example.gihan.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Gihan on 8/18/2017.
 */

public class NumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //-------------------FOR MAKE MESsage-------------------------------//
      //  Toast.makeText(context,"new Message",Toast.LENGTH_LONG).show();


        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);


            Db_Helper helper = new Db_Helper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            helper.saveNumber(number, database);
            helper.close();
        }
        Intent intent1=new Intent(DB_Contract.UPDATE_UI_FILTER);
        context.sendBroadcast(intent1);

    }
}
