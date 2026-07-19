package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Integration {

    private String address;
    private int version;
    private String email;
    private String apiToken;

}