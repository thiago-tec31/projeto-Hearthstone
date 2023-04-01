package com.example.projecthearthstone.domain.usecase

import com.example.projecthearthstone.core.model.ApiError
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards
import com.example.projecthearthstone.domain.repository.InsiderHearthstoneRepository

interface InsiderHearthstoneUseCase {
    suspend fun getFiguresAll(filter: CardType?, cardName: String?): Resource<List<FigureCards>>
}

class InsiderHearthstoneUseCaseImpl(
    private val insiderHearthstoneRepository: InsiderHearthstoneRepository
): InsiderHearthstoneUseCase {

    companion object {
        const val MSG_PARAM_EMPTY = "oops! Failed to load figures"
    }

    override suspend fun getFiguresAll(filter: CardType?, cardName: String?): Resource<List<FigureCards>> {
        return if (cardName.isNullOrEmpty() || filter == null) {
            Resource.Fail(ApiError.EXCEPTION, MSG_PARAM_EMPTY)
        } else {
            insiderHearthstoneRepository.getFiguresAll(filter, cardName)
        }
    }

}