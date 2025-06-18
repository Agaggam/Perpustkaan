class BookLinkedList {
    private Node head;

    public void add(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Sorting methods
    public void sortByYearAsc() {
        head = mergeSortYear(head);
    }

    public void sortByYearDesc() {
        head = mergeSortYearDesc(head);
    }

    public void sortByRatingAsc() {
        head = mergeSortRating(head);
    }

    public void sortByRatingDesc() {
        head = mergeSortRatingDesc(head);
    }

    // Merge Sort - By Year (Ascending)
    private Node mergeSortYear(Node node) {
        if (node == null || node.next == null) return node;
        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSortYear(node);
        Node right = mergeSortYear(nextOfMiddle);
        return mergeYear(left, right);
    }

    private Node mergeYear(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.data.Published_year <= b.data.Published_year) {
            a.next = mergeYear(a.next, b);
            return a;
        } else {
            b.next = mergeYear(a, b.next);
            return b;
        }
    }

    // Merge Sort - By Year (Descending)
    private Node mergeSortYearDesc(Node node) {
        if (node == null || node.next == null) return node;
        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSortYearDesc(node);
        Node right = mergeSortYearDesc(nextOfMiddle);
        return mergeYearDesc(left, right);
    }

    private Node mergeYearDesc(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.data.Published_year >= b.data.Published_year) {
            a.next = mergeYearDesc(a.next, b);
            return a;
        } else {
            b.next = mergeYearDesc(a, b.next);
            return b;
        }
    }

    // Merge Sort - By Rating (Ascending)
    private Node mergeSortRating(Node node) {
        if (node == null || node.next == null) return node;
        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSortRating(node);
        Node right = mergeSortRating(nextOfMiddle);
        return mergeRating(left, right);
    }

    private Node mergeRating(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.data.Average_rating <= b.data.Average_rating) {
            a.next = mergeRating(a.next, b);
            return a;
        } else {
            b.next = mergeRating(a, b.next);
            return b;
        }
    }

    // Merge Sort - By Rating (Descending)
    private Node mergeSortRatingDesc(Node node) {
        if (node == null || node.next == null) return node;
        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSortRatingDesc(node);
        Node right = mergeSortRatingDesc(nextOfMiddle);
        return mergeRatingDesc(left, right);
    }

    private Node mergeRatingDesc(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.data.Average_rating >= b.data.Average_rating) {
            a.next = mergeRatingDesc(a.next, b);
            return a;
        } else {
            b.next = mergeRatingDesc(a, b.next);
            return b;
        }
    }

    // Helper: cari tengah linked list
    private Node getMiddle(Node node) {
        if (node == null) return node;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Konversi linked list ke JSON string
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        boolean first = true;

        while (current != null) {
            Book b = current.data;

            if (!first) {
                sb.append(",");
            } else {
                first = false;
            }

            sb.append("{")
              .append("\"Title\":").append(escapeJson(b.Title)).append(",")
              .append("\"Authors\":").append(escapeJson(b.Authors)).append(",")
              .append("\"Genre\":").append(escapeJson(b.Genre)).append(",")
              .append("\"Thumbnail\":").append(escapeJson(b.Thumbnail)).append(",")
              .append("\"Description\":").append(escapeJson(b.Description)).append(",")
              .append("\"Published_year\":").append(b.Published_year).append(",")
              .append("\"Average_rating\":").append(b.Average_rating).append(",")
              .append("\"Jumblah_halaman_buku\":").append(b.Jumblah_halaman_buku)
              .append("}");

            current = current.next;
        }

        sb.append("]");
        System.out.println("JSON generated: " + sb.toString()); // Debug log
        return sb.toString();
    }

    // Memperbaiki escaping karakter khusus dalam string JSON
    private String escapeJson(String s) {
        if (s == null) return "null";
        s = s.replace("\\", "\\\\");   // Escape backslash
        s = s.replace("\"", "\\\"");    // Escape quotes
        s = s.replace("\n", "\\n");     // Escape newline
        s = s.replace("\r", "\\r");     // Escape carriage return
        s = s.replace("\t", "\\t");     // Escape tab
        return "\"" + s + "\"";
    }

    public Node getHead() {
        return head;
    }
}