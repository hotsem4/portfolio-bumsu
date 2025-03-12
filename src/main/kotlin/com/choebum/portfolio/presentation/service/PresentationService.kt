package com.choebum.portfolio.presentation.service

import com.choebum.portfolio.presentation.dto.IntroductionDTO
import com.choebum.portfolio.presentation.dto.LinkDTO
import com.choebum.portfolio.presentation.dto.ProjectDTO
import com.choebum.portfolio.presentation.dto.ResumeDTO
import com.choebum.portfolio.presentation.repository.PresentationRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    @Transactional(readOnly = true)
    // readOnly를 하게 되면 읽기 전용이 되서 수정이 불가능한 Transactional이 된다.
    // readOnly를 하게 되면 스냅샷 작업을 생략한다. 왜냐 스냅샷을 남기는 이유가 Dirty-checking을 하기 위해서 이다. 하지만 수정은 없다고 명시해줬기 때문!
    fun getIntroductions(): List<IntroductionDTO> {
        val introductions = presentationRepository.getActiveIntroductions()
        return introductions.map { IntroductionDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getLinks(): List<LinkDTO> {
        val links = presentationRepository.getActiveLinks()
        return links.map { LinkDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getResume(): ResumeDTO {
        val experiences = presentationRepository.getActiveExperiences()
        val achievements = presentationRepository.getActiveAchievements()
        val skills = presentationRepository.getActiveSkills()

        return ResumeDTO(
            experiences = experiences,
            achievements = achievements,
            skills = skills
        )
    }

    @Transactional(readOnly = true)
    fun getProjects(): List<ProjectDTO> {
        val projects = presentationRepository.getActiveProjects()
        return projects.map { ProjectDTO(it) }
    }
}