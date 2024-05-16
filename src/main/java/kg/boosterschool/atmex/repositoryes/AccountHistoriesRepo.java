package kg.boosterschool.atmex.repositoryes;

import kg.boosterschool.atmex.models.AccountHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoriesRepo extends JpaRepository<AccountHistories,Long> {
}
