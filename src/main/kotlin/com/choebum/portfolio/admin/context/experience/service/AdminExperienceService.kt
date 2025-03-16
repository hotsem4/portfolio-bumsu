package com.choebum.portfolio.admin.context.experience.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.admin.exception.AdminBadRequestException
import com.choebum.portfolio.domain.entity.Experience
import com.choebum.portfolio.domain.entity.ExperienceDetail
import com.choebum.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository,
) {
    fun getExperienceTable(): TableDTO {
        val classInfo = Experience::class
        val entites = experienceRepository.findAll()

        return TableDTO.from(classInfo, entites, "details")
    }

    fun getExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 업습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}