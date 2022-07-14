package br.com.loanapi.services;

import br.com.loanapi.config.ModelMapperConfig;
import br.com.loanapi.exceptions.InvalidRequestException;
import br.com.loanapi.exceptions.ObjectNotFoundException;
import br.com.loanapi.models.dto.AddressDTO;
import br.com.loanapi.models.dto.CityDTO;
import br.com.loanapi.models.entities.AddressEntity;
import br.com.loanapi.models.entities.CityEntity;
import br.com.loanapi.repositories.AddressRepository;
import br.com.loanapi.repositories.CityRepository;
import br.com.loanapi.validations.AddressValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityService cityService;

    String ADDRESS_NOT_FOUND = "Address not found";

    String VALIDATION_FAILED = "Address validation failed";

    String LOG_BAR = "===========================================================================================";

    @Autowired
    ModelMapperConfig modelMapper;

    AddressValidation validation = new AddressValidation();

    public AddressDTO create(AddressDTO address){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting service create method");

        if(validation.validateRequest(address)) {

            log.info("[PROGRESS] Searching for a CityEntity with the city name: {} and state: {}", address.getCity().getCity(), address.getCity().getState().getPrefix());
            Optional<CityEntity> city = cityRepository.findByCityAndState(address.getCity().getCity(), address.getCity().getState());

            if (city.isPresent()) {

                log.warn("[INFO] City finded: {}", city.get().getCity() + " - " + city.get().getState().getPrefix());
                CityDTO cityDTO = modelMapper.mapper().map(city.get(), CityDTO.class);
                address.setCity(cityDTO);

                log.info("[PROGRESS] Checking if the current city have addresses saved...");
                if (cityDTO.getAddresses() == null){

                    log.warn("[INFO] The CityEntity addresses list is null");

                    log.info("[PROGRESS] Creating a null arraylist for the CityEntity adresses...");
                    List<AddressDTO> addresses = new ArrayList<>();

                    log.info("[PROGRESS] Adding the address to ArrayList...");
                    addresses.add(address);

                    log.info("[PROGRESS] Setting the CityDTO addresses list with the new ArrayList now instantiated...");
                    cityDTO.setAddresses(addresses);

                }
                else {

                    log.warn("[INFO] The CityEntity addresses list is filled");

                    log.info("[PROGRESS] Adding the address to CityEntity addresses list...");
                    cityDTO.getAddresses().add(address);

                }

                log.info("[PROGRESS] Updating the city with a new address in database...");
                cityRepository.save(modelMapper.mapper().map(cityDTO, CityEntity.class));

            }

            else{

                log.warn("[PROGRESS] City not found. Creating a new city...");

                CityDTO cityDTO = address.getCity();
                log.info("[PROGRESS] Instantiated a variable cityDTO with City value included... {}", cityDTO);

                log.info("[PROGRESS] Creating a null arraylist for the CityEntity adresses...");
                List<AddressDTO> addresses = new ArrayList<>();

                log.info("[PROGRESS] Adding the address {} to addresses ArrayList...", address);
                addresses.add(address);

                log.info("[PROGRESS] Setting the CityDTO addresses list with the new ArrayList now instantiated...");
                cityDTO.setAddresses(addresses);

                log.info("[PROGRESS] CityEntity and AddressEntity are being created at database...");
                cityRepository.save(modelMapper.mapper().map(cityDTO, CityEntity.class));

            }

            log.info("[SUCCESS] Request successfull");
            return address;

        }

        log.error("[FAILURE] " + VALIDATION_FAILED);
        throw new InvalidRequestException(VALIDATION_FAILED);

    }

    public List<AddressDTO> findAll(){
        if(!repository.findAll().isEmpty()) return repository.findAll().stream()
                .map(x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList());
        throw new ObjectNotFoundException("There is no addresses saved in the database");
    }

    public AddressDTO findById(Long id){
        Optional<AddressEntity> address = repository.findById(id);
        return modelMapper.mapper().map(
                address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){

        if (validation.validateRequest(address)) {

            AddressDTO dto = modelMapper.mapper().map(repository.findById(id), AddressDTO.class);

            dto.setStreet(address.getStreet());
            dto.setNumber(address.getNumber());
            dto.setCity(address.getCity());
            dto.setCustomers(address.getCustomers());
            dto.setNeighborhood(address.getNeighborhood());
            dto.setPostalCode(address.getPostalCode());

            return modelMapper.mapper().map(repository.save(
                    modelMapper.mapper().map(dto, AddressEntity.class)), AddressDTO.class);
        }
        else{
            throw new InvalidRequestException(VALIDATION_FAILED);
        }

    }

    public Boolean deleteById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ObjectNotFoundException("Address not found");
        }
    }

}
