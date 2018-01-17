
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author Jamie Leary January 16, 2018 7:59 PM
 */
public class FileStructure {

    public static void main(String[] args) {
        new FileStructure().getDirAndPrint();
    }

    private void getDirAndPrint() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(new JFrame());

        if (returnVal != JFileChooser.APPROVE_OPTION) {  // make sure it worked
            System.out.println("so sad...");
            return;  // give up, go home
        }

        printDirs(fc.getSelectedFile());
    }

    private void printDirs(File f) {
        // we need to keep track of the depth of the recursion for pretty
        // printing, but we don't want to expose this functionality as part of
        // the printDirs() function, and we don't want to force someone calling
        // this function to provide a 0 every time.
        printDirsHelper(f,"",true);
    }

    private void printDirsHelper(File f, String prefix0, boolean last) {
        File[] files = f.listFiles();

        char prefix1 = last ? '└' : '├';
        char prefix2 = files == null || files.length == 0 ? '─' : '┬';

        System.out.printf("%s%c%c╼ %s\n", prefix0, prefix1, prefix2, f);

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                char next = last? ' ' : '│';
                if (i == files.length - 1) {
                    printDirsHelper(files[i], prefix0 + next, true);
                } else {
                    printDirsHelper(files[i], prefix0 + next, false);
                }
            }
        }
    }
    
}
