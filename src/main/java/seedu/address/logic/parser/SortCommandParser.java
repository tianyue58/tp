package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_DEADLINE_OF_APPLICATION, PREFIX_PRIORITY);

        SortCommand.SortDescriptor sortDescriptor = new SortDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            sortDescriptor.setCompanySort(true);
            sortDescriptor.setOtherSortsFalse();
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            sortDescriptor.setPositionSort(true);
            sortDescriptor.setOtherSortsFalse();
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            sortDescriptor.setDeadlineSort(true);
            sortDescriptor.setOtherSortsFalse();
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            sortDescriptor.setPrioritySort(true);
            sortDescriptor.setOtherSortsFalse();
        }

        if (!sortDescriptor.isAnyParameterSpecified()) {
            throw new ParseException(SortCommand.MESSAGE_PARAMETER_NOT_SPECIFIED);
        }

        return new SortCommand(sortDescriptor);
    }

}
