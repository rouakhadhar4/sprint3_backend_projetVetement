package com.roua.vetements.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.roua.vetements.entities.Image;
import com.roua.vetements.entities.Vetement;
import com.roua.vetements.repos.ImageRepository;
import com.roua.vetements.repos.VetementRepository;

@Service
public class ImageServiceImpl implements ImageService{
@Autowired
ImageRepository imageRepository;

@Autowired
VetementService vetementservice;



@Autowired
VetementRepository  vetementRepository;
@Override
public Image uplaodImage(MultipartFile file) throws IOException {
/*Ce code commenté est équivalent au code utilisant le design pattern Builder
* Image image = new Image(null, file.getOriginalFilename(),
file.getContentType(), file.getBytes(), null);
return imageRepository.save(image);*/
return imageRepository.save(Image.builder()
.name(file.getOriginalFilename())
.type(file.getContentType())
.image(file.getBytes()).build() );
}
@Override
public Image getImageDetails(Long id) throws IOException{ final Optional<Image> dbImage = imageRepository. findById (id);
return Image.builder()
.idImage(dbImage.get().getIdImage())
.name(dbImage.get().getName())
.type(dbImage.get().getType())
.image(dbImage.get().getImage()).build() ;
}
@Override
public ResponseEntity<byte[]> getImage(Long id) throws IOException{ final Optional<Image> dbImage = imageRepository. findById (id);
return ResponseEntity
.ok()
.contentType(MediaType.valueOf(dbImage.get().getType()))
.body(dbImage.get().getImage());
}
@Override
public void deleteImage(Long id) {
imageRepository.deleteById(id);
}
@Override
public Image uplaodImageVet(MultipartFile file, Long idVet) throws IOException {
	

		Vetement v = new Vetement();
		v.setIdVetement(idVet);
		return imageRepository.save(Image.builder()
		.name(file.getOriginalFilename())
		.type(file.getContentType())
		.image(file.getBytes())
		.vetement(v).build() );
}
@Override
public List<Image> getImagesParVet(Long VetId) {
	Vetement p =  vetementRepository.findById(VetId).get();
	return p.getImages();
	
}


}
