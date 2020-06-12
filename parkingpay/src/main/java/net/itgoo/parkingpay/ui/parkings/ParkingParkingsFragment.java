package net.itgoo.parkingpay.ui.parkings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import java.util.List;

import hk.ids.gws.android.sclick.SClick;

public class ParkingParkingsFragment extends ParkingBaseFragment implements ParkingParkingsContract.View {

    private ParkingParkingsContract.Presenter mPresenter;
    private TitleBar mTitleBar;
    private View mNoParkView;
    private RecyclerView mNearRecyclerView;
    private RecyclerView mCouncilsRecyclerView;
    private ParkingParkingsAdapter mNearAdapter;
    private ParkingParkingsAdapter mCouncilsAdapter;

    public ParkingParkingsFragment() {
        // Required empty public constructor
    }

    public static ParkingParkingsFragment newInstance(Bundle bundle) {
        ParkingParkingsFragment fragment = new ParkingParkingsFragment();
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
    public void setPresenter(ParkingParkingsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_parkings;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
        initTitleBar();
        initNearRecyclerView();
        initCouncilsRecyclerView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initViews() {
        View contentView = getContentView();
        mTitleBar = contentView.findViewById(R.id.parking_view_title_bar_ct);
        mNoParkView = contentView.findViewById(R.id.parking_fragment_parkings_no_park_view);
        mNearRecyclerView = contentView.findViewById(R.id.parking_fragment_parkings_rv);
        mCouncilsRecyclerView = contentView.findViewById(R.id.parking_fragment_councils_rv);
        contentView.findViewById(R.id.parking_fragment_parkings_refresh_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                onLocationRefreshAction();
            }
        });
    }

    private void initTitleBar() {
        mTitleBar.setTitle(getString(R.string.parking_confirm_payment));
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;
                getActivity().finish();
            }
        });
    }

    private void initNearRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mNearRecyclerView.setLayoutManager(layoutManager);

        mNearRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        mNearAdapter = new ParkingParkingsAdapter(R.layout.parking_item_park);
        mNearAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                mPresenter.openPark(position);
            }
        });
        mNearRecyclerView.setAdapter(mNearAdapter);
    }

    private void initCouncilsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mCouncilsRecyclerView.setLayoutManager(layoutManager);

        mCouncilsRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        mCouncilsAdapter = new ParkingParkingsAdapter(R.layout.parking_item_park);
        mCouncilsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                mPresenter.openCouncils(position);

            }
        });
        mCouncilsRecyclerView.setAdapter(mCouncilsAdapter);
    }

    private void onLocationRefreshAction() {
        AndPermission.with(getActivity())
                .runtime()
                .permission(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        mPresenter.requestLocation();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                    }
                })
                .start();
    }
}
