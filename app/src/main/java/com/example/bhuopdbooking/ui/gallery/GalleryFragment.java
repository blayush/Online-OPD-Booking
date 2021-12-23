package com.example.bhuopdbooking.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bhuopdbooking.DatabaseAccess;
import com.example.bhuopdbooking.R;


public class GalleryFragment extends Fragment {

    AutoCompleteTextView dayTextView;
    AutoCompleteTextView departmentTextView;
    Button checkButton;
    TextView checkResultTextView;
    String day;
    String department;
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
                String doctorName=databaseAccess.getDoctorNames(day,department);
                checkResultTextView.setText("Doctors Name : \n\n" + doctorName);
               // Toast.makeText(getContext(), doctorName, Toast.LENGTH_SHORT).show();
            }
        });

        return fragView;
    }
}