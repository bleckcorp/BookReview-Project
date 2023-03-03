package com.bctech.bookreviewproject.dto.request;

import lombok.Data;

@Data

public class BookRequestDto {
        private String key;
        private String title;
        private String authors;
        private String subjects;
        private Integer pageCount;
        private String description;
        private String  coverImageUrl;
        // getters and setters

}
