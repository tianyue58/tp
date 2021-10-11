package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInternship;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicationAdded modelStub = new ModelStubAcceptingApplicationAdded();
        Application validApplication = new ApplicationBuilder().build();

        CommandResult commandResult = new AddCommand(validApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplication), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.applicationAdded);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application validApplication = new ApplicationBuilder().build();
        AddCommand addCommand = new AddCommand(validApplication);
        ModelStub modelStub = new ModelStubWithApplication(validApplication);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_APPLICATION, (
           ) -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Application shopee = new ApplicationBuilder().withCompany("Shopee").build();
        Application grab = new ApplicationBuilder().withCompany("Grab").build();
        AddCommand addShopeeCommand = new AddCommand(shopee);
        AddCommand addGrabCommand = new AddCommand(grab);

        // same object -> returns true
        assertTrue(addShopeeCommand.equals(addShopeeCommand));

        // same values -> returns true
        AddCommand addShopeeCommandCopy = new AddCommand(shopee);
        assertTrue(addShopeeCommand.equals(addShopeeCommandCopy));

        // different types -> returns false
        assertFalse(addShopeeCommand.equals(1));

        // null -> returns false
        assertFalse(addShopeeCommand.equals(null));

        // different application -> returns false
        assertFalse(addShopeeCommand.equals(addGrabCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternshipFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternship(ReadOnlyInternship newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternship getInternship() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(Application target, Application editedApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single application.
     */
    private class ModelStubWithApplication extends ModelStub {
        private final Application application;

        ModelStubWithApplication(Application application) {
            requireNonNull(application);
            this.application = application;
        }

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return this.application.isSameApplication(application);
        }
    }

    /**
     * A Model stub that always accept the application being added.
     */
    private class ModelStubAcceptingApplicationAdded extends ModelStub {
        final ArrayList<Application> applicationAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return applicationAdded.stream().anyMatch(application::isSameApplication);
        }

        @Override
        public void addApplication(Application application) {
            requireNonNull(application);
            applicationAdded.add(application);
        }

        @Override
        public ReadOnlyInternship getInternship() {
            return new Internship();
        }
    }

}
