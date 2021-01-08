package com.example.ecommerceapp.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerceapp.R;

import java.util.Objects;


public class DialogNFragment extends DialogFragment {


    public static final String TAG = "DialogNFragment";
    private String title_;
    private String message_;
    private int type;

    public static final String TITLE = "TITLE";

    public static final String MESSAGE = "MESSAGE";

    public static final String TYPE = "TYPE";


    public static final int SUCCESS_TYPE = 1;
    public static final int ERROR_TYPE = 2;

    boolean isCancelVisible = false;

    public interface Click{

        void okClick(Dialog dialogFragment);
        void cancelClick(Dialog dialogFragment);
    }

    private Click click;

    public DialogNFragment() {
        super();
    }

    public void ClickHere(Click click){
        this.click = click;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        this.click = null;
    }


    /* @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.click = (Click) getActivity();
    }*/

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void disableCancel(boolean data){
        isCancelVisible = data;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);

        Bundle mArgs = getArguments();
        if (mArgs != null) {
            title_ = mArgs.getString(TITLE);
            message_ = mArgs.getString(MESSAGE);
            type = mArgs.getInt(TYPE);
        }
    }


    TextView cancel_;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout._dialog_layout, container, false);

        LottieAnimationView icons_dialog = view.findViewById(R.id.icons_dialog);
        TextView title = view.findViewById(R.id.title);
        TextView message = view.findViewById(R.id.message);

        cancel_= view.findViewById(R.id.cancel_);
        TextView pay = view.findViewById(R.id.pay);

        if(isCancelVisible){
            cancel_.setVisibility(View.GONE);
        }else {
            cancel_.setVisibility(View.VISIBLE);
        }

        title.setText(title_);
        message.setText(message_);




        if(type == SUCCESS_TYPE){
            icons_dialog.setAnimation(R.raw.success_two_);

        }else if(type == ERROR_TYPE){
            icons_dialog.setAnimation(R.raw.error);
        }

        cancel_.setOnClickListener(v -> {
            click.cancelClick(getDialog());
        });

        pay.setOnClickListener(v -> {
            click.okClick(getDialog());
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            try {
                Objects.requireNonNull(dialog.getWindow()).setLayout( width , height);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
