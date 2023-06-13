package com.tugraz.studybuddy.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public abstract class BaseRepository {

    protected final FirebaseAuth auth = FirebaseAuth.getInstance();
    protected final FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected String getCurrentUserId() {
        var user = auth.getCurrentUser();
        return user == null ? "" : user.getUid();
    }
}
