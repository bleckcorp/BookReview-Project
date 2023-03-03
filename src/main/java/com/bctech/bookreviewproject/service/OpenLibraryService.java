package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.dto.request.BookRequestDto;
import com.bctech.bookreviewproject.dto.response.BookSearchResponseDto;

import java.util.List;

public interface OpenLibraryService {

    List<BookSearchResponseDto> getBooksBySearch(String title, int pageSize);

    public BookRequestDto getBookByKey(String key );
}
