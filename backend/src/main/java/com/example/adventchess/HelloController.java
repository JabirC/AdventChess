package com.example.adventchess;

import adventchess.chessgame.model.*;
import adventchess.chessgame.logic.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        ChessGame game = new ChessGame("bob", "alice");
        game.startGame();
        return "Hello, World!";
    }
}


