package mates.web.wiki.repositories;

import org.springframework.data.repository.CrudRepository;

import mates.web.wiki.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
