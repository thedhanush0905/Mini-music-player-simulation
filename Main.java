import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Song {
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

class MiniMusicPlayer {
    public static void main(String[] args) {
        Queue<Song> songQueue = new LinkedList<>();
        int mode = 2;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Now Playing");
            System.out.println("2. Add Songs to Application");
            System.out.println("3. Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (!songQueue.isEmpty()) {
                        System.out.println("Now Playing: " + songQueue.peek());
                        System.out.println("Press Enter to play the next song...");
                        scanner.nextLine();
                        if (mode == 0) {
                            // Repeat one: do nothing
                        } else if (mode == 1) {
                            // Repeat All: Move the first song to the end of the queue
                            Song currentSong = songQueue.poll();
                            songQueue.offer(currentSong);
                        } else if (mode == 2) {
                            // Shuffle: Shuffle the queue
                            LinkedList<Song> tempQueue = new LinkedList<>(songQueue);
                            songQueue.clear();
                            while (!tempQueue.isEmpty()) {
                                int randomIndex = (int) (Math.random() * tempQueue.size());
                                songQueue.offer(tempQueue.remove(randomIndex));
                            }
                        }
                    } else {
                        System.out.println("No songs in the queue.");
                    }
                    break;

                case 2:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    songQueue.offer(new Song(title, artist));
                    break;

                case 3:
                    System.out.println("Queue:");
                    int songNumber = 1;
                    for (Song song : songQueue) {
                        System.out.println(songNumber + ". " + song);
                        songNumber++;
                    }

                    System.out.println("Queue Options:");
                    System.out.println("1. Add Song");
                    System.out.println("2. Remove Song");
                    System.out.println("3. Mode Select");
                    System.out.print("Enter your choice: ");
                    int queueOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (queueOption) {
                        case 1:
                            System.out.print("Enter song title to add to the queue: ");
                            String searchTitle = scanner.nextLine();
                            // Implement a search and add functionality here
                            break;

                        case 2:
                            System.out.print("Enter the number of the song to remove from the queue: ");
                            int songToRemove = scanner.nextInt();
                            scanner.nextLine();
                            // Implement song removal by number in the queue here
                            break;

                        case 3:
                            System.out.println("Mode Select:");
                            System.out.println("0. Repeat One");
                            System.out.println("1. Repeat All");
                            System.out.println("2. Shuffle");
                            System.out.print("Enter your mode: ");
                            mode = scanner.nextInt();
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
