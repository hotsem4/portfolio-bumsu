package com.choebum.portfolio.admin.context.skill.service

import com.choebum.portfolio.admin.context.skill.form.SkillForm
import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Skill
import com.choebum.portfolio.domain.repository.SkillRepository
import jakarta.transaction.Transactional
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

    @Transactional
    fun save(form: SkillForm) {
        val skill = form.toEntity()  // id가 없으니 Insert가 나간다.
        skillRepository.save(skill)
    }

    @Transactional
    fun update(id: Long, form: SkillForm) {
        val skill = form.toEntity(id) // id가 있는 상태로 entity를 Repo에 넘겨줘야 update가 실행된다.
        skillRepository.save(skill)
    }
}