package com.choebum.portfolio.admin.context.achievement.controller

import com.choebum.portfolio.admin.context.achievement.service.AdminAchievementService
import com.choebum.portfolio.admin.data.DateFormElementDTO
import com.choebum.portfolio.admin.data.FormElementDTO
import com.choebum.portfolio.admin.data.SelectFormElementDTO
import com.choebum.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/achievement")
class AdminAchievementViewController(
    private val adminAchievementService: AdminAchievementService,
) {
    @GetMapping
    fun achievement(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("title", 4),
            TextFormElementDTO("description", 8),
            DateFormElementDTO("achievedDate", 5),
            TextFormElementDTO("host", 5),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("formElements", formElements)

        val table = adminAchievementService.getAchievementTable()
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
