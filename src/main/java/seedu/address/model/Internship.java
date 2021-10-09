package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameApplication comparison)
 */
public class Internship implements ReadOnlyInternship {

    private final UniqueApplicationList applications;

    {
        applications = new UniqueApplicationList();
    }

    public Internship() {}

    /**
     * Creates an AddressBook using the Applications in the InterSHIP data storage
     */
    public Internship(ReadOnlyInternship toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternship newData) {
        requireNonNull(newData);

        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Returns true if an application with the same identity as {@code application} exists in the address book.
     */
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Adds a application to the address book.
     * The application must not already exist in the address book.
     */
    public void addApplication(Application p) {
        applications.add(p);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedApplication} must not be the same as another existing
     * application in the address book.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplication(Application key) {
        applications.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Internship // instanceof handles nulls
                && applications.equals(((Internship) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
