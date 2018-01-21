/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedikast;

import filesModel.SoundModel;
import filesModel.VideoModel;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class DedikastController implements Initializable {

    /**
     * Initializes the controller class.
     */
  @FXML  private List<MediaPlayer> videoModel;   
  @FXML  private  ObservableList<MediaPlayer> dataVideo ;
 @FXML    private  TableView <MediaPlayer> listVideo ;
 @FXML    private final String LIST_VIDEO_ID = "list-video";
 @FXML   public String TITRE_COLUMN_NAME = "titre";
 @FXML  public  String ARTISTE_COLUMN_NAME = "artist";
   
 
 @FXML  private List<MediaPlayer> soundModel;   
  @FXML  private  ObservableList<MediaPlayer> dataAudio ;
 @FXML    private  TableView <MediaPlayer> listAudio ;
 @FXML   public  String TITRE_AUDIO_COLUMN_NAME = "titre-audio";
  @FXML public  String ARTISTE_AUDIO_COLUMN_NAME = "artist-audio";
  @FXML public  String ALBUM_AUDIO_COLUMN_NAME = "album-audio";
 @FXML   public String GENRE_AUDIO_COLUMN_NAME = "genre-audio";
   
 
 
 
 
 @FXML   private String MEDIA_VIEW_ID ="media-view";
  @FXML   private Stage PRIMARY_STAGE;
 @FXML    private String VIS_CONTAINER_ID; 
  @FXML   private ChangeListener<Duration> progressListener;
   @FXML  private String SEEK_POS_SLIDER_ID="seek-pos-slider";
    @FXML private InvalidationListener StatusListener;
    
    
 @FXML   private final  Image buttonPlayImage = new Image(Dedikast.class.getResourceAsStream("play.png"));
  @FXML private final  Image buttonPauseImage = new Image(Dedikast.class.getResourceAsStream("pause.png"));
  @FXML  private final  Image buttonBackwardImage = new Image(Dedikast.class.getResourceAsStream("backWard.png"));
 @FXML   private final  Image buttonForwardImage = new Image(Dedikast.class.getResourceAsStream("forward.png"));
 @FXML   private final  Image buttonLectureAleatoireImage = new Image(Dedikast.class.getResourceAsStream("buttonLectureAleatoire1.png"));
 @FXML   private final  Image buttonLectureContinueImage = new Image(Dedikast.class.getResourceAsStream("buttonLectureContinue1.png"));
 @FXML   private final  Image buttonLectureContinueImage11 = new Image(Dedikast.class.getResourceAsStream("buttonLectureContinue11.png"));
    
    
    public   ImageView imageViewPlayPause = new ImageView(buttonPlayImage);
    ImageView imageViewBackward = new ImageView(buttonBackwardImage);
    ImageView imageViewForward = new ImageView(buttonForwardImage);
    ImageView imageViewLectureAleatoire = new ImageView(buttonLectureAleatoireImage);
    ImageView imageViewLectureContinue = new ImageView(buttonLectureContinueImage);
    ImageView imageViewLectureContinue11 = new ImageView(buttonLectureContinueImage11);
    private final String MENU_BAR ="menu-bar";
    private final String LIST_AUDIO_ID ="list-audio";
    private String TOOL_BAR="tool-bar";
    private String defaultLibrary= "defaul-library";
    private String TAB_AUDIO_ID="tab-audio";
    private String TAB_VIDEO_ID="tab-video";
    private String LIBRARY_PANEL_ID;
    private Point2D anchorPoint;
    private Point2D previousLocation;
    private String SIDE_BAR;
    

    
    public void start(Stage primaryStage) {
   
//instantiate  stage
   
   PRIMARY_STAGE = primaryStage;
   PRIMARY_STAGE.initStyle(StageStyle.DECORATED);
   PRIMARY_STAGE.centerOnScreen();
  
   
    Group total = new Group();
    Group root = new Group();
    root.getChildren().add(total);
    Scene scene = new Scene(root,800,600);
    
    
    // load javafx stle sheet
    scene.getStylesheets().addAll(getClass().getResource("player-audio.css").toExternalForm());
    PRIMARY_STAGE.setScene(scene);
    
    
    

    //application area
final    Pane applicationArea = createApplicationArea();
   
       // Create a media view to display video 
final    MediaView  mediaView = createMediaView();
    
    //Create a side bar for contextual object
     Node SideBarContent = createSideBarContent();
     
     
     SideBar sideBar = new SideBar(200,SideBarContent);
     
     Button controlButton = sideBar.getControlButton();
     VBox.setVgrow(SideBarContent, Priority.ALWAYS);
    
      sideBar.setId(SIDE_BAR);
    
    //container for random circles bouncing out 
    Node visContainer = new Group();
    visContainer.setId(VIS_CONTAINER_ID);
     
    applicationArea.getChildren().add(visContainer);
    
//Create the button panel
    Node tools = createButtonPanel();
    

   
    
    //create Node the library  panel
    Node libraryPanel = createLibraryPanel();
    

    
     
    
    total.getChildren().addAll( applicationArea, 
                              controlButton,
                               sideBar , tools ,
                               libraryPanel  );
    total.getChildren().add(1, mediaView);

    

    // Initialiser la fenetre au mouvement via la souris
    initMovablePlayer();
    
    
    primaryStage.show();
       
    }

    private Pane createApplicationArea(){
Scene scene = PRIMARY_STAGE.getScene();

Pane applicationArea = new Pane();

Rectangle appArea = new Rectangle();
appArea.setStyle("-fx-fill: green");
// add selector to style app-area
applicationArea.setId("app-area");
appArea.widthProperty().bind(scene.widthProperty().subtract(200));
appArea.heightProperty().bind(scene.heightProperty());
applicationArea.getChildren().add(appArea);

// make the app area rectangle the size of the scene

applicationArea.setLayoutX(0);
applicationArea.setLayoutY(0);



return applicationArea;
}

/**
Create the node containing the audioplayer's
* stop , pause & play ,foreward ,backward 
* lecture Continue , Lecture aleatoire ... buttons
* 
* 
* @return Node A button panel having play ,
* pause and stop buttons
**/

private Node createButtonPanel(){
    Scene scene =PRIMARY_STAGE.getScene();
     // create toolbar @return (ToolBar controls)
    
    ToolBar controls = new ToolBar();
    controls.setStyle("-fx-background-color:coral;");
            
    

      // play button
     
 Button playPauseButton = new Button(null, imageViewPlayPause);
  playPauseButton.setId("playPauseButton");
  playPauseButton.setStyle("-fx-background-color:transparent;");
  MediaPlayer mediaPlayer = null;
  playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent arg0) {
     
      if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
        mediaPlayer.pause();
        imageViewPlayPause.setImage(buttonPlayImage);
      } else {
        mediaPlayer.play();
        imageViewPlayPause.setImage(buttonPauseImage);
      }
} });



