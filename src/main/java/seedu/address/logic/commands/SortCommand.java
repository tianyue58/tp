package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;

/**
 * Finds and lists all applications in InternSHIP whose name or fields contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all applications in InternSHIP by the given application detail.\n"
            + "Sorting by company name or position sorts applications in alphabetical order.\n"
            + "Sorting by deadline sorts applications from closer deadlines to later deadlines.\n"
            + "Sorting by priority sorts applications from higher to lower priority.\n"
            + "Parameters:\n"
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_INTERNSHIP_POSITION + "INTERNSHIP_POSITION "
            + PREFIX_DEADLINE_OF_APPLICATION + "DEADLINE_OF_APPLICATION "
            + PREFIX_PRIORITY + "PRIORITY\n"
            + "Example:\n"
            + "Sort by company name: " + COMMAND_WORD + " " + PREFIX_COMPANY_NAME + "\n"
            + "Sort by position: " + COMMAND_WORD + " " + PREFIX_INTERNSHIP_POSITION + "\n"
            + "Sort by deadline: " + COMMAND_WORD + " " + PREFIX_DEADLINE_OF_APPLICATION + "\n"
            + "Sort by priority: " + COMMAND_WORD + " " + PREFIX_COMPLETION + "\n";

    public final String MESSAGE_SUCCESS = "Sorted applications by %s";
    public static final String MESSAGE_PARAMETER_NOT_SPECIFIED = "At least one parameter to sort by "
            + "(application detail) must be provided.";

    private final String parameter;

    public SortCommand(String parameter) {
        requireNonNull(parameter);

        this.parameter = parameter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();
        final List<Application> immutableLastShownList = new ArrayList<>(lastShownList);
        final Predicate<Application> PREDICATE_SHOW_PREVIOUSLY_SHOWN_APPLICATIONS = immutableLastShownList::contains;

        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        List<Application> allApplications = model.getFilteredApplicationList();

        Comparator<Application> comparator = this.getComparator();

        List<Application> sortedApplicationList = new ArrayList<>(allApplications);
        sortedApplicationList.sort(comparator);

        Internship newSortedInternship = new Internship();
        newSortedInternship.setApplications(sortedApplicationList);
        model.setInternship(newSortedInternship);

        model.updateFilteredApplicationList(PREDICATE_SHOW_PREVIOUSLY_SHOWN_APPLICATIONS);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, this.parameter));
    }

    public Comparator<Application> getComparator() {
        switch (this.parameter) {
        case "company":
            return Company.getComparator();
        case "position":
            return Position.getComparator();
        case "deadline":
            return Deadline.getComparator();
        case "priority":
            return Priority.getComparator();
        default:
            // Should not reach this line.
        }
        return null; // Should not reach this line.
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && parameter.equals(((SortCommand) other).parameter)); // state check
    }
}
