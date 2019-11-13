package ch.pandamurai.tutorial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class IncrementDialog extends AppCompatDialogFragment {

    private EditText incrementCounter;
    private IncrementDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_increment_dialog, null);

        builder.setView(view).setTitle("Increment").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String incrementNumber = incrementCounter.getText().toString();
                listener.applyIncrement(incrementNumber);
            }
        });
        incrementCounter = view.findViewById(R.id.edit_increment);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (IncrementDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement IncrementDialogListener");
        }
    }

    public interface IncrementDialogListener{
        void applyIncrement (String incrementNumber);
    }
}
