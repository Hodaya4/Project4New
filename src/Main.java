import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String CREATE_ACCOUNT = "1";
        final String LOG_IN = "2";
        final String EXIT = "3";
        final String PUBLISH_ASSET = "1";
        final String DELETE_ASSET = "2";
        final String VIEW_ASSETS = "3";
        final String VIEW_USER_ASSETS = "4";
        final String SEARCH = "5";
        final String EXIT_INNER = "6";

        Scanner sc = new Scanner(System.in);
        RealEstate realEstate = new RealEstate();
        String choice = "";
        do {
            System.out.println("Choose from the following: \n" +
                    "1) Create an account. \n" +
                    "2) Log in to an existing account. \n" +
                    "3) Exit.");
            choice = sc.nextLine();

            switch (choice) {
                case CREATE_ACCOUNT:
                    realEstate.createUser();
                    System.out.println("Your user has been created. \n");
                    break;
                case LOG_IN:
                    User user = realEstate.userLogin();
                    if (user == null) {
                        break;
                    } else {
                        do {
                            System.out.println("Choose from the following: \n" +
                                    "1) Publish a new asset. \n" +
                                    "2) Delete an asset's publication. \n" +
                                    "3) View all system's assets. \n" +
                                    "4) View all my published assets. \n" +
                                    "5) Search asset by parameters. \n" +
                                    "6) Exit back to main menu. ");
                            choice = sc.nextLine();
                            switch (choice) {
                                case PUBLISH_ASSET:
                                    boolean res = realEstate.postNewProperty(user);
                                    if (res) {
                                        System.out.println("The property has been posted. ");
                                        break;
                                    }
                                case DELETE_ASSET:
                                    realEstate.removeProperty(user);
                                    break;
                                case VIEW_ASSETS:
                                    realEstate.printAllProperties();
                                    break;
                                case VIEW_USER_ASSETS:
                                    realEstate.printAllUserProperties(user);
                                    break;
                                case SEARCH:
                                    realEstate.search();
                                    break;
                                case EXIT_INNER:
                                    break;
                            }
                        } while (!choice.equals(EXIT_INNER));
                    }
                case EXIT:
                    break;
            }
        } while (!choice.equals(EXIT)) ;
    }
}