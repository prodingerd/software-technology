package com.tugraz.studybuddy.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public ViewHolder(View itemView) {
            super(itemView);

            textViewCourseName = itemView.findViewById(R.id.course_name);
            textViewCourseDescription = itemView.findViewById(R.id.course_description);
        }
        public void bind(CourseModel course, OnClickListener onClickListener) {
            textViewCourseName.setText(course.getName());
            textViewCourseDescription.setText(course.getDescription());
            itemView.setOnClickListener(v -> onClickListener.onItemClick(course));
        }
    }

    private final List<CourseModel> courses;
    private final OnClickListener onClickListener;

    public CourseAdapter(List<CourseModel> courses, OnClickListener onClickListener) {
        this.courses = courses;
        this.onClickListener = onClickListener;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(courses.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public interface OnClickListener {
        void onItemClick(CourseModel course);
    }
}