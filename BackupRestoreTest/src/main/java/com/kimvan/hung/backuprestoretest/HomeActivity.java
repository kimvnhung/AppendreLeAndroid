package com.kimvan.hung.backuprestoretest;

import android.app.Activity;
import android.content.Intent;
import android.opengl.EGLDisplay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText dateEdit;
    EditText numEdit;

    Button restore;
    Button backup;
    Button review;

    Intent intent;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dateEdit=(EditText) findViewById(R.id.born_date_edit);
        numEdit=(EditText) findViewById(R.id.telephone_number_edit);
        nameEdit=(EditText) findViewById(R.id.name_edit);

        dbHandler = new DatabaseHandler(this,null,null,0);

        setupUI(findViewById(R.id.layout_home));
    }

    public void backupClicked(View view){
        String name = nameEdit.getText().toString();
        String date = dateEdit.getText().toString();
        String number = numEdit.getText().toString();
        if (name.equals("")){
            Toast.makeText(this,"Nhập Lại",Toast.LENGTH_SHORT).show();
        }else {
            dbHandler.addStudent(new Student(name,date,number));
            nameEdit.setText("");
            dateEdit.setText("");
            numEdit.setText("");
            Toast.makeText(this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
        }
    }

    public void restoreClicked(View view){

    }

    public void reviewClicked(View view){
        intent =  new Intent(this,InformationActivity.class);
        startActivity(intent);

    }

    // hide keyboard when tap outside of EditText
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(HomeActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}
