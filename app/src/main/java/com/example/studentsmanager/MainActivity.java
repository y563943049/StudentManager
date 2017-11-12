package com.example.studentsmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Student> mStudents = new ArrayList<>();
    private Button commit;
    private EditText studentID;
    private EditText studentName;
    private EditText studentGender;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commit = (Button) findViewById(R.id.commit);
        studentID = (EditText) findViewById(R.id.edit_id);
        studentName = (EditText) findViewById(R.id.edit_name);
        studentGender = (EditText) findViewById(R.id.edit_gender);
        commit.setOnClickListener(this);
        adapter = new StudentAdapter(mStudents);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.commit:
                String ID = studentID.getText().toString();
                String name = studentName.getText().toString();
                String gender = studentGender.getText().toString();
                Student student = new Student();
                student.setName(name);
                student.setId(ID);
                student.setGender(gender);
                mStudents.add(student);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
