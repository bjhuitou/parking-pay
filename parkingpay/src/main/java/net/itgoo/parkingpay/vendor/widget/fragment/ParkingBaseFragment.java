package net.itgoo.parkingpay.vendor.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public abstract class ParkingBaseFragment extends Fragment {

    private View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView != null) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        } else {
            mContentView = inflater.inflate(getLayoutResourceID(), null);
            init(savedInstanceState);
        }

        return mContentView;
    }

    public View getContentView() {
        return mContentView;
    }

    private void init(Bundle savedInstanceState) {
        initUI(savedInstanceState);
        initData(savedInstanceState);
    }

    protected abstract int getLayoutResourceID();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);
}
