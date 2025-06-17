package it.epicode.U5_W2_D4.service;

import com.cloudinary.Cloudinary;
import it.epicode.U5_W2_D4.dto.StudenteDto;
import it.epicode.U5_W2_D4.exception.NotFoundException;
import it.epicode.U5_W2_D4.model.Studente;
import it.epicode.U5_W2_D4.model.Universita;
import it.epicode.U5_W2_D4.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private UniversitaService universitaService;

    @Autowired
    private Cloudinary cloudinary;

    public Studente saveStudente(StudenteDto studenteDto) throws NotFoundException {

        Universita universita = universitaService.getUniversita(studenteDto.getUniversitaId());

        Studente studente = new Studente();
        studente.setNome(studenteDto.getNome());
        studente.setCognome(studenteDto.getCognome());
        studente.setDataNascita(studenteDto.getDataNascita());
        studente.setUniversita(universita);

        return studenteRepository.save(studente);
    }

    public List<Studente> getAllStudenti() {
        return studenteRepository.findAll();
    }

    public Studente getStudente(int matricola) throws NotFoundException {
        return studenteRepository.findById(matricola).orElseThrow(() -> new NotFoundException("Studente " +
            "con matricola " + matricola + " non presente"));
    }

    public Studente updateStudente(int matricola, StudenteDto studenteDto) throws NotFoundException{
        Studente studenteDaAggiornare = getStudente(matricola);

        studenteDaAggiornare.setNome(studenteDto.getNome());
        studenteDaAggiornare.setCognome(studenteDto.getCognome());
        studenteDaAggiornare.setDataNascita(studenteDto.getDataNascita());

        if(studenteDaAggiornare.getUniversita().getId()!=studenteDto.getUniversitaId()){
            Universita universita = universitaService.getUniversita(studenteDto.getUniversitaId());
            studenteDaAggiornare.setUniversita(universita);
        }

        return studenteRepository.save(studenteDaAggiornare);
    }

    public void deleteStudente(int matricola) throws NotFoundException {
        Studente studenteDaCancellare = getStudente(matricola);

        studenteRepository.delete(studenteDaCancellare);
    }

    public String patchStudente(int matricola, MultipartFile file) throws NotFoundException, IOException {

        Studente studenteDaPatchare = getStudente(matricola);

        String url = (String) cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap()).get("url");

        studenteDaPatchare.setUrlImmagine(url);
        studenteRepository.save(studenteDaPatchare);

        return url;
    }
}
