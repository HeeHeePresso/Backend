package org.heeheepresso.orderapi.order.domain.model

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class Money(
    val value: BigDecimal
) {
    constructor(value: Int) : this(BigDecimal(value))

    fun getIntValue(): Int {
        return value.toInt()
    }

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun minus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun times(other: Money): Money {
        return Money(value.multiply(other.value))
    }

    operator fun div(other: Money): Money {
        return Money(value.divide(other.value))
    }

    override fun equals(other: Any?): Boolean {
        if (other is Money && (this.value.compareTo(other.value) == 0)) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}