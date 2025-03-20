package co.com.api.rest.user.requests;

import lombok.Data;

@Data
public class PhoneResquet {
    private String number;
    private String citycode;
    private String contrycode;
}
