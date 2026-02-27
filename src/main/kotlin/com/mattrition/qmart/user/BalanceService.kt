package com.mattrition.qmart.user

import com.mattrition.qmart.exception.ForbiddenException
import com.mattrition.qmart.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.UUID

/** Service for handling queries related to funds within user accounts. */
@Service
class BalanceService(
    private val userRepository: UserRepository,
) {
    /**
     * Retrieves a user's balance.
     *
     * @throws NotFoundException If the user does not exist.
     */
    fun getBalance(userId: UUID): BigDecimal {
        val user =
            userRepository.findById(userId).orElseThrow {
                throw NotFoundException("User ID not found: $userId")
            }

        return user.balance
    }

    /**
     * Adds money to a user's balance and returns the new amount.
     *
     * @throws NotFoundException If the user does not exist.
     */
    @Transactional
    fun addBalance(
        userId: UUID,
        amount: BigDecimal,
    ): BigDecimal {
        val user =
            userRepository.findById(userId).orElseThrow {
                throw NotFoundException("User ID not found: $userId")
            }

        user.balance += amount

        return user.balance
    }

    /**
     * Deducts money from a user's balance and returns the new amount.
     *
     * @throws NotFoundException If the user does not exist.
     * @throws ForbiddenException If the user's balance is less than the [amount].
     */
    @Transactional
    fun deductBalance(
        userId: UUID,
        amount: BigDecimal,
    ): BigDecimal {
        val user =
            userRepository.findById(userId).orElseThrow {
                throw NotFoundException("User ID not found: $userId")
            }

        if (user.balance < amount) {
            throw ForbiddenException(
                "User ${user.username} has insufficient funds: ${user.balance} < $amount",
            )
        }

        user.balance -= amount
        return user.balance
    }

    /**
     * Transfers funds from one user to another user. Follows typical rules from other transactional
     * functions.
     *
     * @param fromUserId The user to deduct funds from.
     * @param toUserId The user receiving the funds.
     * @param amount The amount of money to transfer.
     * @see deductBalance
     * @see addBalance
     */
    @Transactional
    fun transferBalance(
        fromUserId: UUID,
        toUserId: UUID,
        amount: BigDecimal,
    ): BigDecimal {
        val newBalance = deductBalance(fromUserId, amount)
        addBalance(toUserId, amount)

        return newBalance
    }

    fun setBalance(
        userId: UUID,
        balance: BigDecimal,
    ): BigDecimal {
        val user =
            userRepository.findById(userId).orElseThrow {
                throw NotFoundException("User ID not found: $userId")
            }
        user.balance = balance

        return user.balance
    }
}
