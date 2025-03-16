package com.choebum.portfolio.admin.context.project.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.repository.ProjectRepository
import com.choebum.portfolio.domain.repository.SkillRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository
) {
    @Transactional
    fun getProjectSkillTable(): TableDTO {
        val projects = projectRepository.findAll()
        val columns = mutableListOf<String>(
            "id",
            "projectId",
            "projectName",
            "skillId",
            "skillName",
            "createdDateTime",
            "updatedDateTime"
        )  // list<String>

        val records = mutableListOf<MutableList<String>>()
        for (project in projects) {
            project.skills.forEach {
                val record = mutableListOf<String>()
                record.add(it.id.toString())
                record.add(it.project.id.toString())
                record.add(it.project.name)
                record.add(it.skill.id.toString())
                record.add(it.skill.name)
                record.add(it.createdDateTime.toString())
                record.add(it.updateDateTime.toString())

                records.add(record)
            }
        }
        return TableDTO(name = "ProjectSkill", columns = columns, records = records)
    }

    fun getProjectList(): List<String> {
        val projects = projectRepository.findAll()

        return projects.map { "${it.id} ${it.name}" }.toList()
    }

    fun getSkillList(): List<String> {
        val skills = skillRepository.findAll()

        return skills.map { "${it.id} ${it.name}" }.toList()

    }
}