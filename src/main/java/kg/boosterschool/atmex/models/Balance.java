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
@Table(name = "balances")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double balance;
    double blockedBalance;

    @ManyToOne
    @JoinColumn(name = "id_card_account")
   CardAccount cardAccount;

}
