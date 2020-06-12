package net.itgoo.parkingpay.ui.merchants;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lufficc.stateLayout.StateLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import java.util.List;

import hk.ids.gws.android.sclick.SClick;

public class ParkingMerchantsFragment extends ParkingBaseFragment implements ParkingMerchantsContract.View {

    private ParkingMerchantsContract.Presenter mPresenter;
    TitleBar mTitleBar;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mSmartRefreshLayout;
    StateLayout mStateLayout;
    private ParkingMerchantsAdapter mAdapter;

    public ParkingMerchantsFragment() {
        // Required empty public constructor
    }

    public static ParkingMerchantsFragment newInstance(Bundle bundle) {
        ParkingMerchantsFragment fragment = new ParkingMerchantsFragment();
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
    public void setPresenter(ParkingMerchantsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_merchants;
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
        mRecyclerView = contentView.findViewById(R.id.parking_fragment_merchants_rv);
        mSmartRefreshLayout = contentView.findViewById(R.id.parking_fragment_merchants_srl);
        mStateLayout = contentView.findViewById(R.id.parking_fragment_merchants_sl);
    }

    private void initTitleBar() {
        mTitleBar.setTitle("");
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

        mAdapter = new ParkingMerchantsAdapter(R.layout.parking_item_merchants);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
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
        mAdapter.setNewData(data);
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
    }
}
