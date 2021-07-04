package com.padovasbar.core.scheduler

import com.opencsv.CSVWriter
import com.padovasbar.core.repository.CategoryRepository
import com.padovasbar.core.repository.OrderHistoryRepository
import com.padovasbar.core.repository.OrderItemHistoryRepository
import com.padovasbar.core.repository.OrderItemRepository
import com.padovasbar.core.repository.OrderRepository
import com.padovasbar.core.repository.PartialPaymentHistoryRepository
import com.padovasbar.core.repository.PartialPaymentRepository
import com.padovasbar.core.repository.ProductRepository
import com.padovasbar.core.repository.TrustedClientRepository

import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BackupScheduler(private val orderRepository: OrderRepository,
                      private val categoryRepository: CategoryRepository,
                      private val productRepository: ProductRepository,
                      private val orderItemRepository: OrderItemRepository,
                      private val partialPaymentRepository: PartialPaymentRepository,
                      private val orderHistoryRepository: OrderHistoryRepository,
                      private val orderItemHistoryRepository: OrderItemHistoryRepository,
                      private val partialPaymentHistoryRepository: PartialPaymentHistoryRepository,
                      private val trustedClientRepository: TrustedClientRepository
) {

    private val prefix = "/home/padova/padovas-bar/backup-padovas-bar"
    private val suffix = "backup-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")) + ".csv"

    @Scheduled(fixedRate = 21600000) //21600000 = 6h
    fun backup(){
        println("Iniciando backup de dados as " + LocalDateTime.now())

        makeCategoryCsv()
        makeProductCsv()
        makeOrderCsv()
        makeOrderItemCsv()
        makePartialPaymentCsv()
        makeOrderHistoryCsv()
        makeOrderItemHistoryCsv()
        makePartialPaymentHistoryCsv()
        makeTrustedClientCsv()

        println("Backup de dados finalizado as " + LocalDateTime.now())
    }

    fun makeTrustedClientCsv(){
        val fileWriter = FileWriter("$prefix/trusted_client/trusted_client-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val trustedClients = trustedClientRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(trustedClient in trustedClients){
            records.add(
                arrayOf<String>(
                    trustedClient.trustedClientId.toString(),
                    trustedClient.name,
                    trustedClient.description?:"null"
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makePartialPaymentHistoryCsv(){
        val fileWriter = FileWriter("$prefix/partial_payment_history/partial_payment_history-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val partialPaymentHistories = partialPaymentHistoryRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(partialPaymentHistory in partialPaymentHistories){
            records.add(
                arrayOf<String>(
                    partialPaymentHistory.partialPaymentHistoryId.toString(),
                    partialPaymentHistory.orderHistoryId.toString(),
                    partialPaymentHistory.description?:"null",
                    partialPaymentHistory.value.toString(),
                    partialPaymentHistory.paidAt.toString(),
                    partialPaymentHistory.paymentType.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeOrderItemHistoryCsv(){
        val fileWriter = FileWriter("$prefix/order_item_history/order_item_history-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val orderItemHistories = orderItemHistoryRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(orderItemHistory in orderItemHistories){
            records.add(
                arrayOf<String>(
                    orderItemHistory.orderItemHistoryId.toString(),
                    orderItemHistory.orderHistoryId.toString(),
                    orderItemHistory.name?:"null",
                    orderItemHistory.price.toString(),
                    orderItemHistory.quantity.toString(),
                    orderItemHistory.itemOrderedAt.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeOrderHistoryCsv(){
        val fileWriter = FileWriter("$prefix/order_history/order_history-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val orderHistories = orderHistoryRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(orderHistory in orderHistories){
            records.add(
                arrayOf<String>(
                    orderHistory.orderHistoryId.toString(),
                    orderHistory.name,
                    orderHistory.status.toString(),
                    orderHistory.statusChangedAt.toString(),
                    orderHistory.totalValue.toString(),
                    orderHistory.pendentOwner?:"null",
                    if(orderHistory.paymentType != null) orderHistory.paymentType.toString() else "null"
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makePartialPaymentCsv(){
        val fileWriter = FileWriter("$prefix/partial_payment/partial_payment-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val partialPayments = partialPaymentRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(partialPayment in partialPayments){
            records.add(
                arrayOf<String>(
                    partialPayment.partialPaymentId.toString(),
                    partialPayment.orderId.toString(),
                    partialPayment.description?:"null",
                    partialPayment.value.toString(),
                    partialPayment.paidAt.toString(),
                    partialPayment.paymentType.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeOrderItemCsv(){
        val fileWriter = FileWriter("$prefix/order_item/order_item-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val orderItems = orderItemRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(orderItem in orderItems){
            records.add(
                arrayOf<String>(
                    orderItem.orderItemId.toString(),
                    orderItem.orderId.toString(),
                    orderItem.productId.toString(),
                    orderItem.quantity.toString(),
                    orderItem.itemOrderedAt.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeProductCsv(){
        val fileWriter = FileWriter("$prefix/product/product-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val products = productRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(product in products){
            records.add(
                arrayOf<String>(
                    product.productId.toString(),
                    product.categoryId.toString(),
                    product.name,
                    product.price.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeCategoryCsv(){
        val fileWriter = FileWriter("$prefix/category/category-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val categories = categoryRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(category in categories){
            records.add(
                arrayOf<String>(
                    category.categoryId.toString(),
                    category.name
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }

    fun makeOrderCsv(){
        val fileWriter = FileWriter("$prefix/order/order-$suffix")
        val csvWriter = CSVWriter(fileWriter)

        val orders = orderRepository.findAll()
        val records = mutableListOf<Array<String>>()
        for(order in orders){
            records.add(
                arrayOf<String>(
                    order.orderId.toString(),
                    order.name,
                    order.status.toString(),
                    order.statusChangedAt.toString()
                )
            )
        }

        csvWriter.writeAll(records)
        csvWriter.close()
    }
}