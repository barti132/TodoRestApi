package pl.bartoszsredzinski.todorestapi.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {

    @RequestMapping("test")
    @ResponseBody
    public String purchase(){
        return "example";
    }
}
