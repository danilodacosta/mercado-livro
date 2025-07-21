package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Profile
import org.hibernate.annotations.CollectionType
import org.yaml.snakeyaml.constructor.Constructor
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    var password: String,

    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    @Column
    @Enumerated(EnumType.STRING)
    var roles: Set<Profile> = setOf()

)