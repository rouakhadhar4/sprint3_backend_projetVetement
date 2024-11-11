package com.roua.vetements;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.List;

import com.roua.vetements.entities.Genre;
import com.roua.vetements.entities.Vetement;
import com.roua.vetements.repos.VetementRepository;

@SpringBootTest
class ProjetvetementApplicationTests {
	

	@Autowired
	private VetementRepository vetementRepository;
	
	@Test
	public void testCreateVetement() {
	Vetement vet = new Vetement("chemise",77.9, new Date(), "vert", "36");
	vetementRepository.save(vet);
	
	}
	
	
	@Test
	public void testFindVetement()
	{
	Vetement v = vetementRepository.findById(2L).get();
	System.out.println(v);
	}
	


 
     @Test
   public void testUpadateVetement()
   {
      Vetement v = vetementRepository.findById(6L).get();
      v.setPrix(39.0);
      vetementRepository.save(v);
      
      System.out.println(v);
    }
     
	
     @Test
     public void testDeleteVetement()
     {
    	 vetementRepository.deleteById(103L);;
     }
     


     

   @Test
    public void testListerTousVetements()
   {
     List<Vetement> vet =  vetementRepository.findAll();
        for (Vetement v : vet)
       {
        System.out.println(v);
       
       }
   }
   
   
   
	@Test
	public void testFindVetementByNom()
	{
		List<Vetement> vet =vetementRepository.findByNomVetement("jean");
				for (Vetement v : vet)
				{
				System.out.println(v);
				}
	
}

	
	
	@Test
	public void testFindByNomVetementContains()
	{
		List<Vetement> vet =vetementRepository.findByNomVetementContains("p");
				for (Vetement v : vet)
				{
				System.out.println(v);
				}
	
}

	
	 
	
	 
	
	@Test
	public void testfindByNomPrix()
	{
	List<Vetement> vet = vetementRepository.findByNomPrix("pul", 57.9);
	for (Vetement v : vet)
	{
	System.out.println(v);
	}
	}
	
	

	
	@Test
	public void testFindByGenre() {
	    Genre gn = new Genre();
	    gn.setIdGenre(1L); 
	    List<Vetement> vet = vetementRepository.findByGenre(gn);
	    
	    
	    for (Vetement v : vet) {
	        System.out.println(v);
	    }
	}
	
	@Test
	public void findByGenreIdGenre()
	{
	List<Vetement> vets = vetementRepository.findByGenreIdGenre(1L);
	for (Vetement v : vets)
	{
	System.out.println(v);
	}
	}
	
	
	@Test
	public void testfindByOrderByNomVetementAsc()
	{
	List<Vetement> vets =
	vetementRepository.findByOrderByNomVetementAsc();
	for (Vetement v : vets)
	{
	System.out.println(v);
	}
	}
	

	@Test
	public void testTrierProduitsNomsPrix()
	{
	List<Vetement> vets = vetementRepository.trierVetementsNomsPrix();
	for (Vetement v : vets)
	{
	System.out.println(v);
	}
	}
	
}

	







	
	
	
	

