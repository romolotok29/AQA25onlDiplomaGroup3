
package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Milestone {

    private String name;
    private String reference;
    private String description;
    private boolean isCompleted;

    public static class Builder {

        private final Milestone newMilestone;

        public Builder() {
            this.newMilestone = new Milestone();
        }

        public Builder withName(String name) {
            newMilestone.name = name;
            return this;
        }

        public Builder withReference(String reference) {
            newMilestone.reference = reference;
            return this;
        }

        public Builder withDescription(String description) {
            newMilestone.description = description;
            return this;
        }

        public Builder isMilestoneCompleted(Boolean isCompleted) {
            newMilestone.isCompleted = isCompleted;
            return this;
        }

        public Milestone build() {
            return newMilestone;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(name, milestone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}