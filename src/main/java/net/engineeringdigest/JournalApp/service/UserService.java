// now here we going to write our business logic,and will use JournalEntryService in controller package.
package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component  // add @Component here so it doesn't found null and spring will create object of this.
public class UserService {

    @Autowired
    private UserRepository userRepository;  // we are inject this  JournalEntryRepository in JournalEntryServices class.
     // here what actually happen userServices called userRepository

    public void saveEntry(User user){  // we want to save this.
        userRepository.save(user);
    }

    public List<User>getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String Username){
        return userRepository.findByusername(Username); // it just take username and passing to  UserRepository
        // where we already created this findByUserName method.
    }
}
// controller call to service and service call to repository
// so i got error on JournalEntry so i just clicked on JournalEntry and click on import class.