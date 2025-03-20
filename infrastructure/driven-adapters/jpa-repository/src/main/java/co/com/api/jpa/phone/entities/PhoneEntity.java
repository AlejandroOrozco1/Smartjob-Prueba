package co.com.api.jpa.phone.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String phoneId;
    private String number;
    private String citycode;
    private String contrycode;
    private String userId;
}
