package com.choebum.portfolio.admin.context.skill.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Skill
import com.choebum.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service


@Service
class AdminSkillService(
    private val skillRepository: SkillRepository,
) {
    fun getSkillTable(): TableDTO {
        val classInfo = Skill::class
        val entities = skillRepository.findAll()

        return TableDTO.from(classInfo, entites = entities)
    }
}