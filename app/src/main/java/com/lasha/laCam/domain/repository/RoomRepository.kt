package com.lasha.laCam.domain.repository

import com.lasha.laCam.data.model.Photo
import javax.inject.Singleton

@Singleton
interface RoomRepository{
    suspend fun getAllRoomData(): List<Photo>
    suspend fun getSortedRoomData(): List<Photo>
    suspend fun insertRoom(photo: Photo)
}