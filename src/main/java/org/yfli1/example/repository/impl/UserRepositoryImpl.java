package org.yfli1.example.repository.impl;

import org.yfli1.example.repository.UserRepository;
import org.yfli1.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    @Qualifier("myMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User getUser() {
        Query query = new Query(Criteria.where("username").is("yfli1"));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }
}
