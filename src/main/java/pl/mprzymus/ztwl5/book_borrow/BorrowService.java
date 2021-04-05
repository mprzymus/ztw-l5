package pl.mprzymus.ztwl5.book_borrow;

import pl.mprzymus.ztwl5.books.model.BookDto;

public interface BorrowService {
    BorrowDto borrowBook(BookDto bookDto);
    BorrowDto returnBook(BorrowDto borrowData);
}
