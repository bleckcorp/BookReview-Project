package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.dto.request.BookRequestDto;
import com.bctech.bookreviewproject.dto.response.BookResponseDto;

public interface BookShelfService {
    BookResponseDto addBook(BookRequestDto request, String username);
}
