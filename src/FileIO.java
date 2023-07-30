import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class FileIO<T> {
    public void writeFile(String pathFile, T data) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File(pathFile).toPath()));
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public T readFile(String pathFile){
        T data = null;
        try {
            ObjectInputStream ois = new ObjectInputStream((Files.newInputStream(new File(pathFile).toPath())));
            data = (T) ois.readObject();
        }catch (IOException | ClassCastException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }
}
