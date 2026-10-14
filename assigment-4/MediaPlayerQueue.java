import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/** A linked-list playlist with priority additions, history, and two-way browsing. */
public class MediaPlayerQueue {

    private static class Song {
        private final String id;
        private final String title;
        private final String artist;

        private Song(String id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof Song)) return false;
            Song song = (Song) other;
            return id.equals(song.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public String toString() {
            return title + " — " + artist;
        }
    }

    private final LinkedList<Song> playlist = new LinkedList<>();
    private final LinkedList<Song> recentlyPlayed = new LinkedList<>();

    public void addLast(Song song) {
        playlist.addLast(song);
    }

    /** Places an urgent song at the front of the queue. */
    public void addPriority(Song song) {
        playlist.addFirst(song);
    }

    /** Inserts at a queue position; position 0 means the very front. */
    public boolean insertAt(int position, Song song) {
        if (position < 0 || position > playlist.size()) {
            return false;
        }
        ListIterator<Song> iterator = playlist.listIterator();
        for (int current = 0; current < position; current++) {
            iterator.next();
        }
        iterator.add(song);
        return true;
    }

    /** Inserts after the requested song. ListIterator keeps the traversal sequential. */
    public boolean insertAfter(String existingSongId, Song newSong) {
        ListIterator<Song> iterator = playlist.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().id.equals(existingSongId)) {
                iterator.add(newSong);
                return true;
            }
        }
        return false;
    }

    /** Removes repeated IDs in one pass while keeping the first occurrence and its position. */
    public void removeDuplicates() {
        Set<String> seenIds = new HashSet<>();
        ListIterator<Song> iterator = playlist.listIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (!seenIds.add(song.id)) {
                iterator.remove();
            }
        }
    }

    /** Plays the next song, moves it out of the queue, and adds it to recent history. */
    public Song playNext() {
        if (playlist.isEmpty()) {
            return null;
        }
        Song song = playlist.removeFirst();
        recentlyPlayed.addFirst(song);
        return song;
    }

    /** Shows the playlist in both directions without using numeric indexes. */
    public void showPlaylistBothWays() {
        System.out.println("Playlist, next up first:");
        ListIterator<Song> forward = playlist.listIterator();
        while (forward.hasNext()) {
            System.out.println("  " + forward.next());
        }

        System.out.println("Playlist, from the end:");
        ListIterator<Song> backward = playlist.listIterator(playlist.size());
        while (backward.hasPrevious()) {
            System.out.println("  " + backward.previous());
        }
    }

    public void showRecentHistory() {
        System.out.println("Recently played (newest first): " + recentlyPlayed);
    }

    public static void main(String[] args) {
        MediaPlayerQueue player = new MediaPlayerQueue();
        Song blueSky = new Song("S1", "Blue Sky", "The Daylights");
        Song quietRoad = new Song("S2", "Quiet Road", "Mira Lane");
        Song nightDrive = new Song("S3", "Night Drive", "The Daylights");

        player.addLast(blueSky);
        player.addLast(quietRoad);
        player.addLast(new Song("S1", "Blue Sky (duplicate)", "The Daylights"));
        player.insertAfter("S2", nightDrive);
        player.addPriority(new Song("S4", "Morning Run", "Jun Park"));
        player.removeDuplicates();
        player.showPlaylistBothWays();
        System.out.println("Now playing: " + player.playNext());
        player.showRecentHistory();
    }
}
