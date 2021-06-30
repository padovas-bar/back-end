package com.padovasbar.core.report.controller

import com.padovasbar.core.report.dto.InventoryDTO
import com.padovasbar.core.report.dto.RankDTO
import com.padovasbar.core.report.repository.InventoryRepository
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/report")
class InventoryReportController(
    private val inventoryRepository: InventoryRepository
) {

    @GetMapping("/inventory")
    fun inventory(@RequestParam since: Long): MutableList<InventoryDTO> {
        val inventory = inventoryRepository.inventory(since)
        val items = mutableListOf<InventoryDTO>()

        inventory.forEach {
            items.add(
                InventoryDTO(it.orderItemHistoryId,
                    it.name,
                    it.itemOrderedAt?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))?:"",
                    it.quantity?:0,
                    if(it.price != null && it.quantity != null) it.price!! * it.quantity!! else 0.0
                )
            )
        }

        return items
    }

    @GetMapping("/inventory/product-rank")
    fun productRank(@RequestParam since: Long): MutableList<RankDTO> {
        val rank = inventoryRepository.rank(since)
        val response = mutableListOf<RankDTO>()
        rank.forEach {
            response.add(RankDTO(it.getName(), it.getTotal()))
        }

        return response
    }

}