package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.service.implementation.OfficeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    private final OfficeServiceImpl officeService;

    @Autowired
    public OfficeController(OfficeServiceImpl officeService){ this.officeService = officeService; }

    @GetMapping
    public List<OfficeDtoResponse> getAllOffices(){
        return this.officeService.getAllOffices();
    }

    @GetMapping("/{officeId}")
    public OfficeDtoResponse getOffice(@PathVariable int officeId){
        return this.officeService.getOffice(officeId);
    }

    @PostMapping
    public OfficeDtoResponse createNewOffice(@RequestBody @Valid OfficeDto officeDto){
        return this.officeService.createNewOffice(officeDto);
    }

    @PatchMapping("/{officeId}")
    public OfficeDtoResponse updateOffice(
            @PathVariable int officeId,
            @RequestBody @Valid OfficeDto officeDto
    ){
        return this.officeService.updateOffice(officeId, officeDto);
    }

    @DeleteMapping("/{officeId}")
    public ResponseEntity<String> deleteOffice(@PathVariable int officeId){
        this.officeService.deleteOffice(officeId);

        return ResponseEntity.ok("Office with id: '" + officeId + "' was successfully deleted!");
    }
}
