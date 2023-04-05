package com.tugraz.studybuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.Course;
import com.tugraz.studybuddy.R;

import java.util.List;

public class CustomCourseAdapter extends RecyclerView.Adapter<CustomCourseAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCourseName;
        public TextView textViewCourseDescription;
        public Button buttonCourseEdit;
        public Button buttonCoursePlay;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewCourseName = itemView.findViewById(R.id.course_name);
            textViewCourseDescription = itemView.findViewById(R.id.course_description);
            buttonCourseEdit = itemView.findViewById(R.id.button_edit_course);
            buttonCoursePlay = itemView.findViewById(R.id.button_play_course);
        }
    }

    private List<Course> courseList;

    public CustomCourseAdapter(List<Course> courses) {
        courseList = courses;
    }

    @Override
    public CustomCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.course_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomCourseAdapter.ViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.textViewCourseName.setText(course.getName());
        holder.textViewCourseDescription.setText(course.getDescription());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}