package com.roua.vetements.service;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.roua.vetements.entities.Genre;
import com.roua.vetements.entities.Vetement;
import com.roua.vetements.repos.ImageRepository;
import com.roua.vetements.repos.VetementRepository;

@Service
public class VetementServiceImpl implements VetementService {
    @Autowired
    VetementRepository vetementRepository;
    @Autowired
     ImageRepository  imageRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Vetement saveVetement(Vetement v) {
        return vetementRepository.save(v);
    }

    @Override
    public Vetement updateVetement(Vetement v) {
    //Long oldVetImageId = this.getVetement(v.getIdVetement()).getImage().getIdImage();
   // Long newVetImageId = v.getImage().getIdImage();
    Vetement vetUpdated = vetementRepository.save(v);
    //if (oldVetImageId != newVetImageId) //si l'image a été modifiée
   // imageRepository.deleteById( oldVetImageId);
    return vetUpdated;
    }

    @Override
    public void deleteVetement(Vetement v) {
    	
    	
        vetementRepository.delete(v);
    }

    @Override
    public void deleteVetementById(Long id) {  
        Vetement p = getVetement(id);
        
        // Supprimer l'image avant de supprimer le produit
        try {
            Files.delete(Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
            // Vous pouvez ajouter une gestion d'erreur ici si nécessaire
        }
        
        // Supprimer le produit du repository
        vetementRepository.deleteById(id);
    }


    @Override
    public Vetement getVetement(Long id) {
        return vetementRepository.findById(id).get();
    }

    @Override
    public List<Vetement> getAllVetements() {
        return vetementRepository.findAll();
    }

	@Override
	public List<Vetement> findByNomVetement(String nom) {
		
		   return vetementRepository.findByNomVetement(nom);
	}

	@Override
	public List<Vetement> findByNomVetementContains(String nom) {
		return  vetementRepository.findByNomVetementContains(nom);
	}

	@Override
	public List<Vetement> findByNomPrix(String nom, Double prix) {
		return  vetementRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Vetement> findByGenre(Genre genre) {
		
		return vetementRepository.findByGenre(genre);
	}

	@Override
	public List<Vetement> findByGenreIdGenre(Long id) {
		
		return vetementRepository.findByGenreIdGenre(id);
	}

	@Override
	public List<Vetement> findByOrderByNomVetementAsc() {
		return vetementRepository.findByOrderByNomVetementAsc();
	}

	@Override
	public List<Vetement> trierVetementsNomsPrix() {
		return vetementRepository.trierVetementsNomsPrix();
		
	}
}
