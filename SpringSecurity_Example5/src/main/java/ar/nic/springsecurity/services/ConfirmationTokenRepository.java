package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}