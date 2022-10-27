package at.martinahrer.tdd.samples.domain.testfixture

interface DomainObjectFactory<T> {
    fun create(): T
}