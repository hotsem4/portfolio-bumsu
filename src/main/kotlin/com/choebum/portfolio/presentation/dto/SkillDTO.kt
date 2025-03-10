package com.choebum.portfolio.presentation.dto

import com.choebum.portfolio.domain.entity.Skill

data class SkillDTO(
    val name: String,
    val type: String,
) {
    constructor(skill: Skill) : this(
        name = skill.name,
        type = skill.type.name
    )
}