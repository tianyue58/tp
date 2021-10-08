package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.application.Application;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Internship internship;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;

    /**
     * Initializes a ModelManager with the given internship and userPrefs.
     */
    public ModelManager(ReadOnlyInternship internship, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(internship, userPrefs);

        logger.fine("Initializing with InternSHIP: " + internship + " and user preferences " + userPrefs);

        this.internship = new Internship(internship);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = new FilteredList<>(this.internship.getApplicationList());
    }

    public ModelManager() {
        this(new Internship(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternshipFilePath() {
        return userPrefs.getInternshipFilePath();
    }

    @Override
    public void setInternshipFilePath(Path InternshipFilePath) {
        requireNonNull(InternshipFilePath);
        userPrefs.setInternshipFilePath(InternshipFilePath);
    }

    //=========== Internship ================================================================================

    @Override
    public void setInternship(ReadOnlyInternship internship) {
        this.internship.resetData(internship);
    }

    @Override
    public ReadOnlyInternship getInternship() {
        return internship;
    }

    @Override
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return internship.hasApplication(application);
    }

    @Override
    public void deleteApplication(Application target) {
        internship.removeApplication(target);
    }

    @Override
    public void addApplication(Application application) {
        internship.addApplication(application);
        updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        internship.setApplication(target, editedApplication);
    }

    //=========== Filtered Application List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Application} backed by the internal list of
     * {@code versionedInternship}
     */
    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return filteredApplications;
    }

    @Override
    public void updateFilteredApplicationList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplications.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return internship.equals(other.internship)
                && userPrefs.equals(other.userPrefs)
                && filteredApplications.equals(other.filteredApplications);
    }

}
