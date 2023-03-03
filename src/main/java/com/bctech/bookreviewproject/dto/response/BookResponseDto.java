package com.bctech.bookreviewproject.dto.response;

import com.bctech.bookreviewproject.constants.BookStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class BookResponseDto {
        private Long id;
        private String key;
        private String title;
        private String authors;
        private String subjects;
        private Integer pageCount;
        private String description;
        private String  coverImageUrl;
        private LocalDateTime creationDate;
        private BookStatus bookStatus;
        // getters and setters

}
