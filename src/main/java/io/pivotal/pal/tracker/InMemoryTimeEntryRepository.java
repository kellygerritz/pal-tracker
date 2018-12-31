package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private final HashMap<Long, TimeEntry> repository;
    private long iter;

    public InMemoryTimeEntryRepository() {
        repository = new HashMap<>();
        iter = 0;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++iter);
        repository.put(iter, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return repository.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        Collection<TimeEntry> entries = repository.values();
        return new ArrayList<>(entries);
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        timeEntry.setId(timeEntryId);
        repository.put(timeEntryId, timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        repository.remove(timeEntryId);
    }
}
