package com.choebum.portfolio.admin.context.experience.controller

import com.choebum.portfolio.admin.context.experience.form.ExperienceForm
import com.choebum.portfolio.admin.context.experience.service.AdminExperienceService
import com.choebum.portfolio.admin.data.ApiResponse
import com.choebum.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/experiences")
class AdminExperienceApiController(
    private val adminExperienceService: AdminExperienceService
) {
    @PostMapping
    fun postExperience(@RequestBody @Validated form: ExperienceForm): ResponseEntity<Any> {
        adminExperienceService.save(form)
        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putExperience(
        @PathVariable id: Long,
        @RequestBody @Validated form: ExperienceForm
    ): ResponseEntity<Any> {
        adminExperienceService.update(id, form)
        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getExperience(@PathVariable id: Long): TableDTO {
        return adminExperienceService.getExperienceDetailTable(id)
    }
}