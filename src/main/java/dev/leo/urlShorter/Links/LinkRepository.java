package dev.leo.urlShorter.Links;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LinkRepository extends JpaRepository<Link, Long> {

    Link findByurlLonga(String urlEncurtada);


}
