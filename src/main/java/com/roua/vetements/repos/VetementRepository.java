package com.roua.vetements.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.roua.vetements.entities.Genre;
import com.roua.vetements.entities.Vetement;
@RepositoryRestResource(path = "rest")
public interface VetementRepository extends JpaRepository<Vetement, Long > {
	 List<Vetement> findByNomVetement(String nom);
	 List<Vetement> findByNomVetementContains(String nom); 
	  @Query("select v from Vetement v where v.nomVetement like %?1 and v.prix > ?2")
		 List<Vetement> findByNomPrix (String nom, Double prix);
	  
	  /* @Query("select v from Vetement v where v.nomVetement like %:nom and v.prix > :prix")
		 List<Vetement> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);*/
	
	 @Query("select v from Vetement v where v.genre = ?1")
	 List<Vetement> findByGenre(Genre genre);
	 
	 List<Vetement> findByGenreIdGenre(Long id);
	 
	 List<Vetement> findByOrderByNomVetementAsc();
	 
	 @Query("select v from Vetement v order by v.nomVetement ASC, v.prix Desc")
	 List<Vetement> trierVetementsNomsPrix();

	 
	
	 


}
