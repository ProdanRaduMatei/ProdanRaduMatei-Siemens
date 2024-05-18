package com.example.gui;

import com.example.services.Services;
import com.example.domain.Hotel;
import com.example.domain.Room;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;

public class Controller {
    private Services services;

    public Controller(Services services) {
        this.services = services;
    }

    private ObservableList<Hotel> hotelList = FXCollections.observableArrayList();

    @FXML
    private ListView<Hotel> hotelListView = new ListView<>();

    @FXML
    private TextField userLatitude = new TextField();

    @FXML
    private TextField userLongitude = new TextField();

    @FXML
    private TextField userRadius = new TextField();

    @FXML
    private Button searchHotelsButton = new Button();

    @FXML
    private TextField selectedHotel = new TextField();

    @FXML
    private TextField checkInYear = new TextField();

    @FXML
    private TextField checkInMonth = new TextField();

    @FXML
    private TextField checkInDay = new TextField();

    @FXML
    private TextField checkOutYear = new TextField();

    @FXML
    private TextField checkOutMonth = new TextField();

    @FXML
    private TextField checkOutDay = new TextField();

    @FXML
    private Button searchRoomsButton = new Button();

    private ObservableList<Room> roomList = FXCollections.observableArrayList();

    @FXML
    private ListView<Room> roomListView = new ListView<>();

    @FXML
    private TextField selectedRoom = new TextField();

    @FXML
    private Button bookRoomButton = new Button();

    @FXML
    private TextField bookedHotel = new TextField();

    @FXML
    private TextField bookedRoom = new TextField();

    @FXML
    private TextField oldCheckInYear = new TextField();

    @FXML
    private TextField oldCheckInMonth = new TextField();

    @FXML
    private TextField oldCheckInDay = new TextField();

    @FXML
    private TextField oldCheckOutYear = new TextField();

    @FXML
    private TextField oldCheckOutMonth = new TextField();

    @FXML
    private TextField oldCheckOutDay = new TextField();

    @FXML
    private TextField newCheckInYear = new TextField();

    @FXML
    private TextField newCheckInMonth = new TextField();

    @FXML
    private TextField newCheckInDay = new TextField();

    @FXML
    private TextField newCheckOutYear = new TextField();

    @FXML
    private TextField newCheckOutMonth = new TextField();

    @FXML
    private TextField newCheckOutDay = new TextField();

    @FXML
    private Button updateBookingButton = new Button();

    @FXML
    private Button cancelBookingButton = new Button();

    @FXML
    private TextField message = new TextField();

    @FXML
    private Button saveMessageButton = new Button();

    public void initialize() {
        searchHotelsButton.setOnAction(e -> {
            hotelList.clear();
            hotelList.addAll(services.getHotels(userLatitude, userLongitude, userRadius));
            hotelListView.setItems(hotelList);
        });

        searchRoomsButton.setOnAction(e -> {
            roomList.clear();
            roomList.addAll(services.getRooms(selectedHotel, checkInYear, checkInMonth, checkInDay, checkOutYear, checkOutMonth, checkOutDay));
            roomListView.setItems(roomList);
        });

        bookRoomButton.setOnAction(e -> {
            services.bookRoom(selectedHotel, selectedRoom, checkInYear, checkInMonth, checkInDay, checkOutYear, checkOutMonth, checkOutDay);
        });

        updateBookingButton.setOnAction(e -> {
            services.updateBooking(bookedHotel, bookedRoom, oldCheckInYear, oldCheckInMonth, oldCheckInDay, oldCheckOutYear, oldCheckOutMonth, oldCheckOutDay, newCheckInYear, newCheckInMonth, newCheckInDay, newCheckOutYear, newCheckOutMonth, newCheckOutDay);
        });

        cancelBookingButton.setOnAction(e -> {
            services.cancelBooking(bookedHotel, bookedRoom, oldCheckInYear, oldCheckInMonth, oldCheckInDay, oldCheckOutYear, oldCheckOutMonth, oldCheckOutDay);
        });

        saveMessageButton.setOnAction(e -> {
            services.saveMessage(message, bookedHotel, bookedRoom);
        });
    }
}
