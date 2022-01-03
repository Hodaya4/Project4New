import java.util.Scanner;

public class Property {
    private Address address;
    private int rooms;
    private int price;
    private String type;
    private boolean isForRent;
    private int numberOfHouse;
    private int floorNumber;
    private User publishingUser;

    public Property(Address address, int rooms, int price, String type, boolean isForRent,
                    int numberOfHouse, int floorNumber, User publishingUser) {
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.numberOfHouse = numberOfHouse;
        this.floorNumber = floorNumber;
        this.publishingUser = publishingUser;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getRooms() {
        return this.rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsForRent() {
        return this.isForRent;
    }

    public void setIsForRent(boolean isForRent) {
        this.isForRent = isForRent;
    }

    public int getNumberOfHouse() {
        return this.numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public User getPublishingUser() {
        return this.publishingUser;
    }

    public void setPublishingUser(User publishingUser) {
        this.publishingUser = publishingUser;
    }

    public String toString() {
        StringBuilder outcome = new StringBuilder();
        outcome.append(this.type).append(" - ");
        if (this.isForRent) {
            outcome.append("for rent: ");
        } else {
            outcome.append("for sale: ");
        }
        outcome.append(this.rooms).append(" rooms");
        if (this.type.equals("Apartment") || this.type.equals("Penthouse")) {
            outcome.append(", floor ").append(this.floorNumber);
        }
        outcome.append("\n").append("Price: ").append(this.price).append("$\n");
        outcome.append("Contact info: ").append(this.publishingUser);
        return outcome.toString();
    }
}
