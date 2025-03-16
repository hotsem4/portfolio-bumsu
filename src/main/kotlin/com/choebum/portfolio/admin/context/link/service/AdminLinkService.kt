package com.choebum.portfolio.admin.context.link.service

import com.choebum.portfolio.admin.context.link.form.LinkForm
import com.choebum.portfolio.admin.data.TableDTO
import com.choebum.portfolio.domain.entity.Link
import com.choebum.portfolio.domain.repository.LinkRepository
import jakarta.transaction.Transactional
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

    @Transactional
    fun save(form: LinkForm) {
        val link = form.toEntity()  // id가 없으니 Insert가 나간다.
        linkRepository.save(link)
    }

    @Transactional
    fun update(id: Long, form: LinkForm) {
        val link = form.toEntity(id) // id가 있는 상태로 entity를 Repo에 넘겨줘야 update가 실행된다.
        linkRepository.save(link)
    }
}