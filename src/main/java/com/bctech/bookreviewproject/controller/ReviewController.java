package com.bctech.bookreviewproject.controller;

import com.bctech.bookreviewproject.dto.request.ReviewDto;
import com.bctech.bookreviewproject.dto.response.APIResponse;
import com.bctech.bookreviewproject.dto.response.ReviewResponseDto;
import com.bctech.bookreviewproject.service.AuthenticationService;
import com.bctech.bookreviewproject.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ReviewResponseDto>> createReview(@RequestBody @Valid ReviewDto request, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        String username = authenticationService.getUserNameFromToken(token);

        ReviewResponseDto response = reviewService.createReview(request, username);
        return ResponseEntity.ok().body(
                APIResponse.<ReviewResponseDto>builder()
                        .data(response)
                        .message("SUCCESS")
                        .status(HttpStatus.CREATED)
                        .build()
        );


    }
}
