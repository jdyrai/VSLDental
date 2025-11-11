package com.example.vsldental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_ProfileManage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_ProfileManage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_ProfileManage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_ProfileManage.
     */
    // TODO: Rename and change types and number of parameters
    public static User_ProfileManage newInstance(String param1, String param2) {
        User_ProfileManage fragment = new User_ProfileManage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    /*
    <LinearLayout
            android:id="@+id/LogOutContainer"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:clickable="true"
            android:focusableInTouchMode="true"
            android:focusable="true"
            android:orientation="horizontal"
            android:paddingHorizontal="10dp"
            android:paddingVertical="10dp"
            app:layout_constraintEnd_toEndOf="@id/endGuideline"
            app:layout_constraintHorizontal_bias="0.0"
            app:layout_constraintStart_toStartOf="@id/startGuideline"
            app:layout_constraintTop_toBottomOf="@id/layoutPass1TopGuideline">

            <LinearLayout
                android:id="@+id/LogOutImage"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="7"
                android:clickable="true"
                android:gravity="center"
                android:orientation="horizontal"
                android:paddingHorizontal="10dp"
                android:paddingVertical="10dp"
                app:layout_constraintEnd_toEndOf="@id/endGuideline"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="@id/startGuideline"
                app:layout_constraintTop_toBottomOf="@id/layoutPass1TopGuideline">

                <ImageView
                    android:id="@+id/LogOutIMG"
                    android:layout_width="20dp"
                    android:layout_height="20dp"
                    android:adjustViewBounds="true"
                    android:background="@drawable/img_adminlogout"
                    android:scaleType="centerCrop" />
            </LinearLayout>


            <TextView
                android:id="@+id/LogOutText"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:paddingVertical="12dp"
                android:fontFamily="@font/inter_24pt_semibold"
                android:text="Log Out"
                android:textColor="@color/black"
                android:textSize="13sp" />

        </LinearLayout>*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__profile_manage, container, false);

        LinearLayout personalinfoBtn = view.findViewById(R.id.PersonalInfoContainer);
        personalinfoBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_UserProfileManagement_to_PersonalInformation);
        });
        return view;
    }
}