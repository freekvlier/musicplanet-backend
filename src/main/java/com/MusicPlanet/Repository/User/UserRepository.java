package com.MusicPlanet.Repository.User;

import com.MusicPlanet.DalInterfaces.IUserDal;
import com.MusicPlanet.Repository.User.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.MusicPlanet.Entities.User.User;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserDal {

    @Autowired
    IUserRepository repo;

    @Override
    public Optional<User> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(User user) {
        repo.save(user);
    }

    @Override
    public void delete(int id) {
        repo.delete(repo.findById(id).get());
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public int enable(String email) {
        return repo.enable(email);
    }
}