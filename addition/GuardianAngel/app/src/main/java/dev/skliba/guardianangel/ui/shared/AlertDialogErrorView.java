package dev.skliba.guardianangel.ui.shared;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

public class AlertDialogErrorView implements BaseMvp.ErrorView {

    private Activity activity;

    public AlertDialogErrorView(Fragment fragment) {
        this(fragment.getActivity());
    }

    public AlertDialogErrorView(Context context) {
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        } else {
            throw new IllegalArgumentException("Context is not instance of activity.");
        }
    }

    @Override
    public void showError(String message) {
        showError(message, null);
    }

    @Override
    public void showError(String message, @Nullable final BaseMvp.DismissListener dismissListener) {
        if (activity != null && !activity.isFinishing()) {
            new AlertDialog.Builder(activity)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (dismissListener != null) {
                                dismissListener.onDismiss();
                            }
                        }
                    })
                    .show();
        }
    }

    @Override
    public void showError(int resourceInt) {
        showError(resourceInt, null);
    }

    @Override
    public void showError(int resourceInt, @Nullable BaseMvp.DismissListener dismissListener) {
        showError(activity.getString(resourceInt), dismissListener);
    }
}
