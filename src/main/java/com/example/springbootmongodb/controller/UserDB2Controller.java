package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor
public class UserDB2Controller {

    //The standard template pattern in Spring and provides a ready-to-go, basic API to the underlying persistence engine.
    //this works with user-db2 database.
    private final MongoTemplate mongoTemplate;

    @PostMapping( "/create")
    public User addNewUsers(@RequestBody User user) {
        log.info("Saving user.");
        return mongoTemplate.insert(user);
    }

    @PostMapping("/update")
    public User onlyUpdate(@RequestBody User user) {
        log.info("update user.");
        return mongoTemplate.save(user);
    }

    @PostMapping("/updateFirst")
    public void updateFirst() {
        log.info("update first user.");
        var query = new Query();
        query.addCriteria(Criteria.where("name").is("alex"));
        var update = new Update();
        update.set("name", "james");

        //updates the very first document that matches the query.
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @PostMapping("/updateMulti")
    public void UpdateMulti() {
        log.info("update multi user.");
        var query = new Query();
        query.addCriteria(Criteria.where("name").is("alex"));
        var update = new Update();
        update.set("name", "james");

        //updates all documents that match the given query.
        mongoTemplate.updateMulti(query, update, User.class);
    }
}
