package com.example.gihan.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mEmptyText;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;
    private ArrayList<IncomingNumbers>mList=new ArrayList<IncomingNumbers>();

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyText=(TextView)findViewById(R.id.empty_text);
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);



        getfromDatabase();

        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getfromDatabase();

            }
        };





    }

    private void getfromDatabase(){

        mList.clear();
        Db_Helper helper=new Db_Helper(this);
        SQLiteDatabase database=helper.getWritableDatabase();

        Cursor cursor=helper.readNumber(database);
        if(cursor.getCount()>0){

            while (cursor.moveToNext()){
                String number;
                int id;

                number=cursor.getString(cursor.getColumnIndex(DB_Contract.INCOMING_NUMBER));
                id=cursor.getInt(cursor.getColumnIndex("id"));

                mList.add(new IncomingNumbers(id,number));

            }
            cursor.close();
            helper.close();

            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyText.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastReceiver,new IntentFilter(DB_Contract.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }
}
