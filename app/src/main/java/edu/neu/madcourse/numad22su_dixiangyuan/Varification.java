package edu.neu.madcourse.numad22su_dixiangyuan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class Varification extends DialogFragment {
    NoticeDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dissmissActivity)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Varification.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (Varification.NoticeDialogListener) context;
        } catch (ClassCastException e) { // Catch error if the context cannot implement
            throw new ClassCastException("Cannot implement NoticeDialogListener");
        }
    }
}