package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Changes the status of an application in InternSHIP to Rejected.
 */
public class RejectCommand extends Command {
    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the application identified by the index number as 'Rejected' "
            + "in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Marked application as 'Rejected': %1$s";


    private final Index targetIndex;

    /**
     * Creates a RejectCommand to mark the specified {@code Application} as Rejected.
     *
     * @param targetIndex Index pf the application to be marked as Rejected.
     */
    public RejectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToComplete = lastShownList.get(targetIndex.getZeroBased());

        Company company = applicationToComplete.getCompany();
        Position position = applicationToComplete.getPosition();
        Deadline deadline = applicationToComplete.getDeadline();
        Completion completion = new Completion("Completed");
        Status status = new Status("Rejected");
        Priority priority = applicationToComplete.getPriority();
        Set<Requirement> requirementList = applicationToComplete.getRequirements();

        Application completedApplication = new Application(company, position, deadline, completion, status, priority,
                requirementList);

        model.setApplication(applicationToComplete, completedApplication);
        model.commitInternship(model.getInternship());

        return new CommandResult(String.format(MESSAGE_SUCCESS, completedApplication));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectCommand// instanceof handles nulls
                && targetIndex.equals(((RejectCommand) other).targetIndex)); // state check
    }
}
