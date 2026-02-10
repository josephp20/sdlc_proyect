import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

void main() {
    //*******IMPORTANT******
    //try use the file route inside the project for prevent any problem with restricted access
    // Testing file data route (Jose Computer)
    // C:\Users\jose2\Desktop\Software1\sdlc\src\data.txt


    //Call my class FileLoader- and starting read the file
    Scanner scanner = new Scanner(System.in);

    // Read My File
    System.out.print("Type the route of your file: ");
    String filePath = scanner.nextLine();

    List<String> patronData = FileLoader.readFile(filePath);

    if (patronData.isEmpty()) {
        System.out.println("We can't load the data the file is empty.");
        scanner.close();
        return;
    }

    System.out.println("File exist.\n");

    int option = 0;

    // Menú can't stop until the user type option 4
    while (option != 4) {
        System.out.println("===== LMS MENU =====");
        System.out.println("1. Add new patron");
        System.out.println("2. Remove patron");
        System.out.println("3. List all patrons");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            scanner.nextLine(); // clean buffer
        } else {
            System.out.println("Type a valid number.\n");
            scanner.nextLine();
            continue;
        }

        switch (option) {
            case 1:
                //-------------------------Printing all data inside the document----------------------

            System.out.println("Data inside the File:");
                for (String line : patronData) {
                    System.out.println(line);
                }
                //------------------------------------Creating the new register----------------------------------------------
                Random random = new Random();
                int id = 1000000 + random.nextInt(9000000);

                System.out.print("Type the name: ");
                String name = scanner.nextLine();

                System.out.print("Type the address: ");
                String address = scanner.nextLine();

                double fine;
                while (true) {
                    System.out.print("Type the fine (0-250) ");
                    if (scanner.hasNextDouble()) {
                        fine = scanner.nextDouble();
                        scanner.nextLine();

                        if (fine >= 0 && fine <= 250) {
                            break;
                        }
                    } else {
                        scanner.nextLine();
                    }
                    System.out.println("invalid Fine.");
                }

                String record = id + "-" + name + "-" + address + "-" + fine;
                FileLoader.addingData(filePath, record);

                System.out.println("Record added sucessfully.\n");

                break;

            case 2:
                // Reload the file data
                //-------------------------Printing all data inside the document UPDATED----------------------
                List<String> currentData = FileLoader.readFile(filePath);

                if (currentData.isEmpty()) {
                    System.out.println("No data for delete.\n");
                    break;
                }

                // Listar todos los registros
                System.out.println("\n=== Listin patrons FSL ===");
                for (String recordDelete : currentData) {
                    System.out.println(recordDelete);
                }
                //-------------------------Starting to Collecting Data for Delete--------------------------
                System.out.print("\nType the ID of the record you want delete: ");
                String idToRemove = scanner.nextLine();

                boolean found = false;
                List<String> updatedData = new ArrayList<>();

                for (String recordDelete : currentData) {
                    String[] parts = recordDelete.split("-", 2);
                    if (parts[0].equals(idToRemove)) {
                        found = true;
                    } else {
                        updatedData.add(recordDelete);
                    }
                }

                if (!found) {
                    System.out.println("ID didn't find.\n");
                } else {
                    FileLoader.updateData(filePath, updatedData);
                    System.out.println("Patron Deleted.\n");
                }
                break;

            case 3:
                //-------------------------Starting to Collecting Data--------------------------
                System.out.println("List all the patrons\n");

                // Bring Update Data
                List<String> currentDataList = FileLoader.readFile(filePath);

                if (currentDataList.isEmpty()) {
                    System.out.println("No data to show\n");
                    break;
                }

                // List data
                System.out.println("\n=== Listin patrons FSL  ===");
                for (String recordDelete : currentDataList) {
                    System.out.println(recordDelete);
                }
                break;

            case 4:
                System.out.println("Exit the system LMS...");
                break;

            default:
                System.out.println("Invalid Option, try again.\n");
        }
    }

    scanner.close();
}



