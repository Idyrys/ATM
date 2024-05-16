package kg.boosterschool.atmex.repositoryes;

import jakarta.transaction.Transactional;
import kg.boosterschool.atmex.models.EntryTrieCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTrieCountRepo extends JpaRepository<EntryTrieCount,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE entry_tries_counts SET trie_count = trie_count+1 WHERE id = :id", nativeQuery = true)
    void changeTrieCount( Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE entry_tries_counts SET trie_count = 0 WHERE id = :id", nativeQuery = true)
    void changeToZero( Long id);
    EntryTrieCount findEntryTrieCountByCardAccountId(Long cardID);



}
