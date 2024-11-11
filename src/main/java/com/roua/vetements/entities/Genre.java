package com.roua.vetements.entities;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGenre;
	private String nomGenre;
	private String descriptionGenre;
	
	@OneToMany(mappedBy = "genre")
	@JsonIgnore
	 
	private List<Vetement> vetements;

	public Long getIdGenre() {
		return idGenre;
	}
	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
	}
	public String getNomGenre() {
		return nomGenre;
	}
	public void setNomGenre(String nomGenre) {
		this.nomGenre = nomGenre;
	}
	public String getDescriptionGenre() {
		return descriptionGenre;
	}
	public void setDescriptionGenre(String descriptionGenre) {
		this.descriptionGenre = descriptionGenre;
	}
	
	public List<Vetement> getVetements() {
		return vetements;
	}
	public void setVetements(List<Vetement> vetements) {
		this.vetements = vetements;
	}
	
	

}
