
/**
 * The GourmetGame is the main class of a game which objective is to guess what
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

    private GourmetGame() {
//      Initialize the decision tree
        this.root = new Tree<>("massa", "Lazanha", "Bolo de Chocolate");
    }

    /**
     * Starts asking to user about the plate it is thinking about
     *
     * @return the node which data is more likely to be correct
     */
    private Tree<String> guess() {
        /**
         * This method implements the search tree algorithm with a iterative
         * approach
         * https://en.wikipedia.org/wiki/Search_tree#Searching_Algorithms
         */
        Tree<String> tree = root;

//      If tree is a leaf, the search has been finished.
        while (!tree.isLeaf()) {
            if (ui.ask(tree)) {
                tree = tree.getLeft();
            } else {
                tree = tree.getRight();
            }
        }

        return tree;
    }

    /**
     * Add to game's knowledgment tree a new plate.
     *
     * @param leaf where new entry will be added. It must have no children.
     * @param plate the plate's name.
     * @param feature the feature's name to associate the plate with.
     * @throws IllegalArgumentException if leaf have children.
     */
    private static void learn(Tree<String> leaf, String plate, String feature) throws IllegalArgumentException {
        /**
         * Learning is a process which a plate, associated with feature, is
         * added to the decision tree. This add must be done on a leaf.
         */

        if (!leaf.isLeaf()) {
            throw new IllegalArgumentException("leaf must have no children");
        }

        leaf.setRight(leaf.getData());
        leaf.setLeft(plate);
        leaf.setData(feature);
    }

    public static void start() {
        GourmetGame game = new GourmetGame();

//      While user wants to play
        while (ui.askForANewRound()) {

            Tree<String> found = game.guess();

            if (ui.ask(found)) {
//               If the game could guess what plate user was thinking about
//              show a message.
                ui.showWinMessage();
                continue;
            }

//           If the game could not guess what plate user was thinking about,
//          add this new entry to the decision tree
            String plate = ui.askForANewPlate();
            String feature = ui.askForANewFeature(plate, found);
            learn(found, plate, feature);
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
