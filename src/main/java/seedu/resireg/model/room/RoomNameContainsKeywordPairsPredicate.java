package seedu.resireg.model.room;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import seedu.resireg.commons.util.StringUtil;

/**
 * Tests that a {@code Room}'s name, that is the combination
 * of its {@code Floor} and {@code RoomNumber} (given as pairs)
 * matches any of the keywords given.
 */
public class RoomNameContainsKeywordPairsPredicate implements Predicate<Room> {
    private final List<Map.Entry<String, String>> pairList;

    public RoomNameContainsKeywordPairsPredicate(List<Map.Entry<String, String>> pairList) {
        this.pairList = pairList;
    }

    @Override
    public boolean test(Room room) {
        return pairList.stream()
                .anyMatch(pair -> StringUtil.containsWordIgnoreCase(room.getFloor().value, pair.getKey())
                        && StringUtil.containsWordIgnoreCase(room.getRoomNumber().value, pair.getValue()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNameContainsKeywordPairsPredicate // instanceof handles nulls
                && pairList.equals(((RoomNameContainsKeywordPairsPredicate) other).pairList)); // state check
    }
}
