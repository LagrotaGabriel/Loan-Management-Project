package br.com.loanapi.proxys;

import br.com.loanapi.models.dto.InstallmentDTO;
import br.com.loanapi.models.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "installment-service", url = "http://wgod-alp-no0071.server01.foursys.local:8091/")
public interface InstallmentServiceProxy {

    @PostMapping(value="installment-service")
    ResponseEntity<List<InstallmentDTO>> calculateInstallments(@RequestBody LoanDTO loanDTO);

}
