package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;

import seedu.address.model.Model;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.application.Application;
import seedu.address.model.application.Complete;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.tag.Tag;


import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;


public class CompleteCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Completes the application identified by the index number used in the displayed application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Completed application: %1$s";


    private final Index targetIndex;

    public CompleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            //need to change PERSON
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Application applicationToComplete = lastShownList.get(targetIndex.getZeroBased());

        Name name = applicationToComplete.getName();
        Position position = applicationToComplete.getPosition();
        Deadline deadline = applicationToComplete.getDeadline();
        Set<Tag> tagList = applicationToComplete.getTags();
        Complete complete = new Complete("Completed");

        Application completedApplication = new Application(name, position, deadline, tagList, complete);
        model.setApplication(applicationToComplete, completedApplication);
        //need to change PERSON
        //model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, applicationToComplete));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompleteCommand // instanceof handles nulls
                && targetIndex.equals(((CompleteCommand) other).targetIndex)); // state check
    }
}
