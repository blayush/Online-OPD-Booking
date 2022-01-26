package com.example.bhuopdbooking.ui.opd_schedule;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bhuopdbooking.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class opd_details_result extends BottomSheetDialogFragment {

    TextView docText,roomText;
    String docName,roomNo;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    docName=getArguments().getString("doctorName");
    roomNo=getArguments().getString("roomNo");
    }

    public opd_details_result() {

    }

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_opd_details_result, container, false);
        roomText=view.findViewById(R.id.roomnoTextView);
        docText=view.findViewById(R.id.docnameTextView);
        docText.setText(docName);
        roomText.setText(roomNo);
        getDialog().getWindow().setLayout(1000, 1000);
        return view;
    }
}