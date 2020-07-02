package net.itgoo.parkingpay.ui.parkings;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.parkingpay.vendor.widget.recyclerview.flexibledivider.HorizontalDividerItemDecoration;
import net.itgoo.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import hk.ids.gws.android.sclick.SClick;

public class ParkingParkingsFragment extends ParkingBaseFragment implements ParkingParkingsContract.View {

    private static final int PERMISSION_REQUEST_CODE = 1;
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

        mNearAdapter = new ParkingParkingsAdapter(getActivity());
        mNearAdapter.setOnItemClickListener(new ParkingParkingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
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

        mCouncilsAdapter = new ParkingParkingsAdapter(getActivity());
        mCouncilsAdapter.setOnItemClickListener(new ParkingParkingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                mPresenter.openCouncils(position);
            }
        });
        mCouncilsRecyclerView.setAdapter(mCouncilsAdapter);
    }

    private void onLocationRefreshAction() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[i]);
            }
        }
        if (permissionList.size() > 0) {
            requestPermissions(permissionList.toArray(new String[0]), PERMISSION_REQUEST_CODE);
        } else {
            mPresenter.requestLocation();
        }
    }
}
