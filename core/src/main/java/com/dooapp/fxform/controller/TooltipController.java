package com.dooapp.fxform.controller;

import com.dooapp.fxform.FXForm;
import com.dooapp.fxform.model.Element;
import com.dooapp.fxform.view.factory.FXFormNode;
import javafx.beans.binding.ObjectBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created at 27/09/12 17:07.<br>
 *
 * @author Antoine Mischler <antoine@dooapp.com>
 */
public class TooltipController extends NodeController {
    /**
     * The logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TooltipController.class);

    public static String TOOLTIP_SUFFIX = "-tooltip";

    public TooltipController(FXForm fxForm, Element element) {
        super(fxForm, element);
    }

    @Override
    protected void bind(FXFormNode fxFormNode) {
        fxFormNode.getProperty().bind(new ObjectBinding() {
            {
                bind(getFxForm().resourceBundleProperty());
            }

            @Override
            protected Object computeValue() {
                String label;
                try {
                    label = getFxForm().getResourceBundle().getString(getElement().getName() + TOOLTIP_SUFFIX);
                } catch (Exception e) {
                    // label is undefined
                    label = (getElement().getName());
                }
                return getFxForm().getAdapter(getNode(), getElement()).adaptTo(label);
            }
        });
    }
}