controls.getItems().add(playPauseButton);

Button backward = new Button();
backward.setGraphic(imageViewBackward);
backward.setPrefSize(20 , 20);
backward.setStyle("-fx-translate-x:5;");
backward.setStyle("-fx-background-color:coral;");
controls.getItems().add(backward);
backward.setOnMousePressed(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent mouseEvent) {
            if (mediaPlayer != null){
                
            }       }
    });
    
Button foreward = new Button();
foreward.setGraphic(imageViewForward);
foreward.setPrefSize(20 , 20 );
foreward.setStyle("-fx-translate-x:5;");
foreward.setStyle("-fx-background-color:coral;");
controls.getItems().add(foreward);
foreward.setOnMousePressed(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent mouseEvent) {
         }
    });    
    controls.getItems().add(new Separator());
   
   
   
// create slider control : Progress and seek position slider 

Slider progressSlider = new Slider(0,100,1);
progressSlider.setId(SEEK_POS_SLIDER_ID);
progressSlider.valueProperty()
       .addListener((observable)->{
       if(progressSlider.isValueChanging()){
       // must check if media is paused before seeking
 if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED){
 
 //convert seconds to millis
     double dur = progressSlider.getValue() * 1000;
     mediaPlayer.seek(Duration.millis(dur));
     
 }
       }
       
       });//addListenr()
//update slider as media progress
    progressListener = (observable , old_value , new_value)->
            progressSlider.setValue(new_value.toSeconds());


 progressSlider.setStyle("-fx-translate-x:3;");
controls.getItems().add(progressSlider);
controls.getItems().add(new Separator());

