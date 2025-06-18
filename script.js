// LinkedList implementation (unchanged)
class BookNode {
  constructor(book) {
    this.data = book;
    this.next = null;
  }
}

class LinkedList {
  constructor() {
    this.head = null;
    this.size = 0;
  }

  add(book) {
    const newNode = new BookNode(book);
    if (!this.head) {
      this.head = newNode;
    } else {
      let current = this.head;
      while (current.next) current = current.next;
      current.next = newNode;
    }
    this.size++;
  }

  toArray() {
    const result = [];
    let current = this.head;
    while (current) {
      result.push(current.data);
      current = current.next;
    }
    return result;
  }

  clear() {
    this.head = null;
    this.size = 0;
  }
}

const booksDatabase = [
  // Data awal ini akan dihapus jika NUMBER_OF_BOOKS_TO_GENERATE diatur untuk membuat 10000 buku
  {
    Title: "The Great Gatsby",
    Authors: "F. Scott Fitzgerald",
    Genre: "Fiction",
    Thumbnail: "📖",
    Description: "A novel about the American Dream.",
    Rating: 4.2
  },
  {
    Title: "Zero",
    Authors: "Charles Seife",
    Genre: "Zero (The number)",
    Thumbnail: "📘",
    Description: "Exploring the concept of zero in mathematics and science.",
    Rating: 3.8
  },
  {
    Title: "Data Structures and Abstractions with Java",
    Authors: "Frank M. Carrano",
    Genre: "Computers",
    Thumbnail: "💻",
    Description: "A comprehensive guide to data structures using Java.",
    Rating: 4.5
  },
  {
    Title: "The Lord of the Rings",
    Authors: "J.R.R. Tolkien",
    Genre: "Fantasy",
    Thumbnail: "🧙",
    Description: "An epic high-fantasy adventure.",
    Rating: 4.9
  },
  {
    Title: "Sapiens: A Brief History of Humankind",
    Authors: "Yuval Noah Harari",
    Genre: "History",
    Thumbnail: "🌍",
    Description: "A global bestseller examining the history of our species.",
    Rating: 4.7
  },
];

// Helper function to get a random element from an array
function getRandomElement(arr) {
  return arr[Math.floor(Math.random() * arr.length)];
}

