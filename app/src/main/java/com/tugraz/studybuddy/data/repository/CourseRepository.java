package com.tugraz.studybuddy.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.SetOptions;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;
import com.tugraz.studybuddy.data.model.SharedCourseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class CourseRepository extends BaseRepository implements ICourseRepository {

    private static final String TAG = "CourseRepository";
    private static final String COURSE_COLLECTION = "courses";
    private static final String SHARED_COURSE_COLLECTION = "course-codes";
    private static final String CARD_COLLECTION = "cards";

    @Inject
    public CourseRepository() {}

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
                .set(entity, SetOptions.mergeFields(CourseModel.MUTABLE_FIELDS))
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

    public MutableLiveData<List<CardModel>> getAllCards(String courseId) {
        var liveCards = new MutableLiveData<List<CardModel>>();

        db.collection(COURSE_COLLECTION)
                .document(courseId)
                .collection(CARD_COLLECTION)
                .addSnapshotListener((value, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure getting cards", exception);
                    }

                    if (value != null) {
                        liveCards.postValue(value.toObjects(CardModel.class));
                    }
                });

        return liveCards;
    }

    @Override
    public void addCard(String courseId, CardModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(courseId)
                .collection(CARD_COLLECTION)
                .add(entity)
                .addOnSuccessListener(document -> Log.d(TAG, "Success adding card"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure adding card", exception));
    }

    @Override
    public void updateCard(String courseId, CardModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(courseId)
                .collection(CARD_COLLECTION)
                .document(entity.getId())
                .set(entity, SetOptions.mergeFields(CardModel.MUTABLE_FIELDS))
                .addOnSuccessListener(unused -> Log.d(TAG, "Success updating card"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure updating card", exception));
    }

    @Override
    public void deleteCard(String courseId, CardModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(courseId)
                .collection(CARD_COLLECTION)
                .document(entity.getId())
                .delete()
                .addOnSuccessListener(unused -> Log.d(TAG, "Success deleting card"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure deleting card", exception));
    }

    public CourseModel getCourseById(String courseId) {
        List<SharedCourseModel> sharedCourses = new ArrayList<>();
        Log.w(TAG, "get all shared courses");
        DocumentReference ref = db.collection(COURSE_COLLECTION).document(courseId);
        DocumentSnapshot tmp;
        try {
            tmp = Tasks.await(ref.get());
            if(tmp.exists()){
                return tmp.toObject(CourseModel.class);
            }
            return null;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfCourseId(String courseId){
        List<CourseModel> courses = new ArrayList<>();
        db.collection(COURSE_COLLECTION)
                .addSnapshotListener((value, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure getting documents", exception);
                    }
                    if (value != null) {
                        courses.addAll(value.toObjects(CourseModel.class));
                    }
                });
        for (CourseModel course : courses) {
            Log.w(TAG, course.getId() +  courseId);
            if(course.getId().equals(courseId)){
                return true;
            }
        }
        return false;
    }

    public void addSharedCourse(SharedCourseModel entity) {
        db.collection(COURSE_COLLECTION)
                .add(entity)
                .addOnSuccessListener(document -> Log.d(TAG, "Success adding card"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure adding card", exception));

    }
}