Button lectAleat = new Button();
lectAleat.setGraphic(imageViewLectureAleatoire);
lectAleat.setPrefSize(20, 20);
lectAleat.setStyle("-fx-translate-x:3;");
lectAleat.setStyle("-fx-background-color:coral;");
controls.getItems().add(lectAleat);

Button lectCont = new Button();
lectCont.setGraphic(imageViewLectureContinue);
lectCont.setPrefSize(20 , 20);
lectCont.setStyle("-fx-translate-x:3;");
lectCont.setStyle("-fx-background-color:coral;");
controls.getItems().add(lectCont);

// Add the volume label
        Label volumeLabel = new Label("Vol");
         volumeLabel.setStyle("-fx-text-fill:green");
         volumeLabel.setStyle("-fx-translate-x:3;");
        controls.getItems().add(volumeLabel);
      
// Add Volume slider

        Slider   volumeSlider = new Slider(0,100,60);
        volumeSlider.setPrefWidth(100);
       
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
        private MediaPlayer mediaPlayer;
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            if (!volumeSlider.isValueChanging()) {
                            volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                        }
            }
        });
     volumeSlider.setStyle("-fx-translate-x:3;");
     volumeSlider.setOrientation(Orientation.HORIZONTAL);
        controls.getItems().add(volumeSlider);

        // creation bouton de lecture aleatoire
        



// HBox container for "pocketing" controls: @ return (HBox "tools")
    
    HBox  tools = new HBox(5.0);
    tools.setAlignment(Pos.CENTER);
    tools.setPrefWidth(600);
    tools.setPrefHeight(25);
    tools.setId(TOOL_BAR);
    tools.setStyle("-fx-background-color:coral;");
    tools.setStyle("-fx-border-color:green;");
       
    tools.getChildren().addAll(controls);
    tools.setHgrow(controls ,Priority.ALWAYS);
    
      
   
   tools.setLayoutX(0);
   tools.translateYProperty().bind(scene.heightProperty().subtract(250));
   
  
   
return tools;
}

    @FXML private void loadSystemAudioLists(ActionEvent event)throws Exception{
       
       
    }    
   
   @FXML
   private void loadSystemVideosLists(ActionEvent event)throws Exception{
       
       
    }   
   
   @FXML
   private void loadSystemLivresLists(ActionEvent event)throws Exception{
       
       
    }    
   
    @FXML
   private void loadOwnaAudioLists(ActionEvent event)throws Exception{
       
       
    } 
   
   @FXML
   private void loadOwnaVideosLists(ActionEvent event)throws Exception{
       
       
    }   
   
   @FXML
   private void loadOwnaLivresLists(ActionEvent event)throws Exception{
       
       
    }    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    
   
    } 
    
    /**
*Building the library for available
* files both audio and video
*/
private Node createLibraryPanel(){
    Scene scene = PRIMARY_STAGE.getScene();

    
    List<MediaPlayer> videoModel = new VideoModel().VideoModel();
final ObservableList<MediaPlayer> dataVideo;
      dataVideo = FXCollections.observableArrayList(videoModel);
    final TableView <MediaPlayer> listVideo = new TableView(dataVideo);
    
   TableColumn<MediaPlayer, String> titreColumn = new TableColumn< >("Titre");   
   TableColumn<MediaPlayer, String>  yearColumn = new TableColumn< >("Artiste");


       
       
       // add a metadataTable for meta data display
    listVideo.setStyle("-fx-font-size: 13px;"+"-fx-background-color:green;");
    listVideo.setId(LIST_VIDEO_ID);
    
    titreColumn.setPrefWidth(150);
    yearColumn.setPrefWidth(150);
 
    titreColumn.setCellValueFactory(new PropertyValueFactory<>(TITRE_COLUMN_NAME));
    yearColumn.setCellValueFactory(new PropertyValueFactory<>(ARTISTE_COLUMN_NAME));
 
    
    
    titreColumn.setCellValueFactory((g)-> new SimpleStringProperty ((String) g.getValue().getMedia().getMetadata().get("title")));
    yearColumn.setCellValueFactory((g)-> new SimpleStringProperty ((String) g.getValue().getMedia().getMetadata().get("year")));
    
   
       
       
    
    listVideo.setItems(dataVideo);
    listVideo.getColumns().addAll(titreColumn,yearColumn);
    
    
    
    listVideo.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<MediaPlayer>(){
            public void changed(ObservableValue<? extends MediaPlayer> ov,MediaPlayer old_val , MediaPlayer new_val ){
             MediaPlayer   mediaPlayer = new_val;   
            }
        
        
        });
   
   
    

