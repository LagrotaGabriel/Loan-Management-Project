package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.ScoreDTO;
import br.com.loanapi.models.entities.ScoreEntity;
import br.com.loanapi.repositories.ScoreRepository;
import br.com.loanapi.validations.ScoreValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository repository;

    @Autowired
    ModelMapperConfig modelMapper;

    String SCORE_NOT_FOUND = "Score not found";

    ScoreValidation validation = new ScoreValidation();

    public ScoreDTO create(ScoreDTO score){
        if (validation.validateRequest(score))
            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(score, ScoreEntity.class)), ScoreDTO.class);
        throw new InvalidRequestException("Score validation failed");
    }

    public List<ScoreDTO> findAll() {
        if(!repository.findAll().isEmpty())
            return repository.findAll().stream().map(x -> modelMapper.mapper().map(x, ScoreDTO.class))
                    .collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no Scores saved in the database");
    }

    public ScoreDTO findById(Long id) {
        Optional<ScoreEntity> score = repository.findById(id);
        return modelMapper.mapper().map(
                score.orElseThrow(() -> new ObjectNotFoundException(SCORE_NOT_FOUND)), ScoreDTO.class);
    }

    public ScoreDTO update(Long id, ScoreDTO score) {

        if (validation.validateRequest(score)) {

            ScoreDTO dto = modelMapper.mapper().map(findById(id), ScoreDTO.class);
            dto.setPontuation(score.getPontuation());
            dto.setCustomer(score.getCustomer());

            return modelMapper.mapper().map(repository.save(modelMapper.mapper().map(dto, ScoreEntity.class)), ScoreDTO.class);
        }
        else{
            throw new InvalidRequestException("Score validation failed");
        }

    }

    public Boolean delete(Long id){
        ScoreEntity entity = modelMapper.mapper().map(findById(id), ScoreEntity.class);
        repository.delete(entity);
        return true;
    }
}