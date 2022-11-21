package at.martinahrer.tdd.sample.domain.testfixture

interface DomainObjectFactory<T> {
    fun create(): T
}