package com.padovasbar.core.controller

import com.padovasbar.core.model.Category
import com.padovasbar.core.model.Product
import com.padovasbar.core.model.TrustedClient
import com.padovasbar.core.repository.CategoryRepository
import com.padovasbar.core.repository.ProductRepository
import com.padovasbar.core.repository.TrustedClientRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/trusted-client")
class TrustedClientController(private val trustedClientRepository: TrustedClientRepository) {

    @PostMapping
    fun create(@RequestBody trustedClient: TrustedClient) = trustedClientRepository.save(trustedClient)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = trustedClientRepository.findById(id)

    @GetMapping
    fun getAll() = trustedClientRepository.findAll()

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody trustedClient: TrustedClient): TrustedClient {
        val client = trustedClientRepository.findById(id).get()
        client.name = trustedClient.name
        client.description = trustedClient.description

        return trustedClientRepository.save(client)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = trustedClientRepository.deleteById(id)

}