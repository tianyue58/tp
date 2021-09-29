package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Application.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code application}.
     */
    public static String getAddCommand(Application application) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(application);
    }

    /**
     * Returns the part of command string for the given {@code application}'s details.
     */
    public static String getPersonDetails(Application application) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + application.getName().fullName + " ");
        sb.append(PREFIX_INTERNSHIP_POSITION + application.getPosition().value + " ");
        sb.append(PREFIX_DEADLINE_OF_APPLICATION + application.getDeadline().value + " ");
        application.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditApplicationDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_COMPANY_NAME).append(name.fullName).append(" "));
        descriptor.getPosition().ifPresent(phone -> sb.append(PREFIX_INTERNSHIP_POSITION).append(phone.value).append(" "));
        descriptor.getDeadline().ifPresent(email -> sb.append(PREFIX_DEADLINE_OF_APPLICATION).append(email.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
