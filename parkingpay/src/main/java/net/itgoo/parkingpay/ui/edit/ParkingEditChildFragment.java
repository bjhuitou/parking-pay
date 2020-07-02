package net.itgoo.parkingpay.ui.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import net.itgoo.parkingpay.R;
import net.itgoo.parkingpay.vendor.widget.fragment.ParkingBaseFragment;

import hk.ids.gws.android.sclick.SClick;

import static net.itgoo.parkingpay.ui.main.ParkingMainFragment.PARKING_ACTION_SHOW_PLATE_INFO;

public class ParkingEditChildFragment extends ParkingBaseFragment implements ParkingEditContract.View {

    private ParkingEditContract.Presenter mPresenter;
    private TextView mPlateTextView;

    public ParkingEditChildFragment() {
        // Required empty public constructor
    }

    public static ParkingEditChildFragment newInstance() {
        return new ParkingEditChildFragment();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
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
    public void setPresenter(ParkingEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.parking_fragment_edit_child;
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
        mPlateTextView = contentView.findViewById(R.id.parking_fragment_edit_plate_tv);
        contentView.findViewById(R.id.parking_fragment_edit_left_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreviousPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_edit_right_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_edit_edit_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEditPlateAction();
            }
        });
        contentView.findViewById(R.id.parking_fragment_edit_delete_ib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeletePlateAction();
            }
        });
        mPlateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SClick.check(SClick.BUTTON_CLICK)) return;

                mPresenter.openPlate();
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
    public void showPlateInfo(String plate) {
        Intent intent = new Intent(PARKING_ACTION_SHOW_PLATE_INFO);
        intent.putExtra("plate", plate);
        getActivity().sendBroadcast(intent);
    }
}
