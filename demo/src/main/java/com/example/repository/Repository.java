package com.example.repository;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import com.example.domain.Hotel;
import com.example.domain.Room;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Repository {
    private List<Hotel> hotels;
    private List<Room> rooms;
    public Repository() {
        this.hotels = null;
        this.rooms = null;
    }

    public void readHotels() {
        try (BufferedReader br = new BufferedReader(new FileReader("hotels.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                hotels.add(new Hotel(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("IO Exception");
        }
    }

    public void readRooms() {
        try (BufferedReader br = new BufferedReader(new FileReader("rooms.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Integer hotelId = Integer.parseInt(values[0]);
                Integer roomNumber = Integer.parseInt(values[1]);
                Integer type = Integer.parseInt(values[2]);
                Integer price = Integer.parseInt(values[3]);
                Boolean isAvailable = Boolean.parseBoolean(values[4]);
                if (isAvailable)
                    rooms.add(new Room(hotelId, roomNumber, type, price, isAvailable, 0, null, null, null));
                else {
                    Integer bookings = 1;
                    List<LocalDate> startDates = List.of(LocalDate.of (2024, 5, 23));
                    List<LocalDate> endDates = List.of(LocalDate.of (2024, 5, 26));
                    rooms.add(new Room(hotelId, roomNumber, type, price, isAvailable, bookings, startDates, endDates, null));
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
        catch (IOException e) {
            throw new RuntimeException("IO Exception");
        }
    }

    public void InitRepo() {
        this.hotels = List.of(
            new Hotel(1, "Hotel Ramada", 46.764654252624204, 23.598674125224626),
            new Hotel(2, "Grand Hotel Italia", 46.7522792440665, 23.605990381045697),
            new Hotel(3, "Hampton by Hilton", 46.77539900854998, 23.60182699638966)
        );
        this.rooms = List.of(
            new Room(1, 210, 2, 200, true, 0, null, null, null),
            new Room(1, 125, 1, 350, true, 0, null, null, null),
            new Room(1, 87, 1, 300, false, 1, List.of(LocalDate.of(2024, 5, 23)), List.of(LocalDate.of(2024, 5, 26)), List.of("Great room!")),
            new Room(2, 41, 3, 240, true, 0, null, null, null),
            new Room(3, 32, 2, 410, false, 2, List.of(LocalDate.of(2024, 5, 23), LocalDate.of(2024, 8, 5)), List.of(LocalDate.of(2024, 5, 26), LocalDate.of(2024, 8, 12)), List.of("Great room!", "Nice view!")),
            new Room(3, 21, 2, 350, true, 0, null, null, null),
            new Room(3, 64, 3, 300, true, 0, null, null, null)
        );
    }

    public Hotel getHotelById(int id) {
        for (Hotel hotel : hotels)
            if (hotel.getId() == id)
                return hotel;
        return null;
    }

    public Integer getHotelIdByName(String name) {
        for (Hotel hotel : hotels)
            if (hotel.getName().equals(name))
                return hotel.getId();
        return null;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Room> getRoomsInHotel(int hotelId) {
        List<Room> roomsInHotel = null;
        for (Room room : rooms)
            if (room.getHotelId() == hotelId)
                roomsInHotel.add(room);
        return roomsInHotel;
    }

    public void bookRoom(int hotelId, int roomNumber, int checkInY, int checkInM, int checkInD, int checkOutY, int checkOutM, int checkOutD) {
        for (Room room : rooms)
            if (room.getHotelId() == hotelId && room.getId() == roomNumber) {
                room.setAvailable(false);
                room.setBookings(room.getBookings() + 1);
                room.getStartDates().add(LocalDate.of(checkInY, checkInM, checkInD));
                room.getEndDates().add(LocalDate.of(checkOutY, checkOutM, checkOutD));
            }
    }

    public void updateBooking(int hotelId, int roomNumber, int oldCheckInY, int oldCheckInM, int oldCheckInD, int oldCheckOutY, int oldCheckOutM, int oldCheckOutD, int newCheckInY, int newCheckInM, int newCheckInD, int newCheckOutY, int newCheckOutM, int newCheckOutD) {
        for (Room room : rooms)
            if (room.getHotelId() == hotelId && room.getId() == roomNumber) {
                room.getStartDates().remove(LocalDate.of(oldCheckInY, oldCheckInM, oldCheckInD));
                room.getEndDates().remove(LocalDate.of(oldCheckOutY, oldCheckOutM, oldCheckOutD));
                room.getStartDates().add(LocalDate.of(newCheckInY, newCheckInM, newCheckInD));
                room.getEndDates().add(LocalDate.of(newCheckOutY, newCheckOutM, newCheckOutD));
            }
    }

    public void cancelBooking(int hotelId, int roomNumber, int checkInY, int checkInM, int checkInD, int checkOutY, int checkOutM, int checkOutD) {
        for (Room room : rooms)
            if (room.getHotelId() == hotelId && room.getId() == roomNumber) {
                room.setBookings(room.getBookings() - 1);
                room.getStartDates().remove(LocalDate.of(checkInY, checkInM, checkInD));
                room.getEndDates().remove(LocalDate.of(checkOutY, checkOutM, checkOutD));
                if (room.getBookings() == 0)
                    room.setAvailable(true);
            }
    }

    public void saveMessage(String message, int bookedHotel, int bookedRoom) {
        for (Room room : rooms)
            if (room.getHotelId() == bookedHotel && room.getId() == bookedRoom)
                room.getFeedback().add(message);
    }
}
