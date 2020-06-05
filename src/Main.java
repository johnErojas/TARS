/**
 *
 * @author johnrojas
 */
public class Main {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
            new presenter.AppPresenter().start();
        });
    }
}
