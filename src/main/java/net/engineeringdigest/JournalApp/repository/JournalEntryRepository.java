package net.engineeringdigest.JournalApp.repository;

import net.engineeringdigest.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
// in spring boot  with spring data mongodb you usually create this:
public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId> {  // this is a git repository,this JournalEntry is entity/pojo/collection type and String is JurnalEntry's  id in JournalEntry class.
// why we are giving this JournalEntry coz we are findinnd this JournalEntry which has a id type ObjectId.
}
// this is rule if u want to do some changes in JournalEntryRepository then make sure extends MongoRepository like this.
// JournalEntry means id,title,content.

/*
u are not writing any implementation code inside this Repository so u are not writing repository logic manually spring generates it at runtime
 */