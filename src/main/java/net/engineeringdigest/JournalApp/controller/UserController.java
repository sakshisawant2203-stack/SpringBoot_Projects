package net.engineeringdigest.JournalApp.controller;


import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.service.JournalEntryService;
import net.engineeringdigest.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



// WE ARE IMPLEMENTING ResponseEntity IN ALL THE METHODS/endpoints

@RestController
@RequestMapping("/users") // this is parent mapping we can call every method on this  #1
public class UserController {

    @Autowired  // ere we areinjecting dependencies using  @Autowired
    private UserService userService;  // here 1st we use UserService so @Autowired it. #2

    @GetMapping  // we use @GetMapping to create endpoint.
    public List<User> getAllUsers() {  // here we did public getAllUser it will gives a list of users. #3
        return userService.getAll();
    }

    @PostMapping  //  @PostMapping beacuse we are adding something
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    // if u want to update by Username -> pass old username in url and new username in body
    // this code is only for to update password of a username,by using this we can update a password but can't a username.
   // beacuse we find it by userName.
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username) {
        // we are sending username and password in @RequestBody so we are updating by username and password not by id
        // we are going to find in database with the help of username then will run  this update call
     //   User userIndb = userService.findByUserName(user.getUsername()); //by using this we can update a password but can't a username.
        User userIndb = userService.findByUserName(user.getUsername());
        if (userIndb != null) {
            userIndb.setUsername(user.getUsername()); // set the username which came from @RequestBody
            userIndb.setPassword(user.getPassword());
            // the whole meaning of this method is it set's all the new and old password which comes in @RequestBody.
            // remember we already got a user from postman in our db now we are just set new username and password.
            userService.saveEntry(userIndb); // userIndb have id of that old user by doing this it will be save on same id
        }
        // we don't really want to return anything so just
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
 // setUsername,setPassword these methods always need 1 string parameters user.getUsername() gets username from
// @RequestBody while userIndb.setUsername sets password in database object.
/* If you are searching the user by username, then:
You must already know the old username.
If you change the username in request body, then:
findByUserName() will try to find user using the new username
But that username does NOT exist yet in DB
So it returns null
That’s why username update doesn’t work properly.

 */
