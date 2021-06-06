package com.padovasbar.core.report.controller

import com.padovasbar.core.report.repository.OutcomeRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/report")
class ReportController(private val outcomeRepository: OutcomeRepository) {

    @GetMapping("/outcome")
    fun outcome(@RequestParam since: Long) = outcomeRepository.outcome(since)
}