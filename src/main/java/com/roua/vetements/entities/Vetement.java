package com.roua.vetements.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Vetement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idVetement;
	private String nomVetement;
	private Double prix;
	private Date dateCreation;
	private String couleur;
	private String taille;
	@ManyToOne
	private Genre genre;
	
   /*@OneToOne
	private Image image;
	*/
	private String imagePath;
	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@OneToMany(mappedBy = "vetement", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Image> images;

	
	
	public Vetement() {
		super();
		
	}
	
	
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	


	@Override
	public String toString() {
		return "Vetement [idVetement=" + idVetement + ", nomVetement=" + nomVetement + ", prix=" + prix
				+ ", dateCreation=" + dateCreation + ", couleur=" + couleur + ", taille=" + taille + "]";
	}
	public Vetement(String nomVetement, Double prix, Date dateCreation, String couleur, String taille) {
	    super();
	    this.nomVetement = nomVetement;
	    this.prix = prix;
	    this.dateCreation = dateCreation;
	    this.couleur = couleur;
	    this.taille = taille;
	}

	public Long getIdVetement() {
		return idVetement;
	}
	public void setIdVetement(Long idVetement) {
		this.idVetement = idVetement;
	}
	public String getNomVetement() {
		return nomVetement;
	}
	public void setNomVetement(String nomVetement) {
		this.nomVetement = nomVetement;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	

}
