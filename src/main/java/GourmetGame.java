
/**
 * The GourmetGame is the main class of a game wich objective is to guess what
 * plate of food the user is thinking about. If it coud not guess, it asks to
 * the user what is the plate's name, a feature and store it, learning about new plates.
 *
 *
 * @author Lucas
 * @see https://en.wikipedia.org/wiki/Decision_tree
 * @see http://pt.akinator.com/
 */
public class GourmetGame {

    private static final Ui ui = new Ui();

//  the root of decision tree
    private final Tree<String> root;
    private Tree<String> lastAccessedNode;

    private GourmetGame() {
//      Initialize the decision tree
        this.root = new Tree<>("massa", "bolo de chocolate", "lazanha");
    }

    private Tree<String> guess() {
        /**
         * This method implements the search tree algorithm with a iterative
         * aproach
         * https://en.wikipedia.org/wiki/Search_tree#Searching_Algorithms
         */
        Tree<String> tree = root;

//      If tree is a leaf, the search has been finished.
        while (!tree.isLeaf()) {
            lastAccessedNode = tree;
            if (ui.ask(tree)) {
                tree = tree.getRight();
            } else {
                tree = tree.getLeft();
            }
        }

        return tree;
    }

    /**
     * Add to game's decicion tree a new plate.
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

        Tree<String> entry = new Tree<>(feature);

        entry.setRight(lastAccessedNode.getLeft());
        entry.setLeft(plate);

//      Add new entry to tree
        lastAccessedNode.setLeft(entry);
    }

    public static void start() {
        GourmetGame game = new GourmetGame();

//      While user wants to play
        while (ui.askForANewRound()) {

//          Both, recursive and iterative search, produces the same result
            Tree<String> found = game.guess();

            if (ui.ask(found)) {
//               If the game coud guess what plate user was thinking about
//              show a message.
                ui.showWinMessage();
                continue;
            }

//           If the game could not guess what plate user was thinking about,
//          add this new entry to the decision tree
            String plate = ui.askForANewPlate();
            String feature = ui.askForANewFeature(plate, found);
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
