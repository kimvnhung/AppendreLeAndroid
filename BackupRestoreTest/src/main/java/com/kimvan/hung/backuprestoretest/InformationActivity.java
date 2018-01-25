package com.kimvan.hung.backuprestoretest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity {

    ArrayList<Student> student = new ArrayList<>();
    StudentAdapter studentAdapter;
    ListView listView;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        listView=(ListView) findViewById(R.id.list_view);
        dbHandler = new DatabaseHandler(this,null,null,0);
        prepareInfor();
        showInfor();

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        dbHandler.deleteStudent(student.get(position).getName());
                        student.remove(position);

                        showInfor();
                        Toast.makeText(getApplicationContext(),"Đã xóa!",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
        );
    }

    public void prepareInfor(){
        student=dbHandler.getInfor();
    }

    public void showInfor(){
        studentAdapter = new StudentAdapter(student,this);
        listView.setAdapter(studentAdapter);
    }
}
