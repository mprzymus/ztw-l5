package pl.mprzymus.ztwl5.book_borrow;

import pl.mprzymus.ztwl5.books.services.BookConverter;

public class BorrowConverter {
    public static BorrowDto borrowToDto(BookBorrow borrow) {
        var bookDto = BookConverter.bookToDto(borrow.getBook());
        return new BorrowDto(borrow.getId(), borrow.getBorrowDate(), borrow.getReturnDate(), bookDto);
    }
}
