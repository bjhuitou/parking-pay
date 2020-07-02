package net.itgoo.parkingpay.ui.orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lufficc.stateLayout.StateLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.orderinfo.ParkingOrderInfoActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import java.util.List;

import hk.ids.gws.android.sclick.SClick;

public class ParkingOrdersFragment extends ParkingBaseFragment implements ParkingOrdersContract.View {

    private ParkingOrdersContract.Presenter mPresenter;
    private TitleBar mTitleBar;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private StateLayout mStateLayout;
    private ParkingOrderAdapter mAdapter;

    public ParkingOrdersFragment() {
        // Required empty public constructor
    }

    public static ParkingOrdersFragment newInstance(Bundle bundle) {
        ParkingOrdersFragment fragment = new ParkingOrdersFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.stop();
        }
    }

    @Override
    public void setPresenter(ParkingOrdersContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_orders;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
        initTitleBar();
        initRecyclerView();
        initRefreshLayout();
        initStateLayout();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSmartRefreshLayout.autoRefresh();
    }

    private void initViews() {
        View contentView = getContentView();
        mTitleBar = contentView.findViewById(R.id.parking_view_title_bar_ct);
        mRecyclerView = contentView.findViewById(R.id.parking_fragment_orders_rv);
        mSmartRefreshLayout = contentView.findViewById(R.id.parking_fragment_orders_srl);
        mStateLayout = contentView.findViewById(R.id.parking_fragment_orders_sl);
    }

    private void initTitleBar() {
        mTitleBar.setTitle(getString(R.string.parking_parking_history));
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;
                getActivity().finish();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ParkingOrderAdapter(getActivity());
        mAdapter.setOnItemClickListener(new ParkingOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                mPresenter.openItem(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPresenter.loadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.refresh();
            }
        });
    }

    private void initStateLayout() {
        mStateLayout.setErrorAndEmptyAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateLayout.showContentView();
                initData(null);
            }
        });
    }

    @Override
    public void showRefreshData(List<String> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
        mSmartRefreshLayout.finishRefresh();
    }

    @Override
    public void showRefreshFailure(String error) {
        mStateLayout.showErrorView(error);
        mSmartRefreshLayout.finishRefresh();
    }

    @Override
    public void showMoreData(List<String> data) {
        mAdapter.notifyDataSetChanged();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void showMoreFailure(String error) {
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void showEmptyData() {
        mStateLayout.showEmptyView(getString(R.string.parking_no_data));
        mSmartRefreshLayout.finishRefresh();
    }

    @Override
    public void showItem(long id) {
        Intent intent = new Intent(getActivity(), ParkingOrderInfoActivity.class);
        startActivity(intent);
    }
}
