**Library Lite version**
This code is lite version of we create in the main page. This methode make more Efficient making ui Using Java Script and Html
the code can generate random book with the command like this in Java Script: 

```
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
```
**then you should make the function for the code** 
like : 
```
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
```
