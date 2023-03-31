package com.example.projecthearthstone.domain.repository

import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.InfoCards

interface HomeHearthstoneRepository {
    suspend fun getInfoCards(): Resource<InfoCards>
}