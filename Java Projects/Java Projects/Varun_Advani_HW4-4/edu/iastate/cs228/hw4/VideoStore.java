package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Varun Advani
 */

public class VideoStore {
    protected SplayTree<Video> inventory = new SplayTree<>();     // all the videos at the store

    // ------------
    // Constructors
    // ------------

    /**
     * Default constructor sets inventory to an empty tree.
     */
    public VideoStore() {
        // no need to implement.
    }


    /**
     * Constructor accepts a video file to create its inventory.  Refer to Section 3.2 of
     * the project description for details regarding the format of a video file.
     * <p>
     * Calls setUpInventory().
     *
     * @param videoFile no format checking on the file
     * @throws FileNotFoundException
     */
    public VideoStore(String videoFile) throws FileNotFoundException {
        setUpInventory(videoFile);
    }


    /**
     * Accepts a video file to initialize the splay tree inventory.  To be efficient,
     * add videos to the inventory by calling the addBST() method, which does not splay.
     * <p>
     * Refer to Section 3.2 for the format of video file.
     *
     * @param videoFile correctly formated if exists
     * @throws FileNotFoundException
     */
    public void setUpInventory(String videoFile) throws FileNotFoundException {
        inventory.clear();
        bulkImport(videoFile);
    }


    // ------------------
    // Inventory Addition
    // ------------------

    /**
     * Find a Video object by film title.
     *
     * @param film
     * @return
     */
    public Video findVideo(String film) {
        return inventory.findElement(new Video(film));
    }


    /**
     * Updates the splay tree inventory by adding a number of video copies of the film.
     * (Splaying is justified as new videos are more likely to be rented.)
     * <p>
     * Calls the add() method of SplayTree to add the video object.
     * <p>
     * a) If true is returned, the film was not on the inventory before, and has been added.
     * b) If false is returned, the film is already on the inventory.
     * <p>
     * The root of the splay tree must store the corresponding Video object for the film. Update
     * the number of copies for the film.
     *
     * @param film title of the film
     * @param n    number of video copies
     */
    public void addVideo(String film, int n) {
        boolean added = inventory.add(new Video(film, n));
        if (!added) {
            inventory.getRoot().addNumCopies(n);
        }

    }


    /**
     * Add one video copy of the film.
     *
     * @param film title of the film
     */
    public void addVideo(String film) {
        addVideo(film, 1);
    }


