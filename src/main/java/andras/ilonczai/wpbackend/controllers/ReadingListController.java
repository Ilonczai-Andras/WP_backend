package andras.ilonczai.wpbackend.controllers;

import andras.ilonczai.wpbackend.dtos.Readinglists.*;
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

    @DeleteMapping("/list/{readingListId}")
    public ResponseEntity<String> deleteReadingList(@PathVariable Long readingListId){
        readingListService.deleteReadingList(readingListId);
        return ResponseEntity.ok("Reading list deleted successfully.");
    }

    @DeleteMapping("/item/{readingListItemId}")
    public ResponseEntity<String> deleteReadingListItem(@PathVariable Long readingListItemId){
        readingListService.deleteReadingListItem(readingListItemId);
        return ResponseEntity.ok("Reading list item deleted successfully.");
    }

    @DeleteMapping("/delete-all/{readingListItemId}")
    public ResponseEntity<String> deleteAllReadingListItem(@PathVariable Long readingListItemId){
        readingListService.deleteAllReadingListItem(readingListItemId);
        return ResponseEntity.ok("Reading list items deleted successfully.");
    }

    @PostMapping("/{userId}/reading-lists/reorder")
    public ResponseEntity<String> reorderReadingLists(
            @PathVariable Long userId,
            @RequestBody List<ReadingListOrderUpdateDto> reorderedLists
    ) {
        readingListService.reorderReadingLists(userId, reorderedLists);
        return ResponseEntity.ok("Reading list successfully reoreded");
    }
}
