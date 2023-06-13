package com.tugraz.studybuddy.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tugraz.studybuddy.data.model.CourseModel;

import java.util.List;

import javax.inject.Inject;

public class CourseRepository extends BaseRepository implements ICourseRepository<CourseModel> {

    private static final String TAG = "CourseRepository";
    private static final String COURSE_COLLECTION = "courses";

    @Inject
    public CourseRepository() {
    }

    @Override
    public MutableLiveData<List<CourseModel>> getAll() {
        var liveCourses = new MutableLiveData<List<CourseModel>>();

        db.collection(COURSE_COLLECTION)
                .whereEqualTo("userId", getCurrentUserId())
                .addSnapshotListener((value, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure getting documents", exception);
                    }

                    if (value != null) {
                        liveCourses.postValue(value.toObjects(CourseModel.class));
                    }
                });

        return liveCourses;
    }

    @Override
    public MutableLiveData<CourseModel> getById(String id) {
        var course = new MutableLiveData<CourseModel>();

        db.collection(COURSE_COLLECTION)
                .document(id)
                .addSnapshotListener(((value, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure getting document", exception);
                    }

                    if (value != null) {
                        course.postValue(value.toObject(CourseModel.class));
                    }
                }));

        return course;
    }

    @Override
    public void add(CourseModel entity) {
        entity.setUserId(getCurrentUserId());

        db.collection(COURSE_COLLECTION)
                .add(entity)
                .addOnSuccessListener(document -> Log.d(TAG, "Success adding document"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure adding document", exception));
    }

    @Override
    public void update(CourseModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(entity.getId())
                .set(entity)
                .addOnSuccessListener(unused -> Log.d(TAG, "Success updating document"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure updating document", exception));
    }

    @Override
    public void delete(CourseModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(entity.getId())
                .delete()
                .addOnSuccessListener(unused -> Log.d(TAG, "Success deleting document"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure deleting document", exception));
    }
}
