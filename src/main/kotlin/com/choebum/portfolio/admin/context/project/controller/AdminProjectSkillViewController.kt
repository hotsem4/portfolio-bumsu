package com.choebum.portfolio.admin.context.project.controller

import com.choebum.portfolio.admin.context.project.service.AdminProjectSkillService
import com.choebum.portfolio.admin.data.FormElementDTO
import com.choebum.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
    private val adminProjectSkillService: AdminProjectSkillService,
) {
    @GetMapping
    fun projectSkill(model: Model): String {
        val projectList = adminProjectSkillService.getProjectList()
        val skillList = adminProjectSkillService.getSkillList()

        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("project", 8, projectList),
            SelectFormElementDTO("skill", 4, skillList),
        )
        model.addAttribute("formElements", formElements)

        val table = adminProjectSkillService.getProjectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttribute = mutableMapOf<String, Any>(
            Pair("menuName", "Projects"),
            Pair("page Name", table.name),
            Pair("editable", false),
            Pair("deletable", true),
            Pair("hasDetails", false),
        )
        model.addAllAttributes(pageAttribute)
        return "admin/page-table"
    }
}