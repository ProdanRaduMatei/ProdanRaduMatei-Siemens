package com.example.services;

import com.example.repository.Repository;
import com.example.domain.Hotel;
import com.example.domain.Room;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class Services {
    private Repository repo;
    public Services(Repository repo) {
        this.repo = repo;
        repo.readHotels();
        repo.readRooms();
    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }

    public ArrayList<Hotel> getHotels(TextField userLatitude, TextField userLongitude, TextField userRadius) {
        double latitude = Double.parseDouble(userLatitude.getText());
        double longitude = Double.parseDouble(userLongitude.getText());
        double radius = Double.parseDouble(userRadius.getText());
        ArrayList<Hotel> hotels = new ArrayList<>();
        for (Hotel hotel : repo.getHotels())
            if (distance(latitude, longitude, hotel.getLatitude(), hotel.getLongitude()) <= radius)
                hotels.add(hotel);
        return hotels;
    }

    public ArrayList<Room> getRooms(TextField selectedHotel, TextField checkInYear, TextField checkInMonth, TextField checkInDay, TextField checkOutYear, TextField checkOutMonth, TextField checkOutDay) {
        Integer hotelId = repo.getHotelIdByName(selectedHotel.getText());
        Integer checkInY = Integer.parseInt(checkInYear.getText());
        Integer checkInM = Integer.parseInt(checkInMonth.getText());
        Integer checkInD = Integer.parseInt(checkInDay.getText());
        Integer checkOutY = Integer.parseInt(checkOutYear.getText());
        Integer checkOutM = Integer.parseInt(checkOutMonth.getText());
        Integer checkOutD = Integer.parseInt(checkOutDay.getText());
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room : repo.getRoomsInHotel(hotelId))
            if (room.getHotelId() == hotelId)
                if (room.isAvailable())
                    rooms.add(room);
                else {
                    boolean available = true;
                    for (int i = 0; i < room.getStartDates().size(); i++)
                        if (room.getStartDates().get(i).isBefore(java.time.LocalDate.of(checkInY, checkInM, checkInD)) && room.getEndDates().get(i).isAfter(java.time.LocalDate.of(checkOutY, checkOutM, checkOutD))) {
                            available = false;
                            break;
                        }
                    if (available)
                        rooms.add(room);
                }
        return rooms;
    }

    public void bookRoom(TextField selectedHotel, TextField selectedRoom, TextField checkInYear, TextField checkInMonth, TextField checkInDay, TextField checkOutYear, TextField checkOutMonth, TextField checkOutDay) {
        Integer hotelId = repo.getHotelIdByName(selectedHotel.getText());
        Integer roomNumber = Integer.parseInt(selectedRoom.getText());
        Integer checkInY = Integer.parseInt(checkInYear.getText());
        Integer checkInM = Integer.parseInt(checkInMonth.getText());
        Integer checkInD = Integer.parseInt(checkInDay.getText());
        Integer checkOutY = Integer.parseInt(checkOutYear.getText());
        Integer checkOutM = Integer.parseInt(checkOutMonth.getText());
        Integer checkOutD = Integer.parseInt(checkOutDay.getText());
        repo.bookRoom(hotelId, roomNumber, checkInY, checkInM, checkInD, checkOutY, checkOutM, checkOutD);
    }

    public void updateBooking(TextField bookedHotel, TextField bookedRoom, TextField oldCheckInYear, TextField oldCheckInMonth, TextField oldCheckInDay, TextField oldCheckOutYear, TextField oldCheckOutMonth, TextField oldCheckOutDay, TextField newCheckInYear, TextField newCheckInMonth, TextField newCheckInDay, TextField newCheckOutYear, TextField newCheckOutMonth, TextField newCheckOutDay) {
        Integer hotelId = repo.getHotelIdByName(bookedHotel.getText());
        Integer roomNumber = Integer.parseInt(bookedRoom.getText());
        Integer oldCheckInY = Integer.parseInt(oldCheckInYear.getText());
        Integer oldCheckInM = Integer.parseInt(oldCheckInMonth.getText());
        Integer oldCheckInD = Integer.parseInt(oldCheckInDay.getText());
        Integer oldCheckOutY = Integer.parseInt(oldCheckOutYear.getText());
        Integer oldCheckOutM = Integer.parseInt(oldCheckOutMonth.getText());
        Integer oldCheckOutD = Integer.parseInt(oldCheckOutDay.getText());
        Integer newCheckInY = Integer.parseInt(newCheckInYear.getText());
        Integer newCheckInM = Integer.parseInt(newCheckInMonth.getText());
        Integer newCheckInD = Integer.parseInt(newCheckInDay.getText());
        Integer newCheckOutY = Integer.parseInt(newCheckOutYear.getText());
        Integer newCheckOutM = Integer.parseInt(newCheckOutMonth.getText());
        Integer newCheckOutD = Integer.parseInt(newCheckOutDay.getText());
        repo.updateBooking(hotelId, roomNumber, oldCheckInY, oldCheckInM, oldCheckInD, oldCheckOutY, oldCheckOutM, oldCheckOutD, newCheckInY, newCheckInM, newCheckInD, newCheckOutY, newCheckOutM, newCheckOutD);
    }

    public void cancelBooking(TextField bookedHotel, TextField bookedRoom, TextField oldCheckInYear, TextField oldCheckInMonth, TextField oldCheckInDay, TextField oldCheckOutYear, TextField oldCheckOutMonth, TextField oldCheckOutDay) {
        Integer hotelId = repo.getHotelIdByName(bookedHotel.getText());
        Integer roomNumber = Integer.parseInt(bookedRoom.getText());
        Integer oldCheckInY = Integer.parseInt(oldCheckInYear.getText());
        Integer oldCheckInM = Integer.parseInt(oldCheckInMonth.getText());
        Integer oldCheckInD = Integer.parseInt(oldCheckInDay.getText());
        Integer oldCheckOutY = Integer.parseInt(oldCheckOutYear.getText());
        Integer oldCheckOutM = Integer.parseInt(oldCheckOutMonth.getText());
        Integer oldCheckOutD = Integer.parseInt(oldCheckOutDay.getText());
        repo.cancelBooking(hotelId, roomNumber, oldCheckInY, oldCheckInM, oldCheckInD, oldCheckOutY, oldCheckOutM, oldCheckOutD);
    }

    public void saveMessage(TextField message, TextField bookedHotel, TextField bookedRoom) {
        Integer hotelId = repo.getHotelIdByName(bookedHotel.getText());
        Integer roomNumber = Integer.parseInt(bookedRoom.getText());
        repo.saveMessage(message.getText(), hotelId, roomNumber);
    }
}
