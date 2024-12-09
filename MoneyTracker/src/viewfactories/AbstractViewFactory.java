package viewfactories;

import controller.Controller;
import view.AbstractView;

public interface AbstractViewFactory {
    AbstractView createView(Controller c);

}
