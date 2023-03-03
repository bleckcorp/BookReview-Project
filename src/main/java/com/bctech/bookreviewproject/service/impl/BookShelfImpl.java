package com.bctech.bookreviewproject.service.impl;

import com.bctech.bookreviewproject.dto.request.BookRequestDto;
import com.bctech.bookreviewproject.dto.response.BookResponseDto;
import com.bctech.bookreviewproject.entity.Book;
import com.bctech.bookreviewproject.entity.User;
import com.bctech.bookreviewproject.repository.BookRepository;
import com.bctech.bookreviewproject.repository.UserRepository;
import com.bctech.bookreviewproject.service.BookShelfService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookShelfImpl implements BookShelfService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

  @Override
    public BookResponseDto addBook(BookRequestDto request, String username) {
        if (request != null) {
//
            Book book = mapper.map(request, Book.class);
            User user = userRepository.findByUsername(username).orElseThrow();
            book.setUser(user);
            bookRepository.save(book);
            return mapper.map(book, BookResponseDto.class);

        }
        return null;
    }

}
