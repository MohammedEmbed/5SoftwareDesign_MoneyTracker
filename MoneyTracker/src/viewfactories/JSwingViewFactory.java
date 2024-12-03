package viewfactories;

import view.AbstractView;
import view.JSwingView;

public class JSwingViewFactory implements AbstractViewFactory{

    @Override
    public AbstractView createView() {
        return new JSwingView();
    }
}
