package com.choebum.portfolio.admin.context.link.service

import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Link
import com.choebum.portfolio.domain.repository.LinkRepository
import org.springframework.stereotype.Service


@Service
class AdminLinkService(
    private val linkRepository: LinkRepository,
) {
    fun getLinkTable(): TableDTO {
        val classInfo = Link::class
        val entities = linkRepository.findAll()

        return TableDTO.from(classInfo, entites = entities)
    }
}