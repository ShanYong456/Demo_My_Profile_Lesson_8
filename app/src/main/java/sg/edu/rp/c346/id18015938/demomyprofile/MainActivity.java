package sg.edu.rp.c346.id18015938.demomyprofile;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.SharedPreferences;
        import android.os.Bundle;
        import androidx.preference.PreferenceManager;

        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btn_save = findViewById(R.id.buttonSave);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int gender_id = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("username", username);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", gender_id);

                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();

        //step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //step 1d: Add the key-value pair
        prefEdit.putString("username", etName.getText().toString());
        float GPA = Float.parseFloat(etGPA.getText().toString());
        prefEdit.putFloat("gpa", GPA);
        int gender_id = rgGender.getCheckedRadioButtonId();
        prefEdit.putInt("genderId", gender_id);

        //step 1e: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String msg = prefs.getString("username", "No username");
        float gpa = prefs.getFloat("gpa", 0);
        int gender_id = prefs.getInt("genderId", R.id.radioButtonGenderMale);

        // Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(String.valueOf(gpa));
        rgGender.check(gender_id);
    }
}
