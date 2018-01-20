
import javax.swing.JFrame;
import static javax.swing.JOptionPane.*;

/**
 * This class offers a set of interactions with the user of GourmetGame. It uses
 * JOptionPane as a main source of resources for this interactions.
 *
 * @author Lucas
 * @see https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
 * @see GourmetGame
 */
public class Ui {

    private final JFrame frame;

    public Ui() {
        this.frame = new JFrame();
    }

    /**
     * Show to user a dialog asking to start a new round
     *
     * @return true if user answer ok, false if user answer 'cancel' or close
     * the window
     */
    boolean askForANewRound() {
        int answer = showConfirmDialog(frame, "Pense em um prato que gosta", "Jogo Gourmet", OK_CANCEL_OPTION, INFORMATION_MESSAGE);
        return answer == OK_OPTION;
    }

    /**
     * Show to user a dialog asking if the parameter text is a feature about the
     * plate chosen
     *
     * @param text the content of the dialog
     * @return true if user answer 'yes', false if user answer 'no' or close the
     * window
     */
    boolean askIfPlateIs(String text) {
        String message = String.format("O prato que você pensou é %s ?", text);
        int answer = showConfirmDialog(frame, message, "Jogo Gourmet", YES_NO_OPTION, QUESTION_MESSAGE);
        return answer == YES_OPTION;
    }

    /**
     * Show to user a dialog asking if the parameter text is a feature about the
     * plate chosen
     *
     * @param tree the node wich data is the content of the dialog
     * @return true if user answer 'yes', false if user answer 'no' or close the
     * window
     */
    boolean ask(Tree<String> tree) {
        return askIfPlateIs(tree.getData());
    }

    /**
     * Show to user a dialog asking if the parameter text is a feature about the
     * plate chosen
     */
    void showWinMessage() {
        showMessageDialog(frame, "Acertei de novo! :D");
    }

    /**
     * Show to user a dialog asking for prompt a new plate
     */
    String askForANewPlate() {
        return showInputDialog("Qual é o nome do prato ?");
    }

    /**
     * Show to user a dialog asking for prompt a new feature
     *
     * @param plate1 the name of plate that have the feature
     * @param plate2 the node which contains the name of plate that have not the
     * feature
     */
    String askForANewFeature(String plate1, Tree<String> plate2) {
        return showInputDialog(frame, String.format("%s é ______ mas %s não ?", plate1, plate2.getData()));
    }
}
