package it.epicode.U5_W2_D4.controller;

import it.epicode.U5_W2_D4.dto.StudenteDto;
import it.epicode.U5_W2_D4.exception.NotFoundException;
import it.epicode.U5_W2_D4.exception.ValidationException;
import it.epicode.U5_W2_D4.model.Studente;
import it.epicode.U5_W2_D4.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/studenti")
//il path sara sempre quello qui sopra inserito per tutti i metodi che scriveremo sotto
public class StudenteController {

    @Autowired
    private StudenteService studenteService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Studente saveStudente(@RequestBody @Validated StudenteDto studenteDto, BindingResult bindingResult) throws NotFoundException, ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }
        return studenteService.saveStudente(studenteDto);
    }

    @GetMapping("")
    public List<Studente> getAllStudenti() {
        return studenteService.getAllStudenti();
    }

    @GetMapping("/{matricola}")
    public Studente getStudente(@PathVariable int matricola) throws NotFoundException {
        return studenteService.getStudente(matricola);
    }

    @PutMapping("/{matricola}")
    public Studente updateStudente(@PathVariable int matricola, @RequestBody @Validated StudenteDto studenteDto, BindingResult bindingResult) throws NotFoundException, ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }
        return studenteService.updateStudente(matricola, studenteDto);
    }

    @DeleteMapping("/{matricola}")
    public void deleteStudente(@PathVariable int matricola) throws NotFoundException {
        studenteService.deleteStudente(matricola);
    }

    @PatchMapping("/{matricola}")
    public String patchStudente(@PathVariable int matricola, @RequestBody MultipartFile file) throws NotFoundException, IOException {
        return studenteService.patchStudente(matricola, file);
    }
}
