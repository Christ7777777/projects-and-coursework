package edu.iastate.cs228.hw4;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Varun Advani
 */

/**
 *
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions {

    /**
     * The main method generates a simulation of rental and return activities.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        VideoStore videoStore = new VideoStore("videoList1.txt");
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Transactions at a Video Store");
			System.out.println("keys: 1 (rent)     2 (bulk rent)");
			System.out.println("      3 (return)   4 (bulk return)");
			System.out.println("      5 (summary)  6 (exit)");
			boolean isOver = false;
            while (!isOver) {
                int choice = menuChoice(scanner);
                switch (choice) {
                    case 1:
						try {
							System.out.print("Film to rent: ");
							String lineRent = scanner.nextLine();
							String filmToRent = VideoStore.parseFilmName(lineRent);
							int filmToRentN = VideoStore.parseNumCopies(lineRent);
							videoStore.videoRent(filmToRent, filmToRentN);
						}
						catch (IllegalArgumentException | FilmNotInInventoryException | AllCopiesRentedOutException e)
						{
							System.out.println(e.getMessage());
						}
                        break;
                    case 2:
						try {
							System.out.print("Video file (rent): ");
							String fileToRent = scanner.nextLine();
							videoStore.bulkRent(fileToRent);
						}
						catch (FileNotFoundException | IllegalArgumentException | FilmNotInInventoryException | AllCopiesRentedOutException e)
						{
							System.out.println(e.getMessage());
						}
                        break;
                    case 3:
						try {
							System.out.print("Film to return: ");
							String lineReturn = scanner.nextLine();
							String filmToReturn = VideoStore.parseFilmName(lineReturn);
							int filmToReturnN = VideoStore.parseNumCopies(lineReturn);
							videoStore.videoReturn(filmToReturn, filmToReturnN);
						}
						catch (IllegalArgumentException | FilmNotInInventoryException e)
						{
							System.out.println(e.getMessage());
						}
						break;
                    case 4:
						try {
							System.out.print("Video file (return): ");
							String fileToRent = scanner.nextLine();
							videoStore.bulkReturn(fileToRent);
						}
						catch (FileNotFoundException | IllegalArgumentException | FilmNotInInventoryException e)
						{
							System.out.println(e.getMessage());
						}
						break;
                    case 5:
						System.out.println(videoStore.transactionsSummary());
                        break;
                    case 6:
                        isOver = true;
                        System.out.println("Thank you for visiting, Good Bye!");
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
    }

    private static int menuChoice(Scanner scanner) {
        while (true) {
            try {
				System.out.println();
				System.out.print("Transaction: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 6) {
                    throw new IllegalArgumentException();
                }
                return choice;
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }
}
