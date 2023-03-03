package com.bctech.bookreviewproject.service.impl;

import com.bctech.bookreviewproject.dto.request.ReviewDto;
import com.bctech.bookreviewproject.dto.response.ReviewResponseDto;
import com.bctech.bookreviewproject.entity.Book;
import com.bctech.bookreviewproject.entity.Review;
import com.bctech.bookreviewproject.entity.User;
import com.bctech.bookreviewproject.repository.BookRepository;
import com.bctech.bookreviewproject.repository.ReviewRepository;
import com.bctech.bookreviewproject.repository.UserRepository;
import com.bctech.bookreviewproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDto createReview(ReviewDto request, String username) {
        if (request != null) {
            Book book = bookRepository.findById(request.getBookId()).orElseThrow();
            Review review = mapper.map(request, Review.class);
            review.setBook(book);
            User user = userRepository.findByUsername(username).orElseThrow();
            review.setUser(user);
            reviewRepository.save(review);
            return mapper.map(review, ReviewResponseDto.class);
        }
        return null;
    }
}
