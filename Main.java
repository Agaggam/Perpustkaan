import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    private static BookLinkedList bookList = new BookLinkedList();

    public static void main(String[] args) throws Exception {
        loadBooksFromFile("books.json");

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/api/books", new BooksHandler());
        server.createContext("/", new StaticFileHandler());

        server.setExecutor(null);
        System.out.println("Server berjalan di http://localhost:8000/");
        server.start();
    }

    private static void loadBooksFromFile(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        String[] entries = content.split("\\{");
        for (int i = 1; i < entries.length; i++) {
            String entry = "{" + entries[i].trim().replaceFirst("},?$", "");
            Book book = parseBook(entry);
            if (book != null) {
                bookList.add(book);
            }
        }
    }

    private static Book parseBook(String jsonEntry) {
        Book book = new Book();

        book.Title = extractValue(jsonEntry, "\"Title\":").replaceAll("^\"|\"$", "");
        book.Authors = extractValue(jsonEntry, "\"Authors\":").replaceAll("^\"|\"$", "");
        book.Genre = extractValue(jsonEntry, "\"Genre\":").replaceAll("^\"|\"$", "");
        book.Thumbnail = extractValue(jsonEntry, "\"Thumbnail\":").replaceAll("^\"|\"$", "");
        book.Description = extractValue(jsonEntry, "\"Description\":").replaceAll("^\"|\"$", "");

        try {
            book.Published_year = Integer.parseInt(extractValue(jsonEntry, "\"Published_year\":").trim());
            book.Average_rating = Double.parseDouble(extractValue(jsonEntry, "\"Average_rating\":").trim());
            book.Jumblah_halaman_buku = Integer.parseInt(extractValue(jsonEntry, "\"Jumblah_halaman_buku\":").trim());
        } catch (Exception e) {
            return null;
        }

        return book;
    }

    private static String extractValue(String json, String key) {
        int startIndex = json.indexOf(key);
        if (startIndex == -1) return "";
        startIndex = json.indexOf(':', startIndex) + 1;
        int endIndex = startIndex;
        int bracketCount = 0;
        boolean inString = false;

        while (endIndex < json.length()) {
            char c = json.charAt(endIndex);

            if (c == '"' && (endIndex == 0 || json.charAt(endIndex - 1) != '\\')) {
                inString = !inString;
            }

            if (!inString) {
                if (c == '{') bracketCount++;
                else if (c == '}') bracketCount--;
                else if (c == ',' && bracketCount == 0) break;
            }

            endIndex++;
        }

        return json.substring(startIndex, endIndex).trim();
    }

    static class BooksHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            BookLinkedList responseList = copyList(bookList);

            Map<String, String> params = new HashMap<>();
            if (query != null) {
                // Parsing query string
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=", 2);
                    if (keyValue.length > 1) {
                        params.put(keyValue[0], keyValue[1]);
                    } else {
                        params.put(keyValue[0], "");
                    }
                }

                // Filter by keyword
                String keyword = params.get("keyword");
                if (keyword != null && !keyword.isEmpty()) {
                    BookLinkedList filtered = new BookLinkedList();
                    Node current = responseList.getHead();
                    while (current != null) {
                        if (current.data.Title.toLowerCase().contains(keyword.toLowerCase())) {
                            filtered.add(current.data);
                        }
                        current = current.next;
                    }
                    responseList = filtered;
                }

                // Filter by genre
                String genre = params.get("genre");
                if (genre != null && !genre.isEmpty()) {
                    BookLinkedList filtered = new BookLinkedList();
                    Node current = responseList.getHead();
                    while (current != null) {
                        if (current.data.Genre.equalsIgnoreCase(genre)) {
                            filtered.add(current.data);
                        }
                        current = current.next;
                    }
                    responseList = filtered;
                }

                // Sorting
                String sort = params.get("sort");
                if ("year_asc".equals(sort)) {
                    responseList.sortByYearAsc();
                } else if ("year_desc".equals(sort)) {
                    responseList.sortByYearDesc();
                } else if ("rating_asc".equals(sort)) {
                    responseList.sortByRatingAsc();
                } else if ("rating_desc".equals(sort)) {
                    responseList.sortByRatingDesc();
                }
            }

            String jsonResponse = responseList.toJson();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
            exchange.getResponseBody().write(jsonResponse.getBytes());
            exchange.getResponseBody().close();
        }

        private BookLinkedList copyList(BookLinkedList source) {
            BookLinkedList dest = new BookLinkedList();
            Node current = source.getHead();
            while (current != null) {
                dest.add(current.data);
                current = current.next;
            }
            return dest;
        }
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String filename = path.equals("/") ? "index.html" : "." + path;

            if (Files.exists(Paths.get(filename))) {
                byte[] response = Files.readAllBytes(Paths.get(filename));
                String contentType = getContentType(filename);
                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
            } else {
                String response = "404 Not Found";
                exchange.sendResponseHeaders(404, response.length());
                exchange.getResponseBody().write(response.getBytes());
            }
            exchange.getResponseBody().close();
        }

        private String getContentType(String filename) {
            if (filename.endsWith(".html")) return "text/html";
            if (filename.endsWith(".css")) return "text/css";
            if (filename.endsWith(".js")) return "application/javascript";
            if (filename.endsWith(".json")) return "application/json";
            return "application/octet-stream";
        }
    }
}