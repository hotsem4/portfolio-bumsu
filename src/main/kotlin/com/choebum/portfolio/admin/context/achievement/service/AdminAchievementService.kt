package com.choebum.portfolio.admin.context.achievement.service

import com.choebum.portfolio.admin.context.achievement.form.AchievementForm
import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Achievement
import com.choebum.portfolio.domain.repository.AchievementRepository
import jakarta.transaction.Transactional
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

    @Transactional
    fun save(form: AchievementForm) {
        val achievement = form.toEntity()  // id가 없으니 Insert가 나간다.
        achievementRepository.save(achievement)
    }

    @Transactional
    fun update(id: Long, form: AchievementForm) {
        val achievement = form.toEntity(id) // id가 있는 상태로 entity를 Repo에 넘겨줘야 update가 실행된다.
        achievementRepository.save(achievement)
    }
}