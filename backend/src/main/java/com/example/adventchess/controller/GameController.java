package com.example.adventchess;

import com.example.adventchess.service.ChessGameService;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import java.security.Principal;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class GameController {
  private final Queue<String> sessionQueue = new LinkedList<>();

  SimpMessagingTemplate simpMessagingTemplate;
  ChessGameService chessGameService;

  @Autowired
  public GameController(SimpMessagingTemplate simpMessagingTemplate, ChessGameService chessGameService){
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.chessGameService = chessGameService;
  }

  @MessageMapping("/connect/game")
  public void handleConnect(Principal principal, SimpMessageHeaderAccessor headerAccessor, String mode) {
        String session = principal.getName();
        if (session != null) {
            // System.out.println(mode);
            sessionQueue.add(session);
            // Check if there are two sessions in the queue
            if (sessionQueue.size() >= 2) {
                // Retrieve the two sessions
                String session1 = sessionQueue.poll();
                String session2 = sessionQueue.poll();
                // Create new game session
                chessGameService.createGameSession(session1, session2);
            }
        }
  }

  @MessageMapping("/game/{gameId}/move")
  public void handleMove(@DestinationVariable String gameId, Principal principal, String moveMessage) {
        String session = principal.getName();
        chessGameService.verifyMove(session, gameId, moveMessage);
  }


}
