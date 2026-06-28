package net.engineeringdigest.JournalApp.repository;

import net.engineeringdigest.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {  // this is a git repository,this JournalEntry is entity type and String is JurnalEntry's  id in JournalEntry class.
  User findByusername(String username);  // here just written findBy and written field name(UserName).
}
// findByUserName  this field is from User.java public class User was the field.
// this is rule if u want to do some changes in UserRepository then make sure extends MongoRepository like this.