// read selected item on 2Click
    
   listVideo.setOnMouseClicked(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2){
               MediaPlayer mediaPlayer ;
                mediaPlayer = listVideo.getSelectionModel().getSelectedItem() ;
                    playMedia(mediaPlayer );
             Scene scene = PRIMARY_STAGE.getScene();
             scene.lookup("#"+VIS_CONTAINER_ID).setVisible(false);
            
            }       }
    });
    
   //resolve audio files
      
  
   final List <MediaPlayer> soundModel =  new SoundModel().getSoundModel(); 
 
     
    final ObservableList <MediaPlayer>    dataAudio = FXCollections.observableArrayList(soundModel);
    final   TableView<MediaPlayer > listAudio = new TableView(dataAudio);




    
     // Transférez dans la vue de liste et costumiser
    TableColumn<MediaPlayer, String> titreAudioColumn = new TableColumn<>("Titre");   
    TableColumn<MediaPlayer, String>  artisteAudioColumn = new TableColumn<>("Artiste");
    TableColumn<MediaPlayer, String>  albumAudioColumn = new TableColumn<>("Album");    
    TableColumn<MediaPlayer, String>  genreAudioColumn = new TableColumn<>("Genre");


       
       
       // add a metadataTable for meta data display
    listAudio.setStyle("-fx-font-size: 13px;"+"-fx-background-color:green;");
    listAudio.setId(LIST_AUDIO_ID);
    
   titreAudioColumn.setPrefWidth(150);
   artisteAudioColumn.setPrefWidth(150);
   genreAudioColumn.setPrefWidth(150);
   albumAudioColumn.setPrefWidth(150);
    
   
   titreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(TITRE_AUDIO_COLUMN_NAME));
   artisteAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ARTISTE_AUDIO_COLUMN_NAME));
   albumAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ALBUM_AUDIO_COLUMN_NAME));
   genreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(GENRE_AUDIO_COLUMN_NAME));
       
    
     
    titreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("title")));
    artisteAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("artist")));
    albumAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("album")));
    genreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("genre")));
    
   
       
       
    
    listAudio.setItems(dataAudio);
    listAudio.getColumns().addAll(titreAudioColumn,artisteAudioColumn,albumAudioColumn,genreAudioColumn);
    
    listAudio.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<MediaPlayer>(){
            public void changed(ObservableValue<? extends MediaPlayer> ov,MediaPlayer old_val , MediaPlayer new_val ){
             MediaPlayer   mediaPlayer = new_val;   
            }
            });
   
     
    
    listAudio.setOnMouseClicked(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2){
               MediaPlayer mediaPlayer ;
                mediaPlayer = listAudio.getSelectionModel().getSelectedItem();
                      playMedia(mediaPlayer );
             Scene scene = PRIMARY_STAGE.getScene();
             scene.lookup("#"+VIS_CONTAINER_ID).setVisible(true);
            }       
       
            }
    });
   
    // Pane as  containers 
 final  Accordion vitrine = new   Accordion();
 vitrine.setId(defaultLibrary);
    TitledPane  audio = new TitledPane();
    audio.setId(TAB_AUDIO_ID);
    audio.setText("Sonos");
    TitledPane  video = new TitledPane();
    video.setId(TAB_VIDEO_ID);
    video.setText("Video");
    
   
    video.setContent(listVideo);
        
    audio.setContent(listAudio);
    
    
    vitrine.getPanes().addAll(audio,video);
    
    vitrine.setStyle("-fx-background-color:green;"+"-fx-border-color:coral;"+"-fx-opacity:0.7;");
    
    VBox libraryPanel = new VBox();
    libraryPanel.setId(LIBRARY_PANEL_ID);
    
    libraryPanel.setPrefWidth(600);
    libraryPanel.setPrefHeight(200);
    
    libraryPanel.setStyle("-fx-background-color:green;"+"-fx-border-color:coral;"+"-fx-opacity:0.7;");
    
    
    libraryPanel.getChildren().add(vitrine);
    VBox.setVgrow(listVideo,Priority.ALWAYS);
    VBox.setVgrow(listAudio,Priority.ALWAYS);
      
   libraryPanel.setLayoutX(0);
   libraryPanel.setLayoutY(395);
   
   return libraryPanel;
  
}
 /* create Side Bar
*@return sideBar sliding in & out
*/
private Node createSideBarContent(){

        
    final VBox SideBarContent = new VBox() ;
    SideBarContent.setPrefSize(200, 600);
    SideBarContent.setPrefWidth(200);
    SideBarContent.setMinWidth(0);    
    
    final   Button dfltPlayLists = new Button("Source PlayLists");
    dfltPlayLists.setAlignment(Pos.TOP_CENTER);
    dfltPlayLists.setUnderline(true);
    dfltPlayLists.setFont(new Font(18));
    dfltPlayLists.setStyle("-fx-border-color:green ;"+ "-fx-background-color:transparent ;" );
    dfltPlayLists.setPrefWidth( 200  );
    
    final Button musicLists = new Button("Musik Lists");
    musicLists.setAlignment(Pos.BASELINE_CENTER);
    musicLists.setFont(new Font(14));
     musicLists.setStyle("-fx-background-color:bisque ;"+ " -fx-translate-y:10; " );
     musicLists.setPrefWidth( 200  );
    
    final Button videoLists = new Button("Video Lists");
    videoLists.setAlignment(Pos.BASELINE_CENTER);
    videoLists.setFont(new Font(14));
    videoLists.setStyle("-fx-background-color:bisque ;"+ "-fx-translate-y:20;" );
    videoLists.setPrefWidth( 200  );
     
    final Button bookLists = new Button("Livres Lists");
    bookLists.setAlignment(Pos.BASELINE_CENTER);
    bookLists.setFont(new Font(14));
    bookLists.setStyle("-fx-background-color:bisque ;"+"-fx-translate-y:30; " );
    bookLists.setPrefWidth( 200  );
    
    final VBox defPlayPane = new VBox();
    defPlayPane.setPrefSize(200, 175);
    defPlayPane.setMinWidth(0);
    defPlayPane.setStyle("-fx-border-color:grey;");
    defPlayPane.setPadding(new Insets(10,5 , 10,5))   ;
    defPlayPane.getChildren().addAll(dfltPlayLists,musicLists,videoLists,bookLists);
    defPlayPane.setLayoutX(600);    
    defPlayPane.setLayoutY(0);
    defPlayPane.setAlignment(Pos.TOP_CENTER);
    
    
    final Button custPlaylists = new Button("Owna Playlists");
    custPlaylists.setAlignment(Pos.TOP_CENTER);
    custPlaylists.setUnderline(true);
    custPlaylists.setFont(new Font(18));
    custPlaylists.setStyle("-fx-border-color:green ;"+ "-fx-background-color:transparent ;");
  
    
    custPlaylists.setPrefWidth( 200  );
    
    final Button musicUserLists = new Button("User Musik Lists");
    musicUserLists.setAlignment(Pos.BOTTOM_CENTER);
    musicUserLists.setFont(new Font(14));
    musicUserLists.setStyle("-fx-background-color:bisque; "+ " -fx-translate-y:10; " );
    musicUserLists.setPrefWidth( 200  );
   
    
     final Button videoUserLists = new Button("User Video Lists");
    videoUserLists.setAlignment(Pos.BOTTOM_CENTER);
    videoUserLists.setFont(new Font(14));
    videoUserLists.setStyle("-fx-background-color:bisque ;"+ "-fx-translate-y:20;" );
    videoUserLists.setPrefWidth( 200  );
     
     
    final Button bookUserLists = new Button("User Livres Lists");
    bookUserLists.setAlignment(Pos.BOTTOM_CENTER);
    bookUserLists.setFont(new Font(14));
    bookUserLists.setStyle("-fx-background-color:bisque;" +"-fx-translate-y:30; " );
    bookUserLists.setPrefWidth( 200  );
     
    final VBox custPlayPane = new VBox();
    custPlayPane.setPrefSize(200, 175);
    custPlayPane.setMinWidth(0);
    custPlayPane.setPadding(new Insets(10, 5, 10, 5));
    custPlayPane.setStyle("-fx-border-color:grey;");
    custPlayPane.getChildren().addAll(custPlaylists,musicUserLists,videoUserLists,bookUserLists);
    custPlayPane.setLayoutX(600);    
    custPlayPane.setLayoutY(176);
    custPlayPane.setAlignment(Pos.TOP_CENTER);
    
    
    final Label Network = new Label("NetWork Session");
    Network.setAlignment(Pos.TOP_CENTER);
    Network.setUnderline(true);;
    Network.setFont(new Font(18));
    Network.setStyle("-fx-border-color:green;" +"-fx-translate-y:10; ");
    Network.setPrefWidth(200);
    
    final VBox netPane = new VBox();
    netPane.setPrefSize(200, 248);
    netPane.setMinWidth(0);
    netPane.setPadding(new Insets(10,5 , 10,5))   ;
    netPane.setStyle("-fx-border-color:grey;");
    netPane.getChildren().add(Network);
    netPane.setLayoutX(600);    
    netPane.setLayoutY(300);
    netPane.setAlignment(Pos.TOP_CENTER);

   Scene scene = PRIMARY_STAGE.getScene();
   
    
    
    
    SideBarContent.setLayoutX(600);
    SideBarContent.setLayoutY(0);
    
    SideBarContent.getChildren().addAll(defPlayPane,custPlayPane,netPane);
    
        
    return SideBarContent;

}   
   


