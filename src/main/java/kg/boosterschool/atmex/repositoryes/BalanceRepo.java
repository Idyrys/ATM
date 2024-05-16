package kg.boosterschool.atmex.repositoryes;

import jakarta.transaction.Transactional;
import kg.boosterschool.atmex.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {
    Balance findBalanceByCardAccountId(Long cardId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE balances SET blocked_balance = :amount WHERE id = :id", nativeQuery = true)
    void changeBlockedBalance(@Param("amount") double amount, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE balances SET balance = balance - :amount WHERE id = :id", nativeQuery = true)
    void changeBalance(@Param("amount") double amount, @Param("id") Long id);


}
