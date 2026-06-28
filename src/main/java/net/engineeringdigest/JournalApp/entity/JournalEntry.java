package net.engineeringdigest.JournalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

    @Document(collection = "journalEntry") // here we just map by the collection @Document
/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

 */
    @Data // in data their is a constructor @RequiredArgsConstructor but we need a @NoArgsConstructor is required during
// this kind of realization means JSON to POJO SO  now add the @NoArgsConstructor here.
    @NoArgsConstructor   //  @NoArgsConstructor it is required to add something from json to pojo.


    // @Data   this annotation is equivalent to getter,setter,requiredArgsConstructor,toString,EqualsAndHashcode,value these all annotation

    public class JournalEntry{  // after typing id,title,content right click->getters and setters this is called plain old java object
        //this jpurnalEntry's instant is equal to document.
        @Id  // maped as primary key. this unique key of your documents  this is not that important  u can skip it.


        private ObjectId id;
        @NonNull
        private String title;

        private String content;

        private LocalDateTime date;

    }

