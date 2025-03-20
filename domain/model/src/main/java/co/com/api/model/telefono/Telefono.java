package co.com.api.model.telefono;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Telefono {
    private String phoneId;
    private String number;
    private String citycode;
    private String contrycode;
    private String userId;
}
