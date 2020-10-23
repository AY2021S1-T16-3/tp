package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_1;

import org.junit.jupiter.api.Test;

class SemesterTest {

    @Test
    public void isSameSemester() {
        // same object -> returns true
        assertTrue(AY2020_SEM_1.isSameSemester(AY2020_SEM_1));

        // null -> returns false
        assertFalse(AY2020_SEM_1.isSameSemester(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(AY2020_SEM_1.isSameSemester(AY2020_SEM_1));

        // null -> returns false
        assertFalse(AY2020_SEM_1.isSameSemester(null));
    }
}