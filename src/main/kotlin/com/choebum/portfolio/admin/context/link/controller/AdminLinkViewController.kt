package com.choebum.portfolio.admin.context.link.controller

import com.choebum.portfolio.admin.context.link.service.AdminLinkService
import com.choebum.portfolio.admin.data.FormElementDTO
import com.choebum.portfolio.admin.data.SelectFormElementDTO
import com.choebum.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/link")
class AdminLinkViewController(
    private val adminLinkService: AdminLinkService,
) {
    @GetMapping
    fun link(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 8),
            TextFormElementDTO("name", 2),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("formElements", formElements)

        val table = adminLinkService.getLinkTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttribute = mutableMapOf<String, Any>(
            Pair("menuName", "Index"),
            Pair("page Name", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false),
        )
        model.addAllAttributes(pageAttribute)
        return "admin/page-table"
    }
}