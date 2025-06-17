package it.epicode.U5_W2_D4.service;

import it.epicode.U5_W2_D4.dto.UniversitaDto;
import it.epicode.U5_W2_D4.exception.NotFoundException;
import it.epicode.U5_W2_D4.model.Universita;
import it.epicode.U5_W2_D4.repository.UniversitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversitaService {
    @Autowired
    private UniversitaRepository universitaRepository;

    public Universita saveUniversita(UniversitaDto universitaDto) {
        Universita universita = new Universita();
        universita.setNome(universitaDto.getNome());
        universita.setCitta(universitaDto.getCitta());

        return universitaRepository.save(universita);
    }

    public List<Universita> getAllUniversita() {
        return universitaRepository.findAll();
    }

    public Universita getUniversita(int id) throws NotFoundException {
        return universitaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Università con id " + id + " non trovata"));
    }

    public Universita updateUniversita(int id, UniversitaDto universitaDto) throws NotFoundException {
        Universita universitaDaAggiornare = getUniversita(id);

        universitaDaAggiornare.setNome(universitaDto.getNome());
        universitaDaAggiornare.setCitta(universitaDto.getCitta());

        return universitaRepository.save(universitaDaAggiornare);
    }

    public void deleteUniversita(int id) throws NotFoundException {
        Universita universitaDaCancellare = getUniversita(id);

        universitaRepository.delete(universitaDaCancellare);
    }
}
