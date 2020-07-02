package net.itgoo.parkingpay.ui.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.itgoo.parkingpay.R;

import java.util.ArrayList;
import java.util.List;

public class ParkingOrderAdapter extends RecyclerView.Adapter<ParkingOrderAdapter.RecyclerHolder> {
    private Context mContext;
    private List<String> dataList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public ParkingOrderAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<String> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.parking_item_order, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        //holder.textView.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.parking_item_order_date_tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}