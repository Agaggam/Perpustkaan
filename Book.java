public class Book {
    String Title;
    String Authors;
    String Genre;
    String Thumbnail;
    String Description;
    int Published_year;
    double Average_rating;
    int Jumblah_halaman_buku;

    @Override
    public String toString() {
        return "{Title='" + Title + "', Authors='" + Authors + "', Genre='" + Genre + "', Published_year=" + Published_year + "}";
    }
}