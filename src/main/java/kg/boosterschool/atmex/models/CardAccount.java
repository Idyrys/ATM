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
@Table(name = "card_accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String accountNumber;
    String pin;
    boolean active;

    @ManyToOne
    @JoinColumn(name = "id_clients")
    Client client;
}
