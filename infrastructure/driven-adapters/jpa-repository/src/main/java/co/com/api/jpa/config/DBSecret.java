package co.com.api.jpa.config;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class DBSecret {
    private final String url;
    private final String username;
    private final String password;

}
