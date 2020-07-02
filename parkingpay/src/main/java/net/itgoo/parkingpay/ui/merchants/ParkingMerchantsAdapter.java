package net.itgoo.parkingpay.ui.merchants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import net.itgoo.parkingpay.R;

import java.util.ArrayList;
import java.util.List;

public class ParkingMerchantsAdapter extends RecyclerView.Adapter<ParkingMerchantsAdapter.RecyclerHolder> {
    private Context mContext;
    private List<String> dataList = new ArrayList<>();
    private ParkingMerchantsAdapter.OnItemClickListener onItemClickListener;

    public ParkingMerchantsAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(ParkingMerchantsAdapter.OnItemClickListener onItemClickListener) {
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
    public ParkingMerchantsAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.parking_fragment_merchants, parent, false);
        return new ParkingMerchantsAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(ParkingMerchantsAdapter.RecyclerHolder holder, final int position) {
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
        private RecyclerHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
