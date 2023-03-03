package com.bctech.bookreviewproject.service.impl;

import com.bctech.bookreviewproject.dto.request.BookRequestDto;
import com.bctech.bookreviewproject.dto.response.BookSearchResponseDto;
import com.bctech.bookreviewproject.service.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenLibraryImpl implements OpenLibraryService{
    private static final String API_BASE_URL = "https://openlibrary.org/";
    private static final String SEARCH_QUERY  = "search.json?q=";



    @Override
    public  List<BookSearchResponseDto> getBooksBySearch(String title, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();


        String searchResourceUrl = API_BASE_URL + SEARCH_QUERY + title;

//            ResponseEntity<Map> response = (ResponseEntity<Map>) restTemplate.getForObject(searchResourceUrl, Map.class);
        ResponseEntity<Map> response = restTemplate.exchange(searchResourceUrl, HttpMethod.GET, null, Map.class);
        Map<String, Object> responseMap = response.getBody();
        List<Map<String, Object>> docs = (List<Map<String, Object>>) responseMap.get("docs");


        return docs.stream()
                .map(doc -> {
                    BookSearchResponseDto book = new BookSearchResponseDto();
                    book.setKey(doc.getOrDefault("key", "").toString());
                    book.setTitle(doc.getOrDefault("title", "").toString());
                    book.setAuthors(doc.containsKey("author_name") ? doc.get("author_name"): null);
                    book.setPageCount(doc.containsKey("number_of_pages_median") ? (Integer) doc.get("number_of_pages_median"): null);
                    book.setDescription(doc.getOrDefault("description", "").toString());
                    book.setCoverImageUrl(doc.containsKey("cover_i") ? "http://covers.openlibrary.org/b/id/" + doc.get("cover_i").toString() + "-L.jpg" : null);
                    book.setSubjects(doc.getOrDefault("subject", null));
                    return book;
                })
                .filter(book -> book.getTitle() != null && !book.getTitle().isEmpty())
                .limit(pageSize)
                .collect(Collectors.toList());

    }

    @Override
    public BookRequestDto getBookByKey(String key) {
        RestTemplate restTemplate = new RestTemplate();
        String bookResourceUrl = API_BASE_URL + "/works/" + key + ".json";
        Map<String, Object> bookMap = restTemplate.getForObject(bookResourceUrl, Map.class);

        BookRequestDto book = new BookRequestDto();
        assert bookMap != null;
        book.setKey(key);
        book.setTitle(bookMap.getOrDefault("title", "").toString());
//        book.setAuthors(dto.getAuthors().toString());
//        book.setPageCount(dto.getPageCount());
        book.setDescription(bookMap.getOrDefault("description", "").toString());
        book.setCoverImageUrl(bookMap.containsKey("covers") ?
                "http://covers.openlibrary.org/b/id/" +
                        bookMap.get("covers").toString().split(",")[1] + "-L.jpg" : null);
        book.setSubjects(bookMap.containsKey("subjects") ? bookMap.get("subjects").toString(): null);
        return book;

    }
}