// animation to slide in & out SideBar
class SideBar extends VBox {
//@return a control button to hide and show the sidebar
    
    public Button getControlButton(){return controlButton ;}
    private final Button controlButton ;
    SideBar (final double expandedwidth,Node...nodes){
    
    getStyleClass().add("SideBar");
    this.setPrefWidth(expandedwidth);
    this.setMinWidth(0);
    
    setLayoutX(600);
    setLayoutY(0);
   

// create a bar to hide and show
    setAlignment(Pos.CENTER);
    getChildren().addAll(nodes);
    
    // create a button to hide and show the sidebar
    controlButton = new Button(">");
    controlButton.setId("control-sideBar");
    controlButton.getStyleClass().add("hide-left");
    
    
    controlButton.setLayoutX(565);
    controlButton.setLayoutY(0);
    
    //apply animation when button is pressed
    controlButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            //create an animation to hide sideBar
       final Animation hideSidebar = new Transition(){
            {setCycleDuration(Duration.millis(250));}
            protected void interpolate (double frac){
            final double curWidth = 200 * (1.0 - frac);
            setPrefWidth ( curWidth );
            setTranslateX(-200+curWidth);
            
            }
        } ;
        hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent actionEvent) {
        setVisible(false);
        
        
        controlButton.setText(">");
        controlButton.getStyleClass().remove("hide-left");
        controlButton.getStyleClass().add("show-right");
           }
       });
        //create an animation to show side bar 
        final Animation showSideBar = new Transition(){
            {setCycleDuration(Duration.millis(250)); }
            protected void interpolate (double frac){
            final double curWidth = 200 *frac ;
            setPrefWidth(curWidth);
            setTranslateX(-200+curWidth);
            }
        };
        showSideBar.onFinishedProperty().setValue(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               controlButton.setText("<");
               controlButton.getStyleClass().add("hide-left");
               controlButton.getStyleClass().remove("show-right");
                   }
       });
               if(showSideBar.statusProperty().get() == Animation.Status.STOPPED 
                && hideSidebar.statusProperty().get() == Animation.Status.STOPPED){ 
        if (isVisible()){
        hideSidebar.play();
        }else{
            setVisible(true);
            showSideBar.play();
        }
        }
        
        }
    });
    }

}




