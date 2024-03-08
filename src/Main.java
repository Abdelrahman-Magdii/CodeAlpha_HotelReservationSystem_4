import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Room> rooms = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new SingleRoom(1, true));
        rooms.add(new SingleRoom(2, false));

        while (true) {
            System.out.println("\n1. Search for rooms\n2. Make a reservation\n3. View booking details\n4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void searchRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        System.out.println("Available Rooms: ");
        for (Room room : availableRooms) {
            System.out.println(room.getRoomNumber());
        }
    }

    private static void makeReservation() {
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }
        if (selectedRoom != null && selectedRoom.isAvailable()) {
            selectedRoom.setAvailable(false);
            System.out.println("Reservation successful!");
        } else {
            System.out.println("Invalid room number or room already reserved.");
        }
    }

    private static void viewBookingDetails() {
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }
        if (selectedRoom != null) {
            System.out.println("Room Number: " + selectedRoom.getRoomNumber());
            System.out.println("Type: " + selectedRoom.getClass().getSimpleName());
            System.out.println("Is Available: " + selectedRoom.isAvailable());
        } else {
            System.out.println("Invalid room number.");
        }
    }

    abstract static class Room {
        private int roomNumber;
        private boolean isAvailable;

        public Room(int roomNumber, boolean isAvailable) {
            this.roomNumber = roomNumber;
            this.isAvailable = isAvailable;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

        public boolean isAvailable() {
            return isAvailable;
        }
    }

    static class SingleRoom extends Room {
        public SingleRoom(int roomNumber, boolean isAvailable) {
            super(roomNumber, isAvailable);
        }
    }

    // Similarly, create other room types like DoubleRoom, Suite, etc.

    // Implement Reservation class if needed
}