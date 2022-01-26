package com.example.bhuopdbooking.ui.opd_schedule;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.bhuopdbooking.DatabaseAccess;
import com.example.bhuopdbooking.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class GalleryFragment extends Fragment {

    AutoCompleteTextView dayTextView;
    AutoCompleteTextView departmentTextView;
    Button checkButton;
    TextView checkResultTextView;
    String day;
    String department,doctorName,roomNo;
    String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String[] departments={"GASTROENTEROLOGY","POSTPARTUM/Special Clinic","GYNAECOLOGY","T.B & CHEST","ONCOLOGY SURGERY","GERIARTRIC CLINIC","RADIOTHERAPY","GENERAL SURGERY","ADOLESCENT CLINIC","CARDIOLOGY","CARDIOTHORACIC","ANAESHTESIOLOGY (PAC OPD)","PAIN CLINIC","UROLOGY","DERMATOLOGY","NEUROLOGY","PSYCHIATRIC","SPECIALITY CLINIC","RHEUMATOLOGY","ONCO MEDICINE","A.R.T. CENTRE (HIV)","GENERAL MEDICINE","HAEMATOLOGY","ENDOCRINOLOGY","NEPHROLOGY","OPHTHALMO /EYE","OTORHINO. /E.N.T","PAEDIATRIC MEDICINE","PAEDIATRIC SURGERY","WOUND CLINIC","NEUROSURGERY ( in wound clinic building)"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView=inflater.inflate(R.layout.fragment_gallery,container,false);
        dayTextView=fragView.findViewById(R.id.dayTextView);
        departmentTextView=fragView.findViewById(R.id.departmentTextView);
        checkResultTextView=fragView.findViewById(R.id.checkResultTextView);
        checkButton=fragView.findViewById(R.id.checkButton);
        dayTextView.setAdapter(new ArrayAdapter<>(fragView.getContext(), android.R.layout.simple_dropdown_item_1line,days));
        departmentTextView.setAdapter(new ArrayAdapter<>(fragView.getContext(),android.R.layout.simple_expandable_list_item_1,departments));
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               day=dayTextView.getText().toString();
                department=departmentTextView.getText().toString();
                DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getContext());
                databaseAccess.open();
                doctorName=databaseAccess.getDoctorNames(day,department);
                checkResultTextView.setText("Doctors Name : \n\n" + doctorName);
                roomNo=databaseAccess.getRoomNo(department);


                openDialog();



            }
        });


        dayTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                InputMethodManager in = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg1.getApplicationWindowToken(), 0);
                // hiding keyboard after choosing input
            }

        });
        departmentTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                InputMethodManager in = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(arg1.getApplicationWindowToken(), 0);
                // hiding keyboard after choosing input
            }

        });
        return fragView;
    }
    // send data when dialog is opened
    private void openDialog() {
        opd_details_result opd=new opd_details_result();
        opd.show(getActivity().getSupportFragmentManager(),null);
        Bundle bundle=new Bundle();
        bundle.putString("doctorName",doctorName);
        bundle.putString("roomNo",roomNo);
        opd.setArguments(bundle);

    }
}