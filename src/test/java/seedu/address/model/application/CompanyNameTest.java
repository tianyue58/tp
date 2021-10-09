package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Company.isValidCompanyName(null));

        // invalid name
        assertFalse(Company.isValidCompanyName("")); // empty string
        assertFalse(Company.isValidCompanyName(" ")); // spaces only
        assertFalse(Company.isValidCompanyName("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompanyName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Company.isValidCompanyName("shopee")); // alphabets only
        assertTrue(Company.isValidCompanyName("12345")); // numbers only
        assertTrue(Company.isValidCompanyName("Shopee123")); // alphanumeric characters
        assertTrue(Company.isValidCompanyName("Shopee")); // with capital letters
        assertTrue(Company.isValidCompanyName("The Best Company In the World")); // long names
    }
}
