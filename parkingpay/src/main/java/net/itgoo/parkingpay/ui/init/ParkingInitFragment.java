package net.itgoo.parkingpay.ui.init;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.main.ParkingMainActivity;
import net.itgoo.parkingpay.ui.pay.ParkingPayActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import static android.view.animation.Animation.RESTART;

public class ParkingInitFragment extends ParkingBaseFragment implements ParkingInitContract.View { ;

    private static final int CIRCLE_NUM = 5;
    private static final int ANIM_INTERVAL = 300;

    private ParkingInitContract.Presenter mPresenter;

    public ParkingInitFragment() {
        // Required empty public constructor
    }

    public static ParkingInitFragment newInstance(Bundle bundle) {
        ParkingInitFragment fragment = new ParkingInitFragment();
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
    public void setPresenter(ParkingInitContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_init;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        showProgressAnimation();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.initModel();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), ParkingMainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }

    private void showProgressAnimation() {
        for (int i = 0; i < CIRCLE_NUM; i++) {
            View view = getContentView().findViewById(R.id.parking_fragment_init_progress_1_iv + i);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.parking_anim_circle_progress);
            animation.setStartOffset(i * ANIM_INTERVAL);
            animation.setAnimationListener(new ReStartAnimationListener());
            view.startAnimation(animation);
        }
    }

    private class ReStartAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            if (getActivity().isFinishing()) {
                return;
            }
            animation.reset();
            animation.setStartOffset((CIRCLE_NUM - 1) * ANIM_INTERVAL);
            animation.setAnimationListener(new ReStartAnimationListener());
            animation.start();
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }
}
