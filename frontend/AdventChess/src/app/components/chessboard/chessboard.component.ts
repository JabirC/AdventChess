import { Component, ElementRef, Renderer2, ViewChild, AfterViewInit, HostListener, ChangeDetectorRef, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import { pieceDragDirective} from '../../shared/directives/piece.directive'
import { WebSocketService } from '../../shared/services/websocket.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [CommonModule, pieceDragDirective],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.scss'
})
export class ChessboardComponent implements AfterViewInit {
  @Input() mode!: string;
  boundary = { top: 0, bottom: 100, left: 0, right: 100, scroll: 0, windowSize:0};
  boardState: string[][] = [];
  isWhite!: boolean;
  orientationWhite!: boolean;
  @ViewChild('chessboardContainer') chessboardContainer!: ElementRef;
  @ViewChild('overlay') overlay!: ElementRef;
  @ViewChild('rematch') rematchButton!: ElementRef; // ViewChild reference

  containerWidth!: number;
  username!: string;
  gameSession!: string;
  turn!: boolean;
  message!: string;
  submessage!: string;
  rematch!: boolean;
  isLoading!: boolean;

  fromRow = -1;
  fromCol = -1;
  toRow = -1;
  toCol = -1;

  
  constructor(private renderer: Renderer2, private cdr: ChangeDetectorRef, private webSocketService: WebSocketService, private router: Router) {
    // Initialize the boardState with a default chessboard configuration
    this.isWhite = true;
    this.isWhite? this.orientationWhite = true: this.orientationWhite = false;
    this.rematch = true;
    this.isLoading = false;
    this.initializeBoard();
  }

  private initializeBoard(): void {
    // Array representing the default starting position of pieces on a chessboard
    const defaultBoard: string[][] = [
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
    ];

    // Copy the default board configuration to the boardState
    defaultBoard.reverse();
    this.boardState = defaultBoard.map(row => [...row]);
  }

  // Map piece names to their image urls
  keyValuePairs = new Map([
    ['BR', '../../../assets/pieces/rook-b.svg'],
    ['BN', '../../../assets/pieces/knight-b.svg'],
    ['BB', '../../../assets/pieces/bishop-b.svg'],
    ['BQ', '../../../assets/pieces/queen-b.svg'],
    ['BK', '../../../assets/pieces/king-b.svg'],
    ['BP', '../../../assets/pieces/pawn-b.svg'],

    ['WR', '../../../assets/pieces/rook-w.svg'],
    ['WN', '../../../assets/pieces/knight-w.svg'],
    ['WB', '../../../assets/pieces/bishop-w.svg'],
    ['WQ', '../../../assets/pieces/queen-w.svg'],
    ['WK', '../../../assets/pieces/king-w.svg'],
    ['WP', '../../../assets/pieces/pawn-w.svg'],

    ['--', '../../../assets/pieces/pawn-w.svg'],

  ]);

  // Check if square dark
  isDarkSquare(row: number, col: number): boolean {
    return (row + col) % 2 !== 0;
  }

  // Check if square was involved in last move and is dark
  isMoveSquareDark(row: number, col: number): boolean {
    return (this.getMappingMove(this.toRow, "row") == row && this.getMappingMove(this.toCol, "col") == col 
           || this.getMappingMove(this.fromRow, "row") == row && this.getMappingMove(this.fromCol, "col") == col)
           && this.isDarkSquare(row, col);
  }

  // Check if square was involved in last move and is light
  isMoveSquareLight(row: number, col: number): boolean {
    return (this.getMappingMove(this.toRow, "row") == row && this.getMappingMove(this.toCol, "col") == col 
           || this.getMappingMove(this.fromRow, "row") == row && this.getMappingMove(this.fromCol, "col") == col)
           && !this.isDarkSquare(row, col);
  }

