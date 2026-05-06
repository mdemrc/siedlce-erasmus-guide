package com.siedlce.erasmusguide.data.remote

/*
 * Firebase Firestore implementation TEMPLATE.
 *
 * Kept fully commented out so the project compiles without Firebase
 * dependencies or `google-services.json`. To enable remote sync:
 *
 *   1. Add to android/build.gradle.kts:
 *        classpath("com.google.gms:google-services:4.4.0")
 *   2. Add to android/app/build.gradle.kts:
 *        plugins { id("com.google.gms.google-services") }
 *        implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
 *        implementation("com.google.firebase:firebase-firestore-ktx")
 *   3. Drop your `google-services.json` into android/app/.
 *   4. Uncomment the class below.
 *   5. In RemoteModule, bind FirestorePoiSource instead of NoopRemotePoiSource.
 *
 * Firestore document shape (collection: "pois") should mirror Poi.kt.
 */

// import com.google.firebase.firestore.FirebaseFirestore
// import com.google.firebase.firestore.ktx.firestore
// import com.google.firebase.firestore.ktx.toObjects
// import com.google.firebase.ktx.Firebase
// import com.siedlce.erasmusguide.data.model.Poi
// import kotlinx.coroutines.tasks.await
// import javax.inject.Inject
// import javax.inject.Singleton
//
// @Singleton
// class FirestorePoiSource @Inject constructor(
//     private val firestore: FirebaseFirestore = Firebase.firestore
// ) : RemotePoiSource {
//
//     override suspend fun fetchPois(): List<Poi>? = runCatching {
//         firestore.collection("pois").get().await().toObjects(Poi::class.java)
//     }.getOrNull()
// }
