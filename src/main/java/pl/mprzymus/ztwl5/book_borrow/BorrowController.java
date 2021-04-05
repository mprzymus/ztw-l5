package pl.mprzymus.ztwl5.book_borrow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
