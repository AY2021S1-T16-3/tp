package seedu.resireg.model.room;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;

/**
 * Represents a Room in ResiReg.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Room {

    // Identity fields
    private final Floor floor;
    private final RoomNumber number;
    private final RoomType roomType;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Room(Floor floor, RoomNumber number, RoomType roomType,
                Set<Tag> tags) {
        requireAllNonNull(floor, number, roomType, tags);
        this.floor = floor;
        this.number = number;
        this.roomType = roomType;
        this.tags.addAll(tags);
    }

    public Floor getFloor() {
        return floor;
    }

    public RoomNumber getRoomNumber() {
        return number;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getRoomLabel() {
        return getFloor() + ":" + getRoomNumber();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both rooms have the same floor and room numbers.
     * This defines a weaker notion of equality between two rooms, and includes
     * rooms that have the same ID, but different fields (roomtype).
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null && otherRoom.getFloor().equals(getFloor())
                && otherRoom.getRoomNumber().equals(getRoomNumber());
    }

    /**
     * Returns true if both rooms have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.resireg.model.room.Room)) {
            return false;
        }

        seedu.resireg.model.room.Room otherRoom =
                (seedu.resireg.model.room.Room) other;
        return otherRoom.getFloor().equals(getFloor())
                && otherRoom.getRoomNumber().equals(getRoomNumber())
                && otherRoom.getRoomType().equals(getRoomType())
                && otherRoom.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your
        // own
        return Objects.hash(floor, number, roomType, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Floor: ")
                .append(getFloor())
                .append(" Room Number: ")
                .append(getRoomNumber())
                .append(" Type: ")
                .append(getRoomType())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
