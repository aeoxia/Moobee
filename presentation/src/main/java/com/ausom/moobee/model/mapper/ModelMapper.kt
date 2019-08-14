package com.ausom.moobee.model.mapper

interface ModelMapper<TModel, TDomain> {
    fun toModel(from: TDomain): TModel
    fun toDomain(from: TModel): TDomain

    fun toModel(from: List<TDomain>): List<TModel> = from.map(::toModel)
    fun toDomain(from: List<TModel>): List<TDomain> = from.map(::toDomain)
}