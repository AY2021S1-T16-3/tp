package seedu.resireg.model.allocation;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.resireg.model.allocation.exceptions.AllocationNotFoundException;
import seedu.resireg.model.allocation.exceptions.DuplicateAllocationException;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * A list of allocations that enforces uniqueness between its elements and does not allow nulls.
 * An allocation is considered unique by comparing using {@code Allocation#isSameAllocation(Allocation)}.
 * As such, adding and updating of allocations uses Allocation#isSameAllocation(Allocation) for equality so as to ensure
 * that the allocation being added or updated is unique in terms of floor and roomNumber or studentId in the
 * UniqueAllocationList. However, the removal of an allocation uses Allocation#equals(Object) so as to ensure that the
 * allocation with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Allocation#isSameAllocation(Allocation)
 */
public class UniqueAllocationList implements Iterable<Allocation> {

    private final ObservableList<Allocation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Allocation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent allocation as the given argument.
     */
    public boolean contains(Allocation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAllocation);
    }

    /**
     * Returns true if the allocation list contains the {@code student} identifier.
     */
    public boolean contains(Student student) {
        for (Allocation allocation : internalList) {
            if (allocation.getStudentId().equals(student.getStudentId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the allocation list contains the {@code room} identifier.
     */
    public boolean contains(Room room) {
        for (Allocation allocation : internalList) {
            if (allocation.getFloor().equals(room.getFloor())
                    && allocation.getRoomNumber().equals(room.getRoomNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a allocation to the list.
     * The allocation must not already exist in the list,
     * i.e. the room and student must not have already been allocated.
     */
    public void add(Allocation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAllocationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the allocation {@code target} in the list with {@code editedAllocation}.
     * {@code target} must exist in the list.
     * The allocation identity of {@code editedAllocation} must not be the same as another existing allocation
     * in the list.
     */
    public void setAllocation(Allocation target, Allocation editedAllocation) {
        requireAllNonNull(target, editedAllocation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AllocationNotFoundException();
        }

        if (!target.isSameAllocation(editedAllocation) && contains(editedAllocation)) {
            throw new DuplicateAllocationException();
        }

        internalList.set(index, editedAllocation);
    }

    /**
     * Removes the equivalent allocation from the list.
     * The allocation must exist in the list.
     */
    public void remove(Allocation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AllocationNotFoundException();
        }
    }

    public void setAllocations(UniqueAllocationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code allocations}.
     * {@code allocations} must not contain duplicate allocations.
     */
    public void setAllocations(List<Allocation> allocations) {
        requireAllNonNull(allocations);
        if (!allocationsAreUnique(allocations)) {
            throw new DuplicateAllocationException();
        }
        internalList.setAll(allocations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Allocation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Allocation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAllocationList // instanceof handles nulls
                && internalList.equals(((UniqueAllocationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code allocations} contains only unique allocations.
     */
    private boolean allocationsAreUnique(List<Allocation> allocations) {
        for (int i = 0; i < allocations.size() - 1; i++) {
            for (int j = i + 1; j < allocations.size(); j++) {
                if (allocations.get(i).isSameAllocation(allocations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
