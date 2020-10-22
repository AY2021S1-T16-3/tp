package seedu.resireg.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.StudentId;

public class RoomCard extends UiPart<Region> {
    private static final String FXML = "RoomListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Room room;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label roomLabel;
    @FXML
    private Label roomType;
    @FXML
    private Label studentId;
    @FXML
    private Label studentName;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code RoomCard} with the given {@code Room} and index to display.
     */
    public RoomCard(Room room, int displayedIndex) {
        super(FXML);
        this.room = room;
        id.setText(displayedIndex + ". ");
        roomLabel.setText(room.getFloor() + "-" + room.getRoomNumber());
        roomType.setText(room.getRoomType().toString());
        studentId.setText("Unallocated");
        studentName.setText("Unallocated");
        room.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Creates a {@code RoomCard} with the given {@code Room} and index to display.
     * Includes the {@code studentId} and {@code studentName}.
     */
    public RoomCard(Room room, int displayedIndex, StudentId studentId, Name name) {
        super(FXML);
        this.room = room;
        id.setText(displayedIndex + ". ");
        roomLabel.setText(room.getFloor() + "-" + room.getRoomNumber());
        roomType.setText(room.getRoomType().toString());
        this.studentId.setText(studentId.toString());
        this.studentName.setText(name.toString());
        room.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoomCard)) {
            return false;
        }

        // state check
        RoomCard card = (RoomCard) other;
        return id.getText().equals(card.id.getText())
                && room.equals(card.room);
    }
}