    /**
     * Update the splay trees inventory by adding videos.  Perform binary search additions by
     * calling addBST() without splaying.
     * <p>
     * The videoFile format is given in Section 3.2 of the project description.
     *
     * @param videoFile correctly formated if exists
     * @throws FileNotFoundException
     */
    public void bulkImport(String videoFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(videoFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String film = parseFilmName(line);
                int numCopies = parseNumCopies(line);
                inventory.addBST(new Video(film, numCopies));
            }
        }
    }


    // ----------------------------
    // Video Query, Rental & Return 
    // ----------------------------

    /**
     * Search the splay tree inventory to determine if a video is available.
     *
     * @param film
     * @return true if available
     */
    public boolean available(String film) {
        Video video = findVideo(film);
        if (video != null) {
            return video.getNumAvailableCopies() > 0;
        }
        return false;
    }


    /**
     * Update inventory.
     * <p>
     * Search if the film is in inventory by calling findElement(new Video(film, 1)).
     * <p>
     * If the film is not in inventory, prints the message "Film <film> is not
     * in inventory", where <film> shall be replaced with the string that is the value
     * of the parameter film.  If the film is in inventory with no copy left, prints
     * the message "Film <film> has been rented out".
     * <p>
     * If there is at least one available copy but n is greater than the number of
     * such copies, rent all available copies. In this case, no AllCopiesRentedOutException
     * is thrown.
     *
     * @param film
     * @param n
     * @throws IllegalArgumentException    if n <= 0 or film == null or film.isEmpty()
     * @throws FilmNotInInventoryException if film is not in the inventory
     * @throws AllCopiesRentedOutException if there is zero available copy for the film.
     */
    public void videoRent(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException,
            AllCopiesRentedOutException {
        if (film == null || film.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Video video = inventory.findElement(new Video(film, 1));
        if (video == null) {
            throw new FilmNotInInventoryException("Film " + film + " is not in inventory");
        }
        video.rentCopies(n);
    }


    /**
     * Update inventory.
     * <p>
     * 1. Calls videoRent() repeatedly for every video listed in the file.
     * 2. For each requested video, do the following:
     * a) If it is not in inventory or is rented out, an exception will be
     * thrown from videoRent().  Based on the exception, prints out the following
     * message: "Film <film> is not in inventory" or "Film <film>
     * has been rented out." In the message, <film> shall be replaced with
     * the name of the video.
     * b) Otherwise, update the video record in the inventory.
     * <p>
     * For details on handling of multiple exceptions and message printing, please read Section 3.4
     * of the project description.
     *
     * @param videoFile correctly formatted if exists
     * @throws FileNotFoundException
     * @throws IllegalArgumentException    if the number of copies of any film is <= 0
     * @throws FilmNotInInventoryException if any film from the videoFile is not in the inventory
     * @throws AllCopiesRentedOutException if there is zero available copy for some film in videoFile
     */
    public void bulkRent(String videoFile) throws FileNotFoundException, IllegalArgumentException,
            FilmNotInInventoryException, AllCopiesRentedOutException {
        List<String> errorMessages = new ArrayList<>();
        boolean hasIllegalArgumentException = false;
        boolean hasFilmNotInInventoryException = false;
        boolean hasAllCopiesRentedOutException = false;
        try (Scanner scanner = new Scanner(new File(videoFile))) {
            while (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine().trim();
					if (line.isEmpty()) {
						continue;
					}
                    String film = parseFilmName(line);
                    int numCopies = parseNumCopies(line);
                    videoRent(film, numCopies);
                } catch (IllegalArgumentException e) {
                    hasIllegalArgumentException = true;
                    errorMessages.add(e.getMessage());
                } catch (FilmNotInInventoryException e) {
                    hasFilmNotInInventoryException = true;
                    errorMessages.add(e.getMessage());
                } catch (AllCopiesRentedOutException e) {
                    hasAllCopiesRentedOutException = true;
                    errorMessages.add(e.getMessage());
                }
            }
            if (hasIllegalArgumentException) {
                throw new IllegalArgumentException(String.join("\n", errorMessages));
            }
            if (hasFilmNotInInventoryException) {
                throw new FileNotFoundException(String.join("\n", errorMessages));
            }
            if (hasAllCopiesRentedOutException) {
                throw new AllCopiesRentedOutException(String.join("\n", errorMessages));
            }
        }
    }


    /**
     * Update inventory.
     * <p>
     * If n exceeds the number of rented video copies, accepts up to that number of rented copies
     * while ignoring the extra copies.
     *
     * @param film
     * @param n
     * @throws IllegalArgumentException    if n <= 0 or film == null or film.isEmpty()
     * @throws FilmNotInInventoryException if film is not in the inventory
     */
    public void videoReturn(String film, int n) throws IllegalArgumentException, FilmNotInInventoryException {
        if (film == null || film.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Video video = inventory.findElement(new Video(film, 1));
        if (video == null) {
            throw new FilmNotInInventoryException("Film " + film + " is not in inventory");
        }
        video.returnCopies(n);
    }


    /**
     * Update inventory.
     * <p>
     * Handles excessive returned copies of a film in the same way as videoReturn() does.  See Section
     * 3.4 of the project description on how to handle multiple exceptions.
     *
     * @param videoFile
     * @throws FileNotFoundException
     * @throws IllegalArgumentException    if the number of return copies of any film is <= 0
     * @throws FilmNotInInventoryException if a film from videoFile is not in inventory
     */
    public void bulkReturn(String videoFile) throws FileNotFoundException, IllegalArgumentException,
            FilmNotInInventoryException {
        List<String> errorMessages = new ArrayList<>();
        boolean hasIllegalArgumentException = false;
        boolean hasFilmNotInInventoryException = false;
        try (Scanner scanner = new Scanner(new File(videoFile))) {
            while (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine().trim();
					if (line.isEmpty()) {
						continue;
					}
                    String film = parseFilmName(line);
                    int numCopies = parseNumCopies(line);
                    videoReturn(film, numCopies);
                } catch (IllegalArgumentException e) {
                    hasIllegalArgumentException = true;
                    errorMessages.add(e.getMessage());
                } catch (FilmNotInInventoryException e) {
                    hasFilmNotInInventoryException = true;
                    errorMessages.add(e.getMessage());
                }
            }
            if (hasIllegalArgumentException) {
                throw new IllegalArgumentException(String.join("\n", errorMessages));
            }
            if (hasFilmNotInInventoryException) {
                throw new FileNotFoundException(String.join("\n", errorMessages));
            }
        }
    }


    // ------------------------
    // Methods without Splaying
    // ------------------------

    /**
     * Performs inorder traversal on the splay tree inventory to list all the videos by film
     * title, whether rented or not.  Below is a sample string if printed out:
     * <p>
     * <p>
     * Films in inventory:
     * <p>
     * A Streetcar Named Desire (1)
     * Brokeback Mountain (1)
     * Forrest Gump (1)
     * Psycho (1)
     * Singin' in the Rain (2)
     * Slumdog Millionaire (5)
     * Taxi Driver (1)
     * The Godfather (1)
     *
     * @return
     */
    public String inventoryList() {
        String result = "Films in inventory:\n\n";
        for (Video video : inventory) {
            result += video.getFilm() + " (" + video.getNumCopies() + ")\n";
        }
        return result;
    }


    /**
     * Calls rentedVideosList() and unrentedVideosList() sequentially.  For the string format,
     * see Transaction 5 in the sample simulation in Section 4 of the project description.
     *
     * @return
     */
    public String transactionsSummary() {
        return rentedVideosList() + "\n" + unrentedVideosList();
    }

    /**
     * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
     * <p>
     * Below is a sample return string when printed out:
     * <p>
     * Rented films:
     * <p>
     * Brokeback Mountain (1)
     * Forrest Gump (1)
     * Singin' in the Rain (2)
     * The Godfather (1)
     *
     * @return
     */
    public String rentedVideosList() {
        String result = "Rented films:\n\n";
        for (Video video : inventory) {
            if (video.getNumRentedCopies() > 0) {
                result += video.getFilm() + " (" + video.getNumRentedCopies() + ")\n";

            }
        }
        return result;
    }


    /**
     * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
     * Prints only the films that have unrented copies.
     * <p>
     * Below is a sample return string when printed out:
     * <p>
     * <p>
     * Films remaining in inventory:
     * <p>
     * A Streetcar Named Desire (1)
     * Forrest Gump (1)
     * Psycho (1)
     * Slumdog Millionaire (4)
     * Taxi Driver (1)
     *
     * @return
     */
    public String unrentedVideosList() {
        String result = "Films remaining in inventory:\n\n";
        for (Video video : inventory) {
            if (video.getNumAvailableCopies() > 0) {
                result += video.getFilm() + " (" + video.getNumAvailableCopies() + ")\n";

            }
        }
        return result;
    }


    /**
     * Parse the film name from an input line.
     *
     * @param line
     * @return
     */
    public static String parseFilmName(String line) {
        String[] parts = line.split("[()]");
        return parts[0].trim();
    }


    /**
     * Parse the number of copies from an input line.
     *
     * @param line
     * @return
     */
    public static int parseNumCopies(String line) {
        String[] parts = line.split("[()]");
        if (parts.length == 1) {
            return 1;
        }
        return Integer.parseInt(parts[1]);

    }
}