  // Handles websocket connection, and destination subsriptions after successful connection
  ngAfterViewInit(): void {
    // this.containerWidth = this.chessboardContainer.nativeElement.clientWidth;
    this.setBoundary();
    this.cdr.detectChanges();
    console.log(this.mode);
    this.webSocketService.connect().then(user => {
      // console.log('User:', user);
      this.username = user;

      // for ping-pong functionality to check player connection from backend
      this.webSocketService.subscribe('/topic/ping' + this.username, (message) => {
        this.webSocketService.sendMessage("/app/pong", "PONG");
        console.log("pinged");
      });

      // New game is created
      this.webSocketService.subscribe('/topic/reply' + this.username, (message) => {
        this.overlayOff();
        this.isLoading = false;
        this.gameSession = message.gameId;
        this.boardState = message.gameState.reverse().map((row: string) => [...row]);
        this.isWhite = message.isWhite;
        this.orientationWhite = message.isWhite;
        this.turn = message.turn;

        // A move is made
        this.webSocketService.subscribe('/topic/state' + this.gameSession + this.username, (move)=>{
          this.boardState = move.gameState.reverse().map((row: string) => [...row]);
          this.turn = move.turn;
          if(move.fromRow != -1){
            this.fromRow = move.fromRow;
            this.fromCol = move.fromCol;
            this.toRow = move.toRow;
            this.toCol = move.toCol;
          }
        });

        // End game conditions
        this.webSocketService.subscribe('/topic/state' + this.gameSession, (msg)=>{
          if(msg.condition != "Resignation"){
            this.rematch = false;
            this.isLoading = false;
            this.webSocketService.disconnect("disc");
          }
          if(!this.message){
            this.overlayOn(msg.result, msg.condition);
          }
          // this.webSocketService.disconnect("Disconnection");
        });
      });

      // Sends a message to initiate joining a game queue
      this.webSocketService.sendMessage("/app/connect/game", this.mode);
    });

  }

  // Send disconnect message when the component is destoryed
  ngOnDestroy(): void {
    this.webSocketService.disconnect("Disconnection");
  }

  // Handle resignation from the game
  disc(): void {
    this.webSocketService.resign("Resignation");
  }

  // Handle window before unload event
  @HostListener('window:beforeunload', ['$event'])
  beforeunloadHandler(event: Event) {
    this.webSocketService.disconnect("Disconnection");
  }
  
  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    // Handle window resize event
    this.setBoundary();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    // Get the vertical scroll position
    const scrollPosition = window.scrollY;
    this.boundary.scroll = scrollPosition;
  }


  // Set the boundary for the draggable pieces
  private setBoundary(): void {
    if (this.chessboardContainer) {
      this.containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      this.boundary.bottom = this.containerWidth;
      this.boundary.right= this.containerWidth;
      this.boundary.windowSize = window.innerWidth;
    }
  }

  // Handle the change in board position after a move
  handleBoardChange(newCoordinates: number[], oldRow: number, oldCol: number) {
    const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
    // const squareSize = containerWidth / 8;

    const pieceName: string = this.boardState[this.getMapping(oldRow)][this.getMapping(oldCol)];
    this.boardState[this.getMapping(oldRow)][this.getMapping(oldCol)] = "--";
    this.boardState[this.getMapping(newCoordinates[0])][this.getMapping(newCoordinates[1])] = pieceName;

    // Send the move to the backend to verify validity
    this.webSocketService.sendMessage(`/app/game/${this.gameSession}/move`, {fromRow: this.getMappingMove(oldRow, "row"), fromCol: this.getMappingMove(oldCol, "col"), toRow: this.getMappingMove(newCoordinates[0], "row"), toCol: this.getMappingMove(newCoordinates[1], "col")});
  }

  // Used to inverse the chessboard based on player color
  getMapping(index: number): number{
    const mappedIndex = this.orientationWhite? index: 7-index;
    return mappedIndex;
  }

  // Map the index based on the board orientation for moves
  getMappingMove(index: number, rowOrCol: String){
    if(this.orientationWhite){
      if(rowOrCol == "row"){
        return 7-index;
      }
      else{
        return index;
      }
    }
    else{
      if(rowOrCol == "row"){
        return index;
      }
      else{
        return 7-index;
      }
    }

  }

  // Rotate the board based on player color
  rotateBoard(){
    this.orientationWhite = !this.orientationWhite;
  }

  // Display an overlay message
  overlayOn(msg: string, submsg: string){
    this.message = msg;
    this.submessage = submsg;
    this.turn = true;
    this.overlay.nativeElement.style.display = 'block';
  }

  // Hide the overlay message
  overlayOff(){
    this.overlay.nativeElement.style.display = 'none';
    this.message = '';
    this.submessage = '';
  }

  // Navigate to the game menu
  gameMenu(){
    this.router.navigate(['/mode']);
  }

  // Handle click event on overlay message
  onOverlayMessageClick(event: MouseEvent): void {
    event.stopPropagation();
  }

  // Handle rematch request
  handleRematch(){
    this.webSocketService.sendMessage(`/app/game/${this.gameSession}/rematch`, this.mode);
    this.isLoading = true;
  }

  // Cancel the rematch request
  cancelRematch(){
    this.rematch = false;
    this.isLoading = false;
    this.webSocketService.disconnect("disc");
  }


}
