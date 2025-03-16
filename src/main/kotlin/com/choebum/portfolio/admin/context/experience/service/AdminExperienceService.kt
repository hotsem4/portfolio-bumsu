package com.choebum.portfolio.admin.context.experience.service

import com.choebum.portfolio.admin.context.experience.form.ExperienceForm
import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.admin.exception.AdminBadRequestException
import com.choebum.portfolio.domain.entity.Experience
import com.choebum.portfolio.domain.entity.ExperienceDetail
import com.choebum.portfolio.domain.repository.ExperienceRepository
import jakarta.transaction.Transactional
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

    @Transactional
    fun save(form: ExperienceForm) {
        val experienceDetails = form.details
            ?.map { detail -> detail.toEntity() }
            ?.toMutableList()
        val experience = form.toEntity()
        experience.addDetails(experienceDetails)

        experienceRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ExperienceForm) {

        val experience = experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터가 존재하지 않습니다.") }

        experience.update(
            title = form.title,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive,
        )

        // {id: experienceDetail}
        val detailMap = experience.details.map { it.id to it }.toMap()
        form.details?.forEach {
            val entity = detailMap.get(it.id)
            if (entity != null) {
                entity.update(
                    content = it.content,
                    isActive = it.isActive,
                )
            } else {
                experience.details.add(it.toEntity())
            }
        }

    }
}