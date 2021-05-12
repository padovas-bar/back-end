package com.padovasbar.core.controller

import com.padovasbar.core.dto.OrderItemsResponseDTO
import com.padovasbar.core.dto.OrderResponseDTO
import com.padovasbar.core.dto.Resume
import com.padovasbar.core.model.Order
import com.padovasbar.core.model.OrderHistory
import com.padovasbar.core.model.OrderItemsHistory
import com.padovasbar.core.model.PartialPaymentHistory
import com.padovasbar.core.model.Status
import com.padovasbar.core.repository.OrderHistoryRepository
import com.padovasbar.core.repository.OrderItemHistoryRepository
import com.padovasbar.core.repository.OrderItemRepository
import com.padovasbar.core.repository.OrderRepository
import com.padovasbar.core.repository.PartialPaymentHistoryRepository
import com.padovasbar.core.repository.PartialPaymentRepository
import com.padovasbar.core.repository.ProductRepository
import java.lang.RuntimeException
import java.time.LocalDateTime
import javax.transaction.Transactional
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
@RequestMapping("/order")
class OrderController(private val orderRepository: OrderRepository,
                      private val orderItemRepository: OrderItemRepository,
                      private val productRepository: ProductRepository,
                      private val orderHistoryRepository: OrderHistoryRepository,
                      private val orderItemHistoryRepository: OrderItemHistoryRepository,
                      private val partialPaymentRepository: PartialPaymentRepository,
                      private val partialPaymentHistoryRepository: PartialPaymentHistoryRepository
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
            val orderItems = orderItemRepository.findAllByOrderIdOrderByOrderItemIdAsc(order.orderId!!)
            val orderItemsResponse = mutableListOf<OrderItemsResponseDTO>()

            for(item in orderItems){
                val orderItemsResponseDTO = OrderItemsResponseDTO(item.orderItemId, item.orderId, item.productId, null, null, item.quantity, item.itemOrderedAt)
                getProductInfo(orderItemsResponseDTO)
                orderItemsResponse.add(orderItemsResponseDTO)
            }

            val orderResponseDTO = OrderResponseDTO(order.orderId, order.name, order.status!!, order.statusChangedAt!!, orderItemsResponse)
            response.add(orderResponseDTO)
        }

        return response
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): OrderResponseDTO {
        val order = orderRepository.findById(id).get()
        val response = OrderResponseDTO(order.orderId!!, order.name, order.status!!, order.statusChangedAt!!, null)

        val orderItems = orderItemRepository.findAllByOrderIdOrderByOrderItemIdAsc(id)
        val orderItemsResponse = mutableListOf<OrderItemsResponseDTO>()

        for(item in orderItems){
            val orderItemsResponseDTO = OrderItemsResponseDTO(item.orderItemId, item.orderId, item.productId, null, null, item.quantity, item.itemOrderedAt)
            getProductInfo(orderItemsResponseDTO)
            orderItemsResponse.add(orderItemsResponseDTO)
        }

        response.orderItems = orderItemsResponse
        return response
    }

    fun getProductInfo(orderItemsResponseDTO: OrderItemsResponseDTO) {
        if(orderItemsResponseDTO.productId != null){
            val product = productRepository.findById(orderItemsResponseDTO.productId).get()
            orderItemsResponseDTO.productName = product.name
            orderItemsResponseDTO.productPrice = product.price
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = orderRepository.deleteById(id)

    @PatchMapping("/{id}/name")
    fun updateOrderName(@PathVariable id: Long, @RequestBody orderRequest: Order): Order {
        val order = orderRepository.findById(id).get()
        order.name = orderRequest.name
        return orderRepository.save(order)
    }

    @Transactional
    @PatchMapping("/{id}/close")
    fun closeOrder(@PathVariable id: Long, @RequestBody resume: Resume) {
        val payments = partialPaymentRepository.findAllByOrderIdOrderByOrderIdAsc(id)
        val items = orderItemRepository.findAllByOrderIdOrderByOrderItemIdAsc(id)
        val order = orderRepository.findById(id).get()

        val orderHistory = OrderHistory(order.orderId, order.name, Status.CLOSED, LocalDateTime.now(), resume.total)

        val itemsHistory = mutableListOf<OrderItemsHistory>()
        for(item in items){
            val product = productRepository.findById(item.productId!!).get()
            itemsHistory.add(
                OrderItemsHistory(item.orderItemId, item.orderId, product.name, product.price, item.quantity, item.itemOrderedAt)
            )
        }

        val partialPayments = mutableListOf<PartialPaymentHistory>()
        for(payment in payments){
            partialPayments.add(
                PartialPaymentHistory(payment.partialPaymentId, payment.orderId, payment.description, payment.value, payment.paidAt)
            )
        }

        orderHistoryRepository.save(orderHistory)
        orderItemHistoryRepository.saveAll(itemsHistory)
        partialPaymentHistoryRepository.saveAll(partialPayments)

        partialPaymentRepository.deleteAllByOrderId(id)
        orderItemRepository.deleteAllByOrderId(id)
        orderRepository.deleteById(id)
    }


}