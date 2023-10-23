import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Song {
    private String title;
    private String artist;
    private int duration;

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + " seconds)";
    }
}

public class practice {
    private LinkedList<Song> queue = new LinkedList<>();
    private Song currentSong = null;
    private String playMode = "Repeat All";
    private List<Song> allSongs = new LinkedList<>();

    public void addSong(Song song) {
        queue.add(song);
    }

    public void removeSong(int index) {
        if (index >= 0 && index < queue.size()) {
            queue.remove(index);
        } else {
            System.out.println("Invalid song index.");
        }
    }

    public void playNext() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        if (playMode.equals("Repeat All")) {
            if (currentSong == null) {
                currentSong = queue.getFirst();
            } else {
                int currentIndex = queue.indexOf(currentSong);
                int nextIndex = (currentIndex + 1) % queue.size();
                currentSong = queue.get(nextIndex);
            }
        } else if (playMode.equals("Repeat One")) {
            // Handle Repeat One mode.
        } else if (playMode.equals("Shuffle")) {
            // Handle Shuffle mode.
        }

        System.out.println("Now Playing: " + currentSong);
    }

    public void setPlayMode(String mode) {
        playMode = mode;
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue:");
            int index = 0;
            for (Song song : queue) {
                System.out.println(index + ". " + song);
                index++;
            }
        }
    }

    public void addSongsByKeyword(String keyword, List<Song> allSongs) {
        List<Song> matchingSongs = new LinkedList<>();

        for (Song song : allSongs) {
            if (song.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    song.getArtist().toLowerCase().contains(keyword.toLowerCase())) {
                matchingSongs.add(song);
            }
        }

        if (matchingSongs.isEmpty()) {
            System.out.println("No songs found matching the keyword: " + keyword);
        } else {
            System.out.println("Matching Songs:");
            int index = 0;
            for (Song song : matchingSongs) {
                System.out.println(index + ". " + song);
                index++;
            }

            System.out.print("Enter the number(s) of the song(s) you want to add to the queue (comma-separated): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] indices = input.split(",");

            for (String indexStr : indices) {
                try {
                    int songIndex = Integer.parseInt(indexStr.trim());
                    if (songIndex >= 0 && songIndex < matchingSongs.size()) {
                        addSong(matchingSongs.get(songIndex));
                        System.out.println("Added to the queue: " + matchingSongs.get(songIndex));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + indexStr);
                }
            }
        }
    }

    public static void main(String[] args) {
        practice musicPlayer = new practice();

        musicPlayer.allSongs.add(new Song("Song 1", "Artist 1", 180));
        musicPlayer.allSongs.add(new Song("Song 2", "Artist 2", 210));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMini Music Player Menu:");
            System.out.println("1. Now Playing");
            System.out.println("2. Add Songs");
            System.out.println("3. Queue");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                musicPlayer.playNext();
            } else if (choice == 2) {
                System.out.print("Enter song title: ");
                String title = scanner.nextLine();
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.print("Enter song duration (seconds): ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                Song song = new Song(title, artist, duration);
                musicPlayer.addSong(song);
            } else if (choice == 3) {
                musicPlayer.displayQueue();
                System.out.println("Queue Menu:");
                System.out.println("1. Edit Queue");
                System.out.println("2. Set Play Mode");
                System.out.println("3. Search and Add Songs");

                System.out.print("Enter your choice: ");
                int subChoice = scanner.nextInt();
                scanner.nextLine();

                if (subChoice == 1) {
                    musicPlayer.displayQueue();
                    System.out.println("Edit Queue Menu:");
                    System.out.println("1. Remove Song from Queue");
                    System.out.print("Enter song number to remove: ");
                    int removeIndex = scanner.nextInt();
                    scanner.nextLine();
                    musicPlayer.removeSong(removeIndex);
                } else if (subChoice == 2) {
                    System.out.print("Enter play mode (Repeat All, Repeat One, Shuffle): ");
                    String mode = scanner.nextLine();
                    musicPlayer.setPlayMode(mode);
                } else if (subChoice == 3) {
                    System.out.print("Enter a keyword to search for songs: ");
                    String keyword = scanner.nextLine();
                    musicPlayer.addSongsByKeyword(keyword, musicPlayer.allSongs);
                }
            } else if (choice == 4) {
                break;
            }
        }
    }
}