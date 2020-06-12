package net.itgoo.parkingpay.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.ui.parkings.ParkingParkingsActivity;
import net.itgoo.parkingpay.ui.payconfirm.ParkingPayConfirmActivity;
import net.itgoo.parkingpay.ui.vehicleimage.ParkingVehicleImageActivity;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import hk.ids.gws.android.sclick.SClick;

import static net.itgoo.parkingpay.ui.main.ParkingMainFragment.PARKING_ACTION_SHOW_MAIN_MENU;

public class ParkingHomeChildFragment extends ParkingBaseFragment implements ParkingHomeContract.View {

    private ParkingHomeContract.Presenter mPresenter;
    private TextView mPlateTextView;
    private View mMenuTitleView;
    private View mMenuActionView;
    private View mPayView;
    private View mToolView;

    public ParkingHomeChildFragment() {
        // Required empty public constructor
    }

    public static ParkingHomeChildFragment newInstance() {
        return new ParkingHomeChildFragment();
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
    public void setPresenter(ParkingHomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_home_child;
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
        mPlateTextView = contentView.findViewById(R.id.parking_fragment_home_plate_tv);
        mMenuTitleView = contentView.findViewById(R.id.parking_fragment_home_child_menu_title_view);
        mMenuActionView = contentView.findViewById(R.id.parking_fragment_home_child_menu_action_view);
        mPayView = contentView.findViewById(R.id.parking_fragment_home_child_pay_view);
        mToolView = contentView.findViewById(R.id.parking_fragment_home_child_park_tool_view);
        contentView.findViewById(R.id.parking_fragment_home_left_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreviousPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_right_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_edit_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_delete_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeletePlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_child_park_selection_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onParkingsAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_child_park_image_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVehicleImageAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_home_pay_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPayAction();
            }
        });
    }

    private void onEditPlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.openPlateEdit();
    }

    private void onDeletePlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.deletePlateConfirm();
    }

    private void onPreviousPlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.previousPlate();
    }

    private void onNextPlateAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        mPresenter.nextPlate();
    }

    private void onVehicleImageAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingVehicleImageActivity.class);
        startActivity(intent);
    }

    private void onParkingsAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingParkingsActivity.class);
        startActivity(intent);
    }

    private void onPayAction() {
        if (!SClick.check(SClick.BUTTON_CLICK)) return;

        Intent intent = new Intent(getActivity(), ParkingPayConfirmActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPlateEdit(String plate) {
        if (plate == null || plate.isEmpty()) {
            plate = "";
        }

        final EditText editText = new EditText(getActivity());
        editText.setSingleLine();
        editText.setText(plate);
        new AlertDialog.Builder(getActivity())
                .setView(editText)
                .setMessage(R.string.parking_add_plate_title)
                .setPositiveButton(R.string.parking_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!SClick.check(SClick.BUTTON_CLICK)) return;

                        mPresenter.savePlate(editText.toString());
                    }
                }).setNegativeButton(R.string.parking_cancel, null)
                .create().show();
    }

    @Override
    public void showDeleteConfirm() {
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.parking_delete_plate_confirm)
                .setPositiveButton(R.string.parking_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!SClick.check(SClick.BUTTON_CLICK)) return;

                        mPresenter.deletePlate();
                    }
                }).setNegativeButton(R.string.parking_cancel, null)
                .create().show();
    }

    @Override
    public void showPlate(String plate) {
        mPlateTextView.setText(plate);
    }

    @Override
    public void showEditView() {
        mMenuTitleView.setVisibility(View.VISIBLE);
        mMenuActionView.setVisibility(View.VISIBLE);
        mPayView.setVisibility(View.GONE);
    }

    @Override
    public void showPayView() {
        mMenuTitleView.setVisibility(View.INVISIBLE);
        mMenuActionView.setVisibility(View.GONE);
        mPayView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMainActionView(boolean show) {
        Intent intent = new Intent(PARKING_ACTION_SHOW_MAIN_MENU);
        intent.putExtra("show", show);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void showToolView(boolean show) {
        mToolView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
