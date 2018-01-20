
/**
 * The GourmetGame is the main class of a game wich objective is to guess what
 * plate of food the user is thinking about. If it coud not guess, it asks to
 * the user what is the plate's name and store it, learning about new plates.
 *
 *
 * @author Lucas
 * @see https://en.wikipedia.org/wiki/Decision_tree
 * @see http://pt.akinator.com/
 */
public class GourmetGame {

    private static final Ui ui = new Ui();

    // the root of deciocion tree
    private final Tree<String> root;

    // the last nodes accessed during the searches
    private Tree<String> lastAccessedPlate;
    private Tree<String> lastAccessedFeature;

    private GourmetGame() {
//      Initialize the decision tree
        this.root = new Tree<>("bolo de chocolate", "massa", "lazanha");
    }

    private void iterativeSearch() {
        /**
         * This method implements the search tree algorithm with a iterative
         * aproach
         * https://en.wikipedia.org/wiki/Search_tree#Searching_Algorithms
         */

        lastAccessedFeature = null;
        lastAccessedPlate = root;
        Tree<String> tree = root;

//      If tree is a leaf, the search has been finished.
        while (!tree.isLeaf()) {
//          Ask to user if feature is correct
            if (ui.ask(tree.getLeft())) {
//               If user confirms that the feature is correct then
//              save this information at lastAccessedPlate and keep
//              searching through the tree.
                lastAccessedPlate = tree.getRight();
                tree = lastAccessedPlate;
            } else {
//               If user does not confirm that the feature is correct then
//              save this information at lastAccessedFeature and keep
//              searching through the tree.
                lastAccessedFeature = tree.getLeft();
                tree = lastAccessedFeature;
            }
        }
    }

    private void recursiveSearch() {
        lastAccessedFeature = null;
        lastAccessedPlate = root;
        recursiveSearch(root);
    }

    private void recursiveSearch(Tree<String> tree) {
        /**
         * This method implements the search tree algorithm with a recursive
         * aproach
         * https://en.wikipedia.org/wiki/Search_tree#Searching_Algorithms
         */

//      If tree is a leaf, the search has been finished.
        if (tree.isLeaf()) {
            return;
        }

//      Ask to user if feature is correct
        if (ui.ask(tree.getLeft())) {
//           If user confirms that the feature is correct then
//          save this information at lastAccessedPlate and keep
//          searching through the tree.
            lastAccessedPlate = tree.getRight();
            recursiveSearch(lastAccessedPlate);
        } else {
//              If user does not confirm that the feature is correct then
//             save this information at lastAccessedFeature and keep
//             searchig through the tree.
            lastAccessedFeature = tree.getLeft();
            recursiveSearch(lastAccessedFeature);
        }
    }

    /**
     * Add to game's knoledgement a new plate.
     *
     * @param plate the plate's name.
     * @param feature the feature's name to associate the plate with.
     */
    private void learn(String plate, String feature) {
        /**
         * Learning is a process which a plate, associated with feature, is
         * added to the decision tree. This add is done on the last accessed
         * feature.
         */

//      Plates must be stored at right node
        lastAccessedFeature.setRight(plate);
//      Features must be stored at left node
        lastAccessedFeature.setLeft(feature);
    }

    public static void start() {
        GourmetGame game = new GourmetGame();

//      While user wants to play
        while (ui.askForANewRound()) {

//          Both, recursive and iterative produces the same result
//            game.recursiveSearch();
            game.iterativeSearch();

            if (ui.ask(game.lastAccessedPlate)) {
//              If the game guess what plate user was thinking about
                ui.showWinMessage();
                continue;
            }

//           If the game could not guess what plate user was thinking about
//          was thinking about, add this entry to the decision tree
            String plate = ui.askForANewPlate();
            String feature = ui.askForANewFeature(plate, game.lastAccessedPlate);
            game.learn(plate, feature);
        }
    }

    public static void main(String args[]) {
//      Create a new thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//              Start the game.
                start();
                System.exit(0);
            }
        });
    }
}
