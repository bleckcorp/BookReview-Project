package com.bctech.bookreviewproject.dto.response;

import lombok.Data;

@Data

public class BookSearchResponseDto {
        private String key;
        private String title;
        private Object authors;
        private Object subjects;
        private Integer pageCount;
        private String description;
        private String  coverImageUrl;
        // getters and setters

}
