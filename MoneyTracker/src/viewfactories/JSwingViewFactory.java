package viewfactories;

import controller.Controller;
import view.AbstractView;
import view.JSwingView;

public class JSwingViewFactory implements AbstractViewFactory{

    @Override
    public AbstractView createView(Controller c) {
        return new JSwingView(c);
    }
}
