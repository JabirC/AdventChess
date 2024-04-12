package com.example.adventchess;

import com.example.adventchess.service.ChessGameService;
import com.example.adventchess.model.MoveMessage;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;


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
  private final Set<String> pings = new HashSet<>();


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



  @MessageMapping("/disconnect")
  public void disconnect(Principal principal, String reason) {
      String session = principal.getName();
      chessGameService.handleDisconnect(session, reason);
  }

  @MessageMapping("/game/{gameId}/rematch")
  public void handleRematch(@DestinationVariable String gameId, Principal principal, String mode) {
      String session = principal.getName();
      chessGameService.handleRematch(gameId, session, mode);
  }

  @MessageMapping("/game/{gameId}/move")
  public void handleMove(@DestinationVariable String gameId, Principal principal, MoveMessage moveMessage) {
        String session = principal.getName();
        chessGameService.verifyMove(session, gameId, moveMessage);
  }
  

  @MessageMapping("/pong")
  public void handlePing(Principal principal, String message) {
        String session = principal.getName();
        pings.add(session);
  }

  private void startQueueProcessor(BlockingQueue<String> sessionQueue, String mode) {
    executorService.execute(() -> {
        while (true) {
            try {
                String session1 = sessionQueue.take();
                String session2 = sessionQueue.take();
                CompletableFuture<Boolean> heartbeatCheck1 = checkHeartbeatAsync(session1);
                CompletableFuture<Boolean> heartbeatCheck2 = checkHeartbeatAsync(session2);

                // Perform actions asynchronously when heartbeat checks complete
                heartbeatCheck1.thenAccept(result1 -> {
                    heartbeatCheck2.thenAccept(result2 -> {
                        if (result1 && result2) {
                            chessGameService.createGameSession(session1, session2, mode);
                        } else if (!result1 && result2) {
                            sessionQueue.add(session2);
                        } else if (result1 && !result2) {
                            sessionQueue.add(session1);
                        }
                    });
                });
                
                // chessGameService.createGameSession(session1, session2, mode);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    });
}

private CompletableFuture<Boolean> checkHeartbeatAsync(String sessionId) {
  return CompletableFuture.supplyAsync(() -> {
      try {
          // Send ping message to the client
          String message = String.format("{\"message\" : \"%s\"}", "PING");
          simpMessagingTemplate.convertAndSend("/topic/ping" + sessionId, message);
          
          // Wait for pong response within a timeout period
          Thread.sleep(500); // Wait for .5 second (adjust as needed)
          
          // Check if pong response was received within the timeout
          if(pings.contains(sessionId)){
            pings.remove(sessionId);
            return true;
          }
          return false;
      } catch (InterruptedException e) {
          // Handle interrupted exception if needed
          e.printStackTrace();
          return false;
      }
  });
}


}
