package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CliSyntaxTest {
    @Test
    public void companyNamePrefix() {
        assertEquals(CliSyntax.PREFIX_COMPANY_NAME, new Prefix("c/"));
    }

    @Test
    public void internshipPositionPrefix() {
        assertEquals(CliSyntax.PREFIX_INTERNSHIP_POSITION, new Prefix("p/"));
    }

    @Test
    public void deadlineOfApplicationPrefix() {
        assertEquals(CliSyntax.PREFIX_DEADLINE_OF_APPLICATION, new Prefix("d/"));
    }

    @Test
    public void statusPrefix() {
        assertEquals(CliSyntax.PREFIX_STATUS, new Prefix("s/"));
    }

    @Test
    public void completionPrefix() {
        assertEquals(CliSyntax.PREFIX_COMPLETION, new Prefix("c1/"));
    }

    @Test
    public void priorityPrefix() {
        assertEquals(CliSyntax.PREFIX_PRIORITY, new Prefix("pr/"));
    }

    @Test
    public void requirementPrefix() {
        assertEquals(CliSyntax.PREFIX_REQUIREMENT, new Prefix("r/"));
    }

    @Test
    public void interviewDateAndTimePrefix() {
        assertEquals(CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME, new Prefix("i/"));
    }
}
