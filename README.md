# javanese-rest-api
##Library Management REST API##

REST API developed using Spring Boot, Maven, PostgreSQL, Hibernate/JPA.

Include below Enitities:

1. Management of Users;
2. Management of Books;
3. Management of Rents;

Well defined tasks and responsabilities for each entity were setted up to increase code scalability.

# UsersService:
- CRUD User;
- User rental activity;

# BooksService:
- CRUD Books;
- Insert books into MyLibrary repo;
- Display available books;
- Display book info as HTML;

# LibraryRentalService:
- User home page and display available books;
- Get User activity by its ID and when must return books;
- Rent books by UserId and BooksId;
