package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    // create the list to take the data to bind it to the view holder
    List<Student> allStudentData = new ArrayList<>();

    // create a public constructor to be used in the activity (to set the data to the adapter)
    public  StudentAdapter (ArrayList<Student> allStudentData){
        this.allStudentData = allStudentData;

    }

    // create the view holder class (wrap the data and the view)
    //note : we create a static inner class inorder to create a view holder without create instances

    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        // create a model object
        public Student student;

        // create the view object
        View itemView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("my Adapter", "Element "+ getAdapterPosition() + " clicked");
                }
            });
        }
    }
    // extends the recycler view adapter


    @NonNull
    @Override
    // this method will create a view to be represented in the UI.
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_student, parent , false);

//        StudentViewHolder studentViewHolder = new StudentViewHolder(view);

        return  new StudentViewHolder(view);
    }

    // this method will bind the data from the adapter to the view holder that we create
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        holder.student = allStudentData.get(position);
        TextView studentName = holder.itemView.findViewById(R.id.std);
        TextView enrollYear = holder.itemView.findViewById(R.id.enrollYear);

        studentName.setText(holder.student.student);
        enrollYear.setText(Integer.toString(holder.student.enrollYear));

    }

    @Override
    // return the number of items that I have in my data
    // will use by the recyclerView inorder to create a fragment as much as we need to cover the list of data
    public int getItemCount() {

        return allStudentData.size();
    }
}
