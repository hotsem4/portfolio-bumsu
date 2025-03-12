package com.choebum.portfolio.presentation.repository

import com.choebum.portfolio.domain.entity.*
import com.choebum.portfolio.domain.repository.*
import org.springframework.stereotype.Repository

/**
 * Facade 패턴을 사용한 이유는?
 * Service에서 필요한 데이터를 한 번에 조회하려고 할 때, 여러 개의 Repository에서 데이터를 가져와야 하는 경우가 많다.
 * 만약 Facade 패턴 없이 개별 Repository를 직접 Service에서 호출한다면, Service에 여러 개의 Repository를 주입해야 하는 문제가 발생한다.
 *
 * 이렇게 되면 Repository의 변경이 있을 때마다 Service 코드도 함께 수정해야 하며, Service가 불필요하게 Repository의 내부 구현을 알아야 하는 문제가 생긴다.
 * 이런 문제를 해결하기 위해 PresentationRepository를 만들어, Service에서는 오직 PresentationRepository만 호출하면 필요한 데이터를 조회할 수 있도록 하였다.
 *
 * 이 방식의 또 다른 장점은 코드의 간결함이다.
 * Service에서 여러 개의 Repository를 직접 호출하면 코드가 복잡하고 지저분해질 수 있지만,
 * PresentationRepository를 통해 데이터를 조회하면 Service 코드가 깔끔하게 유지되며, 가독성과 유지보수성이 향상된다.
 */

@Repository
class PresentationRepository(
    private val achievementRepository: AchievementRepository,
    private val skillRepository: SkillRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val experienceRepository: ExperienceRepository,
    private val projectRepository: ProjectRepository,
) {
    fun getActiveAchievements(): List<Achievement> {
        return achievementRepository.findAllByIsActive(true)
    }

    fun getActiveExperiences(): List<Experience> {
        return experienceRepository.findAllByIsActive(true)
    }

    fun getActiveIntroductions(): List<Introduction> {
        return introductionRepository.findAllByIsActive(true)
    }

    fun getActiveSkills(): List<Skill> {
        return skillRepository.findAllByIsActive(true)
    }

    fun getActiveLinks(): List<Link> {
        return linkRepository.findAllByIsActive(true)
    }

    fun getActiveProjects(): List<Project> {
        return projectRepository.findAllByIsActive(true)
    }

}
