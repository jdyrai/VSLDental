package com.example.vsldental;
import org.json.JSONObject;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_Records#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_Records extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_Records() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_Records.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_Records newInstance(String param1, String param2) {
        Admin_Records fragment = new Admin_Records();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__records, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        LinearLayout containerLayout = view.findViewById(R.id.RecordsContainerParent);

        new Thread(() -> {
            try {
                String urlStr = "http://10.0.2.2/AndroidStudioVSLDentalClinic/admin_loadpatientlist.php";
                String result = ConnectToDatabase.sendPostRequest(urlStr, ""); // no data sent

                requireActivity().runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("status").equals("success")) {

                            JSONArray dataArray = json.getJSONArray("data");


                            // ✅ Loop through all items
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject item = dataArray.getJSONObject(i);

                                String email = item.getString("email");
                                String role = item.getString("role");
                                String userid = item.getString("accounts_id");

                                // 🔹 Create parent LinearLayout for this record
                                LinearLayout itemLayout = new LinearLayout(requireContext());
                                itemLayout.setOrientation(LinearLayout.VERTICAL);
                                itemLayout.setPadding(30, 30, 30, 30);
                                itemLayout.setBackgroundResource(R.drawable.input_layout);
                                itemLayout.setClickable(true);
                                itemLayout.setFocusable(true);

                                // 🔹 Create TextView for email & role
                                TextView tv = new TextView(requireContext());
                                tv.setText("Email: " + email + "\nRole: " + role);
                                tv.setTextSize(16f);
                                tv.setTextColor(getResources().getColor(android.R.color.black));
                                itemLayout.addView(tv);

                                // 🔹 Add margin between items
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                params.setMargins(0, 0, 0, 25);
                                itemLayout.setLayoutParams(params);

                                // 🔹 Add Click Listener
                                    itemLayout.setOnClickListener(v -> {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", email);
                                        bundle.putString("role", role);
                                        bundle.putString("accounts_id", userid);

                                        navController.navigate(R.id.action_adminRecords_to_adminClientDetails, bundle);
                                        Toast.makeText(requireContext(),
                                                "Email: " + email + "\nRole: " + role,
                                                Toast.LENGTH_SHORT).show();
                                    });

                                // ✅ Add this record layout to the parent container
                                containerLayout.addView(itemLayout, 0);
                            }


                        } else {
                            Toast.makeText(requireContext(),
                                    "Failed: " + json.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(requireContext(),
                                "Parse error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(),
                                "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();


        LinearLayout HomeBtn = view.findViewById(R.id.HomeContainer);
        HomeBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminRecords_to_adminDashboard);
        });

        LinearLayout ProfileBtn = view.findViewById(R.id.ProfileContainer);
        ProfileBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Profile clicked!");
            navController.navigate(R.id.action_adminRecords_to_adminProfile);
        });

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Log.d("BookingHistory", "Back button clicked!");
            navController.popBackStack();
        });

        LinearLayout AppointBtn = view.findViewById(R.id.AppointContainer);
        AppointBtn.setOnClickListener(v -> {
            Log.d("BookingHistory", "Records clicked!");
            navController.navigate(R.id.action_adminRecords_to_adminAppoint);

        });






        return view;
    }
}