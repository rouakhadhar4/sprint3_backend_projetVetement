package com.roua.vetements.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roua.vetements.entities.Vetement;
import com.roua.vetements.service.VetementService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") // Limite l'accès à ce domaine
public class VetementRestController {

    @Autowired
    private VetementService vetementService;
    
    @GetMapping("/all")
    public List<Vetement> getAllVetements() {
        return vetementService.getAllVetements();
    }

    @GetMapping("/getbyid/{id}")
    public Vetement getVetementById(@PathVariable("id") Long id) {
        Vetement vetement = vetementService.getVetement(id); // Assurez-vous que cette méthode est définie dans VetementService
        if (vetement != null && vetement.getImagePath() != null) {
            try {
                // Supprimez l'image associée en utilisant le chemin défini dans l'objet Vetement
                Files.delete(Paths.get(System.getProperty("user.home") + "/images/" + vetement.getImagePath()));
            } catch (IOException e) {
                e.printStackTrace();
                // Vous pouvez également ajouter un log d'erreur ici
            }
        }
        return vetement;
    }
    

    @PostMapping("/addvet")
 
    public Vetement createVetement(@RequestBody Vetement vetement) {
        return vetementService.saveVetement(vetement);
    }

    @PutMapping("/updatevet")
    public Vetement updateVetement(@RequestBody Vetement vetement) {
        return vetementService.updateVetement(vetement);
    }

    @DeleteMapping("/delvet/{id}")
    public void deleteVetement(@PathVariable("id") Long id) {
        vetementService.deleteVetementById(id);
    }

    @GetMapping("/vetcgen/{idGen}")
    public List<Vetement> getVetementsByGenId(@PathVariable("idGen") Long idGenre) {
        return vetementService.findByGenreIdGenre(idGenre);
    }

    @GetMapping("/vetsByName/{nom}")
    public List<Vetement> findByNomProduitContains(@PathVariable("nom") String nom) {
        return vetementService.findByNomVetementContains(nom);
    }
}
