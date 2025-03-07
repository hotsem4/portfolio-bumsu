package com.choebum.portfolio.domain.repository

import com.choebum.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long> {
    fun findAllByIsActive(isActive: Boolean): List<Experience>


//    override fun findById(id: Long): Optional<Experience>
    /**
     * 상위에 정의되어 있는 findById를 왜 override할까?
     * 그 이유는 Experience가 1:다 매핑이되어 있기 때문에 기존 정의된 그대로 사용하게 될 경우
     * 성능상에 치명적인 이슈가 발생할 수 있기 때문이다.
     */
}