// Function to generate a random book entry
function generateRandomBook() {
  const adjs = ["Mysterious", "Ancient", "Lost", "Whispering", "Forgotten", "Digital", "Cosmic", "Golden", "Silent", "Hidden", "Enchanted", "Curious", "Bright", "Dark", "Epic", "Grand", "Tiny", "Giant", "Simple", "Complex"];
  const nouns = ["Chronicle", "Tale", "Adventure", "Voyage", "Secret", "Code", "Universe", "Realm", "Shadows", "Lights", "City", "Forest", "Ocean", "Mountain", "River", "Star", "Planet", "Machine", "Dream", "Echo"];
  const firstNames = ["Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Harry", "Ivy", "Jack", "Lena", "Mike", "Nora", "Oscar", "Penny", "Quinn", "Rachel", "Sam", "Tina", "Umar"];
  const lastNames = ["Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Jackson", "White", "Harris", "Clark"];

  const genres = [
    "Fiction", "Fantasy", "Science Fiction", "Mystery", "Thriller", "Horror", "Romance", "Historical Fiction",
    "Biography", "Autobiography", "Memoir", "Self-Help", "True Crime", "Poetry", "Humor", "Cookbook",
    "Travel", "History", "Science", "Technology", "Business", "Art", "Photography", "Music", "Philosophy",
    "Religion", "Spirituality", "Parenting", "Health", "Fitness", "Education", "Young Adult", "Children's"
  ];

  const thumbnails = ["📖", "🗺️", "💻", "🕵️", "🌌", "🍃", "🏛️", "🎨", "🦄", "🌋", "🧠", "💡", "🔬", "🎭", "🎵", "🧘", "✈️", "💰", "❤️", "👻"];

  const descriptionStarters = [
    "Uncover the truth behind a long-lost secret.",
    "Embark on an extraordinary journey through time and space.",
    "Explore the intricate workings of the human mind.",
    "A gripping narrative of survival and resilience.",
    "Dive into a world where magic and technology collide.",
    "This book challenges conventional wisdom about",
    "Follow the captivating life of a visionary leader.",
    "Discover practical strategies for personal growth.",
    "A chilling account of a real-life mystery.",
    "Experience the beauty and power of verse."
  ];
  const descriptionMiddles = [
    " With unexpected twists and turns,",
    " Through vivid prose and compelling characters,",
    " Blending historical accuracy with imaginative storytelling,",
    " Offering profound insights and actionable advice,",
    " This thought-provoking read will leave you questioning everything.",
    " Perfect for fans of deep philosophical discussions,",
    " It delves into the complexities of human relationships,",
    " Revealing shocking details and untold stories,",
    " A must-read for anyone interested in the future of humanity,",
    " Packed with humor and heartwarming moments,"
  ];
  const descriptionEnds = [
    " it's a story you won't soon forget.",
    " this book is a true masterpiece.",
    " a testament to the power of the human spirit.",
    " an essential guide for modern living.",
    " and a captivating exploration of the unknown.",
    " providing a fresh perspective on timeless themes.",
    " making it a truly immersive experience.",
    " leading to a surprising and satisfying conclusion.",
    " guaranteed to spark your imagination.",
    " leaving a lasting impact on its readers."
  ];

  const title = `${getRandomElement(adjs)} ${getRandomElement(nouns)} of ${getRandomElement(adjs)} ${getRandomElement(nouns)}`;
  const author = `${getRandomElement(firstNames)} ${getRandomElement(lastNames)}`;
  const genre = getRandomElement(genres);
  const thumbnail = getRandomElement(thumbnails);
  const description = `${getRandomElement(descriptionStarters)}${getRandomElement(descriptionMiddles)}${getRandomElement(descriptionEnds)}`;
  const rating = parseFloat((Math.random() * 4 + 1).toFixed(1)); // Rating from 1.0 to 5.0

  return {
    Title: title,
    Authors: author,
    Genre: genre,
    Thumbnail: thumbnail,
    Description: description,
    Rating: rating
  };
}

// Clear the initial array and add 10,000 random entries
booksDatabase.length = 0; // Clear existing initial data

const NUMBER_OF_BOOKS_TO_GENERATE = 10000;
for (let i = 0; i < NUMBER_OF_BOOKS_TO_GENERATE; i++) {
  booksDatabase.push(generateRandomBook());
}

console.log(booksDatabase);
console.log(`Total books in database: ${booksDatabase.length}`);

// Remaining functions
let booksList = new LinkedList();
let filteredBooks = new LinkedList();
let allGenres = [];
let currentView = 'grid';

function init() {
  booksDatabase.forEach(book => booksList.add(book));

  const genreSet = new Set();
  booksDatabase.forEach(book => genreSet.add(book.Genre));
  allGenres = Array.from(genreSet);

  const genreFilter = document.getElementById('genreFilter');
  genreFilter.innerHTML = '<option value="">Semua Genre</option>';
  allGenres.forEach(genre => {
    const option = document.createElement('option');
    option.value = genre;
    option.textContent = genre;
    genreFilter.appendChild(option);
  });

  // Event listener untuk filter rating baru
  document.getElementById('minRatingFilter').addEventListener('change', applyFilters);
  document.getElementById('searchInput').addEventListener('input', applyFilters);
  document.getElementById('genreFilter').addEventListener('change', applyFilters);
  document.getElementById('sortSelect').addEventListener('change', applyFilters);
  document.getElementById('gridView').addEventListener('click', () => switchView('grid'));
  document.getElementById('listView').addEventListener('click', () => switchView('list'));

  applyFilters();
}

