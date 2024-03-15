package com.example.adventchess;

import com.example.adventchess.service.ChessGameService;
// import java.util.Map;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import java.security.Principal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// import java.lang.InterruptedException;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class GameController {
  private final BlockingQueue<String> sessionQueueClassic = new LinkedBlockingQueue<>();
  private final BlockingQueue<String> sessionQueueAdventure = new LinkedBlockingQueue<>();
  private final ExecutorService executorService = Executors.newCachedThreadPool();

  SimpMessagingTemplate simpMessagingTemplate;
  ChessGameService chessGameService;

  @Autowired
  public GameController(SimpMessagingTemplate simpMessagingTemplate, ChessGameService chessGameService){
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.chessGameService = chessGameService;

    startQueueProcessor(sessionQueueClassic, "classic");
    startQueueProcessor(sessionQueueAdventure, "adventure");
  }

  @MessageMapping("/connect/game")
  public void handleConnect(Principal principal, SimpMessageHeaderAccessor headerAccessor, String mode) {
        String session = principal.getName();
        if (session != null) {
            if(mode.equals("\"classic\"")){
              sessionQueueClassic.add(session);
            }
            else if(mode.equals("\"adventure\"")){
              sessionQueueAdventure.add(session);
            }
            else{
              System.out.println("Invalid game mode");
            }

        }
  }

  @MessageMapping("/game/{gameId}/move")
  public void handleMove(@DestinationVariable String gameId, Principal principal, String moveMessage) {
        String session = principal.getName();
        chessGameService.verifyMove(session, gameId, moveMessage);
  }

  private void startQueueProcessor(BlockingQueue<String> sessionQueue, String mode) {
    executorService.execute(() -> {
        while (true) {
            try {
                String session1 = sessionQueue.take();
                String session2 = sessionQueue.take();
                chessGameService.createGameSession(session1, session2, mode);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    });
}

}
