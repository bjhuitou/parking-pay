package net.itgoo.parkingpay.ui.pay;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.paid.ParkingPaidActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;
import net.itgoo.titlebar.TitleBar;

import hk.ids.gws.android.sclick.SClick;

public class ParkingPayFragment extends ParkingBaseFragment implements ParkingPayContract.View {

    private TitleBar mTitleBar;
    private WebView mWebView;

    private ParkingPayContract.Presenter mPresenter;

    public ParkingPayFragment() {
        // Required empty public constructor
    }

    public static ParkingPayFragment newInstance(Bundle bundle) {
        ParkingPayFragment fragment = new ParkingPayFragment();
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
    public void setPresenter(ParkingPayContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_pay;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initViews();
        initTitleBar();
        initWebView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mWebView.loadUrl("http://192.168.0.3:8080/cywz/api/pay/test");
    }

    private void initViews() {
        View contentView = getContentView();
        mTitleBar = contentView.findViewById(R.id.parking_view_title_bar_ct);
        mWebView = contentView.findViewById(R.id.parking_fragment_pay_wv);
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

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (handler != null) {
                    handler.proceed();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String path = request.getUrl().getPath();
                if (path.contains("wav-pay-redirection")) {
                    showPaid();
                    getActivity().finish();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    private void showPaid() {
        Intent intent = new Intent(getActivity(), ParkingPaidActivity.class);
        startActivity(intent);
    }
}
