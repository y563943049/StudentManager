package com.example.studentsmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MySQLite mMySQLite;
    //private SharedPreferences.Editor mEditor;
    //private SharedPreferences mPreferences;
    private List<Student> mStudents = new ArrayList<>();
    private Button commit;
    private Button deleted;
    private Button updated;
    private EditText studentID;
    private EditText studentName;
    private EditText studentGender;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commit = (Button) findViewById(R.id.commit);
        deleted = (Button) findViewById(R.id.delete);
        updated = (Button) findViewById(R.id.update);
        studentID = (EditText) findViewById(R.id.edit_id);
        studentName = (EditText) findViewById(R.id.edit_name);
        studentGender = (EditText) findViewById(R.id.edit_gender);
        mMySQLite = new MySQLite(this,"studentManager",null,1);
        SQLiteDatabase bd = mMySQLite.getReadableDatabase();
        Cursor cursor = bd.query("student",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                Student student = new Student();
                student.setName(name);
                student.setId(id);
                student.setGender(gender);
                mStudents.add(student);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //mEditor = getSharedPreferences("data",MODE_PRIVATE).edit();
       // mPreferences = getSharedPreferences("data",MODE_PRIVATE);
        /*if(mPreferences != null){
            String name = mPreferences.getString("name","");
            String id = mPreferences.getString("id","");
            String gender = mPreferences.getString("gender","");
            Student student = new Student();
            student.setName(name);
            student.setId(id);
            student.setGender(gender);
            mStudents.add(student);
        }*/
        commit.setOnClickListener(this);
        deleted.setOnClickListener(this);
        updated.setOnClickListener(this);
        adapter = new StudentAdapter(mStudents);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase db = mMySQLite.getWritableDatabase();
        switch (view.getId()){
            case R.id.commit:
                Toast.makeText(this,"you click commit",Toast.LENGTH_SHORT).show();
                String ID = studentID.getText().toString();
                String name = studentName.getText().toString();
                String gender = studentGender.getText().toString();
                Student student = new Student();
                student.setName(name);
                student.setId(ID);
                student.setGender(gender);
                /*mEditor.putString("name",name);
                mEditor.putString("id",ID);
                mEditor.putString("gender",gender);
                mEditor.apply();*/
                mStudents.add(student);
                adapter.notifyDataSetChanged();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("id",ID);
                values.put("gender",gender);
                db.insert("student",null,values);
                break;
            case R.id.update:
                Toast.makeText(this,"you click update",Toast.LENGTH_SHORT).show();
                ContentValues value = new ContentValues();
                String update_id = studentID.getText().toString();
                String update_name = studentName.getText().toString();
                String update_gender = studentGender.getText().toString();
                value.put("name",update_name);
                value.put("gender",update_gender);
                db.update("student",value,"id = ?",new String[]{update_id});
                break;
            case R.id.delete:
                Toast.makeText(this,"you click delete",Toast.LENGTH_SHORT).show();
                String delete_id = studentID.getText().toString();
                db.delete("student","id = ?",new String[]{delete_id});
                Toast.makeText(this,"you delete"+delete_id,Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
