package com.padovasbar.core.controller

import com.padovasbar.core.dto.OrderItemsResponseDTO
import com.padovasbar.core.dto.OrderResponseDTO
import com.padovasbar.core.model.Order
import com.padovasbar.core.model.Status
import com.padovasbar.core.repository.OrderItemRepository
import com.padovasbar.core.repository.OrderRepository
import com.padovasbar.core.repository.ProductRepository
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/order")
class OrderController(private val orderRepository: OrderRepository,
                      private val orderItemRepository: OrderItemRepository,
                      private val productRepository: ProductRepository
) {

    @PostMapping
    fun create(@RequestBody order: Order): Order {
        order.status = Status.OPEN
        order.statusChangedAt = LocalDateTime.now()
        return orderRepository.save(order)
    }

    @GetMapping
    fun getAll(): MutableList<OrderResponseDTO> {
        val response = mutableListOf<OrderResponseDTO>()

        val orders = orderRepository.findAll()

        for(order in orders){
            val orderItems = orderItemRepository.findAllByOrderId(order.orderId!!)
            val orderItemsResponse = mutableListOf<OrderItemsResponseDTO>()

            for(item in orderItems){
                val orderItemsResponseDTO = OrderItemsResponseDTO(item.orderItemId, item.orderId, item.productId, getProductName(item.productId), item.quantity, item.itemOrderedAt)
                orderItemsResponse.add(orderItemsResponseDTO)
            }

            val orderResponseDTO = OrderResponseDTO(order.orderId, order.name, order.status!!, order.statusChangedAt!!, orderItemsResponse)
            response.add(orderResponseDTO)
        }

        return response
    }

    fun getProductName(productId: Long) : String {
       return productRepository.findById(productId).get().name
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = orderRepository.deleteById(id)

}