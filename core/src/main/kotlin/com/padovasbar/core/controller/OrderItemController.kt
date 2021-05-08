package com.padovasbar.core.controller

import com.padovasbar.core.dto.OrderItemsResponseDTO
import com.padovasbar.core.dto.OrderResponseDTO
import com.padovasbar.core.model.Order
import com.padovasbar.core.model.OrderItems
import com.padovasbar.core.model.Status
import com.padovasbar.core.repository.OrderItemRepository
import com.padovasbar.core.repository.OrderRepository
import com.padovasbar.core.repository.ProductRepository
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/order/item")
class OrderItemController(private val orderRepository: OrderRepository,
                          private val orderItemRepository: OrderItemRepository,
                          private val productRepository: ProductRepository
) {

    @PatchMapping("/{id}")
    fun create(@PathVariable id: Long, @RequestBody orderItemRequest: OrderItems): OrderItems {
        val orderItem = orderItemRepository.findById(id).get()
        orderItem.productId = orderItemRequest.productId ?: orderItem.productId
        orderItem.quantity = orderItemRequest.quantity ?: orderItem.quantity

        return orderItemRepository.save(orderItem)
    }

}