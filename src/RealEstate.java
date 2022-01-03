import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;
    private final int IRRELEVANT = -999;
    private final int ARR_LENGTH = 10;
    private final int PHONE = 10;
    private final int PHONE_FIRST = 0;
    private final int PHONE_SECOND = 1;
    private final int MEDIATOR = 1;
    private final int RESTRICTION_MEDIATOR = 10;
    private final int RESTRICTION_PRIVATE_USER = 3;
    private final int APARTMENT = 1;
    private final int PENTHOUSE = 2;
    private final int PRIVATE_HOUSE = 3;
    private final int SELL = 1;
    private final int NO_PROPERTIES = 0;
    private final int USER_PROPERTIES = 1;
    private final int RENT = 0;

    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[ARR_LENGTH];
        this.addresses[0] = new Address("Ashkelon", "Kislev");
        this.addresses[1] = new Address("Tel Aviv", "Herzel");
        this.addresses[2] = new Address("Jerusalem", "Ben Gurion");
        this.addresses[3] = new Address("Tel Aviv", "Even Gvirol");
        this.addresses[4] = new Address("Ashkelon", "Sivan");
        this.addresses[5] = new Address("Jerusalem", "Bezalel");
        this.addresses[6] = new Address("Tel Aviv", "Allenby");
        this.addresses[7] = new Address("Ashkelon", "Eli Cohen");
        this.addresses[8] = new Address("Jerusalem", "Bar Ilan");
        this.addresses[9] = new Address("Tel Aviv", "Dizengoff");
    }

    private boolean isUsernameExist(String username) {
        boolean exists = false;
        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if (currentUser.getUserName().equals(username)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean isStrongPassword(String password) {
        boolean isStrong = false;
        boolean hasDigit = false;
        boolean hasChar = false;

        for (int i = 0; i < password.length(); i++) {
            char temp = password.charAt(i);
            if (Character.isDigit(temp)) {
                hasDigit = true;
            }
            if (password.charAt(i) == '_' || password.charAt(i) == '$' || password.charAt(i) == '%') {
                hasChar = true;
            }
        }
        if (hasChar && hasDigit) {
            isStrong = true;
        }
        return isStrong;
    }

    private boolean isCorrectPhoneNumber(String phoneNumber) {
        boolean correctNumber = false;
        boolean correctLength = false;
        boolean allDigits = true;
        boolean correctStart = false;

        if (phoneNumber.length() == PHONE) {
            correctLength = true;
        }
        if (phoneNumber.charAt(PHONE_FIRST) == '0' && phoneNumber.charAt(PHONE_SECOND) == '5') {
            correctStart = true;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char temp = phoneNumber.charAt(i);
            if (!Character.isDigit(temp)) {
                allDigits = false;
            }
        }
        if (correctLength && correctStart && allDigits) {
            correctNumber = true;
        }
        return correctNumber;
    }

    private void addUserToArray(String username, String password, String phoneNumber, boolean isMediator) {
        User[] newArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newArray[i] = this.users[i];
        }
        User user = new User(username, password, phoneNumber, isMediator);
        newArray[this.users.length] = user;
        this.users = newArray;
    }

    public void createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        while (isUsernameExist(username)) {
            System.out.println("Username is taken. Please enter your username again: ");
            username = sc.nextLine();
        }

        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        while (!isStrongPassword(password)) {
            System.out.print("Password is not strong enough. Enter a password again: ");
            password = sc.nextLine();
        }

        System.out.println("Enter your phone number: ");
        String phoneNumber = sc.nextLine();
        while (!isCorrectPhoneNumber(phoneNumber)) {
            System.out.print("Phone number is not correct. Enter it again: ");
            phoneNumber = sc.nextLine();
        }

        System.out.println("Are you a mediator(1)  or a regular user(2)? ");
        boolean isMediator = false;
        int answer = sc.nextInt();
        if (answer == MEDIATOR) {
            isMediator = true;
        }
        addUserToArray(username, password, phoneNumber, isMediator);
    }

    public User userLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if (currentUser.getUserName().equals(username) && currentUser.getPassword().equals(password)) {
                return currentUser;
            }
        }
        return null;
    }

    private void addPropertyToArray(Address address, int rooms, int price, String type, boolean isForRent,
                                    int houseNumber, int floorNumber, User user) {
        Property[] newArray = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            newArray[i] = this.properties[i];
        }
        Property property = new Property(address, rooms, price, type, isForRent, houseNumber, floorNumber, user);
        newArray[this.properties.length] = property;
        this.properties = newArray;
    }

    public boolean postNewProperty(User user) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (user.equals(this.properties[i].getPublishingUser())) {
                counter++;
                if (user.getIsMediator()) {
                    if (counter == RESTRICTION_MEDIATOR) {
                        System.out.println("You cannot publish any more assets.");
                        return false;
                    }
                } else {
                    if (counter == RESTRICTION_PRIVATE_USER) {
                        System.out.println("You cannot publish any more assets.");
                        return false;
                    }
                }
            }
        }
        String[] cityArr = new String[this.addresses.length];
        boolean exists = false;
        for (int i = 0; i < this.addresses.length; i++) {
            for (int j = 0; j < cityArr.length; j++) {
                if (cityArr[j] == (this.addresses[i].getCityName())) {
                    exists = true;
                }
            }
            if (!exists) {
                cityArr[i] = this.addresses[i].getCityName();
            }
            exists = false;
        }
        for (int i = 0; i < cityArr.length; i++) {
            if (cityArr[i] != null) {
                System.out.println(cityArr[i]);
            }
        }
        exists = false;
        String[] streetArr = new String[this.addresses.length];
        System.out.println("Choose a city: ");
        String city = sc.nextLine();
        for (int i = 0; i < cityArr.length; i++) {
            if (city.equals(cityArr[i])) {
                exists = true;
            }
        }
        if (!exists) {
            System.out.println("Your input does not exist in the list of cities. ");
            return false;
        } else {
            for (int i = 0; i < this.addresses.length; i++) {
                if (this.addresses[i].getCityName().equals(city)) {
                    System.out.println(this.addresses[i].getStreetName());
                    streetArr[i] = this.addresses[i].getStreetName();
                }
            }
        }
        exists = false;
        System.out.println("Choose a street: ");
        String street = sc.nextLine();
        for (int i = 0; i < streetArr.length; i++) {
            if (street.equals(streetArr[i])) {
                exists = true;
            }
        }
        if (!exists) {
            System.out.println("Your input does not exist in the list of streets. ");
            return false;
        } else {
            Address address = new Address(city, street);
            String type = "0";
            System.out.println("What is the type of property? \n" +
                    "1) Apartment. \n" +
                    "2) Penthouse. \n" +
                    "3) Private house.");
            int answer = sc.nextInt();
            int floor = 0;
            if (answer == (APARTMENT)) {
                type = "Apartment";
                System.out.println("Which floor is the apartment at? ");
                floor = sc.nextInt();
            } else if (answer == (PENTHOUSE)) {
                type = "Penthouse";
                System.out.println("Which floor is the penthouse at? ");
                floor = sc.nextInt();
            } else if (answer == (PRIVATE_HOUSE)) {
                type = "Private house";
            }
            System.out.println("How many rooms are there? ");
            int rooms = sc.nextInt();
            System.out.println("What is the asset's number? ");
            int houseNumber = sc.nextInt();
            System.out.println("Is the asset for sale(1) or rent(2)? ");
            boolean isForRent = true;
            int answer2 = sc.nextInt();
            if (answer2 == SELL) {
                isForRent = false;
            }
            System.out.println("What is the asset's price? ");
            int price = sc.nextInt();
            addPropertyToArray(address, rooms, price, type, isForRent, houseNumber, floor, user);
            return true;
        }
    }

    public void removeProperty(User user) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getPublishingUser().equals(user)) {
                System.out.println(count + 1 + ")" + this.properties[i].toString() + "\n");
                count++;
            }
        }
        if (count == NO_PROPERTIES) {
            System.out.println("You have not posted any assets yet.");
            return;
        }
        System.out.println("Choose the asset you would like to remove. ");
        int answer = sc.nextInt();
        Property[] property2 = new Property[this.properties.length - 1];
        if (count > USER_PROPERTIES) {
            count = 0;
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPublishingUser().equals(user)) {
                    ++count;
                    if (count == answer) {
                        --i;
                    } else {
                        property2[i] = this.properties[i + 1];
                        ++i;
                    }
                }
            }
            this.properties = property2;
            System.out.println("The asset has been removed. ");
        } else {
            this.properties = property2;
            System.out.println("The asset has been removed. ");
        }
    }

    public void printAllProperties() {
        for (int i = 0; i < this.properties.length; i++) {
            System.out.println(properties[i] + "\n");
        }
    }

    public void printAllUserProperties(User user) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getPublishingUser().equals(user)) {
                System.out.println(this.properties[i].toString() + "\n");
            }
        }
    }

    public Property[] search() {
        Scanner sc = new Scanner(System.in);
        boolean rent = true;
        String type;
        int rooms;
        int priceMax;
        int priceMin;
        int irrelevant = 0;

        System.out.println("Rent(0) or sell(1)? -999 if irrelevant.");
        int answer = sc.nextInt();
        if (answer == RENT) {
            rent = true;
        } else if (answer == SELL) {
            rent = false;
        } else if (answer == IRRELEVANT)
            irrelevant = IRRELEVANT;

        System.out.println("What is the property type? Apartment, Penthouse or Private house? -999 if irrelevant.");
        type = sc.next();
        System.out.println("How many rooms?  -999 if irrelevant.");
        rooms = sc.nextInt();
        System.out.println("What is the maximum price?  -999 if irrelevant.");
        priceMax = sc.nextInt();
        System.out.println("What is the minimum price?  -999 if irrelevant.");
        priceMin = sc.nextInt();

        boolean fittingRooms = false;
        boolean fittingRent = false;
        boolean fittingType = false;
        boolean fittingPrice = false;
        boolean fitting = false;

        Property[] tempProperties = new Property[this.properties.length];
        for (int i = 0; i < this.properties.length; i++) {
            Property tempProperty = this.properties[i];
            if (tempProperty.getIsForRent() == rent || irrelevant == IRRELEVANT) {
                fittingRent = true;
            }
            if (tempProperty.getRooms() == rooms || rooms == IRRELEVANT) {
                fittingRooms = true;
            }
            if (tempProperty.getType().equals(type) || type.equals(IRRELEVANT + "")) {
                fittingType = true;
            }
            if ((tempProperty.getPrice() <= priceMax && tempProperty.getPrice() >= priceMin)
                    || (priceMin == IRRELEVANT || priceMax == IRRELEVANT)) {
                fittingPrice = true;
            }
            if (fittingRent && fittingRooms && fittingType && fittingPrice) {
                fitting = true;
            }
            if (fitting) {
                tempProperties[i] = this.properties[i];
            }
        }
        int count = 0;

        for (int i = 0; i < tempProperties.length; i++) {
            if (tempProperties[i] != null) {
                count++;
            }
        }
        Property[] returnedProperties = new Property[count];
        count = 0;
        for (int i = 0; i < tempProperties.length; i++) {
            if (tempProperties[i] != null) {
                returnedProperties[count] = tempProperties[i];
                count++;
            }
        }
        for (Property property : returnedProperties) {
            System.out.println(property);
        }
        System.out.println();

        return returnedProperties;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    public String toString() {
        return "Users: " + this.users + "\n" +
                "Properties: " + this.properties + "\n" +
                "Addresses: " + this.addresses + "\n";
    }
}
