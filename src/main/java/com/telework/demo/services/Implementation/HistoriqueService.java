package com.telework.demo.services.Implementation;

import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.exception.EntityNotFoundException;
import com.telework.demo.repository.IHistoriqueRepository;
import com.telework.demo.services.IHistoriqueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.telework.demo.exception.ErrorMessages.HISTORIQUE_NOT_FOUND;

@Service
public class HistoriqueService implements IHistoriqueService {

    private final IHistoriqueRepository repository;

    public HistoriqueService(IHistoriqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public HistoriqueDto save(HistoriqueDto dto) {
        return HistoriqueDto.fromEntity(repository.save(HistoriqueDto.toEntity(dto)));
    }

    @Override
    public HistoriqueDto findById(Integer id) {
        return repository.findById(id).map(HistoriqueDto::fromEntity).orElseThrow(
                () -> new EntityNotFoundException(HISTORIQUE_NOT_FOUND + id)
        );
    }

    @Override
    public List<HistoriqueDto> findAll() {
        return repository.findAll().stream()
                .map(HistoriqueDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        HistoriqueDto historiqueDto = findById(id);
        if (historiqueDto == null) {
            throw new EntityNotFoundException(HISTORIQUE_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }
}
