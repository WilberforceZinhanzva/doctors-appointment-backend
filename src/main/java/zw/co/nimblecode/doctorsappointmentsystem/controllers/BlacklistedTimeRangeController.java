package zw.co.nimblecode.doctorsappointmentsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableBlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableBlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.services.BlacklistedTimeRangeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blacklisted-time-range")
public class BlacklistedTimeRangeController {

    private BlacklistedTimeRangeService blacklistedTimeRangeService;

    public BlacklistedTimeRangeController(BlacklistedTimeRangeService blacklistedTimeRangeService) {
        this.blacklistedTimeRangeService = blacklistedTimeRangeService;
    }

    @PostMapping
    public ResponseEntity<TransferableBlacklistedTimeRange> createTimeRange(@RequestBody ConsumableBlacklistedTimeRange consumableBlacklistedTimeRange) {
        return ResponseEntity.ok(blacklistedTimeRangeService.createTimeRange(consumableBlacklistedTimeRange));
    }

    @GetMapping
    public ResponseEntity<List<TransferableBlacklistedTimeRange>> timeRanges() {
        return ResponseEntity.ok(blacklistedTimeRangeService.timeRanges());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransferableBlacklistedTimeRange> deleteRange(@PathVariable("id") String id) {
        return ResponseEntity.ok(blacklistedTimeRangeService.deleteRange(id));
    }
}
