package net.engineeringdigest.JournalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;


@Document(collection = "users") // here we just map by the collection @Document
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

 */

@Data
// @Data   this annotation is equivalent to getter,setter,requiredArgsConstructor,toString,EqualsAndHashcode,value these all annotation
public class User {  // in this User intity we are giving login functionality  e.g 10 users having 10 correponding journal entries,
    // e.g if i put my userid password then i will able to see my journalentry we are going to make connection between user and journalentries
   //  please see the notes from notebook.

    @Id
    private ObjectId id;    // u can also use String instead of ObjectId spring data mongodb will convert it automatically.
    @Indexed(unique = true) // indexing make our searching fast and unique make it unique for every user.
    @NonNull  // this lombok's annotation use when we set something using setter if among field is null then null pointer exception will be thrown.
    private String username;
    @NonNull
    private String password;

    @DBRef // annotate by  @DBRef means u are creating reference of journalentries in user collection
    // means this jounalentries List will store refernce of entries which is created in this jounalentries.
    private List<JournalEntry> journalEntries = new ArrayList<>(); // here will be the list of jounalentries
    // beacuse whenever a user initialize in that jounalentries will be atleast a empty list and not null.

}
/*     private List<JournalEntry> journalEntries = new ArrayList<>(); this line  always gives us a empty
journalEntries in every username entry like "sakshi"
 */