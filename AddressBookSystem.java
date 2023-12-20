import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}

class AddressBookSystem {
    private static final String FILE_PATH = "contacts.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        loadContactsFromFile(addressBook);

        while (true) {
            System.out.println("\nAddress Book System Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save and Exit");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addContact(addressBook);
                    break;
                case 2:
                    removeContact(addressBook);
                    break;
                case 3:
                    searchContact(addressBook);
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    saveContactsToFile(addressBook);
                    System.out.println("Address Book saved successfully.");
                    System.out.println("Exiting the Address Book System. Thank you for using the application!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addContact(AddressBook addressBook) {
        String name = getStringInput("Enter name: ");
        String phoneNumber = getStringInput("Enter phone number: ");
        String emailAddress = getStringInput("Enter email address: ");

        Contact newContact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(newContact);
        System.out.println("Contact added successfully.");
    }

    private static void removeContact(AddressBook addressBook) {
        String nameToRemove = getStringInput("Enter the name to remove: ");
        addressBook.removeContact(nameToRemove);
        System.out.println("Contact removed successfully.");
    }

    private static void searchContact(AddressBook addressBook) {
        String nameToSearch = getStringInput("Enter the name to search: ");
        Contact foundContact = addressBook.searchContact(nameToSearch);

        if (foundContact != null) {
            System.out.println("Contact found: " + foundContact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void loadContactsFromFile(AddressBook addressBook) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    addressBook.addContact(contact);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }

    private static void saveContactsToFile(AddressBook addressBook) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : addressBook.getContacts()) {
                writer.write(contact.getName() + ", " + contact.getPhoneNumber() + ", " + contact.getEmailAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
