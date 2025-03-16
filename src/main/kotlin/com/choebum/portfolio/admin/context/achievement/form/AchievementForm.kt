package com.choebum.portfolio.admin.context.achievement.form

import com.choebum.portfolio.domain.entity.Achievement
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

// 일종의 DTO 클래스
data class AchievementForm(

    @field:NotBlank(message = "필수값입니다.")
    val title: String,

    val description: String,
    val host: String,
    val achievedDate: String,
    val isActive: Boolean,
) {
    fun toEntity(): Achievement {
        return Achievement(
            title = this.title,
            description = this.description,
            host = this.host,
            achievedDate = LocalDate.parse(this.achievedDate),
            isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Achievement {
        val achievement = this.toEntity()
        achievement.id = id

        return achievement
    }
}