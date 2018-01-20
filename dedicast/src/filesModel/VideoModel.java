/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author macbookpro
 */
public class VideoModel {
    
   private  List<  MediaPlayer >    videoModel = new ArrayList<>();
    MediaPlayer mediaPlayer ;
    public StringProperty titre = new SimpleStringProperty();
    public StringProperty auteur = new SimpleStringProperty();

   

public   void main(String[] args) throws IOException{
			
File dir = new File("/Users/macbookpro/Documents/Vidéos");
		String[] extensions = new String[] { "mp4", "flv" };
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
                files.stream().map((file) -> file.toPath().toString()).map((String biir) -> {
                mediaPlayer = createPlayer("file:///" + ( biir).replace("\\", "/").replaceAll(" ", "%20"));
                      return biir;
       }).forEachOrdered((_item) -> {
           videoModel.add(mediaPlayer);
       });


 
 

}

                       /** @return a MediaPlayer for the given source which will report any errors it encounters */
  public  MediaPlayer createPlayer(String mediaSource) {
    final Media media = new Media(mediaSource);
    final MediaPlayer player = new MediaPlayer(media);
    player.setOnError(new Runnable() {
      @Override public void run() {
        System.out.println("Media error occurred: " + player.getError());
      }
    });

    return player ;
    
  } 
    
 public  List<MediaPlayer>getVideoModel(){
			
return videoModel ;

}
 public void   setVideoModel (List<MediaPlayer>videoModel){
			
this.videoModel =  videoModel ;

}
  public  StringProperty getAuteur() {
    return  auteur  =  (StringProperty) mediaPlayer.getMedia().getMetadata().get("artist");
    
  }

    public void setAuteur(String value) {
        auteur.set(value);
    }

    public StringProperty auteurProperty() {
        return auteur;
    }
    

    public StringProperty getTitre() {
      return  titre = (StringProperty) mediaPlayer.getMedia().getMetadata().get("title");
        
    }

    public void setTitre(String value) {
        titre.set(value);
    }

    public StringProperty titreProperty() {
        return titre;
    }
 
}
