package com.example.studentsmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @version $Rev$
 * @anthor Administrator
 * @dsc ${TOOD}
 * @updateAuthor $Author
 * @updateDsc ${TOOD}
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> mStudents;

    public StudentAdapter(List<Student> students){
        this.mStudents = students;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView studentID;
        TextView studentName;
        TextView studentGender;

        public ViewHolder(View itemView) {
            super(itemView);
            studentGender = (TextView)itemView.findViewById(R.id.student_gender);
            studentID = (TextView)itemView.findViewById(R.id.student_id);
            studentName = (TextView)itemView.findViewById(R.id.student_name);
        }
    }
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.studentName.setText(student.getName());
        holder.studentID.setText(student.getId());
        holder.studentGender.setText(student.getGender());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }
}
