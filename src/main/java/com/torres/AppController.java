package com.torres;


import com.torres.domain.FraudDetectResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Slf4j
public class AppController {

    // Fraud Detection
    @ApiOperation(value = "Fraud Detection API using user_id")
    @RequestMapping(
        value="/v1/fraud/{user_id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success to check fraud detection"),
        @ApiResponse(code = 400, message = "Bad Request - No exist this URL. check the URL again"),
        @ApiResponse(code = 404, message = "Not Found - Not Found. check user_id"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    public @ResponseBody FraudDetectResponse fraudDetectResponse(@PathVariable Long user_id){

        // TODO: check user_id is LONG?
        FraudDetectResponse fraudDetectResponse = new FraudDetectResponse(user_id, Boolean.TRUE, "RuleA,RuleB");
        log.info("FraudDetectResponse");
        return fraudDetectResponse;
    }

    // Config - Swagger
    @ApiOperation(value = "Swagger UI main", hidden= true)
    @RequestMapping(value="/", method = RequestMethod.GET)
    public RedirectView swaggerRedirect(){
        return new RedirectView("/swagger-ui.html");
    }

}
