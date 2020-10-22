package seedu.resireg.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    private final ObservableList<Allocation> allocationList;
    private final ObservableList<Room> roomList;
    @FXML
    private ListView<Student> studentListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList,
                            ObservableList<Allocation> allocationList,
                            ObservableList<Room> roomList) {
        super(FXML);
        this.allocationList = allocationList;
        this.roomList = roomList;
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                Allocation relatedAllocation = null;
                for (Allocation allocation : allocationList) {
                    if (allocation.isRelatedTo(student)) {
                        relatedAllocation = allocation;
                    }
                }
                if (relatedAllocation == null) {
                    setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
                    return;
                }
                Room relatedRoom = null;
                for (Room room : roomList) {
                    if (relatedAllocation.isRelatedTo(room)) {
                        relatedRoom = room;
                    }
                }
                if (relatedRoom == null) {
                    setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
                    return;
                }
                setGraphic(new StudentCard(student,
                        getIndex() + 1,
                        relatedRoom.getFloor(),
                        relatedRoom.getRoomNumber()).getRoot());
            }
            studentListView.refresh();
        }
    }
}
