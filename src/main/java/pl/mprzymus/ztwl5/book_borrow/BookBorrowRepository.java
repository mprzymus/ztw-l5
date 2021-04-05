package pl.mprzymus.ztwl5.book_borrow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowRepository extends JpaRepository<BookBorrow, Integer> {
}
