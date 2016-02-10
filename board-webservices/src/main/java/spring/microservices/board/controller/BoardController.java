package spring.microservices.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.microservices.board.model.Board;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wonwoo on 2016. 2. 10..
 */

@RestController
@RequestMapping("/")
public class BoardController {

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Board> getUsers() {
        return Arrays.asList(new Board(1L,"title","content"),new Board(2L,"spring boot microservices","Hello spring boot microservices"));
    }
}
