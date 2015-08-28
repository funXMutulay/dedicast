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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author macbookpro
 */
public class BookModel {
      final  List<  MediaPlayer >    bookModel = new ArrayList<MediaPlayer>();

 public  List<  MediaPlayer >  BookModel(){
			
return bookModel ;

}

public   void main(String[] args) throws IOException{
			
File dir = new File("/Users/macbookpro/Documents/bagass");
		String[] extensions = new String[] { "pdf", "txt" };
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			String biir = file.toPath().toString();
bookModel.add(createPlayer("file:///" + ( biir).replace("\\", "/").replaceAll(" ", "%20")));
                    
		}


 
 

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
    
}
