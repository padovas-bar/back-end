package com.padovasbar.core.repository

import com.padovasbar.core.model.TrustedClient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrustedClientRepository : CrudRepository<TrustedClient, Long>{
}