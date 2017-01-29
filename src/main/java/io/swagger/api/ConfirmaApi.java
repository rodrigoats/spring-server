package io.swagger.api;


import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-01-28T15:43:10.271Z")

@Api(value = "confirma", description = "the confirma API")
public interface ConfirmaApi {

    @ApiOperation(value = "", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Echo GET", response = Void.class) })
    @RequestMapping(value = "/confirma",
        method = RequestMethod.GET)
    ResponseEntity<Void> confirmaGet();


    @ApiOperation(value = "", notes = "", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Echo POST", response = Void.class) })
    @RequestMapping(value = "/confirma",
        method = RequestMethod.POST)
    ResponseEntity<Void> confirmaPost();

}
