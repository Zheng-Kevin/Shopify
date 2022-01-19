package com.example;

import com.helger.commons.csv.CSVWriter;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.io.IOUtils;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@Route("")
public class ProductView extends VerticalLayout {

    private ProductService productService;

    public ProductView(ProductService service) {
        this.productService = service;
        GridCrud<Product> crud = new GridCrud<>(Product.class, service);
        Anchor link = new Anchor(new StreamResource("product.csv", this::getInputStream), "Export as CSV");
        link.getElement().setAttribute("download", true);
        //link.add(new Button());
        add(link);
        add(crud);
        setSizeFull();

    }


    private InputStream getInputStream() {
        try {
            StringWriter stringWriter = new StringWriter();

            CSVWriter csvWriter = new CSVWriter(stringWriter);
            csvWriter.writeNext("id","Count");
            productService.findAll().forEach(c -> csvWriter.writeNext("" + c.getProductName(),c.getProductID())); //add product ciunmt

            return IOUtils.toInputStream(stringWriter.toString(), "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
