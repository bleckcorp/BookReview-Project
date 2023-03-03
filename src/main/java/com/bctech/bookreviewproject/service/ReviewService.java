package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.dto.request.ReviewDto;
import com.bctech.bookreviewproject.dto.response.ReviewResponseDto;

public interface ReviewService {
    ReviewResponseDto createReview(ReviewDto request, String username);
}
