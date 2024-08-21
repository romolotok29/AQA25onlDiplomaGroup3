package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class Project {

    private String name;
    private String announcement;
    @JsonProperty("show_announcement")
    @SerializedName(value = "show_announcement")
    private boolean isAnnouncementShown;
    @JsonProperty("suite_mode")
    @SerializedName(value = "suite_mode")
    private int projectType;
    private boolean isApprovalEnabled;

}