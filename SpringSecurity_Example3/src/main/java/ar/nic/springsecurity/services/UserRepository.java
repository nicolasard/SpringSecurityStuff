package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}