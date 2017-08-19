package com.example.gihan.broadcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gihan on 8/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {
    private ArrayList<IncomingNumbers>mList=new ArrayList<>();

    public RecyclerAdapter(ArrayList<IncomingNumbers>mList){
        this.mList=mList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_layout,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder  holder, int position) {

        holder.mId.setText(Integer.toString(mList.get(position).getId()));
        holder.mNumber.setText(mList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{


        private TextView mId;
        private TextView mNumber;
        public myViewHolder(View itemView) {
            super(itemView);

            mId=(TextView)itemView.findViewById(R.id.tv_id);
            mNumber=(TextView)itemView.findViewById(R.id.tv_phone_number);


        }
    }
}
