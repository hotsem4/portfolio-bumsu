package com.choebum.portfolio.admin.context.achievement.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Achievement
import com.choebum.portfolio.domain.repository.AchievementRepository
import org.springframework.stereotype.Service


@Service
class AdminAchievementService(
    private val achievementRepository: AchievementRepository,
) {
    fun getAchievementTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = achievementRepository.findAll()

        return TableDTO.from(classInfo, entites = entities)
    }
}