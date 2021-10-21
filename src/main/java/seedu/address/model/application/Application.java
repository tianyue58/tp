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
    private final Company company;
    private final Position position;
    private final Deadline deadline;

    // Default fields
    private final Completion completion;
    private final Status status;
    private final Priority priority;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Application(Company company, Position position, Deadline deadline, Completion completion, Status status,
                       Priority priority, Set<Tag> tags) {
        requireAllNonNull(company, position, deadline, tags, completion);
        this.company = company;
        this.position = position;
        this.deadline = deadline;
        this.completion = completion;
        this.status = status;
        this.priority = priority;
        this.tags.addAll(tags);
    }

    /**
     * Returns the name of the company applied to in the {@code Application}.
     */
    public Company getCompany() {
        return company;
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

    /**
     * Returns the completion status of the {@code Application}.
     */
    public Completion getCompletion() {
        return this.completion;
    }

    /**
     * Returns the status (Pending/ Accepted/ Rejected) of the {@code Application}.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the priority (Low/ Medium/ High) of the {@code Application}.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applications have the same name and position.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getCompany().equals(getCompany())
                && otherApplication.getPosition().equals(getPosition());
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
        return otherApplication.getCompany().equals(getCompany())
                && otherApplication.getPosition().equals(getPosition())
                && otherApplication.getDeadline().equals(getDeadline())
                && otherApplication.getCompletion().equals(getCompletion())
                && otherApplication.getStatus().equals(getStatus())
                && otherApplication.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, position, deadline, completion, status, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompany())
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
