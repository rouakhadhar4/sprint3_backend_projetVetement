package com.roua.vetements.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomVet", types = { Vetement.class })
public interface VetementProjection {
	public String  getNomVetement();

}
