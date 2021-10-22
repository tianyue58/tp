package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirements;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing application in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of "
            + "the application identified "
            + "by the index number used in the displayed application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY] "
            + "[" + PREFIX_INTERNSHIP_POSITION + "POSITION] "
            + "[" + PREFIX_DEADLINE_OF_APPLICATION + "DEADLINE] "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERNSHIP_POSITION + "UI designer "
            + PREFIX_DEADLINE_OF_APPLICATION + "2021-12-23";

    public static final String MESSAGE_EDIT_APPLICATION_SUCCESS = "Edited Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already "
            + "exists in the address book.";

    private final Index index;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * @param index of the application in the filtered application list to edit
     * @param editApplicationDescriptor details to edit the application with
     */
    public EditCommand(Index index, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicationDescriptor);

        this.index = index;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        Application editedApplication = createEditedApplication(applicationToEdit, editApplicationDescriptor);

        if (!applicationToEdit.isSameApplication(editedApplication) && model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.setApplication(applicationToEdit, editedApplication);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternship(model.getInternship());

        return new CommandResult(String.format(MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication));
    }

    /**
     * Creates and returns a {@code Application} with the details of {@code applicationToEdit}
     * edited with {@code editApplicationDescriptor}.
     */
    private static Application createEditedApplication(Application applicationToEdit,
                                                  EditApplicationDescriptor editApplicationDescriptor) {
        assert applicationToEdit != null;

        Company updatedCompany = editApplicationDescriptor.getCompany().orElse(applicationToEdit.getCompany());
        Position updatedPosition = editApplicationDescriptor.getPosition().orElse(applicationToEdit.getPosition());
        Deadline updatedDeadline = editApplicationDescriptor.getDeadline().orElse(applicationToEdit.getDeadline());
        Completion completion = applicationToEdit.getCompletion();
        Status status = applicationToEdit.getStatus();
        Priority priority = editApplicationDescriptor.getPriority().orElse(applicationToEdit.getPriority());
        Requirements updatedRequirements = editApplicationDescriptor.getRequirements()
                .orElse(applicationToEdit.getRequirements());
        Set<Tag> updatedTags = editApplicationDescriptor.getTags().orElse(applicationToEdit.getTags());


        return new Application(updatedCompany, updatedPosition, updatedDeadline, completion,
                status, priority, updatedRequirements, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editApplicationDescriptor.equals(e.editApplicationDescriptor);
    }

    /**
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class EditApplicationDescriptor {
        private Company company;
        private Position position;
        private Deadline deadline;
        private Priority priority;
        private Requirements requirements;
        private Set<Tag> tags;

        public EditApplicationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicationDescriptor(EditApplicationDescriptor toCopy) {
            setCompany(toCopy.company);
            setPosition(toCopy.position);
            setDeadline(toCopy.deadline);
            setPriority(toCopy.priority);
            setRequirements(toCopy.requirements);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, position, deadline, priority, requirements, tags);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setRequirements(Requirements requirements) {
            this.requirements = requirements;
        }

        public Optional<Requirements> getRequirements() {
            return Optional.ofNullable(requirements);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicationDescriptor)) {
                return false;
            }

            // state check
            EditApplicationDescriptor e = (EditApplicationDescriptor) other;

            return getCompany().equals(e.getCompany())
                    && getPosition().equals(e.getPosition())
                    && getDeadline().equals(e.getDeadline())
                    && getPriority().equals(e.getPriority())
                    && getTags().equals(e.getTags());
        }
    }
}
