package net.itgoo.parkingpay.ui.image;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import hk.ids.gws.android.sclick.SClick;

public class ParkingImageFragment extends ParkingBaseFragment implements ParkingImageContract.View {

    private ParkingImageContract.Presenter mPresenter;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;

    public ParkingImageFragment() {
        // Required empty public constructor
    }

    public static ParkingImageFragment newInstance(Bundle bundle) {
        ParkingImageFragment fragment = new ParkingImageFragment();
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
    public void setPresenter(ParkingImageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_image;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initViews() {
        View contentView = getContentView();
        mImageView1 = contentView.findViewById(R.id.parking_fragment_image_photo_1_iv);
        mImageView2 = contentView.findViewById(R.id.parking_fragment_image_photo_2_iv);
        mImageView3 = contentView.findViewById(R.id.parking_fragment_image_photo_3_iv);
        contentView.findViewById(R.id.parking_fragment_image_photo_1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImage1Action();
            }
        });
        contentView.findViewById(R.id.parking_fragment_image_photo_2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImage2Action();
            }
        });
        contentView.findViewById(R.id.parking_fragment_image_photo_3_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImage3Action();
            }
        });
        contentView.findViewById(R.id.parking_fragment_image_later_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaterAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_image_save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveAction();
            }
        });
    }

    private void onImage1Action() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;
    }

    private void onImage2Action() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;
    }

    private void onImage3Action() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;
    }

    private void onLaterAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        getActivity().finish();
    }

    private void onSaveAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;
    }
}
