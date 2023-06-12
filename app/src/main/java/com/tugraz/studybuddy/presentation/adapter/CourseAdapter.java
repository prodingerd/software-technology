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
import com.tugraz.studybuddy.presentation.generic.IClickListener;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewCourseName;
        private final TextView textViewCourseDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewCourseName = itemView.findViewById(R.id.course_name);
            textViewCourseDescription = itemView.findViewById(R.id.course_description);
        }

        public void bind(CourseModel course, IClickListener<CourseModel> onClickListener) {
            textViewCourseName.setText(course.getName());
            textViewCourseDescription.setText(course.getDescription());
            itemView.setOnClickListener(v -> onClickListener.onItemClick(course));
            itemView.setOnLongClickListener(v -> onClickListener.longOnItemClick(course));
        }
    }

    private final List<CourseModel> courses;
    private final IClickListener<CourseModel> onClickListener;

    public CourseAdapter(List<CourseModel> courses, IClickListener<CourseModel> onClickListener) {
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
}
