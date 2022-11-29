package com.ourbalance.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.ourbalance.data.source.remote.auth.AuthDataSource
import com.ourbalance.domain.model.auth.UserInfo
import com.ourbalance.domain.result.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) : AuthDataSource {

    private val currentUser = callbackFlow<FirebaseUser?> {
        val authStateListener: ((FirebaseAuth) -> Unit) = { auth ->
            trySend(auth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose { auth.removeAuthStateListener(authStateListener) }
    }

    override suspend fun login(userInfo: UserInfo): Result<Unit> {
        auth.signInWithEmailAndPassword(userInfo.email, userInfo.password).await()
        return if (auth.currentUser != null) Result.Success(Unit) else Result.Error("로그인 실패")
    }

    override suspend fun signup(userInfo: UserInfo): Result<Unit> {
        auth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
            .continueWith { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.updateProfile(
                        UserProfileChangeRequest.Builder().setDisplayName(userInfo.userName).build()
                    )
                }
            }.await()
        return if (auth.currentUser != null) Result.Success(Unit) else Result.Error("회원가입 실패")
    }

    override suspend fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): String {
        TODO("Not yet implemented")
    }

    override fun isLogin(): Flow<Boolean> {
        return currentUser.map { it != null }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}