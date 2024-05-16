package kg.boosterschool.atmex.repositoryes;

import kg.boosterschool.atmex.models.CardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountRepo extends JpaRepository<CardAccount , Long> {
    CardAccount findCardAccountByAccountNumber(String account_number);

}
