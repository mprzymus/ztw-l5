package pl.mprzymus.ztwl5.book_borrow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.ztwl5.books.model.BookDto;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public BorrowDto borrowBook(@RequestBody BookDto bookDto) {
        return borrowService.borrowBook(bookDto);
    }

    @PutMapping()
    public BorrowDto returnBook(@RequestBody BorrowDto borrowDto) {
        return borrowService.returnBook(borrowDto);
    }
}
