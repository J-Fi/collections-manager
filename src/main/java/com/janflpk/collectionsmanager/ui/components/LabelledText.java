package com.janflpk.collectionsmanager.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;

@Tag("div")
public class LabelledText extends Component implements HasSize {

    //Element bold = new Element("b");
    Element descriptor = new Element("h5");
    Element text = new Element("p");

    public LabelledText() {
        getElement().appendChild(descriptor, text);
        //descriptor.getStyle().set("font-size", "20px");
        //descriptor.getStyle().set("font-weight", "bold");
        getElement().getStyle().set("line-height", "normal");
        getElement().getStyle().set("margin", "0px 0px 0px 0px");
        getElement().getStyle().set("padding", "0px");
        descriptor.getStyle().set("margin", "5px 0px 0px 0px");
        descriptor.getStyle().set("padding", "0px");
        text.getStyle().set("margin", "0px 0px 0px 5px");
        text.getStyle().set("padding", "0px");
    }

    public String getDescriptorText() {
        return descriptor.getText();
    }

    public void setDescriptorText(String descriptorText) {
        descriptor.setText(descriptorText);
    }

    public String getTextContent() {
        return text.getText();
    }

    public void setTextContent(String textContent) {
        text.setText(textContent);
    }
}