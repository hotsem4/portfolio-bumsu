package com.choebum.portfolio.admin.context.project.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.admin.exception.AdminBadRequestException
import com.choebum.portfolio.domain.entity.Project
import com.choebum.portfolio.domain.entity.ProjectDetail
import com.choebum.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository,
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Project::class
        val entites = projectRepository.findAll()

        return TableDTO.from(classInfo, entites, "details", "skills")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ProjectDetail::class
        val entities = if (id != null) projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 업습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}