package com.bctech.bookreviewproject.entity;

import com.bctech.bookreviewproject.constants.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String key;
    private String  authors;
    @Lob
    private String  subjects;
    @Lob
    private String description;

    private Integer pageCount;
    @Lob
    private String publication_date;
    @Enumerated
    private BookStatus bookStatus;
    private String coverImageUrl ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.EAGER)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
