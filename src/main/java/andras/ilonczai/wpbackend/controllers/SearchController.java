package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Search.SearchResponseDto;
import andras.ilonczai.wpbackend.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResponseDto> search(
            @RequestParam(name = "q") String query,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(searchService.search(query.trim(), pageable));
    }
}