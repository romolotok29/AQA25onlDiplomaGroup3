package models;

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
    private boolean isAnnouncementShown;
    private int projectType;
    private boolean isApprovalEnabled;
}