function applyFilters() {
  const searchQuery = document.getElementById('searchInput').value.toLowerCase();
  const selectedGenre = document.getElementById('genreFilter').value;
  const minRating = parseFloat(document.getElementById('minRatingFilter').value); // Ambil nilai rating minimal
  const sortOption = document.getElementById('sortSelect').value;

  filteredBooks.clear();

  let tempArray = booksList.toArray().filter(book => {
    const matchesSearch = book.Title.toLowerCase().includes(searchQuery) || book.Authors.toLowerCase().includes(searchQuery);
    const matchesGenre = !selectedGenre || book.Genre === selectedGenre;
    // Tambahkan kondisi untuk rating minimal
    const matchesRating = isNaN(minRating) || book.Rating >= minRating;
    
    return matchesSearch && matchesGenre && matchesRating;
  });

  switch (sortOption) {
    case 'title':
      tempArray.sort((a, b) => a.Title.localeCompare(b.Title));
      break;
    case 'title-desc':
      tempArray.sort((a, b) => b.Title.localeCompare(a.Title));
      break;
    case 'author':
      tempArray.sort((a, b) => a.Authors.localeCompare(b.Authors));
      break;
    case 'author-desc':
      tempArray.sort((a, b) => b.Authors.localeCompare(a.Authors));
      break;
    case 'genre':
      tempArray.sort((a, b) => a.Genre.localeCompare(b.Genre));
      break;
    case 'rating-asc':
      tempArray.sort((a, b) => a.Rating - b.Rating);
      break;
    case 'rating-desc':
      tempArray.sort((a, b) => b.Rating - a.Rating);
      break;
  }

  tempArray.forEach(book => filteredBooks.add(book));
  updateStats();
  displayBooks();
}

function displayBooks() {
  const container = document.getElementById('booksContainer');
  const books = filteredBooks.toArray();

  if (books.length === 0) {
    container.innerHTML = `<div class="no-results"><h3>Tidak ada buku yang ditemukan</h3><p>Coba ubah kata kunci pencarian atau filter genre.</p></div>`;
    return;
  }

  const containerClass = currentView === 'grid' ? 'books-grid' : 'books-list';
  const booksHTML = books.map(book => {
    function getRatingStars(rating) {
      const fullStars = '⭐'.repeat(Math.floor(rating));
      // Menambahkan emoji bintang setengah jika rating memiliki desimal >= 0.5
      const halfStar = rating % 1 >= 0.5 && rating < 5 ? '🌟' : ''; // Atau '⭐' jika ingin konsisten dengan bintang penuh, atau biarkan kosong
      return `${fullStars}${halfStar} (${rating.toFixed(1)})`;
    }

    if (currentView === 'grid') {
      return `
        <div class="book-card">
          <div class="book-thumbnail">${book.Thumbnail}</div>
          <div class="book-title">${book.Title}</div>
          <div class="book-author">oleh ${book.Authors}</div>
          <div class="book-genre">${book.Genre}</div>
          <div class="book-rating">${getRatingStars(book.Rating)}</div>
          <div class="book-description">${book.Description}</div>
        </div>`;
    } else {
      return `
        <div class="book-list-item">
          <div class="book-thumbnail">${book.Thumbnail}</div>
          <div class="book-info">
            <div class="book-title">${book.Title}</div>
            <div class="book-author">oleh ${book.Authors}</div>
            <div class="book-genre">${book.Genre}</div>
            <div class="book-rating">${getRatingStars(book.Rating)}</div>
            <div class="book-description">${book.Description}</div>
          </div>
        </div>`;
    }
  }).join('');

  container.innerHTML = `<div class="${containerClass}">${booksHTML}</div>`;
}

function switchView(view) {
  currentView = view;
  document.getElementById('gridView').classList.toggle('active', view === 'grid');
  document.getElementById('listView').classList.toggle('active', view === 'list');
  displayBooks();
}

function updateStats() {
  document.getElementById('bookCount').textContent = filteredBooks.size;
  document.getElementById('totalBooks').textContent = booksList.size;
}

window.onload = init;