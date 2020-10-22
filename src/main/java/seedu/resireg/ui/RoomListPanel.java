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

public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);

    private final ObservableList<Allocation> allocationList;
    private final ObservableList<Student> studentList;
    @FXML
    private ListView<Room> roomListView;

    /**
     * Creates a {@code RoomListPanel} with the given {@code ObservableList}.
     */
    public RoomListPanel(ObservableList<Room> roomList,
                         ObservableList<Allocation> allocationList,
                         ObservableList<Student> studentList) {
        super(FXML);
        this.allocationList = allocationList;
        this.studentList = studentList;
        roomListView.setItems(roomList);
        roomListView.setCellFactory(listView -> new RoomListPanel.RoomListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Room} using a {@code RoomCard}.
     */
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);
            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                Allocation relatedAllocation = null;
                for (Allocation allocation : allocationList) {
                    if (allocation.isRelatedTo(room)) {
                        relatedAllocation = allocation;
                    }
                }
                if (relatedAllocation == null) {
                    setGraphic(new RoomCard(room, getIndex() + 1).getRoot());
                    return;
                }
                Student relatedStudent = null;
                for (Student student : studentList) {
                    if (relatedAllocation.isRelatedTo(student)) {
                        relatedStudent = student;
                    }
                }
                if (relatedStudent == null) {
                    setGraphic(new RoomCard(room, getIndex() + 1).getRoot());
                    return;
                }
                setGraphic(new RoomCard(room,
                        getIndex() + 1,
                        relatedStudent.getStudentId(),
                        relatedStudent.getName()).getRoot());
            }
        }
    }
}
