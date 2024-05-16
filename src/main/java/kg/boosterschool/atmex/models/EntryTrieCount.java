package kg.boosterschool.atmex.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "entry_tries_counts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntryTrieCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int trieCount;
   final int maxCount = 3;

    @ManyToOne
    @JoinColumn(name = "id_card_account")
    CardAccount cardAccount;

}
