package com.telework.demo.controller;

import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.services.IHistoriqueService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.telework.demo.utils.Constants.HISTORIQUE_ENDPOINT;

@RestController
@Api(HISTORIQUE_ENDPOINT)
public class HistoriqueController {

    private final IHistoriqueService service;

    public HistoriqueController(IHistoriqueService service) {
        this.service = service;
    }

    @PostMapping(value = HISTORIQUE_ENDPOINT + "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    HistoriqueDto save(@RequestBody HistoriqueDto dto) {
        return service.save(dto);
    }

    @GetMapping(value = HISTORIQUE_ENDPOINT + "/filterById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    HistoriqueDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = HISTORIQUE_ENDPOINT + "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<HistoriqueDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = HISTORIQUE_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
