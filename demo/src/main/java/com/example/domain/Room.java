package com.example.domain;

import java.time.LocalDate;
import java.util.List;

public class Room {
    private Integer hotelId;
    private Integer roomNumber;
    private Integer type;
    private Integer price;
    private Boolean isAvailable;
    private Integer bookings;
    private List<LocalDate> startDates;
    private List<LocalDate> endDates;
    private List<String> feedback;

    public Room() {
        this.hotelId = 0;
        this.roomNumber = 0;
        this.type = 0;
        this.price = 0;
        this.isAvailable = true;
        this.bookings = 0;
        this.startDates = null;
        this.endDates = null;
        this.feedback = null;
    }

    public Room(int hotelId, int roomNumber, int type, int price, boolean isAvailable, int bookings, List<LocalDate> startDates, List<LocalDate> endDates, List<String> feedback) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
        this.bookings = bookings;
        this.startDates = startDates;
        this.endDates = endDates;
        this.feedback = feedback;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getId() {
        return roomNumber;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getBookings() {
        return bookings;
    }

    public List<LocalDate> getStartDates() {
        return startDates;
    }

    public List<LocalDate> getEndDates() {
        return endDates;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setId(Integer id) {
        this.roomNumber = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }

    public void setStartDates(List<LocalDate> startDates) {
        this.startDates = startDates;
    }

    public void setEndDates(List<LocalDate> endDates) {
        this.endDates = endDates;
    }

    public void setFeedback(List<String> feedback) {
        this.feedback = feedback;
    }

    public String toString() {
        return "Room [hotelId=" + hotelId + ", roomNumber=" + roomNumber + ", type=" + type + ", price=" + price + ", isAvailable=" + isAvailable + ", bookings=" + bookings + ", startDates=" + startDates + ", endDates=" + endDates + ", feedback=" + feedback + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        if (hotelId != other.hotelId)
            return false;
        if (roomNumber != other.roomNumber)
            return false;
        if (type != other.type)
            return false;
        if (price != other.price)
            return false;
        if (isAvailable != other.isAvailable)
            return false;
        if (bookings != other.bookings)
            return false;
        if (startDates == null) {
            if (other.startDates != null)
                return false;
        } else if (!startDates.equals(other.startDates))
            return false;
        if (endDates == null) {
            if (other.endDates != null)
                return false;
        } else if (!endDates.equals(other.endDates))
            return false;
        if (feedback == null) {
            if (other.feedback != null)
                return false;
        } else if (!feedback.equals(other.feedback))
            return false;
        return true;
    }

    public int hashCode() {
        return roomNumber;
    }
}
