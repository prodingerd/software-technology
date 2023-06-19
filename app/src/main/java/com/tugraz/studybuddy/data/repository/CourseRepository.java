package com.tugraz.studybuddy.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.tugraz.studybuddy.data.model.CardModel;
import com.tugraz.studybuddy.data.model.CourseModel;

import java.util.List;

import javax.inject.Inject;

public class CourseRepository extends BaseRepository implements ICourseRepository {

    private static final String TAG = "CourseRepository";
    private static final String COURSE_COLLECTION = "courses";
    private static final String CARD_COLLECTION = "cards";

    @Inject
    public CourseRepository() {}

    @Override
    public void cloneByShareCode(String shareCode) {
        db.collection(COURSE_COLLECTION)
                .whereEqualTo("shareCode", shareCode)
                .addSnapshotListener((courseSnapshot, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure cloning course", exception);
                    }

                    if (courseSnapshot == null || courseSnapshot.isEmpty()) {
                        return;
                    }

                    var oldCourse = courseSnapshot.toObjects(CourseModel.class)
                            .stream().findFirst().orElseThrow(RuntimeException::new);

                    var newCourse = new CourseModel(oldCourse.getName(),
                            oldCourse.getDescription(),
                            oldCourse.prettyExamDate());

                    newCourse.setUserId(getCurrentUserId());

                    db.collection(COURSE_COLLECTION)
                            .add(newCourse)
                            .addOnSuccessListener(document -> {
                                Log.d(TAG, "Success cloning course");

                                db.collection(COURSE_COLLECTION)
                                        .document(oldCourse.getId())
                                        .collection(CARD_COLLECTION)
                                        .addSnapshotListener((cardsSnapshot, exception1) -> {
                                            if (exception1 != null) {
                                                Log.w(TAG, "Failure getting cards", exception1);
                                            }

                                            if (cardsSnapshot == null) {
                                                return;
                                            }

                                            WriteBatch batch = db.batch();

                                            cardsSnapshot.toObjects(CardModel.class).forEach(x ->
                                                    batch.set(document.collection(CARD_COLLECTION).document(), x)
                                            );

                                            batch.commit()
                                                    .addOnSuccessListener(unused -> Log.d(TAG, "Success cloning cards"))
                                                    .addOnFailureListener(exception2 -> Log.w(TAG, "Failure cloning cards", exception2));
                                        });

                            });
                });
    }

    @Override
    public MutableLiveData<List<CourseModel>> getAll() {
        var liveCourses = new MutableLiveData<List<CourseModel>>();

        db.collection(COURSE_COLLECTION)
                .whereEqualTo("userId", getCurrentUserId())
                .addSnapshotListener((value, exception) -> {
                    if (exception != null) {
                        Log.w(TAG, "Failure getting courses", exception);
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
                .addOnSuccessListener(unused -> Log.d(TAG, "Success adding course"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure adding course", exception));
    }

    @Override
    public void update(CourseModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(entity.getId())
                .set(entity, SetOptions.mergeFields(CourseModel.MUTABLE_FIELDS))
                .addOnSuccessListener(unused -> Log.d(TAG, "Success updating course"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure updating course", exception));
    }

    @Override
    public void delete(CourseModel entity) {
        db.collection(COURSE_COLLECTION)
                .document(entity.getId())
                .delete()
                .addOnSuccessListener(unused -> Log.d(TAG, "Success deleting course"))
                .addOnFailureListener(exception -> Log.w(TAG, "Failure deleting course", exception));
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
                .addOnSuccessListener(unused -> Log.d(TAG, "Success adding card"))
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
}
