package it.epicode.U5_W2_D4.controller;

import it.epicode.U5_W2_D4.dto.UniversitaDto;
import it.epicode.U5_W2_D4.exception.NotFoundException;
import it.epicode.U5_W2_D4.exception.ValidationException;
import it.epicode.U5_W2_D4.model.Universita;
import it.epicode.U5_W2_D4.service.UniversitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/universita")
public class UniversitaController {

    @Autowired
    private UniversitaService universitaService;

    @PostMapping("")
    public Universita saveUniversita(@RequestBody @Validated UniversitaDto universitaDto, BindingResult bindingResult) throws ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }
        return universitaService.saveUniversita(universitaDto);
    }

    @GetMapping("")
    public List<Universita> getAllUniversita() {
        return universitaService.getAllUniversita();
    }

    @GetMapping("/{id}")
    public Universita getUniversita(@PathVariable int id) throws NotFoundException {
        return universitaService.getUniversita(id);
    }

    @PutMapping("/{id}")
    public Universita updateUniversita(@PathVariable int id, @RequestBody @Validated UniversitaDto universitaDto, BindingResult bindingResult) throws NotFoundException, ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }
        return universitaService.updateUniversita(id, universitaDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUniversita(@PathVariable int id) throws NotFoundException {
        universitaService.deleteUniversita(id);
    }
}
