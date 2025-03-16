package com.choebum.portfolio.admin.context.introduction.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Introduction
import com.choebum.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service


@Service
class AdminIntroductionService(
    private val introductionRepository: IntroductionRepository
) {
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()

        return TableDTO.from(classInfo, entites = entities)
    }
}