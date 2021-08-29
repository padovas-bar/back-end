package com.padovasbar.core.repository

import com.padovasbar.core.model.Login
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoginRepository : CrudRepository<Login, Long>