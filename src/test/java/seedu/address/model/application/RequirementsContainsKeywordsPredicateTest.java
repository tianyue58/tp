package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RequirementsContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> keywords = new ArrayList<>();
        keywords.add("test");
        RequirementsContainsKeywordsPredicate predicate = new RequirementsContainsKeywordsPredicate(keywords);

        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // same keywords -> returns true
        assertTrue(predicate.equals(new RequirementsContainsKeywordsPredicate(keywords)));

        // different keywords -> returns false
        List<String> otherKeywords = new ArrayList<>();
        assertFalse(predicate.equals(new RequirementsContainsKeywordsPredicate(otherKeywords)));
    }
}
