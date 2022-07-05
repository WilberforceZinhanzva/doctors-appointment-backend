package zw.co.nimblecode.doctorsappointmentsystem.services;


import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceAlreadyExistsException;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.consumables.ConsumableBlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.BlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.models.transferables.TransferableBlacklistedTimeRange;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.BlacklistedTimeRangeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlacklistedTimeRangeService {

    private BlacklistedTimeRangeRepository blacklistedTimeRangeRepository;

    public BlacklistedTimeRangeService(BlacklistedTimeRangeRepository blacklistedTimeRangeRepository) {
        this.blacklistedTimeRangeRepository = blacklistedTimeRangeRepository;
    }

    public TransferableBlacklistedTimeRange createTimeRange(ConsumableBlacklistedTimeRange consumableBlacklistedTimeRange) {
        if (blacklistedTimeRangeRepository.existsByStartTime(consumableBlacklistedTimeRange.getStartTime()))
            throw new ResourceAlreadyExistsException("Time range starting at " + consumableBlacklistedTimeRange.getStartTime() + " already exists. Overlaps are not allowed!");
        BlacklistedTimeRange blacklistedTimeRange = new BlacklistedTimeRange();
        blacklistedTimeRange.setActive(true);
        blacklistedTimeRange.setStartTime(consumableBlacklistedTimeRange.getStartTime());
        blacklistedTimeRange.setEndTime(consumableBlacklistedTimeRange.getEndTime());

        return blacklistedTimeRangeRepository.save(blacklistedTimeRange).serializeForTransfer();
    }

    public List<TransferableBlacklistedTimeRange> timeRanges() {
        return blacklistedTimeRangeRepository.findAll().stream().map(BlacklistedTimeRange::serializeForTransfer).collect(Collectors.toList());
    }

    public TransferableBlacklistedTimeRange deleteRange(String id) {
        Optional<BlacklistedTimeRange> timeRange = blacklistedTimeRangeRepository.findById(id);
        if (timeRange.isEmpty())
            throw new ResourceNotFoundException("Range not found!");

        blacklistedTimeRangeRepository.delete(timeRange.get());
        return timeRange.get().serializeForTransfer();
    }
}
