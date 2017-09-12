/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.layout.GridPane;
/**
 *
 * @author exchange
 */
public class CardGridPane extends GridPane {
    private Card[][] cards;
    private ArrayList<String> cardList;
    private int MAXROWS;
    private int MAXCOLS;
    private int currentRows;
    private int currentCols;
    private int cardSize;
    
    public CardGridPane(){
        cards = new Card[8][8];
        cardList = new ArrayList();
        MAXROWS = 8;
        MAXCOLS = 8;
        currentRows = 0;
        currentCols = 0;
        cardSize = 64;
        this.setHgap(3);
        this.setVgap(3);
        this.setStyle("-fx-background-color: BLACK");

    }
    
    public CardGridPane(int cardSize){
        cards = new Card[8][8];
        cardList = new ArrayList();
        MAXROWS = 8;
        MAXCOLS =8;
        currentRows = 0;
        currentCols = 0;
        cardSize = 0;
        this.setHgap(3);
        this.setVgap(3);
    }
    
    public void createCardImageList(int size){
        cardList = new ArrayList();
        int count = 0;
        
        for(int i = 0; i < size / 2; i++){
            cardList.add("cardimages/image_" + count + ".png");
            cardList.add("cardimages/image_" + count + ".png");
            count++;
        }
    }
    
    public Card getCard(int r, int c){
        return cards[r][c];
    }
    
    public void setCard(int r, int c, Card card){
        cards[r][c] = card;
        
    }
    public int getNumCardsInList(){
        return cardList.size();
    }
    
    public String getCardInList(int index){
        return cardList.get(index);
        
    }
    
    public void setCardInList(int index, String item){
        cardList.set(index, item);
    }
    
    public void addCardInList(String item){
        cardList.add(item);
        
    }
    
    public String deleteCardInList(int index){
        return cardList.remove(index);
    }

    /**
     * @return the MAXROWS
     */
    public int getMAXROWS() {
        return MAXROWS;
    }

    /**
     * @param MAXROWS the MAXROWS to set
     */
    public void setMAXROWS(int MAXROWS) {
        this.MAXROWS = MAXROWS;
    }

    /**
     * @return the MAXCOLS
     */
    public int getMAXCOLS() {
        return MAXCOLS;
    }

    /**
     * @param MAXCOLS the MAXCOLS to set
     */
    public void setMAXCOLS(int MAXCOLS) {
        this.MAXCOLS = MAXCOLS;
    }

    /**
     * @return the currentRows
     */
    public int getCurrentRows() {
        return currentRows;
    }

    /**
     * @param currentRows the currentRows to set
     */
    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

    /**
     * @return the currentCols
     */
    public int getCurrentCols() {
        return currentCols;
    }

    /**
     * @param currentCols the currentCols to set
     */
    public void setCurrentCols(int currentCols) {
        this.currentCols = currentCols;
    }

    /**
     * @return the cardSize
     */
    public int getCardSize() {
        return cardSize;
    }

    /**
     * @param cardSize the cardSize to set
     */
    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }
    
    public void setCardImages(){
        int count = 0;
        
        for(int i = 0; i < currentRows; i++){
            for(int j = 0; j < currentCols; j++){
                System.out.println(cardList.get(count));
                cards[i][j].setPath(cardList.get(count));
                
                cards[i][j].setDisable(false);
                count++;
            }
        }
    }
    
    public void shuffleImages(){
        Collections.shuffle(cardList);
    }
    
    public void initCards(int rows, int cols){
        currentRows = rows;
        currentCols = cols;
        
        createCardImageList(rows * cols);
        
        for(int i = 0; i < currentRows; i++){
            for(int j = 0; j < currentCols; j++){
                cards[i][j] = new Card();
                cards[i][j].setCardAndImageSize(cardSize, cardSize);
                this.add(cards[i][j], j, i);
            }
        }
        for(int i = 0; i < MAXROWS; i++){
            for(int j = 0; j < MAXCOLS; j++){
                if(i < currentRows && j < currentCols){
                    cards[i][j].setCardDisabled(false);
                }
                else{
                    cards[i][j].setCardDisabled(true);
                }
            }
        }
        shuffleImages();
        setCardImages();
    }
    

    
}
