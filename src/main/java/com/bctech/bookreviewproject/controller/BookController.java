package com.bctech.bookreviewproject.controller;

import com.bctech.bookreviewproject.dto.response.APIResponse;
import com.bctech.bookreviewproject.dto.response.BookResponseDto;
import com.bctech.bookreviewproject.dto.response.BookSearchResponseDto;
import com.bctech.bookreviewproject.entity.User;
import com.bctech.bookreviewproject.service.AuthenticationService;
import com.bctech.bookreviewproject.service.BookShelfService;
import com.bctech.bookreviewproject.service.OpenLibraryService;
import com.bctech.bookreviewproject.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final OpenLibraryService openLibraryService;
    private final BookShelfService bookShelfService;
    private final AuthenticationService authenticationService;



    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<BookSearchResponseDto>>>searchForBook(@RequestParam String bookName, @RequestParam int pageSize) {

        List<BookSearchResponseDto>response = openLibraryService.getBooksBySearch(bookName,pageSize);

        return ResponseEntity.ok().body(
                APIResponse.<List<BookSearchResponseDto> >builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse<BookResponseDto>> addToBookShelf(@RequestParam String key,
                                                                       HttpServletRequest httpServletRequest) throws BadJOSEException, ParseException, JOSEException {
        String token = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");

        String username = authenticationService.getUserNameFromToken(token);

        User user = (User) JwtUtil.parseToken(token).getPrincipal();
        BookResponseDto response = bookShelfService.addBook(openLibraryService.getBookByKey(key),user.getUsername());

        return ResponseEntity.ok().body(
                APIResponse.<BookResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

}
