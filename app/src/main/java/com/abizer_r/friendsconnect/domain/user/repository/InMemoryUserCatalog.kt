package com.abizer_r.friendsconnect.domain.user.repository

import javax.inject.Inject

class InMemoryUserCatalog @Inject constructor() {
    private val emailSet: MutableSet<String> = hashSetOf()
    fun contains(email: String) = emailSet.contains(email)
    fun add(email: String) { emailSet.add(email) }
}