/*
create a media View for videos files 
*/


private MediaView createMediaView(){

Scene scene = PRIMARY_STAGE.getScene();

MediaView mediaView = new MediaView();
mediaView.setId(MEDIA_VIEW_ID);
mediaView.setSmooth(true);

mediaView.fitWidthProperty().bind(PRIMARY_STAGE.getScene().widthProperty().subtract(SideBar.USE_COMPUTED_SIZE));
mediaView.fitHeightProperty().bind(PRIMARY_STAGE.getScene().heightProperty());

mediaView.setLayoutX(0);
mediaView.setLayoutY(25);
//Pour les cas d'erreurs

mediaView.setOnError(mediaErrorEvent->{
mediaErrorEvent.getMediaError()
                .printStackTrace();
});
return mediaView;
}
 
    private void playMedia(MediaPlayer mediaPlayer){

Scene scene = PRIMARY_STAGE.getScene();

if (mediaPlayer != null){
    
    mediaPlayer.setOnPaused(null);
    mediaPlayer.setOnPlaying(null);
    mediaPlayer.setOnReady(null);
    mediaPlayer.currentTimeProperty()
               .removeListener(progressListener);
    mediaPlayer.setAudioSpectrumListener(null);
   
}


mediaPlayer.play();   

// as the media is playing move the slider in progress
Slider progressSlider = (Slider) scene.lookup("#"+SEEK_POS_SLIDER_ID);
           progressSlider.setValue(0);
           progressSlider.setMax(mediaPlayer.getMedia()
                                        .getDuration()
                                        .toSeconds());
    

progressSlider.valueProperty()
       .addListener((observable)-> {

        
            if(progressSlider.isValueChanging()){
                // must check if media is paused before seeking
                if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED){
                    
                    //convert seconds to millis
                    double dur = progressSlider.getValue() * 1000;
                    mediaPlayer.seek(Duration.millis(dur));
                    
                }
            }
    });//addListenr()

