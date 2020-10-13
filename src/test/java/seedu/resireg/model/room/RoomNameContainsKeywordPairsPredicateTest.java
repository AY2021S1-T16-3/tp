package seedu.resireg.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.RoomBuilder;

public class RoomNameContainsKeywordPairsPredicateTest {

    @Test
    public void equals() {
        List<Map.Entry<String, String>> firstPredicateKeywordList =
                Collections.singletonList(Map.entry("1", "2"));
        List<Map.Entry<String, String>> secondPredicateKeywordList =
                Arrays.asList(Map.entry("1", "2"), Map.entry("3", "4"));

        RoomNameContainsKeywordPairsPredicate firstPredicate =
                new RoomNameContainsKeywordPairsPredicate(firstPredicateKeywordList);
        RoomNameContainsKeywordPairsPredicate secondPredicate =
                new RoomNameContainsKeywordPairsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoomNameContainsKeywordPairsPredicate firstPredicateCopy =
                new RoomNameContainsKeywordPairsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsPairs_returnsTrue() {
        // Matching Keyword
        RoomNameContainsKeywordPairsPredicate predicate =
                new RoomNameContainsKeywordPairsPredicate(Collections.singletonList(Map.entry("11", "108")));
        assertTrue(predicate.test(new RoomBuilder().withFloor("11").withRoomNumber("108").build()));

        // Only one matching keyword
        predicate = new RoomNameContainsKeywordPairsPredicate(
                Arrays.asList(Map.entry("11", "108"), Map.entry("12", "109"))
        );
        assertTrue(predicate.test(new RoomBuilder().withFloor("11").withRoomNumber("108").build()));
    }

    @Test
    public void test_nameDoesNotContainPairs_returnsFalse() {
        // Zero keywords
        RoomNameContainsKeywordPairsPredicate predicate =
                new RoomNameContainsKeywordPairsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RoomBuilder().withFloor("11").build()));

        // Non-matching pair (one keyword, floor does not match)
        predicate =
                new RoomNameContainsKeywordPairsPredicate(Collections.singletonList(Map.entry("11", "108")));
        assertFalse(predicate.test(new RoomBuilder().withFloor("12").withRoomNumber("108").build()));

        // Non-matching pair (one keyword, room number does not match)
        predicate =
                new RoomNameContainsKeywordPairsPredicate(Collections.singletonList(Map.entry("11", "108")));
        assertFalse(predicate.test(new RoomBuilder().withFloor("11").withRoomNumber("109").build()));

        // Non-matching pair (both keywords)
        predicate =
                new RoomNameContainsKeywordPairsPredicate(Collections.singletonList(Map.entry("11", "108")));
        assertFalse(predicate.test(new RoomBuilder().withFloor("12").withRoomNumber("109").build()));
    }

}
