package com.choebum.portfolio.admin.context.skill.controller

import com.choebum.portfolio.admin.context.skill.service.AdminSkillService
import com.choebum.portfolio.admin.data.FormElementDTO
import com.choebum.portfolio.admin.data.SelectFormElementDTO
import com.choebum.portfolio.admin.data.TextFormElementDTO
import com.choebum.portfolio.domain.constant.SkillType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/skill")
class AdminSkillViewController(
    private val adminSkillService: AdminSkillService
) {
    @GetMapping
    fun skill(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 2),
            SelectFormElementDTO("type", 2, listOf(SkillType.values().map { it.name })),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("formElements", formElements)

        val table = adminSkillService.getSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttribute = mutableMapOf<String, Any>(
            Pair("menuName", "Resume"),
            Pair("page Name", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false),
        )
        model.addAllAttributes(pageAttribute)
        return "admin/page-table"
    }
}