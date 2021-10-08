package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Application in InternSHIP.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {

    // Identity fields
    private final Name name;
    private final Position position;
    private final Deadline deadline;
    private final Completion completion;

    //add status field
    private final Status status;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Application(Name name, Position position, Deadline deadline, Status status,
                       Set<Tag> tags, Completion completion) {
        requireAllNonNull(name, position, deadline, tags, completion);
        this.name = name;
        this.position = position;
        this.deadline = deadline;
        this.status = status;
        this.tags.addAll(tags);
        this.completion = completion;
    }

    /**
     * Returns the name of the company applied to in the {@code Application}.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the position applied for in the {@code Application}.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the application deadline of the {@code Application}.
     */
    public Deadline getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the completion status of the {@code Application}.
     */
    public Completion getCompletion() {
        return this.completion;
    }

    /**
     * Returns true if both applications have the same name.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getName().equals(getName());
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return otherApplication.getName().equals(getName())
                && otherApplication.getPosition().equals(getPosition())
                && otherApplication.getDeadline().equals(getDeadline())
                && otherApplication.getStatus().equals(getStatus())
                && otherApplication.getTags().equals(getTags())
                && otherApplication.getCompletion().equals(getCompletion());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, deadline, status, tags, completion);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Position: ")
                .append(getPosition())
                .append("; Application deadline: ")
                .append(getDeadline())
                .append("; Application completion: ")
                .append(getCompletion())
                .append("; Status(Decision of the application): ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
