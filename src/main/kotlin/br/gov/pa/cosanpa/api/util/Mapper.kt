package br.gov.pa.cosanpa.api.util

interface Mapper<K, T> {
    fun map(k: K): T
}