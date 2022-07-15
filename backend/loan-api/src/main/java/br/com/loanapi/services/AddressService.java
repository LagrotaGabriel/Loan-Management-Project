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

    String ADDRESS_NOT_FOUND = "Address not found";

    String VALIDATION_FAILED = "Address validation failed";

    String LOG_BAR = "=======================================================================================" +
            "==========================================";

    @Autowired
    ModelMapperConfig modelMapper;

    AddressValidation validation = new AddressValidation();

    public AddressDTO create(AddressDTO address){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting create method");

        if(validation.validateRequest(address)) {

            log.info("[PROGRESS] Verifying if the address already exists in the database");
            if(repository.findByStreetNumberAndPostalCode(address.getStreet(), address.getNumber(), address.getPostalCode()).isEmpty()) {

                log.info("[PROGRESS] The address doesn't exist in the database. Starting creation process");

                log.info("[PROGRESS] Searching for a CityEntity with the city name: {} and state: {}", address.getCity().getCity(), address.getCity().getState().getPrefix());
                Optional<CityEntity> city = cityRepository.findByCityAndState(address.getCity().getCity(), address.getCity().getState());

                if (city.isPresent()) {

                    log.warn("[INFO] City finded: {}", city.get().getCity() + " - " + city.get().getState().getPrefix());
                    CityDTO cityDTO = modelMapper.mapper().map(city.get(), CityDTO.class);
                    address.setCity(cityDTO);

                    log.info("[PROGRESS] Checking if the current city have addresses saved...");
                    if (cityDTO.getAddresses() == null) {

                        log.warn("[INFO] The City addresses list is null");

                        List<AddressDTO> addresses = new ArrayList<>();

                        log.info("[PROGRESS] Adding the address to City adresses list...");
                        addresses.add(address);

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

                else {

                    log.warn("[PROGRESS] City not found. Creating a new city...");

                    CityDTO cityDTO = address.getCity();

                    List<AddressDTO> addresses = new ArrayList<>();

                    log.info("[PROGRESS] Adding the address {} to city addresses list...", address);
                    addresses.add(address);

                    cityDTO.setAddresses(addresses);

                    log.info("[PROGRESS] CityEntity and AddressEntity are being created at database...");
                    cityRepository.save(modelMapper.mapper().map(cityDTO, CityEntity.class));

                }

                log.info("[SUCCESS]  Request successfull");
                return address;
            }

            else{

                log.info("[FAILURE]  The address already exists in the database");
                throw new InvalidRequestException("The address already exists in the database");

            }

        }

        log.error("[FAILURE]  " + VALIDATION_FAILED);
        throw new InvalidRequestException(VALIDATION_FAILED);

    }

    public List<AddressDTO> findAll(){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findAll method...");
        log.info("[PROGRESS] Verifying if there is addresses saved in the database...");
        if(!repository.findAll().isEmpty()) {
            log.info("[SUCCESS]  Returning all addresses saved in the database");
            return repository.findAll().stream().map(
                    x -> modelMapper.mapper().map(x, AddressDTO.class)).collect(Collectors.toList());
        }
        log.error("[FAILURE]  There is no addresses saved in the database");
        throw new ObjectNotFoundException("There is no addresses saved in the database");
    }

    public AddressDTO findById(Long id){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting findById method...");

        log.info("[PROGRESS] Searching for a address by id {}...", id);
        Optional<AddressEntity> address = repository.findById(id);

        address.ifPresent(addressEntity -> log.info("[SUCCESS]  Address finded"));
        if(address.isEmpty()) log.error("[FAILURE]  Address with id {} not found", id);
        return modelMapper.mapper().map(
                address.orElseThrow(() -> new ObjectNotFoundException(ADDRESS_NOT_FOUND)), AddressDTO.class);
    }

    public AddressDTO update(Long id, AddressDTO address){

        log.info(LOG_BAR);
        log.info("[STARTING] Starting update method");

        log.info("[PROGRESS] Searching for a address by id {}...", id);
        Optional<AddressEntity> addressEntityOptional = repository.findById(id);

        if(addressEntityOptional.isPresent()){

            log.info("[INFO] Address finded: {}, {}", addressEntityOptional.get().getStreet(), addressEntityOptional.get().getNumber());
            AddressDTO addressDTO = modelMapper.mapper().map(addressEntityOptional.get(), AddressDTO.class);
            CityDTO cityDTO = addressDTO.getCity();

            if (validation.validateRequest(address)){

                log.info("[PROGRESS] Updating the finded address with the JSON body content...");
                addressDTO.setStreet(address.getStreet());
                addressDTO.setNumber(address.getNumber());
                addressDTO.setNeighborhood(address.getNeighborhood());
                addressDTO.setPostalCode(address.getPostalCode());
                addressDTO.setCity(address.getCity());

                if (cityDTO.getCity().equals(address.getCity().getCity()) && cityDTO.getState().equals(address.getCity().getState())){

                    log.warn("[INFO] The city passed in JSON body is the same saved previously");
                    addressDTO.getCity().setId(cityDTO.getId());
                    addressDTO.getCity().setAddresses(cityDTO.getAddresses());

                    log.info("[PROGRESS] Updating the address list of the current city with the new address attributes...");
                    cityDTO.getAddresses()
                            .set(cityDTO.getAddresses().indexOf(addressDTO), addressDTO);

                    log.info("[PROGRESS] Removing the older address of the database...");
                    repository.deleteById(id);

                    log.info("[PROGRESS] Saving the new address at database with updating attributes...");
                    cityRepository.save(modelMapper.mapper().map(cityDTO, CityEntity.class));
                    log.info("[SUCCESS]  Request successfull");
                }

                else{

                    log.warn("[INFO] The city passed in JSON body is different than the city saved previously");
                    deleteById(id);
                    create(address);
                }

            }
            else{
                log.error("[FAILURE] Address attributes validation failed");
                throw new InvalidRequestException("Address validation failed");
            }

            return addressDTO;

        }
        else{
            log.error("[FAILURE] Address not found at database");
            throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);
        }

    }

    public Boolean deleteById(Long id) {

        log.info(LOG_BAR);
        log.info("[STARTING] Starting deleteById method...");

        Optional<AddressEntity> addressEntityOptional = repository.findById(id);

        log.info("[PROGRESS] Searching a Address by id {}...", id);
        if (addressEntityOptional.isPresent()) {

            log.warn("[INFO] Address finded in database");

            log.info("[PROGRESS] Making the instantiation of the Address and his current City...");
            AddressEntity addressEntity = addressEntityOptional.get();
            Optional<CityEntity> cityEntityOptional = cityRepository.findById(addressEntity.getCity().getId());
            if(cityEntityOptional.isPresent()) {
                CityEntity cityEntity = cityEntityOptional.get();
                log.info("[PROGRESS] Removing the address of the city addresses list...");
                cityEntity.getAddresses().remove(addressEntity);

                log.info("[SUCCESS]  City {} - {} updated in database", cityEntity.getState(), cityEntity.getCity());
                cityRepository.save(cityEntity);

                log.info("[SUCCESS]  Address removed from database");
                repository.deleteById(id);
                return true;
            }
            log.error("[FAILURE]  City not found");
            throw new ObjectNotFoundException("City not found");
        }
        else{
            log.error("[FAILURE] " + ADDRESS_NOT_FOUND);
            throw new ObjectNotFoundException(ADDRESS_NOT_FOUND);
        }
    }

}
