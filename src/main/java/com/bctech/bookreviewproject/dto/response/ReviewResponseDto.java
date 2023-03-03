package com.bctech.bookreviewproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
        private String comment;
        private Long bookId;
        private Integer rating;
        private Long reviewId;
        private Long userId;

}