//update slider as media progress

progressListener = (observable , oldValue , newValue) ->
         progressSlider.setValue(newValue.toSeconds());



mediaPlayer.currentTimeProperty()
           .addListener(progressListener);


mediaPlayer.statusProperty().addListener(StatusListener);




// back to the beginning

mediaPlayer.setOnEndOfMedia(()->{

//change button to play and rewind
mediaPlayer.stop();
});// end of media settings




// set up vizualisation circle container


Group visContainer = (Group) PRIMARY_STAGE.getScene()
                                          .lookup("#" + VIS_CONTAINER_ID);
visContainer.prefWidth(PRIMARY_STAGE.getScene().getWidth()-(200));
mediaPlayer.setAudioSpectrumListener(
(double timestamp,
 double duration,
 float[] magnitudes,
 float[] phases )-> {
    visContainer.getChildren().clear();    
    int i =0;
    int x = 2;
    
double y = PRIMARY_STAGE.getScene().getHeight()-(400);
Random rand = new Random(System.currentTimeMillis());

//Build Randoom coloured circles
for (float phase : phases ){
int red = rand.nextInt(200);
int blue = rand.nextInt(200);
int green = rand.nextInt(200);


    Circle circle = new Circle(10);
circle.setCenterX(x+i);
circle.setCenterY(y+(phase*100));
circle.setFill(Color.rgb(red, green, blue, .70));
visContainer.getChildren().add(circle);
i+=5;

;

                    }
});// end set audio spectrum listener


// set the media player to display video

MediaView mediaView = (MediaView) scene.lookup("#"+ MEDIA_VIEW_ID);
mediaView.setMediaPlayer(mediaPlayer);

}//playMedia()

private void initMovablePlayer()    {

    Scene scene = PRIMARY_STAGE.getScene();
    
//Captage du point initial
    
   scene.setOnMousePressed(mouseEvent 
           -> anchorPoint = new Point2D(mouseEvent.getScreenX(),
                   mouseEvent.getScreenY())
   );

   //dragging the entire stage
   scene.setOnMouseDragged(mouseEvent->{
   if (anchorPoint != null && previousLocation != null){
    PRIMARY_STAGE.setX(previousLocation.getX() 
                       + mouseEvent.getScreenX()
                       - anchorPoint.getX());
    PRIMARY_STAGE.setY(previousLocation.getY() 
                        + mouseEvent.getY() 
                        - anchorPoint.getY());
                                                        } 
   
                                       });
   
  //set the current location
   scene.setOnMouseReleased( mouseEvent
            ->previousLocation = new Point2D(PRIMARY_STAGE.getX() ,PRIMARY_STAGE.getY())
                      );
   
   //Initialize previousLocation after stage shown
   PRIMARY_STAGE.addEventHandler(WindowEvent.WINDOW_SHOWN,
                    (WindowEvent t)->{
                    previousLocation = new Point2D(PRIMARY_STAGE.getX(),PRIMARY_STAGE.getY());
                    });
   
   

}



}
