package com.sytoss.edu2021.contollers;

import com.sytoss.edu2021.repo.dto.EngineBOM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-engine", url = "localhost:6050/api/engine")
public interface FeignProxyEngine {
    @GetMapping("/{idCabin}")
    EngineBOM create(@PathVariable Integer idCabin);

    @PostMapping("/engines/")
    EngineBOM[] getEngines(@RequestBody Integer[] ids);

    @GetMapping("/get/{idCabin}")
    EngineBOM getEngine(@PathVariable Integer idCabin);

    @PostMapping("/update")
    void update(@RequestBody EngineBOM engine);

}