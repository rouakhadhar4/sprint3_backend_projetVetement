package com.roua.vetements.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roua.vetements.entities.Image;

public interface ImageRepository extends JpaRepository<Image , Long> {
	
}