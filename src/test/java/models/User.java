package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;

    @EqualsAndHashCode.Exclude
    private int id;

    private String email;
    private String password;

    @SerializedName(value = "is_active")
    @JsonProperty("is_active")
    private boolean isActive;

    @SerializedName(value = "is_admin")
    @JsonProperty("is_admin")
    @EqualsAndHashCode.Exclude
    private boolean isAdmin;

    @SerializedName(value = "role_id")
    @JsonProperty("role_id")
    private int roleId;

    private String role;

    @EqualsAndHashCode.Exclude
    private String email_notifications;
}
