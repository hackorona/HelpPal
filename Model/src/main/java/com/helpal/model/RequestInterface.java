package com.helpal.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestInterface extends JpaRepository<Request, Long> {
}