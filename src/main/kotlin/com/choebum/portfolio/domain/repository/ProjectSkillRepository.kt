package com.choebum.portfolio.domain.repository

import com.choebum.portfolio.domain.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProjectSkillRepository : JpaRepository<ProjectSkill, Long> {
    // select * from project_skill where project_id = :projectId and skill_id = skillId
    fun findByProjectIdAndSkillId(projectId: String, skillId: String): Optional<ProjectSkill>
}