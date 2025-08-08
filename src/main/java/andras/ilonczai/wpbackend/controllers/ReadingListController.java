package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Readinglists.AddStoryToListRequestDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListItemResponseDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListRequestDto;
import andras.ilonczai.wpbackend.dtos.Readinglists.ReadingListResponseDto;
import andras.ilonczai.wpbackend.services.ReadingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reading-lists")
@RequiredArgsConstructor
public class ReadingListController {

    private final ReadingListService readingListService;

    @PostMapping("{ownerId}")
    public ResponseEntity<ReadingListResponseDto> createList(@PathVariable Long ownerId, @RequestBody ReadingListRequestDto req) {
        return ResponseEntity.ok(readingListService.createReadingList(ownerId, req));
    }

    @PostMapping("/add")
    public void addStory(@RequestBody AddStoryToListRequestDto req) {
        readingListService.addStoryToLists(req);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReadingListResponseDto>> getUserLists(@PathVariable Long userId){
        return ResponseEntity.ok(readingListService.getUserLists(userId));
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<List<ReadingListItemResponseDto>> getListItems(@PathVariable Long listId){
        return ResponseEntity.ok(readingListService.getListItems(listId));
    }

    @DeleteMapping("/{readingListId}")
    public ResponseEntity<String> deleteReadingList(@PathVariable Long readingListId){
        readingListService.deleteReadingList(readingListId);
        return ResponseEntity.ok("Reading list deleted successfully.");
    }
}
