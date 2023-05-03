package com.tugraz.studybuddy.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CourseModel;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

    private final List<CourseModel> courses;

    public CourseAdapter(List<CourseModel> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.course_list_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(CourseAdapter.ViewHolder holder, int position) {
        CourseModel course = courses.get(position);

        holder.textViewCourseName.setText(course.getName());
        holder.textViewCourseDescription.setText(course.getDescription());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}