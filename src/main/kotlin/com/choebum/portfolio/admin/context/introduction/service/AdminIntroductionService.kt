package com.choebum.portfolio.admin.context.introduction.service

import com.choebum.portfolio.admin.context.introduction.form.IntroductionForm
import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Introduction
import com.choebum.portfolio.domain.repository.IntroductionRepository
import jakarta.transaction.Transactional
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

    @Transactional
    fun save(form: IntroductionForm) {
        val introduction = form.toEntity()  // id가 없으니 Insert가 나간다.
        introductionRepository.save(introduction)
    }

    @Transactional
    fun update(id: Long, form: IntroductionForm) {
        val introduction = form.toEntity(id) // id가 있는 상태로 entity를 Repo에 넘겨줘야 update가 실행된다.
        introductionRepository.save(introduction)
    }

}