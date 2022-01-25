package com.survivalcoding.ifkakao.data.datasource.local

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class MockLocalDataSource : SessionLocalRepository {
    var nextId = 1
    private var likes = listOf<Like>()

    override suspend fun getLikes(): List<Like> = likes

    override suspend fun addLike(like: Like) {
        likes = likes.plus(like.copy(id = nextId))
        nextId += 1
    }

    override suspend fun deleteLike(like: Like) {
        likes = likes.minus(like)
    }
}