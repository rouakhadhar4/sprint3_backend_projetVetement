package com.roua.vetements.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.roua.vetements.entities.Image;
import com.roua.vetements.entities.Vetement;
import com.roua.vetements.service.ImageService;
import com.roua.vetements.service.VetementService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
@Autowired
ImageService imageService ;
@Autowired
VetementService vetementservice;
private static final String IMAGE_DIRECTORY = "C:/Users/MSI/images/";
@RequestMapping(value = "/upload" , method = RequestMethod.POST)
public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
return imageService.uplaodImage(file);
}
@RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET)
public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
return imageService.getImageDetails(id) ;
}
@RequestMapping(value = "/load/{id}" , method = RequestMethod.GET) public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException { return imageService.getImage(id);
}
@RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
public void deleteImage(@PathVariable("id") Long id){
imageService.deleteImage(id);
}
@RequestMapping(value="/update",method = RequestMethod.PUT)
public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
return imageService.uplaodImage(file);
}
@PostMapping(value = "/uplaodImageProd/{idProd}" )
public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
@PathVariable("idProd") Long idProd)
throws IOException {
return imageService.uplaodImageVet(file,idProd);
}
@RequestMapping(value = "/getImagesProd/{idProd}" , method = RequestMethod.GET)
public List<Image> getImagesProd(@PathVariable("idProd") Long idProd) 
		 throws IOException {
	 return imageService.getImagesParVet(idProd);
}

@RequestMapping(value = "/uploadFS/{id}", method = RequestMethod.POST)
public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id) throws IOException {
 
    Vetement p = vetementservice.getVetement(id);

  
    String imagePath = "C:/Users/MSI/images/";  

   
    Path directoryPath = Paths.get(imagePath);
    if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
    }

 
    String fileName = id + ".jpg";

   
    Path filePath = Paths.get(imagePath + fileName);
    if (Files.exists(filePath)) {
     
        String uniqueSuffix = System.currentTimeMillis() + "";  
        fileName = id + "_" + uniqueSuffix + ".jpg";
    }

  
    Files.write(Paths.get(imagePath + fileName), file.getBytes());

 
    p.setImagePath(fileName);
    vetementservice.saveVetement(p);

    System.out.println("Image sauvegardée à : " + imagePath + fileName);
}


@RequestMapping(value = "/loadfromFS/{id}", method = RequestMethod.GET, produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
    Vetement p = vetementservice.getVetement(id);
    Path imagePath = Paths.get(System.getProperty("user.home") + "/images/" + p.getImagePath());
    
   
    if (!Files.exists(imagePath)) {
        throw new FileNotFoundException("L'image pour l'ID " + id + " n'existe pas.");
    }
    
    return Files.readAllBytes(imagePath);
}

}



