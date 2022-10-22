package com.pe.practiceexam.db;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.practiceexam.R;
import com.pe.practiceexam.ui.MainActivity;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentList;
    private Activity context;
    private StudentDatabase database;

    @SuppressLint("NotifyDataSetChanged")
    public StudentAdapter(Activity context, List<Student> studentList){
        this.context = context;
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        database = StudentDatabase.getInstance(context);

        holder.id.setText(String.valueOf(student.getId()));
        holder.fullname.setText(student.getFullname());
        holder.birthday.setText(student.getBirthday());
        holder.average_score.setText(String.valueOf(student.getAverage_score()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id, fullname, birthday, average_score;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            fullname = itemView.findViewById(R.id.item_fullname);
            birthday = itemView.findViewById(R.id.item_birthday);
            average_score = itemView.findViewById(R.id.item_average_score);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView edt_id = context.findViewById(R.id.item_id);
                    TextView edt_fullname = context.findViewById(R.id.item_fullname);
                    TextView edt_birthday = context.findViewById(R.id.item_birthday);
                    TextView edt_average_score = context.findViewById(R.id.item_average_score);

                    edt_id.setText(id.getText().toString());
                    edt_fullname.setText(fullname.getText().toString());
                    edt_birthday.setText(birthday.getText().toString());
                    edt_average_score.setText(average_score.getText().toString());
                }
            });
        }
    }
}
