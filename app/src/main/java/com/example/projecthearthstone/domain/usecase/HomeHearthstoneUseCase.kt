package com.example.projecthearthstone.domain.usecase

import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.InfoCards
import com.example.projecthearthstone.domain.repository.HomeHearthstoneRepository

interface HomeHearthstoneUseCase {
    suspend fun getInfoCards(): Resource<InfoCards>
}

class HomeHearthstoneUseCaseImpl(
    private val homeHearthstoneRepository: HomeHearthstoneRepository
): HomeHearthstoneUseCase {
    override suspend fun getInfoCards(): Resource<InfoCards> = homeHearthstoneRepository.getInfoCards()
}