package seedu.address.logic.commands;

import static java.util.Objects.checkIndex;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.DeadlineContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.Position;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.Priority;
import seedu.address.model.application.StatusContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

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

    private final SortDescriptor sortDescriptor;

    public SortCommand(SortDescriptor sortDescriptor) {
        requireNonNull(sortDescriptor);

        this.sortDescriptor = new SortDescriptor(sortDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.commitInternship(model.getInternship());

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, this.sortDescriptor.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortDescriptor.equals(((SortCommand) other).sortDescriptor)); // state check
    }

    /**
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class SortDescriptor {
        private boolean isCompanySort;
        private boolean isPositionSort;
        private boolean isDeadlineSort;
        private boolean isPrioritySort;

        public SortDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public SortDescriptor(SortDescriptor toCopy) {
            setCompanySort(toCopy.isCompanySort);
            setPositionSort(toCopy.isPositionSort);
            setDeadlineSort(toCopy.isDeadlineSort);
            setPrioritySort(toCopy.isPrioritySort);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyParameterSpecified() {
            return isCompanySort || isPositionSort || isDeadlineSort|| isPrioritySort;
        }

        public void setCompanySort(boolean isCompanySort) {
            this.isCompanySort = isCompanySort;
        }

        public boolean isCompanySort() {
            return this.isCompanySort;
        }

        public void setPositionSort(boolean position) {
            this.isPositionSort = position;
        }

        public boolean isPositionSort() {
            return this.isPositionSort;
        }

        public void setDeadlineSort(boolean deadline) {
            this.isDeadlineSort = deadline;
        }

        public boolean isDeadlineSort() {
            return this.isDeadlineSort;
        }

        public void setPrioritySort(boolean priority) {
            this.isPrioritySort = priority;
        }

        public boolean isPrioritySort() {
            return this.isPrioritySort;
        }

        public void setOtherSortsFalse() {
            if (this.isCompanySort) {
                setPositionSort(false);
                setDeadlineSort(false);
                setPrioritySort(false);
            }
            if (this.isPositionSort) {
                setCompanySort(false);
                setDeadlineSort(false);
                setPrioritySort(false);
            }
            if (this.isDeadlineSort) {
                setCompanySort(false);
                setPositionSort(false);
                setPrioritySort(false);
            }
            if (this.isPrioritySort) {
                setCompanySort(false);
                setPositionSort(false);
                setDeadlineSort(false);
            }
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SortCommand.SortDescriptor)) {
                return false;
            }

            // state check
            SortCommand.SortDescriptor e = (SortCommand.SortDescriptor) other;

            return isCompanySort() == e.isCompanySort()
                    && isPositionSort() == e.isPositionSort()
                    && isDeadlineSort() == e.isDeadlineSort()
                    && isPrioritySort() == e.isPrioritySort();
        }

        @Override
        public String toString() {
            if (this.isCompanySort) {
                return "company name (alphabetical order)";
            }
            if (this.isPositionSort) {
                return "position applied for (alphabetical order)";
            }
            if (this.isDeadlineSort) {
                return "application deadline (sooner to later)";
            }
            if (this.isPrioritySort) {
                return "priority (High to Low)";
            }
            return ""; // Should never reach this line
        }
    }
}
