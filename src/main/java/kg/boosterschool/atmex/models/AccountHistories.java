package kg.boosterschool.atmex.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "account_histories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double amount;
    LocalDateTime addDate;

    @ManyToOne
    @JoinColumn(name = "id_card_account")
    CardAccount cardAccount;

}
