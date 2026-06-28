package net.engineeringdigest.JournalApp.controller;

import net.engineeringdigest.JournalApp.entity.JournalEntry;
import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.service.JournalEntryService;
import net.engineeringdigest.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// WE ARE IMPLEMENTING ResponseEntity IN ALL THE METHODS/endpoints

@RestController
@RequestMapping("/journal/v2")
public class JournalEntryControllerLink {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJornalEntriesofUsers(@PathVariable String username) {
        User user = userService.findByUserName(username);
        List<JournalEntry> all = user.getJournalEntries();
        // now u can get all entries of this particular user.
        if (all != null && !all.isEmpty()) { // NOT NULL && NOT EMPTY.
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try {
            // now passing the ser and entry to save in journalEntry sevices
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            // also refractor this user into journalEntryService at saveEntry
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String username) {
        journalEntryService.deleteById(myId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{username}/{myid}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myid,
                                               @RequestBody JournalEntry newEntry,
                                               @PathVariable String username)
    {
        JournalEntry old = journalEntryService.findById(myid).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



