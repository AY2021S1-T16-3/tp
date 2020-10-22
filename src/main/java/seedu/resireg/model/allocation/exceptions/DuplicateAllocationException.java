package seedu.resireg.model.allocation.exceptions;

/**
 * Signals that the operation will result in duplicate allocations (Allocations are considered duplicates if they have
 * the same data fields).
 */
public class DuplicateAllocationException extends RuntimeException {
    public DuplicateAllocationException() {
        super("Operation would result in duplicate allocations");
    }
}
